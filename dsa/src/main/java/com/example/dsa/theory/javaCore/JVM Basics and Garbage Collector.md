JVM Basics and Garbage Collection in Java
1. What is JVM?
   JVM (Java Virtual Machine) executes Java bytecode and makes Java platform-independent.
   Execution flow: .java → Compiler (javac) → .class bytecode → JVM → Execution
2. JVM vs JRE vs JDK
- JVM: Executes Java bytecode.
- JRE: Provides the JVM and libraries needed to run Java applications.
- JDK: Provides tools needed to develop Java applications, including the compiler.
3. JVM Memory Areas
- Heap: Stores objects and arrays; shared across threads.
- Stack: Stores method calls, local variables, and references; each thread has its own stack.
- Metaspace: Stores class metadata in native memory.
- PC Register: Tracks the current JVM instruction for each thread.
- Native Method Stack: Supports native method execution.
  Example:
  Employee emp = new Employee();


The emp reference is a local variable in the stack frame, while the Employee object is generally allocated on the heap.
4. What is Garbage Collection (GC)?
   Garbage Collection automatically reclaims heap memory occupied by objects that are no longer reachable by the application.
   Employee emp = new Employee();
   emp = null;


If no other reachable reference points to the object, it becomes eligible for garbage collection. It is not necessarily removed immediately.
5. How Garbage Collection Works
1. Find reachable objects: The JVM starts from GC Roots, such as active thread references.
2. Identify unreachable objects: Objects that cannot be reached from GC Roots are eligible for collection.
3. Reclaim memory: The garbage collector frees memory occupied by eligible objects.
6. Generational GC
- Young Generation: Contains newly allocated objects. Many become unreachable quickly.
- Old Generation: Contains objects that survive long enough to be promoted.
  Common collectors include Serial GC, Parallel GC, G1 GC, and ZGC. They differ in throughput, resource usage, and pause-time goals.
7. Important Points
- System.gc() requests garbage collection but does not guarantee it.
- Java memory leaks can occur when unused objects remain reachable, such as through a static collection.
- Setting a reference to null does not immediately delete an object.
- OutOfMemoryError can occur when the JVM cannot allocate required memory.
  Interview Answer
  "The JVM executes Java bytecode and manages runtime memory. Its main memory areas include the heap for objects, the stack for method execution, and Metaspace for class metadata. Garbage collection automatically reclaims heap memory occupied by unreachable objects. However, the JVM decides when garbage collection runs, so objects are not removed immediately when their references are set to null."