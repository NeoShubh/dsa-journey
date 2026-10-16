## Checked vs. Unchecked Exceptions

- **Checked Exceptions:**
    - Checked by the compiler at compile-time.
    - Must be either caught using a `try-catch` block or declared in the method signature using `throws` (otherwise, code fails to compile).
    - Subclasses of `Exception` (excluding `RuntimeException`).
    - Examples: `IOException`, `SQLException`, `FileNotFoundException`.

- **Unchecked Exceptions:**
    - Not checked by the compiler at compile-time; they occur at runtime.
    - Handling is optional, though recommended.
    - Subclasses of `RuntimeException` (and `Error`).
    - Typically caused by programming logic errors.
    - Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`.

## Try-with-Resources

- Introduced in Java 7 to automatically manage resource cleanup for classes implementing `AutoCloseable` or `Closeable` (e.g., streams, database connections, sockets).
- Resources declared inside the parentheses `try (...)` are closed automatically in the reverse order of their declaration, regardless of whether the try block succeeds or throws an exception.
- If both the body and the close operation throw an exception, the body's exception is preserved as the primary one, and the close-time failure is attached as a suppressed exception (retrievable via `getSuppressed()`).
- Eliminates boilerplate `finally` blocks and reduces accidental resource leaks.

## final, finally, and finalize

- **final (Keyword / Modifier):**
    - Variables: Makes the variable a constant; its reference/value cannot be reassigned after initialization (though the internal state of mutable objects can still change).
    - Methods: Prevents subclasses from overriding the method.
    - Classes: Prevents the class from being extended or inherited.

- **finally (Block):**
    - A block associated with a `try-catch` statement that guarantees execution of cleanup code (like closing legacy non-auto-closable resources or logging) regardless of whether an exception is thrown or caught.

- **finalize (Method):**
    - A legacy method defined in `java.lang.Object` invoked by the garbage collector before reclaiming an object's memory.
    - Deprecated due to unpredictable timing, performance overhead, and safety concerns; modern Java applications should use try-with-resources or `java.lang.ref.Cleaner` instead.