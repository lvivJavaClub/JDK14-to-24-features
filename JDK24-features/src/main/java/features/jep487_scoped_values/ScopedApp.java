package features.jep487_scoped_values;

public class ScopedApp {

  private static final ScopedValue<String> USER_ID = ScopedValue.newInstance();
  private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

  public static void main(String[] args) {

    Thread.startVirtualThread(() -> ScopedValue.where(USER_ID, "U_ID").run(() -> processRequest()));
    ScopedValue
        .where(USER_ID, "U_ID1")
        .where(REQUEST_ID, "").run(() -> processRequest());
  }

  private static void processRequest() {
    System.out.println("Processing request for user: " + USER_ID.get());
    ScopedValue.where(USER_ID, "U_ID nested").run(() -> processRequest2());

  }

  private static void processRequest2() {
    System.out.println("Processing request for user: " + USER_ID.get());
  }
}
