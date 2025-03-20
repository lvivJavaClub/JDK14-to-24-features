package features.jep492_flexible;

public class FlexibleApp {

  public static void main(String[] args) {
    new DebugLogger(true).log("Hello");
    new DebugLogger(false).log("Hello");
  }

  public static class DebugLogger extends Logger {
    public DebugLogger(boolean verbose) {
      String prefix = verbose ? "[DEBUG-VERBOSE]" : "[DEBUG]";
      super(prefix);  // ✅ Can now compute `super()` arguments inside constructor
    }
  }

  static class Logger {
    protected final String prefix;

    Logger(String prefix) {
      this.prefix = prefix;
    }

    public void log(String message) {
      System.out.println(prefix + " :: " + message);
    }
  }
}
