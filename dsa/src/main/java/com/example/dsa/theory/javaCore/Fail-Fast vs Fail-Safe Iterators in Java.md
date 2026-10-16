# Fail-Fast vs Fail-Safe Iterators in Java

31 May 2025

| Aspect | Fail-Fast Iterators | Fail-Safe Iterators |
|---|---|---|
| Modification Handling | Does not allow modification of a collection during iteration. | Allows modification of a collection during iteration. |
| Exception Behavior | Throws `ConcurrentModificationException` if the collection is modified while iterating. | No exception is thrown — safe to modify while iterating. |
| How It Works | Uses the original collection to traverse elements. | Uses a copy of the original collection to traverse elements. |
| Memory Usage | No extra memory needed — no duplication involved. | Requires extra memory to clone the collection. |
| Examples | Iterators returned by `ArrayList`, `Vector`, and `HashMap`. | Iterators returned by `ConcurrentHashMap`. |