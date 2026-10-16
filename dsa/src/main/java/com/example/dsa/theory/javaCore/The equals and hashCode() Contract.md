# The `equals()` / `hashCode()` Contract

When you override `equals()` to compare object contents, you must also override `hashCode()`. Failing to do so can break hash-based collections like `HashMap`, `HashSet`, and `Hashtable`.

## The Rules of the Contract

1. **Equal objects must have equal hash codes:** If `a.equals(b)` is `true`, then `a.hashCode() == b.hashCode()` must also be `true`.
2. **Equal hash codes don't guarantee equal objects:** If `a.hashCode() == b.hashCode()`, it does not mean `a.equals(b)` is `true`. Hash collisions are allowed.
3. **Consistency:** The hash code must remain the same during execution as long as the fields used in `equals()` do not change.
4. **Properties of `equals()`:** It must be reflexive, symmetric, transitive, consistent, and return `false` when compared with `null`.

## Why It Matters for Collections

Hash-based collections like `HashMap` and `HashSet` use a two-step lookup:

1. **`hashCode()`** — Finds the appropriate bucket (shelf).
2. **`equals()`** — Compares objects within that bucket to find the exact match.

### Example of the Problem

Suppose you override `equals()` to compare customers using `customerId` but leave the default `hashCode()` implementation.

Two `Customer` objects with the same `customerId` may have different hash codes because the default implementation does not derive the hash code from that ID.

As a result:
- **`HashSet`:** May fail to remove duplicate logical customers.
- **`HashMap`:** May fail to retrieve a value using an equal `Customer` key.

## Best Practice

Always override `equals()` and `hashCode()` together, using the same fields for both methods.

In modern Java, **records** automatically generate compatible implementations of `equals()` and `hashCode()` based on their components, making them a convenient choice for immutable data objects.