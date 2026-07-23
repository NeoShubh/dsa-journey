# CAP Theorem

The **CAP Theorem** states that a distributed system can guarantee only **two out of three** properties at the same time: **Consistency (C), Availability (A), and Partition Tolerance (P).**

---

## Properties

### 1. Consistency (C)

Every client sees the **same and latest data** from every node.

---

### 2. Availability (A)

Every request receives a **response**, even if some nodes are down.

---

### 3. Partition Tolerance (P)

The system **continues to operate despite network failures** between nodes.

---

# CAP Trade-Offs

### 1. CA System (Consistency + Availability)

Provides **Consistency and Availability**, but cannot tolerate network partitions.

**Example:** Traditional Relational Databases (Single Data Center)

---

### 2. CP System (Consistency + Partition Tolerance)

Provides **Consistency and Partition Tolerance**, but may reject or delay requests during a partition to maintain data consistency.

**Examples:** MongoDB (default replica set behavior), HBase

---

### 3. AP System (Availability + Partition Tolerance)

Provides **Availability and Partition Tolerance**, but data may be **eventually consistent**.

**Examples:** Apache Cassandra, DynamoDB

---

# CAP Theorem Example

Suppose **Server S1** and **Server S2** become disconnected due to a network partition.

- If the system chooses **Consistency**, it must reject some requests, sacrificing **Availability**.
- If the system chooses **Availability**, it serves requests with possibly stale data, sacrificing **Consistency**.

This demonstrates the CAP Theorem.

---

# Use Cases

### Banking Transactions (CP)

Consistency is more important than availability to prevent incorrect balances or double spending.

---

### Social Media Newsfeed (AP)

Availability is preferred, while slightly stale data is acceptable.

---

### Online Shopping Cart (Hybrid)

- **AP** while browsing and adding items to the cart.
- **CP** during checkout and payment processing.