
# Good morning, JavaClub 👋

# JDK24 Features ☕️

## Prepare ⚙️

To begin exploring JDK 24, install it using `sdkman`:

```bash
sdk install java 24-oracle
sdk use java 24-oracle
```

For a complete list of all new features, visit the [OpenJDK JDK 24 Project Page](https://openjdk.java.net/projects/jdk/24/).

---

# `😎 Finilized Features:`

## 📍 **Class-File API (JEP 484)**
- #### Introduces a standard API for parsing, generating, and transforming Java class files.
- #### Replaces third-party libraries like ASM, BCEL, and Javassist in JDK tooling.
- #### Ensures compatibility with future JDK releases (evolves with class file format).
- #### More concise and expressive API compared to traditional visitor-based approaches. 
- #### Immutable class-file entities (fields, methods, attributes, bytecode) and Lazy parsing – processes only necessary parts of the class file.. 

## 📍 **Stream Gatherers API (JEP 485)**

- ####  A custom intermediate operation that allows fine-grained transformations in stream pipelines.
- ####  Can perform one-to-one, one-to-many, many-to-one, or many-to-many transformations.

`Stream<T>.gather(Gatherer<T, A, R>)`

A Gatherer has:
- **Initializer** → Creates state storage.
- **Integrator** → Processes each element, optionally emitting new elements.
- **Combiner** (optional) → Merges results for parallel streams.
- **Finisher** → Performs final cleanup and output.

Useful libraries that provide `Gatherer` implementations for the Stream API:

- #### https://github.com/jhspetersson/packrat
- #### https://github.com/tginsberg/gatherers4j?tab=readme-ov-file
- #### https://github.com/pivovarit/more-gatherers

# `😉 Further features iterations:`

## 📍 **Scoped Values (Fourth Preview) (JEP 487)**

- ####  Better alternative to `ThreadLocal`, designed for virtual threads and structured concurrency
- #### Immutable, structured, and memory-efficient (no leaks!)

## 📍 **Structured Concurrency (Fourth Preview) (JEP 499)**

Structured Concurrency treats related tasks as a unit of work, meaning:

- #### Tasks start together, complete together, and fail together.
- #### Errors & cancellations are handled at the parent level.
- #### Eliminates thread leaks (no orphaned tasks running indefinitely).
- #### Improves observability (easy to see task relationships in logs & thread dumps).

## 📍 **Primitive Types in Patterns, instanceof, and switch (Second Preview) (JEP 488)**

## 📍 **Vector API (Ninth Incubator) (JEP 489)**

#### The Vector API will keep incubating until necessary features of Project Valhalla become available as preview features. When that happens, the Vector API will be adapted to use them, and it will be promoted from incubation to preview.

## 📍 **Synchronize Virtual Threads without Pinning (JEP 491)**

- #### Virtual threads no longer pin platform (OS) threads in synchronized methods.
- #### Virtual threads now unmount properly, allowing multiple virtual threads to run in parallel.
- #### Makes virtual threads scale properly, fixing synchronization issues

## 📍 **Flexible Constructor Bodies (Third Preview) (JEP 492)**

- #### Allows argument validation before calling super()
- #### Reduces reliance on helper methods or intermediate constructors

## 📍 **Simple Source Files and Instance Main Methods (Fourth Preview) (JEP 495)**
## 📍 **Module Import Declarations (Second Preview) (JEP 494)**

# `🆕 What's new:`

## 📍 **Generational Shenandoah (Experimental) (JEP 404)**

The Shenandoah garbage collector is an ultra-low pause time garbage collector. It has been available for production use since Java 15 and has been designed to dramatically reduce garbage collection pause times, regardless of the heap size that is used.

JEP 404 makes Shenandoah generational, meaning:
- #### Young Generation: Short-lived objects (frequent minor collections).
- ####  Old Generation: Long-lived objects (infrequent major collections).
- ####  Less frequent full-heap scans → Lower GC overhead.

``java ... -XX:+UseShenandoahGC -XX:+UnlockExperimentalVMOptions -XX:ShenandoahGCMode=generational``

## 📍 **Compact Object Headers (Experimental) (JEP 450)**

- #### Goal is to the size of Java object headers on 64-bit architectures (from 128 bits to 64 bits or less)

Can be enabled as follows:

``java -XX:+UnlockExperimentalVMOptions -XX:+UseCompactObjectHeaders MyApp
``

The plan is to enable it by default in future releases.

## 📍 **Late Barrier Expansion for G1 (JEP 475)*

The G1 (Garbage-First) GC relies on write barriers to track memory changes.

`Before JEP 475:` Write barriers were expanded early in the JIT compilation phase, increasing compiled code size.

`After JEP 475:` The JIT delays expanding write barriers until late in compilation, reducing overhead. 

- #### Smaller compiled code size → Less JIT compilation time.
- #### Faster execution → Better CPU cache efficiency.
- #### Lower memory usage → Smaller compiled code footprint.

## 📍 **Ahead-of-Time Class Loading & Linking (JEP 483)**

- #### With AOT class loading & linking, the JVM can pre-load and pre-link classes before execution, making applications start faster.

1. Run the application once in "record" mode to capture class loading information: \
 ``java -XX:AOTMode=record -XX:AOTConfiguration=app.aotconf -cp app.jar com.example.App ...
``

2. Create the AOT cache from the recorded configuration:\
   ``java -XX:AOTMode=create -XX:AOTConfiguration=app.aotconf -XX:AOTCache=app.aot -cp app.jar
``

3. Run the application using the AOT cache (faster startup):\
 ``java -XX:AOTCache=app.aot -cp app.jar com.example.App ...
``

## 📍 **Linking Run-Time Images without JMODs (JEP 493)**

- #### The option `--enable-linkable-runtime` builds a JDK whose jlink tool can create run-time images without using JMOD files.
- #### Reduces JDK size (~25%)
- #### Works seamlessly with modular JARs & custom runtime images.

**`Not enabled by default`**\
The default build configuration will remain as it is today: The resulting JDK will contain JMOD files and its jlink tool will not be able to operate without them. Whether the JDK build that you get from your preferred vendor contains this feature is up to that vendor.

## 📍 **Key Derivation Function API (Preview) (JEP 478)**
## 📍 **Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism (JEP 496)**
## 📍 **Quantum-Resistant Module-Lattice-Based Digital Signature Algorithm (JEP 497)**

### These JEPs future-proof Java’s security before quantum computers become a real-world threat.

# `🚫🙅‍♂️ Removals, Deprecations, Restrictions:`

## 📍 **Remove the Windows 32-bit x86 Port (JEP 479)**
## 📍 **Deprecate the 32-bit x86 Port for Removal (JEP 501)**
## 📍 **Permanently Disable the Security Manager (JEP 486)**

- #### Security Manager API was deprecated in JDK 17, and JVM was made to issue warnings in case Security Manager was enabled.
- #### The API will be removed completely in the future releases.

## 📍 **ZGC: Remove the Non-Generational Mode (JEP 490)**

#### The Z Garbage Collector (ZGC) is a scalable, low-latency garbage collector.

### `java -XX:+UseZGC`
### `java -XX:+UseZGC -XX:-ZGenerational`

#### The ZGenerational flag is now deprecated, and the warning message will be displayed if it's used

## 📍 **Prepare to Restrict the Use of JNI (JEP 472)**

- #### Issue warnings about uses of the Java Native Interface (JNI) and adjust the Foreign Function & Memory (FFM) API to issue warnings in a consistent manner.
- #### JNI Will Require Explicit Approval in Future JDK Versions
- #### Developers must enable JNI manually using:
  `java --enable-native-access=ALL-UNNAMED`\
  or for specific modules:\
  `java --enable-native-access=my.module`

## 📍 **Warn upon Use of Memory-Access Methods in sun.misc.Unsafe (JEP 498)**

- #### Methods in `sun.misc.Unsafe` were deprecated for removal and now, a runtime warning is issued when memory-access methods are used
- #### Standard replacements are now available:
  - `VarHandle` API (JDK 9) → For safe on-heap memory access.
  - `MemorySegment` API (JDK 22) → For safe off-heap memory access.
- #### How to Detect & Fix sun.misc.Unsafe Usage
  - `java --sun-misc-unsafe-memory-access=warn`

# Don't wait, try out `JDK 24` now! 🎉 ☕️

# 🇺🇦🇺🇦🇺🇦 СЛАВА УКРАЇНІ 🇺🇦🇺🇦🇺🇦 СЛАВА ЗСУ 🇺🇦🇺🇦🇺🇦
