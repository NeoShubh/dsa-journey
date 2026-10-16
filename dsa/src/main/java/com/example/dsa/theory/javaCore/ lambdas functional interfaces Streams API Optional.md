# Lambda Expressions & Functional Interfaces

- **Functional Interface**: An interface that has **exactly one abstract method**. It can contain any number of `default` or `static` methods. The optional `@FunctionalInterface` annotation helps prevent adding more abstract methods by triggering a compiler error. [[1](https://www.geeksforgeeks.org/java/java-functional-interfaces/), [2](https://www.geeksforgeeks.org/java/lambda-expressions-java-8/), [3](https://www.geeksforgeeks.org/java/java-8-features-tutorial/)]
- **Lambda Expression**: An anonymous block of code (a function without a name) used to provide the inline implementation of a functional interface. [[1](https://www.geeksforgeeks.org/java/lambda-expressions-java-8/), [2](https://www.geeksforgeeks.org/java/java-8-features-tutorial/), [3](https://www.geeksforgeeks.org/java/java-8-interview-questions-and-answers/)]

## 2. Stream API Operations

The [**Java Stream API**](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) provides declarative, functional-style processing for collections. [[1](https://app.thetestingacademy.com/blog/java8-features-for-testers), [2](https://www.geeksforgeeks.org/java/java-8-features-tutorial/)]

### `map` vs `flatMap` [[1](https://www.geeksforgeeks.org/java/difference-between-map-and-flatmap-in-java-stream/)]

- **`map()`**: Used for **1-to-1 transformation**. It passes each item through a `Function` and outputs exactly one transformed item per input. [[1](https://medium.com/@kaustubh.saha/java-lambdas-streams-and-functional-programming-part-2-08ced3acbb78), [2](https://www.youtube.com/shorts/jlwbrt3BmbM), [3](https://www.geeksforgeeks.org/java/difference-between-map-and-flatmap-in-java-stream/)]
- **`flatMap()`**: Used for **1-to-many transformation and flattening**. It transforms each element into a *Stream* of zero or more elements and merges all those nested streams into a single unified stream. [[1](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html), [2](https://www.youtube.com/shorts/jlwbrt3BmbM), [3](https://www.geeksforgeeks.org/java/difference-between-map-and-flatmap-in-java-stream/)]

**Java**

```java
// map: Stream<String> -> Stream<Integer> (Transforms words into their lengths)
List<Integer> lengths = Stream.of("Java", "Stream")
                              .map(String::length)
                              .collect(Collectors.toList()); // [4, 6]

// flatMap: Stream<List<String>> -> Stream<String> (Flattens nested structures)
List<List<String>> complexList = Arrays.asList(Arrays.asList("A", "B"), Arrays.asList("C", "D"));
List<String> flatList = complexList.stream()
                                   .flatMap(Collection::stream)
                                   .collect(Collectors.toList()); // ["A", "B", "C", "D"]
```

### `reduce` vs `collect` [[1](https://www.resumelens.org/blog/java/java-streams-and-lambdas)]

- **`reduce()`**: A **terminal operation** that folds or aggregates stream elements into a **single summary value** (like a sum, minimum, maximum, or concatenation) using an accumulator. [[1](https://www.youtube.com/watch?v=w-iwyp_A7e8), [2](https://www.resumelens.org/blog/java/java-streams-and-lambdas)]
- **`collect()`**: A **terminal operation** designed to repackage stream elements into a **mutable container** like a `List`, `Set`, or `Map`. [[1](https://www.resumelens.org/blog/java/java-streams-and-lambdas)]

**Java**

```java
// reduce: Aggregates values into a single primitive or object
int sum = Stream.of(1, 2, 3, 4).reduce(0, (a, b) -> a + b); // 10

// collect: Gathers elements into a Collection or data structure
Map<Integer, String> map = Stream.of("A", "BB", "CCC")
                                 .collect(Collectors.toMap(String::length, s -> s));
```

## 3. Optional Class

`Optional<T>` is a single-value container object that may or may not contain a non-null value. It provides a type-safe alternative to returning `null`, reducing the frequency of `NullPointerException` errors. [[1](https://medium.com/@Developer_YoussefHassan/embracing-java-8-features-lambdas-functional-interfaces-and-optional-8ec3aafd1ea6), [2](https://www.geeksforgeeks.org/java/java-8-features-tutorial/), [3](https://www.geeksforgeeks.org/java/java-8-interview-questions-and-answers/)]

**Java**

```java
Optional<String> name = Optional.ofNullable(getNameFromDatabase());

// Avoid this anti-pattern (resembles an explicit null check):
if (name.isPresent()) {
    System.out.println(name.get());
}

// Prefer functional alternatives:
name.ifPresent(System.out::println); // Runs only if value exists

String finalName = name.orElse("Default Name"); // Fallback if empty

String dynamicFallback = name.orElseGet(() -> fetchFromBackup()); // Lazy execution
```

# Callable Interface in Java

Last Updated: 1 Nov, 2025

The `Callable` interface is a part of the [**java.util.concurrent**](https://www.geeksforgeeks.org/java/java-util-concurrent-package/) package, introduced in Java 5. It represents a task that can be executed by multiple threads and return a result. Unlike the `Runnable` interface, `Callable` can return a value and throw checked exceptions.

- Used with `ExecutorService` for asynchronous or concurrent execution.
- The result of a `Callable` is obtained using a `Future` object.
- It’s a [**functional interface**](https://www.geeksforgeeks.org/java/java-functional-interfaces/), so you can use lambda expressions.

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

**Here:**
- `V` → The type of result returned by the `call()` method.
- `call()` → The method that performs the computation and returns a result. It can throw a checked exception.

**Example:**

```java
import java.util.concurrent.*;

public class GFG {
    public static void main(String[] args) throws Exception {

        // Create an ExecutorService with a single thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Create a Callable task using Lambda Expression
        Callable<Integer> task = () -> {
            System.out.println("Calculating...");
            Thread.sleep(1000);
            return 10 * 2;
        };

        // Submit the task and get a Future object
        Future<Integer> future = executor.submit(task);

        // Retrieve the result from the Future
        System.out.println("Result: " + future.get());

        // Shutdown the executor
        executor.shutdown();
    }
}
```