What is oops
Its a programming pradigm which deals with the objects that cotain data(fields) and behaviour(methods).

what are 4 constructor in java
Default Constructor: A constructor that does not accept any parameters and initializes an object with default values.
Parameterized Constructor: A constructor that accepts parameters to initialize an object with specific values.
Copy Constructor: A user-defined constructor that creates a new object by copying the data of another object of the same class.
Private Constructor: A constructor declared with the private access modifier that prevents object creation from outside the class.

What are the 4 pillars of OOPs? Explain each with real example.					

What is encapsulation and How did you implement it in your project

Encapsulation means bundling data and behavior into a class, keeping fields private, and only allowing changes through validated methods — so an object can never be pushed into an invalid state from outside. In my RCUServiceImpl, before updating rcuStatus, I check whether the case is assigned, and whether it's already APPROVED or REJECTED — if so, I block the update. Only a PENDING case can move to APPROVED or REJECTED. That's the invariant encapsulation protects here.
, since the entity uses Lombok's @Data, the setters are technically public, so the actual protection is a service-layer convention rather than a hard class-level guarantee — which is a common trade-off when you're optimizing for less boilerplate."

