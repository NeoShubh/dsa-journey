# Runnable: Named Class, Anonymous Class, and Lambda

## 1. Named Class Implementation

```java
class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Running...");
    }
}

public class Main {
    public static void main(String[] args) {
        Runnable r = new MyTask();
        r.run();
    }
}
```

- `MyTask` is a **named class**.
- `implements Runnable` means it must implement the `run()` method.
- `new MyTask()` creates an object of that class.

## 2. Anonymous Class Implementation

```java
public class Main {
    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running...");
            }
        };

        r.run();
    }
}
```

- We don't create a separately named class like `MyTask`.
- `new Runnable() { ... }` defines an anonymous class and creates its object.
- We still implement the `run()` method.

## 3. Lambda Expression

```java
public class Main {
    public static void main(String[] args) {

        Runnable r = () -> System.out.println("Running...");

        r.run();
    }
}
```

- No explicit class implementation.
- No `@Override`.
- No need to write `public void run()`.
- Java infers that the lambda implements `Runnable`'s single abstract method, `run()`.

## 4. Comparison

| Feature | Named Class | Anonymous Class | Lambda |
|---|---|---|---|
| Class name | `MyTask` | No name | No explicit class |
| `implements Runnable` | Yes | Implicit | Implicit |
| Write `run()` | Yes | Yes | No |
| `@Override` | Yes, recommended | Yes, recommended | No |
| Amount of code | Most | Less | Least |

**Key idea:** All three provide the behavior required by `Runnable`. A lambda is shorter because Java already knows which method needs to be implemented.