# String Literal vs `new String()`

| String Literal (`" "`) | `new String()` |
|-------------------------|----------------|
| Stored in the **String Pool**. | Stored in **Heap Memory** (a separate object is created). |
| Reuses an existing object if the same value already exists in the String Pool. | Always creates a new object, even if the value already exists. |
| More memory efficient. | Uses more memory because a new object is created every time. |

### Example

```java
String s1 = "Java";
String s2 = "Java";

System.out.println(s1 == s2); // true
```

Both `s1` and `s2` point to the **same object** in the String Pool.

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2); // false
```

Both variables point to **different objects** in Heap Memory.

---

# `==` vs `equals()`

## `==`

Checks whether **both references point to the same object**.

```java
String s1 = "Java";
String s2 = "Java";

System.out.println(s1 == s2); // true
```

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2); // false
```

---

## `equals()`

Checks whether **both objects have the same content**.

```java
String s1 = "Java";
String s2 = "Java";

System.out.println(s1.equals(s2)); // true
```

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1.equals(s2)); // true
```

---

# String Immutability

A **String is immutable**, which means **its value cannot be changed after it is created**.

Whenever we modify a String, Java creates a **new String object** instead of changing the existing one.

### Example

```java
String s = "Java";
s = s + " Programming";
```

Memory:

```text
"Java"  --------------------> (Old Object)

"Java Programming" ---------> (New Object)

s ---------------------------> "Java Programming"
```

The original `"Java"` object is **not modified**.

---

# Is String Immutable for Both Declarations?

**Yes.**

Whether the String is created using:

```java
String s1 = "Java";
```

or

```java
String s1 = new String("Java");
```

the **String object is immutable in both cases**.

The only difference is **where the object is created**:

- String Literal (`"Java"`) → String Pool
- `new String("Java")` → Heap Memory

Immutability remains the same in both cases.

---

# Interview Summary

| Topic | Answer |
|-------|--------|
| `"Java"` | Stored in the String Pool. |
| `new String("Java")` | Creates a new object in Heap Memory. |
| `==` | Compares object references (memory addresses). |
| `equals()` | Compares String contents. |
| String Immutable? | Yes, for both declarations. |
| On modification | A new String object is created; the original object remains unchanged. |