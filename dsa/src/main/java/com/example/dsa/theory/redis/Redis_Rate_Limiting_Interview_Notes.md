# Redis & Rate Limiting --- Interview Revision Notes

## 1. What is Redis?

**Redis** is a very fast in-memory data store.

-   Data is primarily kept in **RAM**, which gives very low-latency
    access.
-   Redis supports data structures such as:
    -   String
    -   Hash
    -   List
    -   Set
    -   Sorted Set
    -   Streams
-   Redis can also use persistence mechanisms such as RDB and AOF.
-   For our rate limiter, persistence is not critical. If rate-limit
    state disappears after a Redis restart, clients simply get a fresh
    bucket.

### Simple mental model

``` text
Key                         Value
------------------------------------------------
rl:127.0.0.1:loans          tokens = 17
                            last_refill_ms = ...
```

Redis is often used for: - Caching - Rate limiting - Session storage -
Distributed locks - Counters - Temporary state

------------------------------------------------------------------------

# 2. Why Redis for Rate Limiting?

A rate limiter is checked on almost every request.

We need:

``` text
Request
   ↓
Check current limit state
   ↓
Allow / Reject
   ↓
Update state
```

Redis is a good fit because it provides very fast reads/writes and can
act as **shared state** when multiple API Gateway instances are running.

### Why not PostgreSQL?

A database-based implementation could repeatedly do:

``` sql
SELECT request_count ...
UPDATE request_count ...
```

For a high number of requests, this creates unnecessary database traffic
and contention.

Redis is designed for this kind of fast temporary state.

------------------------------------------------------------------------

# 3. What is Rate Limiting?

Rate limiting controls how many requests a client can make within a
period or at a particular rate.

Example:

``` text
Client → API Gateway

Allowed: 100 requests/minute

101st request → HTTP 429 Too Many Requests
```

The goal is to: - Protect services from excessive traffic - Prevent
abuse - Protect expensive APIs - Control traffic spikes - Improve system
stability

------------------------------------------------------------------------

# 4. Token Bucket Algorithm

Our rate limiter uses the **Token Bucket algorithm**.

Imagine a bucket that can hold tokens.

``` text
Capacity = 20

[●][●][●][●][●][●][●][●][●][●]
[●][●][●][●][●][●][●][●][●][●]
```

Each request consumes one token.

``` text
Request → consume 1 token
```

Tokens are gradually refilled.

Example:

``` text
capacity    = 20 tokens
refill rate = 5 tokens/second
```

If the bucket has 20 tokens:

``` text
20 requests can be accepted immediately.
```

After that, tokens are replenished at:

``` text
5 tokens / second
```

The bucket can never contain more than its capacity.

``` text
tokens = min(capacity, tokens + refill)
```

### Why Token Bucket?

It allows controlled **bursts**.

A client can use the tokens that have accumulated in the bucket, while
the refill rate controls its sustained request rate.

------------------------------------------------------------------------

# 5. Token Bucket Example

Suppose:

``` text
capacity = 3
refill-rate = 0
```

Then:

``` text
Request 1 → token 3 → 2
Request 2 → token 2 → 1
Request 3 → token 1 → 0
Request 4 → no token → 429
```

This is useful for testing.

For our normal configuration:

``` yaml
rate-limit:
  capacity: 20
  refill-rate: 5
```

The bucket starts with up to 20 tokens and refills at 5 tokens/second.

------------------------------------------------------------------------

# 6. Why HTTP 429?

When the rate limit is exceeded, the standard response is:

``` http
429 Too Many Requests
```

Our implementation returns:

``` json
{
  "error": "rate_limit_exceeded",
  "message": "Too many requests, please slow down."
}
```

------------------------------------------------------------------------

# 7. Redis Keys in Our Design

We separate rate-limit state by:

``` text
Client + API Group
```

Example:

``` text
rl:127.0.0.1:loans
```

The API groups in our project are:

``` text
auth
loans
documents
rcu
other
```

