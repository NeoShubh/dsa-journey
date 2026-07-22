# Indexing

Indexing creates a lookup table for a column along with pointers to the memory location of the rows containing that column.

### Example Table

| ID | Name | Age | Net Worth |
|----|------|-----|----------:|
| 1 | Shubham | 21 | 5000 |
| 2 | Rakesh | 22 | 10000 |
| 3 | Reema | 64 | 70594 |
| 4 | Shyam | 19 | 1000 |

Suppose we execute the following query:

```sql
SELECT name, age
FROM users
WHERE networth = 1000;
```

Right now we only have **4 records**, so searching with **O(n)** is not a problem.

But what if we have **1 million records**?

Instead, we can create an **index** on the **Net Worth** column, which reduces the search complexity to approximately **O(log n)** (using a B-Tree index).

The index simply creates a separate structure where the **Net Worth** values are stored in sorted order along with pointers to the actual rows where the data is stored.

---

## B-Tree Data Structure

B-Tree is the most commonly used data structure for indexing.

It stores the index in a **multi-level tree structure**, keeping the tree balanced so searching, insertion, and deletion remain efficient.

---

## When to Use Indexing

Always use indexing when the database is **read-intensive**, not **write-intensive**.

Reason:
- Read operations become much faster.
- Every INSERT, UPDATE, and DELETE also needs to update the index, which adds overhead.

---

# Database Optimization Tips

## Batch Operations

Instead of executing **1000 individual INSERT/UPDATE statements**, use **batch operations**.

This reduces the number of round trips between the application and the database.

---

## Pagination Properly

Using `LIMIT/OFFSET` becomes slower as the offset grows because the database still scans and discards those rows.

For large datasets, prefer **Keyset Pagination**.

Instead of:

```sql
LIMIT 20 OFFSET 100000
```

Use:

```sql
WHERE id > last_seen_id
LIMIT 20;
```

---

## Avoid `SELECT *`

Fetch only the columns you need.

This reduces I/O and, in some cases, allows the query to be answered entirely from the index (**Covering Index**) without accessing the actual table.

---

## Avoid Functions on Indexed Columns

Avoid writing queries like:

```sql
WHERE YEAR(created_at) = 2026
```

because the index on `created_at` cannot be used, resulting in a full table scan.

Instead, write:

```sql
WHERE created_at >= '2026-01-01'
AND created_at < '2027-01-01';
```

This allows the database to use the index efficiently.

---

# Clustered Index

A **Clustered Index** stores the actual table data in the order of the indexed column.

A table can have **only one Clustered Index** because the data can be physically stored in only one order.

---

## MySQL (InnoDB)

InnoDB always organizes table data as a **Clustered Index**.

- If you define a **Primary Key**, it automatically becomes the Clustered Index.
- If no Primary Key exists, InnoDB uses the first **UNIQUE NOT NULL** column.
- If neither exists, InnoDB creates a hidden internal Row ID and clusters the table using it.

So, in **MySQL (InnoDB)**, every table always has exactly **one Clustered Index**.

---

## SQL Server

By default, when you create a **Primary Key**, SQL Server automatically makes it the **Clustered Index**.

However, this is **not mandatory**.

You can:
- Make the Primary Key **Non-Clustered**.
- Choose another column as the **Clustered Index** based on your query patterns.

---

## PostgreSQL

PostgreSQL is different.

Tables are stored as a **Heap**, meaning rows have no guaranteed physical order.

The Primary Key is simply a **regular B-Tree index** pointing to the rows.

PostgreSQL provides a `CLUSTER` command, but it is a **one-time manual operation** and is **not maintained automatically**.

---

## Oracle

Oracle also stores tables as **Heap-Organized Tables** by default.

The Primary Key is just a normal **B-Tree index**.

Oracle provides **Index-Organized Tables (IOT)** if you want table data to be stored in index order, but you must create the table explicitly using IOT.

---

# Non-Clustered Index

A **Non-Clustered Index** is a separate structure from the actual table.

It stores:
- The indexed column in sorted order.
- A pointer (row locator) to where the actual row exists.

Unlike a Clustered Index, you can create **multiple Non-Clustered Indexes** on a single table.