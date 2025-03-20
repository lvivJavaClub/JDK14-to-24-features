package features.jep488_primitive_patterns;

public class PrimitiveApp {
  public static void main(String[] args) {
    long test = 100000000;
    if (test instanceof byte bt) {
      System.out.println(bt);
    }

    var result = switch (test) {
      case long l when l > 100 -> "";
      case long l -> "";
    };
  }
}
