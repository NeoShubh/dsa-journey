# Event-Driven Architecture

It's a software design approach where system components communicate with each other when some user action or system status changes.

The components here are **loosely coupled**, so when an event occurs, each component handles it independently.

### Example

In an e-commerce platform, when a user places an order, an event is triggered due to the user action (Order Placed) or any status change.

Services like:
- Inventory Management
- Notification Service
- Payment Service

can't constantly check the system. Instead, they respond to the event when it occurs.

---

## Event Source

It's the component through which an event is generated.

---

## Event

It's a change in the system.

---

## Event Broker

It's a central hub for event communication.

---

## Publisher

The Publisher is responsible for emitting events to the event broker.

---

## Subscriber

A Subscriber registers for specific types of events so that it can listen to and process them.

---

## Real-Life Example

In a retail system, users can place an order or return a product through:
- Retail Website
- Mobile App
- Point of Sale (POS)

The **Event Broker** takes care of routing the events, and subscribers like:
- Warehouse Management
- Database Services
- Finance
- Public Relations

listen to the appropriate events and process them independently.