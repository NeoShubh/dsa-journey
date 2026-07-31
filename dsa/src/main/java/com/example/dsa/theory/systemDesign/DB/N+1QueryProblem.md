# N + 1 Query Problem

We will take the example of our own **Loan Application Project**.

We have a **Loan Application** database and a **Document** database.

Basically, for one Loan Application there could be multiple Documents. Sometimes we need to fetch the documents for a specific Loan Application.

For example:

```sql
SELECT * FROM loan_application;
```

Now we want all the Loan Applications.

Suppose we also want the Documents for each Loan Application.

Since we have a **One-to-Many** relationship, Hibernate by default uses **Lazy Loading**, which means it does not load the related data until it is actually needed.

> **Hibernate** is an **ORM (Object Relational Mapping)** framework that works as a middle layer between the application and the database. It converts Java objects into SQL queries and maps the query results back into Java objects, allowing developers to work with objects instead of writing SQL most of the time.

When we access the documents for a Loan Application, Hibernate executes another query like:

```sql
SELECT * FROM document
WHERE loan_id = 10002;
```

Now imagine we want the documents for **all** Loan Applications.

Here comes the **N + 1 Query Problem**.

For a few records, this is not an issue.

But if we have **1 lakh Loan Applications**, and each Loan Application has **20–50 Documents**, Hibernate will execute one query to fetch all Loan Applications and then one additional query for every Loan Application to fetch its Documents.

So instead of executing **1 query**, it executes **1 + N queries**, which causes a huge performance issue.

---

# Ways to Solve the N + 1 Query Problem

## 1. Fetch Join

```java
@Query("""
SELECT l
FROM LoanApplication l
JOIN FETCH l.documents
""")
List<LoanApplication> findAllWithDocuments();
```

Here we are explicitly telling Hibernate to fetch the Documents along with the Loan Applications.

This generates **one SQL query with a JOIN**, fetching Loan Applications and their Documents in a single database round trip.

---

## 2. Entity Graph

A nice way to tell Hibernate to eagerly load the Documents without writing a `JOIN FETCH` query.

```java
@EntityGraph(attributePaths = {"documents"})
@Query("SELECT l FROM LoanApplication l")
List<LoanApplication> findAllWithDocuments();
```

---

## 3. Batch Fetching (`@BatchSize`)

Instead of loading related data one row at a time, Hibernate groups multiple parent IDs into a single `IN` clause query.

```java
@OneToMany(mappedBy = "loanApplication")
@BatchSize(size = 20)
private List<Document> documents;
```

Instead of executing:

```sql
SELECT * FROM document WHERE loan_application_id = ?;
```

100 times,

Hibernate executes:

```sql
SELECT *
FROM document
WHERE loan_application_id IN (id1, id2, ..., id20);
```

For **100 Loan Applications**, Hibernate executes only **5 queries** (20 at a time) instead of **100**.

It is not as good as a single `JOIN FETCH`, but it is still a huge improvement and avoids the **Cartesian Product** problem that can happen with `JOIN FETCH`.

---

## 4. DTO Projection

If you don't need the complete entity objects and their lazy relationships, query directly into a DTO.

```java
@Query("""
SELECT new com.bank.dto.LoanSummaryDTO(
    l.id,
    l.customerName,
    d.fileName
)
FROM LoanApplication l
JOIN l.documents d
""")
List<LoanSummaryDTO> findLoanSummaries();
```

This completely avoids the entity/lazy-loading mechanism.

Only **one query** is executed, fetching exactly the data you need without any extra overhead.