This means one API group can have its own bucket.

For example:

``` text
rl:127.0.0.1:auth
rl:127.0.0.1:loans
rl:127.0.0.1:documents
rl:127.0.0.1:rcu
```

So heavy traffic to one group does not automatically consume the bucket
for another group.

------------------------------------------------------------------------

# 8. Redis Hash Used by Our Rate Limiter

Our Lua script uses a Redis **Hash**.

Conceptually:

``` text
KEY: rl:127.0.0.1:loans

tokens         → 17
last_refill_ms → 1754740000000
```

The Lua script reads these fields with:

``` lua
HMGET key "tokens" "last_refill_ms"
```

and updates them with:

``` lua
HMSET key "tokens" ... "last_refill_ms" ...
```

------------------------------------------------------------------------

# 9. Why Do We Need Lua?

This is one of the most important interview topics.

A rate-limit operation is not just one operation.

It is:

``` text
1. Read current tokens
2. Calculate elapsed time
3. Calculate refill
4. Check whether a token is available
5. Consume the token
6. Save the new state
```

These operations need to behave as **one atomic operation**.

Otherwise, concurrent requests can create a race condition.

------------------------------------------------------------------------

# 10. Race Condition Example

Suppose:

``` text
Limit = 20
Current tokens = 1
```

Two requests arrive at almost the same time.

Without an atomic operation:

``` text
Request A → reads 1 token
Request B → reads 1 token

A → decides it can proceed
B → decides it can proceed

A → saves 0
B → saves 0
```

Both requests were allowed even though only one token existed.

The final value is:

``` text
0
```

but **two requests consumed one token**.

This is a race condition.

------------------------------------------------------------------------

# 11. How Lua Prevents the Race Condition

We send the complete token-bucket operation to Redis as a Lua script.

The script performs:

``` text
HMGET
   ↓
Calculate refill
   ↓
Check tokens
   ↓
Consume token
   ↓
HMSET
   ↓
EXPIRE
   ↓
Return allowed/rejected
```

Redis executes the Lua script **atomically**.

Conceptually:

``` text
Request A
   |
   |---- entire Lua script ----|
                                |
Request B                       waits
```

Request B cannot jump into the middle of Request A's Lua script.

Therefore Request B sees the state **after** Request A has completed.

If only one token existed:

``` text
Request A → allowed → tokens = 0
Request B → sees 0 → rejected
```

### Interview answer

> We use a Redis Lua script because the token-bucket operation involves
> multiple reads, calculations, and writes. Executing the complete
> operation atomically prevents concurrent requests from reading stale
> token counts and bypassing the rate limit.

------------------------------------------------------------------------

# 12. Why `EXPIRE`?

Our script contains:

``` lua
redis.call("EXPIRE", key, 60)
```

This means the rate-limit key expires after 60 seconds.

The purpose is to remove inactive clients' rate-limit state.

Example:

``` text
Client stops sending requests
        ↓
60 seconds pass
        ↓
Redis key expires
        ↓
Memory is freed
```

When the client returns later, a new bucket is created.

Important:

> We are deleting the Redis **key/state**, not a permanent bucket
> object.

------------------------------------------------------------------------

# 13. Current Client Identification

Our current rate limiter uses the client IP.

``` java
X-Forwarded-For
        ↓
first IP

otherwise

request.getRemoteAddr()
```

Therefore the key is:

``` text
rl:<client-ip>:<api-group>
```

Example:

``` text
rl:192.168.1.10:loans
```

------------------------------------------------------------------------

# 14. Why IP-Based Rate Limiting?

Our current architecture has:

``` text
Client
   ↓
API Gateway
   ↓
Auth-User Service
```

The **Auth-User Service owns authentication**.

The Gateway does not currently perform JWT authentication and therefore
does not have a trusted authenticated user identity available when the
rate limiter executes.

So our Gateway uses:

``` text
Client IP
```

for rate limiting.

This is a valid design for the current architecture.

