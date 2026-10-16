    



public record person(String name, int age){} ;

its a type of class declaration

# Java 17 Features

## 1. Records — Java 16 (Available in Java 17)

**Problem:** Sometimes we create classes just to store data. We end up writing constructors, getters, `equals()`, `hashCode()`, and `toString()`.

### Old way

```java
class Employee {
    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

### With a record

```java
record Employee(String name, int age) {}
```

Java automatically generates the constructor, accessors, `equals()`, `hashCode()`, and `toString()`.

### Usage

```java
Employee e = new Employee("Shubham", 24);

System.out.println(e.name()); // Shubham
System.out.println(e.age());  // 24
```

**Note:** Record accessors are `name()` and `age()`, not `getName()` and `getAge()`.

**Remember:** Use records for data-carrier objects, such as DTOs and API responses. Records are shallowly immutable: their components cannot be reassigned, but referenced objects may themselves be mutable.

---

## 2. Sealed Classes — Java 17

**Problem:** Normally, a class can be extended by any accessible class. Sometimes we want to control exactly which classes can extend it.

Java 17 lets us restrict inheritance.

```java
sealed class Payment
    permits CardPayment, CashPayment {
}

final class CardPayment extends Payment {
}

final class CashPayment extends Payment {
}
```

- `sealed` means inheritance is restricted.
- `permits` lists the allowed direct subclasses.
- `final` means a subclass cannot be extended further.

A permitted subclass must declare how its inheritance continues: `final`, `sealed`, or `non-sealed`.

**Why is this useful?**

Suppose your application supports only card and cash payments. Sealed classes let you enforce that restriction at compile time.

**Remember:** Use sealed classes when you want a controlled class hierarchy.

---

## 3. Text Blocks — Java 15 (Available in Java 17)

**Problem:** Writing long, multiline strings using normal strings requires escape characters and concatenation.

### Old way

```java
String json = "{\n" +
              "  \"name\": \"Shubham\",\n" +
              "  \"age\": 24\n" +
              "}";
```

### With a text block

```java
String json = """
        {
          "name": "Shubham",
          "age": 24
        }
        """;
```

- Start with three double quotes: `"""`.
- Write multiline content naturally.
- End with three double quotes.

### Example: SQL query

```java
String sql = """
        SELECT *
        FROM employees
        WHERE age > 25
        """;
```

**Why is this useful?**

Useful for JSON, SQL queries, HTML, and multiline messages.

**Remember:** Text blocks improve the readability of multiline strings.

---

## 4. Switch Expressions — Java 14 (Available in Java 17)

**Problem:** Traditional `switch` statements often require `break` statements and extra variables when you want to return a value.

### Old way

```java
int day = 2;
String result;

switch (day) {
    case 1:
        result = "Monday";
        break;
    case 2:
        result = "Tuesday";
        break;
    default:
        result = "Unknown";
}
```

### With a switch expression

```java
int day = 2;

String result = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    default -> "Unknown";
};
```

- `switch` now produces a value that can be assigned directly.
- `->` avoids traditional fall-through between cases.
- No `break` is needed with arrow syntax.
- A `default` branch ensures all possible values are handled in this example.

### Using `yield`

You can use a block when a case needs multiple statements.

```java
String result = switch (day) {
    case 1 -> {
        System.out.println("First day");
        yield "Monday";
    }
    default -> "Other day";
};
```

`yield` returns the value from a block-style case.

**Remember:** Use switch expressions when you want a `switch` to calculate and return a value.

---

## 5. Pattern Matching for `instanceof` — Java 16 (Available in Java 17)

**Problem:** Traditionally, you check an object's type and then explicitly cast it.

### Old way

```java
Object obj = "Hello Java";

if (obj instanceof String) {
    String text = (String) obj;
    System.out.println(text.length());
}
```

### With pattern matching

```java
Object obj = "Hello Java";

if (obj instanceof String text) {
    System.out.println(text.length());
}
```

What changed?

- `instanceof String text` checks whether `obj` is a `String`.
- If the check succeeds, Java makes the variable `text` available in the appropriate scope.
- You no longer need the explicit cast `(String) obj`.

**Why is this useful?**

It reduces boilerplate and avoids explicit casts.

**Remember:** Pattern matching combines the type check and variable declaration into one operation.

---

## Quick Revision for Interviews

| Feature | Main Purpose |
|---|---|
| Records | Reduce boilerplate for data-carrier classes |
| Sealed classes | Restrict which classes can extend a type |
| Text blocks | Write multiline strings cleanly |
| Switch expressions | Return values directly from `switch` |
| Pattern matching for `instanceof` | Check a type and bind a variable without an explicit cast |

**Interview tip:** Understand the problem each feature solves, its syntax, and one practical use case. That's more valuable than memorizing definitions alone.