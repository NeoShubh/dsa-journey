# SOLID Principles

**SOLID** is a collection of **5 Object-Oriented Design Principles** that help write clean, maintainable, scalable, and extensible code.

---

## S - Single Responsibility Principle (SRP)

> A class should have **only one responsibility (one reason to change).**

### Example

Instead of one class handling **User Registration**, **Email Sending**, and **Report Generation**, create separate classes.

- `UserService` → Register User
- `EmailService` → Send Email
- `ReportService` → Generate Reports

Each class has only one job.

---

## O - Open/Closed Principle (OCP)

> Code should be **open for extension but closed for modification**.

### Example

Suppose today your application supports:

- UPI Payment
- Card Payment

Tomorrow the business asks to add **Net Banking**.

Instead of modifying the existing payment code, simply create a new `NetBankingPayment` class implementing the `Payment` interface.

The existing code remains unchanged.

---

## L - Liskov Substitution Principle (LSP)

> A child class should be able to replace its parent without breaking the program.

### Example

Suppose we have a `Bird` class with a `fly()` method.

Making `Penguin` extend `Bird` is wrong because Penguins cannot fly.

Instead:

- `Bird`
    - `Sparrow`
    - `Eagle`

and

- `Penguin`

should be separate, or use a better hierarchy where only flying birds implement `fly()`.

---

## I - Interface Segregation Principle (ISP)

> Don't force a class to implement methods it doesn't need.

### Example

Suppose we have:

```text
Worker
 ├── work()
 └── eat()
```

A **Human** can work and eat.

A **Robot** can work but cannot eat.

Instead, split the interface into:

- `Workable`
- `Eatable`

Now the Robot implements only `Workable`.

---

## D - Dependency Inversion Principle (DIP)

> Depend on abstractions (interfaces), not concrete implementations.

### Example

Suppose `NotificationService` needs to send messages.

Instead of directly creating:

- `EmailService`

depend on a common interface:

```text
MessageService
      ▲
 ┌────┴────┐
 │         │
Email     SMS
```

Now tomorrow you can add **WhatsAppService** without changing `NotificationService`.

Only inject another implementation.

---

# Interview Trick

| Principle | Easy Way to Remember |
|-----------|----------------------|
| **S** | One class → One Job |
| **O** | Add new features without changing old code |
| **L** | Child should behave like Parent |
| **I** | Don't force unnecessary methods |
| **D** | Depend on Interface, not Implementation |
---

# DRY Principle

**DRY = Don't Repeat Yourself**

> Don't duplicate code. Keep reusable logic in one place.

**Example:** Instead of writing the same validation or GST calculation logic in multiple classes, create a common utility/service and reuse it.

---

# KISS Principle

**KISS = Keep It Simple, Stupid**

> Choose the simplest solution that solves the problem.

**Example:** If a simple `HashMap` solves the problem, don't introduce Kafka, Redis, and Microservices unnecessarily.

---

# YAGNI Principle

**YAGNI = You Aren't Gonna Need It**

> Don't build features until they are actually required.

**Example:** If your Loan Application currently supports only **Personal Loans**, don't implement Home Loan, Car Loan, or Gold Loan until the business actually requires them.