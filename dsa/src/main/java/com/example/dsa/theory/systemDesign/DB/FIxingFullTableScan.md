# Full Table Scan

Full Table Scan means we are unnecessarily retrieving or iterating over the entire table in the database.

We must fix a few things to avoid it.

## 1. Use Indexing

If we are not using indexing on any column in the query, we should create an index on that column (where appropriate).

---

## 2. Avoid Using Functions on Indexed Columns

If we are using any function like `UPPER()`, `LOWER()`, etc. on an indexed column, then indexing will not work in that scenario.

The values stored in the **B-Tree** are the original values that were stored in the database without any modification.

For example, if the value stored is `shubham` and we execute:

```sql
WHERE UPPER(name) = 'SHUBHAM'
```

The database cannot directly use the index because it has to apply the function to every row first. This results in a **Full Table Scan**.

To avoid this, we can do two things:
- Don't use functions on indexed columns.
- Store the data in the format (case) you want from the beginning.

---

## 3. Don't Use Wildcards Inappropriately

B-Tree stores values in sorted order.

If you use:

```sql
LIKE '%hubham'
```

the database has to check every possible value (`Ahubham`, `Bhubham`, `Chubham`, ...), so it cannot use the index efficiently and ends up performing a **Full Table Scan**.

Instead, queries like:

```sql
LIKE 'Shub%'
```

can make use of the index.

---

## 4. Don't Use Indexing for Large Result Sets

Indexing should be used when you want to retrieve a **small fraction** of the data.

If you are fetching a very large portion of the table, indexing may become more expensive than a Full Table Scan.

For example, suppose we have a `users` table with an `is_active` column, and **90% of the users are active**.

If we create an index on `is_active` and then query for active users, the database still has to retrieve almost the entire table.

In this case, using the index adds extra cost because the index stores the indexed column and a pointer to the actual row, not the complete row itself.

For such queries, a **Full Table Scan** can actually be more efficient.