package features.jep491_sync_virtual_threads_without_pin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//sdk use java 23-oracle && java -Djdk.tracePinnedThreads=full src/main/java/features/jep491_sync_virtual_threads_without_pin/VirtualApp.java
//sdk use java 24-oracle && java -Djdk.tracePinnedThreads=full src/main/java/features/jep491_sync_virtual_threads_without_pin/VirtualApp.java
public class VirtualApp {

  private static final Object LOCK = new Object();

  public static void main(String[] args) throws InterruptedException {

    ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    long start = System.currentTimeMillis();

    for (int i = 0; i < 10; i++) {
      executor.submit(() -> {
        synchronized (LOCK) { // 🔒 JEP 491 allows unmounting here in JDK 24!
          try {
            System.out.println(Thread.currentThread());
            Thread.sleep(1000); // Simulated blocking work
            System.out.println(Thread.currentThread());
            System.out.println();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      });
    }

    executor.shutdown();
    executor.awaitTermination(30, TimeUnit.SECONDS);
    System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");
  }
}
