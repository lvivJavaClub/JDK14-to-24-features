package features.jep455_primitives_in_patterns;

public class PrimitiveApp {

  public static void main(String[] args) {
    // 1. Pattern Matching for switch Supports Primitive Type Patterns
    int errorCode = 120;
    handleErrorCode(errorCode);

    // 2. Enhanced Support for Primitive Types in Record Patterns
    TransactionRecord transaction1 = new TransactionRecord("TX123", 100.5d, TransactionType.CREDIT);
    processTransaction(transaction1);
    TransactionRecord transaction2 = new TransactionRecord("TX124", 15, TransactionType.DEBIT);
    processTransaction(transaction2);

    // 3. Pattern Matching for instanceof Supports Primitive Types
    double rawSensorData = getSensorData();
    processSensorData(100);

    // 4. Expanded Primitive Support in switch
    double purchaseAmount = 5000.50;
    calculateDiscount(purchaseAmount);

    boolean featureToggle = isFeatureEnabled();
    handleFeatureToggle(featureToggle);
  }

  // Example 1: Pattern Matching for switch Supports Primitive Type Patterns
  public static void handleErrorCode(int errorCode) {
    // After JEP 455
    String message = switch (errorCode) {
      case 404 -> "Resource not found";
      case 500 -> "Internal server error";
      case 200 -> "Operation successful";
      // Using a guarded pattern to handle a range of known error codes
      case int i when i >= 400 && i < 500 -> "Client error with code: " + i;
      case int i when i >= 500 && i < 600 -> "Server error with code: " + i;
      case int i -> "Unknown error code: " + i;
    };
    System.out.println("Error message: " + message);


    // Simulating error handling based on different codes returned by an external system
   /* String message = switch (errorCode) {
      case 404 -> "Resource not found";
      case 500 -> "Internal server error";
      case 200 -> "Operation successful";
      case int unknown -> "Unknown error code: " + unknown; // Handles any unexpected error codes
    };
    System.out.println("Error message: " + message);*/
  }

  // After JEP 455
  public static void processTransaction(TransactionRecord transaction) {
    if (transaction instanceof TransactionRecord(_, byte amount, _)) {
      System.out.println("its double amount");
    } else if (transaction instanceof TransactionRecord(_, int amount, _)) {
      System.out.println("its int amount");
    } else if (transaction instanceof TransactionRecord(var id, double amount, var type)) {
      System.out.println("its double amount " + amount);
    }
  }

  // Example 3: Pattern Matching for instanceof Supports Primitive Types
  public static void processSensorData(double sensorValue) {
    // After JEP 455
    if ((int) sensorValue == sensorValue) {
      int intValue = (int) sensorValue;
      System.out.println("Sensor data is an integer value: " + intValue);
    } else if ((float) sensorValue == sensorValue) {
      float floatValue = (float) sensorValue;
      System.out.println("Sensor data is a float value: " + floatValue);
    } else if (sensorValue >= -128 && sensorValue < 127) {
      byte byteValue = (byte) sensorValue;
      System.out.println("Sensor data is an byte value: " + byteValue);
    } else {
      System.out.println("Sensor data is a double value: " + sensorValue);
    }

   /* // After JEP 455
    switch (sensorValue) {
      case long l -> System.out.println("Sensor data is a float value: " + l);
      case byte byteValue -> System.out.println("Sensor data is a byte value: " + byteValue);
      case int intValue -> System.out.println("Sensor data is an integer value: " + intValue);
      case float floatValue -> System.out.println("Sensor data is a float value: " + floatValue);
      default -> System.out.println("Sensor data is a double value: " + sensorValue);
    }*/
  }

  // Example 4.1: Expanded Primitive Support in switch for double
  public static void calculateDiscount(double amount) {
    // Before JEP 455

    double discount = switch (amount) {
      case 0.0 -> 0.0;
      case double a when a > 0 && a <= 100 -> 5.0; // Small discount
      case double b when b > 100 && b <= 1000 -> 10.0; // Medium discount
      case double c when c > 1000 -> 20.0; // Large discount
      default -> 0.0;
    };
    System.out.println("Discount for purchase amount $" + amount + ": " + discount + "%");
  }

  // Example 4.2: Expanded Primitive Support in switch for boolean
  public static void handleFeatureToggle(boolean featureEnabled) {

    switch (featureEnabled) {
      case true -> System.out.println("Feature is enabled.");
      case false -> {
        System.out.println("Feature is disabled.");
      }
    }

    /*// Simulating a feature toggle switch
    switch (featureEnabled) {
      case true -> System.out.println("Feature is enabled.");
      case false -> System.out.println("Feature is disabled.");
    }*/
  }

  // Mock methods to simulate external data or states
  public static int getErrorCodeFromExternalSystem() {
    return 500; // Simulating an error code returned by an external system
  }

  public static double getSensorData() {
    return 42.0; // Simulating sensor data
  }

  public static boolean isFeatureEnabled() {
    return true; // Simulating a feature toggle state
  }

  public record TransactionRecord(String id, double amount, TransactionType type) {}

  enum TransactionType {DEBIT, CREDIT}
}

