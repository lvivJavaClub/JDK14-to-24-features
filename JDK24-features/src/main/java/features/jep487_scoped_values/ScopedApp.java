package features.jep487_scoped_values;

public class ScopedApp {

  //private static final ScopedValue<String> USER_ID = ScopedValue.newInstance();
  private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();

  public static void main(String[] args) {

    USER_ID.set("ID_1");  // ❌ Must manually set and remove
    processRequest();
    USER_ID.remove();     // ❌ Forgetting this leads to memory leaks!
  }

  private static void processRequest() {
    System.out.println("Processing request for user: " + USER_ID.get());
  }
}
