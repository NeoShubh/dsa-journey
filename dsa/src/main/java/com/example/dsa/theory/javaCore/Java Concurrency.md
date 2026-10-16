# Java Concurrency

## 1. `synchronized` vs `volatile` vs `AtomicInteger`

When multiple threads access shared data, we need to ensure thread safety. Java provides different mechanisms for different problems.

### A. `synchronized`

`synchronized` provides mutual exclusion and visibility. Only one thread at a time can execute a synchronized section protected by the same monitor.

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

- Only one thread at a time can execute these methods on the same object.
- Changes made under the same monitor are visible to subsequent threads acquiring that monitor.
- It protects compound operations such as `count++`.

**Use case:** When multiple operations on shared state must execute as one protected operation.

### B. `volatile`

`volatile` guarantees visibility of changes between threads and provides memory-ordering guarantees. It does not provide mutual exclusion.

```java
class Worker {
    private volatile boolean running = true;

    public void work() {
        while (running) {
            // Keep working
        }
    }

    public void stop() {
        running = false;
    }
}
```

- When one thread changes `running`, other threads reading it see the updated value according to volatile visibility guarantees.
- No monitor lock is acquired.
- Compound operations such as `count++` are not made atomic.

**Use case:** Simple shared state flags, such as signalling a worker to stop.

### C. `AtomicInteger`

`AtomicInteger` provides thread-safe atomic operations on an integer without requiring an explicit synchronized block.

```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
```

- `incrementAndGet()` atomically increments the value and returns the updated value.
- `get()` reads the current value.
- It supports atomic operations without explicit monitor locking.

**Use case:** Counters, sequence numbers, and simple numeric state shared across threads.

### Comparison table

| Feature | `synchronized` | `volatile` | `AtomicInteger` |
|---|---|---|---|
| Visibility | Yes | Yes | Yes |
| Mutual exclusion | Yes | No | No explicit monitor lock |
| Atomic increment | Yes, if protected by the same lock | No | Yes |
| Supports compound critical sections | Yes | No | Only through suitable atomic operations |
| Main purpose | Protect shared state | Ensure visibility and ordering | Atomic numeric updates |
| Example | `synchronized void increment()` | `volatile boolean running` | `count.incrementAndGet()` |

### Why `volatile` is not enough for a counter

```java
private volatile int count = 0;

public void increment() {
    count++;
}
```

`count++` involves reading, incrementing, and writing. Two threads can read the same value and overwrite each other's updates.

Use `AtomicInteger` for an atomic increment, or `synchronized` when multiple operations must be protected together.

**Interview answer:** `volatile` provides visibility and ordering, `synchronized` provides mutual exclusion and visibility, and `AtomicInteger` provides atomic operations on an integer.

---

## 2. How Does `ExecutorService` Work?

### Problem

Creating and managing many threads manually can become difficult. `ExecutorService` separates task submission from thread management.

It can manage a pool of reusable threads and execute submitted tasks.

### Example

```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            executor.submit(() -> {
                System.out.println(
                    "Task " + taskId + " running on "
                    + Thread.currentThread().getName()
                );
            });
        }

        executor.shutdown();
    }
}
```

### How does it work?

1. `newFixedThreadPool(3)` creates an executor with three worker threads.
2. `submit()` sends tasks to the executor.
3. At most three tasks execute concurrently through this pool.
4. Remaining tasks wait in the queue until a worker becomes available.
5. `shutdown()` stops accepting new tasks and allows submitted tasks to finish.

### Important methods

| Method | Purpose |
|---|---|
| `submit()` | Submits a task and returns a `Future` |
| `execute()` | Executes a `Runnable` without returning a `Future` |
| `shutdown()` | Stops accepting new tasks and allows submitted tasks to finish |
| `shutdownNow()` | Attempts to interrupt running tasks and returns tasks that never started |
| `awaitTermination()` | Waits for the executor to terminate |

### What is a `Future`?

A `Future` represents the result of an asynchronous task.

```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        try {
            Future<Integer> future =
                    executor.submit(() -> 10 + 20);

            System.out.println(future.get()); // 30
        } finally {
            executor.shutdown();
        }
    }
}
```

`future.get()` waits for the result if the task has not finished yet.

**Interview answer:** `ExecutorService` manages task execution, thread reuse, task results through `Future`, and executor lifecycle.

---

## 3. What Is a Deadlock?

A **deadlock** occurs when two or more threads wait indefinitely for locks held by one another.

### Example

```java
public class DeadlockExample {

    static final Object A = new Object();
    static final Object B = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("Thread 1 locked A");

                synchronized (B) {
                    System.out.println("Thread 1 locked B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("Thread 2 locked B");

                synchronized (A) {
                    System.out.println("Thread 2 locked A");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

This example can deadlock if each thread acquires its first lock before the other thread acquires its first lock.

### How does the deadlock happen?

- Thread 1 acquires lock A and waits for lock B.
- Thread 2 acquires lock B and waits for lock A.
- Neither thread can proceed because each is waiting for the other.

### Four conditions for deadlock

1. **Mutual exclusion:** A resource can be held by only one thread at a time.
2. **Hold and wait:** A thread holds one resource while waiting for another.
3. **No preemption:** A resource cannot simply be forcibly taken away from its holder.
4. **Circular wait:** A cycle exists in which each thread waits for a resource held by another.

All four conditions must hold simultaneously for this classic deadlock scenario.

### How can you prevent deadlocks?

**1. Acquire locks in a consistent order.**

Always acquire A before B across all threads to prevent this circular-wait scenario.

**2. Avoid unnecessary nested locks.**

Keep synchronized sections small and avoid acquiring additional locks when possible.

**3. Use `tryLock()` where appropriate.**

`ReentrantLock.tryLock()` can let a thread back off rather than wait indefinitely, provided the application handles failure and releases any acquired locks correctly.

**Interview answer:** A deadlock is a state where threads wait indefinitely for locks held by one another. Consistent lock ordering and avoiding unnecessary nested locks are common prevention techniques.

---

## 4. Quick Revision

| Topic | Main takeaway |
|---|---|
| `synchronized` | Locking, mutual exclusion, and visibility |
| `volatile` | Visibility and ordering, but no atomic compound operations |
| `AtomicInteger` | Atomic numeric operations without explicit monitor locking |
| `ExecutorService` | Manages task execution and worker threads |
| `Future` | Represents a task result that can be retrieved later |
| Deadlock | Threads wait indefinitely for one another's locks |

**Interview tip:** Be ready to explain why `volatile int count` does not make `count++` thread-safe, and when you would choose `synchronized` over `AtomicInteger`.