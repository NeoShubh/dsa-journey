# Java 21 Features

## 1. Virtual Threads — Java 21

### Problem

Imagine your Spring Boot application receives 10,000 HTTP requests. Each request needs to call a database or another microservice and wait for the response.

With traditional platform threads, a thread may sit blocked while waiting for I/O. Platform threads are relatively expensive because they are backed by operating-system threads.

**Virtual threads** are lightweight threads managed by the JVM. They make it practical to handle many concurrent, mostly I/O-bound tasks without needing a huge number of platform threads.

### Old way: Platform thread

```java
Thread thread = new Thread(() -> {
    System.out.println("Processing request...");
});

thread.start();
```

This creates a platform thread.

### Java 21 way: Virtual thread

```java
Thread thread = Thread.startVirtualThread(() -> {
    System.out.println("Processing request...");
});
```

The key difference is that `startVirtualThread()` starts the task using a virtual thread.

### Another useful approach: Virtual-thread executor

```java
try (var executor =
         java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {

    executor.submit(() -> {
        System.out.println("Calling another service...");
    });
}
```

This executor creates a new virtual thread for each submitted task. Closing it waits for submitted tasks to finish.

### When would you use it?

In a backend service that handles many concurrent requests involving blocking database calls, HTTP calls, or other I/O operations.

**Important interview points:**
- Virtual threads became a permanent feature in Java 21.
- They improve scalability for blocking, I/O-heavy workloads.
- They do not make CPU-intensive calculations faster.
- They are not a replacement for limiting database connections or other scarce resources.

---

## 2. Record Patterns — Java 21

### Problem

Remember Java records?

```java
record Employee(String name, int age) {}
```

Suppose you receive an `Employee` object and want to extract its name and age.

### Old way

```java
Employee emp = new Employee("Shubham", 24);

String name = emp.name();
int age = emp.age();

System.out.println(name + " " + age);
```

You call each accessor separately.

### Java 21 way: Record patterns

```java
Employee emp = new Employee("Shubham", 24);

if (emp instanceof Employee(String name, int age)) {
    System.out.println(name + " " + age);
}
```

Here, `Employee(String name, int age)` is a **record pattern**.

Java checks whether `emp` matches the `Employee` record and extracts its components into `name` and `age`. You don't need to call the accessors yourself.

### Nested record patterns

```java
record Address(String city) {}
record Employee(String name, Address address) {}

Employee emp = new Employee(
    "Shubham",
    new Address("Pune")
);

if (emp instanceof Employee(String name, Address(String city))) {
    System.out.println(name + " lives in " + city);
}
```

Output:

```text
Shubham lives in Pune
```

### Why is this useful?

Record patterns can extract values from nested data objects without multiple accessor calls.

**Remember:** Records define data structures; record patterns let you unpack those structures.

---

## 3. Pattern Matching for `switch` — Java 21

### Problem

Traditional `switch` works well with values such as integers, strings, and enums. But what if you want to execute different logic depending on an object's type?

### Old way

```java
static void process(Object obj) {
    if (obj instanceof String) {
        String s = (String) obj;
        System.out.println(s.length());
    } else if (obj instanceof Integer) {
        Integer i = (Integer) obj;
        System.out.println(i * 2);
    }
}
```

You repeatedly use `instanceof` and explicit casts.

### Java 21 way

```java
static void process(Object obj) {
    switch (obj) {
        case String s ->
            System.out.println(s.length());

        case Integer i ->
            System.out.println(i * 2);

        case null ->
            System.out.println("No value");

        default ->
            System.out.println("Unsupported type");
    }
}
```

### What's happening?

- `case String s` checks whether the object is a `String` and binds it to `s`.
- `case Integer i` does the same for an `Integer`.
- `case null` explicitly handles a null value.
- `default` handles other types.

Pattern matching for `switch` became a permanent feature in Java 21.

### Where would you use this?

Suppose your application processes different types of payment requests: `CardPayment`, `CashPayment`, or `UPIPayment`. You can use type patterns to route each type to the appropriate logic.

**Remember:** `instanceof` pattern matching handles a type check in a condition; pattern matching for `switch` handles multiple types in one structured block.

---

## 4. Sequenced Collections — Java 21

### Problem

Java already has collections such as `List`, `Set`, and `Map`. But accessing the first and last elements consistently across different ordered collection types wasn't always straightforward.

For example, with a list:

```java
List<String> names = new ArrayList<>();

names.add("Amit");
names.add("Shubham");
names.add("Rahul");

System.out.println(names.get(0)); // First
System.out.println(names.get(names.size() - 1)); // Last
```

For a `LinkedList`, you could use `getFirst()` and `getLast()`. Different collection types had different ways of working with their ends.

### Java 21 way

Java 21 introduced sequenced collection interfaces with common methods for accessing the beginning and end of an ordered collection.

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(
            List.of("Amit", "Shubham", "Rahul")
        );

        System.out.println(names.getFirst()); // Amit
        System.out.println(names.getLast());  // Rahul

        names.addFirst("Neha");
        names.addLast("Priya");

        System.out.println(names);
    }
}
```

Output:

```text
Amit
Rahul
[Neha, Amit, Shubham, Rahul, Priya]
```

### Important methods

| Method | Purpose |
|---|---|
| `getFirst()` | Returns the first element |
| `getLast()` | Returns the last element |
| `addFirst(e)` | Adds an element at the beginning |
| `addLast(e)` | Adds an element at the end |
| `removeFirst()` | Removes the first element |
| `removeLast()` | Removes the last element |
| `reversed()` | Provides a reverse-ordered view |

These methods are available through the relevant sequenced collection interfaces, including `SequencedCollection`.

**Remember:** Sequenced collections provide a consistent API for working with the first, last, and reversed order of elements.

---

## Quick Revision for Interviews

| Feature | Main purpose |
|---|---|
| Virtual threads | Handle many concurrent, mostly I/O-bound tasks efficiently |
| Record patterns | Extract components from record objects |
| Pattern matching for `switch` | Branch on an object's type without repetitive casting |
| Sequenced collections | Work consistently with the first, last, and reversed order of elements |

**Recommendation:** Learn virtual threads first because they are particularly relevant to Java backend interviews. Then focus on pattern matching and record patterns, followed by sequenced collections.

**Official reference:** [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)