------------------------------------------------------------------------

# 15. User-Based Rate Limiting

If JWT validation were moved to the API Gateway, the architecture could
become:

``` text
Client
   ↓
API Gateway
   ↓
JWT Authentication
   ↓
Extract User ID
   ↓
Rate Limiter
   ↓
Loan / Document / RCU Service
```

Then Redis keys could be:

``` text
rl:user:123:loans
```

instead of:

``` text
rl:192.168.1.10:loans
```

This gives each authenticated user an independent bucket.

### Important

IP-based rate limiting and user-based rate limiting are not mutually
exclusive.

A production system can use both:

``` text
IP limit
   +
User limit
```

for different protection layers.

------------------------------------------------------------------------

# 16. API Gateway as the Rate-Limiting Point

The API Gateway is a good place for centralized rate limiting because
requests enter through it before reaching the microservices.

``` text
                    Client
                      |
                      ↓
                API Gateway
                      |
                Rate Limiter
                      |
        -----------------------------
        |            |              |
      Auth         Loan         Document
     Service      Service        Service
```

Benefits: - One centralized implementation - Protects downstream
services - No duplicated rate-limiter logic in every service - Easy to
apply different policies to different API groups

------------------------------------------------------------------------

# 17. Distributed Gateway and Redis

Suppose we have:

``` text
Gateway 1
Gateway 2
Gateway 3
```

All three use the same Redis.

``` text
Gateway 1 ─┐
Gateway 2 ─┼──→ Redis
Gateway 3 ─┘
```

The rate-limit state is shared.

Without shared Redis, each Gateway instance could maintain its own
counter and the client could effectively bypass the intended global
limit by hitting different Gateway instances.

Redis provides the shared state.

------------------------------------------------------------------------

# 18. Different Limits Per API Group

Our current implementation already identifies:

``` text
auth
loans
documents
rcu
```

Currently all groups use the same:

``` yaml
capacity: 20
refill-rate: 5
```

We can extend it to:

``` yaml
rate-limit:
  auth:
    capacity: 5
    refill-rate: 1

  loans:
    capacity: 20
    refill-rate: 5

  documents:
    capacity: 10
    refill-rate: 2

  rcu:
    capacity: 20
    refill-rate: 5
```

This is possible without changing the overall algorithm.

Only the configuration lookup needs to become API-group specific.

------------------------------------------------------------------------

# 19. Possible Production Improvements

After the basic implementation, useful improvements include:

### Different limits

``` text
Auth       → strict
Loans      → moderate
Documents  → lower because operations may be expensive
RCU        → application-specific
```

### Rate-limit response headers

For example:

``` http
X-RateLimit-Limit
X-RateLimit-Remaining
Retry-After
```

### Monitoring

Track:

``` text
Allowed requests
Rejected requests
Requests by API group
Requests by client
```

These metrics can later be exposed using monitoring tools such as
Micrometer/Prometheus/Grafana.

### Whitelisting

Trusted internal traffic may be excluded from external-client rate
limits.

### Multiple layers

A mature design can combine:

``` text
IP-based limit
+
User-based limit
+
API-specific limit
```

------------------------------------------------------------------------

# 20. Rate Limiter vs Circuit Breaker

They solve different problems.

  -----------------------------------------------------------------------
  Rate Limiter                        Circuit Breaker
  ----------------------------------- -----------------------------------
  Controls incoming request volume    Handles failing downstream services

  Protects against excessive traffic  Protects against repeated failures

  Usually returns 429                 Often returns 503/fallback

  Uses tokens/counters/windows        Uses failure/timeout thresholds

  Can run at the Gateway              Commonly sits around
                                      service-to-service calls
  -----------------------------------------------------------------------

Our current Redis implementation is a **Rate Limiter**, not a Circuit
Breaker.

------------------------------------------------------------------------

# 21. Interview Questions to Prepare

### Redis

**Q: What is Redis?**

