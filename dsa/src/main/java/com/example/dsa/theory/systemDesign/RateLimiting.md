# Rate Limiting

It's a process that helps us control incoming requests.

Let's say I will only allow a certain number of requests from an individual user or multiple users based on the capacity of my server and a defined time frame to avoid Denial of Service (DoS) attacks.

Bots can send a large number of requests to increase the load on the server. So, we only process the requests that are within the allowed time frame, and the requests exceeding the limit are delayed or rejected.

---

## IP-Based Rate Limiting

A user with the same IP can send multiple requests to increase the server load or make the server unavailable.

So, from a particular IP address, we allow only a limited number of requests (for example, **5 requests per minute**) to avoid DoS attacks.

**Example:** An online retailer allows only **10 requests per minute per IP address** to prevent bots from scraping product data while allowing normal users to browse smoothly.

---

## Server-Based Rate Limiting

A technique used to handle only the number of requests that a server can process within a given time period.

---

## Geography-Based Rate Limiting

This technique limits requests based on the geographic location of the user's IP address.

It is useful for controlling traffic from specific regions and improving security or compliance.

---

## Working

The number of requests a user or system can make to a service in a predetermined period of time is managed by rate limiting.

For example, a service might allow **100 requests per minute**. Once that limit is reached, additional requests are blocked or delayed until the limit allows new requests again.

---

# Algorithms

## Token Bucket Algorithm

In this algorithm, each request is assigned a token from a bucket that contains a predetermined number of tokens.

Only the requests that receive a token are processed. Requests that do not receive a token are delayed or rejected.

---

## Leaky Bucket Algorithm

In this algorithm, we maintain a queue (bucket) with a fixed size.

Requests are added to the queue, and they are processed in **FIFO (First In, First Out)** order. Once a request is processed, it is removed from the queue, allowing the next request to be processed.

---

## Fixed Window Algorithm

In this algorithm, the number of requests is counted within a **fixed time window**.

For example, if the limit is **5 requests per minute**, the counter starts at the beginning of the minute and resets only when the next minute begins. If the user exceeds the limit before the window resets, the remaining requests are rejected or delayed until the next window.

---

## Sliding Window Algorithm

In this algorithm, the limit is calculated over a **moving time window** instead of fixed intervals.

For example, if the limit is **10 requests per minute**, every new request checks how many requests were made in the **last 60 seconds**. As older requests move out of the window, new requests are allowed, making the rate limiting smoother than the Fixed Window approach.