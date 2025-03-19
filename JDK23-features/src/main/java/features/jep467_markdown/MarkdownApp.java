package features.jep467_markdown;

import java.util.function.Consumer;

public class MarkdownApp {

  public static void main(String[] args) {

  }

  /**
   * Calculates the area of a circle given the radius.
   * <p>
   * <strong>Note:</strong> This method uses the formula &pi; &times; r<sup>2</sup>.
   * <p>
   * <table border="1">
   *   <caption>Sample Radii and Areas</caption>
   *   <tr><th>Radius</th><th>Area</th></tr>
   *   <tr><td>1.0</td><td>3.1416</td></tr>
   *   <tr><td>2.0</td><td>12.5664</td></tr>
   *   <tr><td>3.0</td><td>28.2743</td></tr>
   * </table>
   *
   * Example usage:
   * <pre>{@code
   * double area = calculateCircleArea(5.0);
   * System.out.println(area);  // Output: 78.53981633974483
   * }</pre>
   *
   * @param radius the radius of the circle, must be positive
   * @return the calculated area of the circle
   * @throws IllegalArgumentException if the radius is negative
   */
  public double calculateCircleArea1(double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius must be positive");
    }
    return Math.PI * radius * radius;
  }

  /// Calculates the area of a circle given the radius.
  ///
  /// **Note:** This method uses the formula π × r².
  ///
  /// | Radius | Area     |
  /// |--------|----------|
  /// | 1.0    | 3.1416   |
  /// | 2.0    | 12.5664  |
  /// | 3.0    | 28.2743  |
  ///
  /// Example usage:
  ///
  /// ```java
   /// double area = calculateCircleArea(5.0);
   /// System.out.println(area);  // Output: 78.53981633974483
   /// ```
  ///
  /// @param radius the radius of the circle, must be positive
   /// @return the calculated area of the circle
   /// @throws IllegalArgumentException if the radius is negative
  public double calculateCircleArea2(double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius must be positive");
    }
    return Math.PI * radius * radius;
  }
}