> Redis is a fast in-memory data store that supports multiple data
> structures and is commonly used for caching, counters, sessions,
> distributed locks, and rate limiting.

**Q: Why Redis instead of PostgreSQL for rate limiting?**

> Rate limiting requires very frequent low-latency state checks. Redis
> provides fast in-memory operations and shared state without putting
> this temporary traffic-control workload on the relational database.

------------------------------------------------------------------------

### Algorithm

**Q: Which rate-limiting algorithm did you use?**

> Token Bucket.

**Q: Explain Token Bucket.**

> The bucket has a maximum capacity of tokens. Each request consumes a
> token and tokens are replenished at a configured rate. This allows
> controlled bursts while limiting the sustained request rate.

**Q: Why not Fixed Window?**

> Fixed windows can have boundary problems where a client can make a
> large number of requests around the transition between two windows.
> Token Bucket provides smoother control and supports bursts.

------------------------------------------------------------------------

### Lua

**Q: Why did you use Lua?**

> The rate-limit decision requires multiple operations: read state,
> calculate refill, check tokens, consume a token, and update state. Lua
> allows these operations to execute atomically inside Redis, preventing
> race conditions.

**Q: What race condition are you preventing?**

> Two concurrent requests could both read the same remaining token count
> and both conclude that they are allowed. Atomic Lua execution ensures
> one request updates the state before the next request evaluates it.

------------------------------------------------------------------------

### Architecture

**Q: Why rate limit at the Gateway?**

> The Gateway is the common entry point for external requests, so it
> provides centralized protection for all downstream microservices and
> avoids duplicating rate-limiting logic.

**Q: Why IP-based instead of user-based?**

> Authentication is currently handled by a separate Auth-User Service,
> while the Gateway does not validate the JWT. Therefore the Gateway
> does not have a trusted authenticated user identity and currently uses
> the client IP.

**Q: How would you implement user-based limiting?**

> Move or add JWT validation at the Gateway, extract the authenticated
> user ID, and use a Redis key such as `rl:user:<userId>:<apiGroup>`.

**Q: What happens if there are multiple Gateway instances?**

> They can all use the same Redis instance/cluster, so the rate-limit
> state is shared across Gateway instances.

------------------------------------------------------------------------

# 22. Our Current Implementation --- One-Minute Revision

``` text
Client
  ↓
API Gateway
  ↓
RateLimitFilter
  ↓
Identify IP
  ↓
Identify API group
  ↓
Create Redis key
  ↓
RedisRateLimiter
  ↓
Execute Lua Token Bucket
  ↓
Enough tokens?
  ├── YES → consume token → forward request
  └── NO  → HTTP 429
```

Redis stores approximately:

``` text
rl:<client-ip>:<api-group>
        ↓
tokens
last_refill_ms
```

Configuration:

``` yaml
capacity: 20
refill-rate: 5
```

Cleanup:

``` text
60-second expiration
```

Concurrency protection:

``` text
Redis Lua script
        ↓
Atomic execution
        ↓
No token-count race condition
```

------------------------------------------------------------------------

# 23. The 30-Second Interview Explanation

If an interviewer says:

> "Explain the rate limiter you implemented."

You can say:

> "I implemented a distributed rate limiter at the API Gateway using
> Redis and the Token Bucket algorithm. Each client IP gets a separate
> bucket for each API group, such as loans, documents, and auth. The
> bucket has a configurable capacity and refill rate. For every request,
> the Gateway executes a Redis Lua script that atomically reads the
> current token state, calculates replenishment, checks whether a token
> is available, consumes it, and updates the state. This atomic
> execution prevents race conditions when concurrent requests arrive. If
> no token is available, the Gateway returns HTTP 429. We also expire
> inactive Redis keys after 60 seconds to avoid keeping unnecessary
> rate-limit state. Currently we use IP-based limiting because JWT
> authentication is handled by our separate Auth-User Service rather
> than the Gateway."

That is enough for a strong project-level Redis/rate-limiting
discussion.
