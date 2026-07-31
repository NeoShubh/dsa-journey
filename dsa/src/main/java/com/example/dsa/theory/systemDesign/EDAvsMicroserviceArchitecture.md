# Event-Driven Architecture vs Microservices Architecture

| Event-Driven Architecture (EDA) | Microservices Architecture |
|---------------------------------|----------------------------|
| Components communicate **asynchronously** through events. | Services communicate mainly through **synchronous APIs** (can also use events). |
| Components are **loosely coupled** and react to events. | Services are **independent**, each handling a specific business function. |
| Best suited for **real-time event processing**. | Best suited for **large modular applications**. |
| Scales by processing **event streams**. | Each service can be **scaled independently**. |
| Common use cases: **IoT, Real-time Analytics, Notifications, Event Processing**. | Common use cases: **Banking, E-commerce, Healthcare, CMS**. |

---

## Event-Driven Architecture

A software design where components communicate through **events** instead of direct requests. It enables **asynchronous communication**, making the system loosely coupled and scalable.

**Example:** In an e-commerce application, when an order is placed, an **Order Placed** event is generated. Services like Inventory, Notification, and Payment consume the event independently.

---

## Microservices Architecture

A software architecture where an application is divided into **small independent services**, each responsible for a specific business function. Services usually communicate through APIs.

**Example:** In an e-commerce application, separate services handle Users, Orders, Payments, Inventory, and Notifications.

---

## Interview Tip

- **Microservices** tell you **how the application is divided**.
- **Event-Driven Architecture** tells you **how components communicate**.

> **They are not competitors.** A Microservices application can use Event-Driven Architecture (Kafka, RabbitMQ, etc.) for communication between services.