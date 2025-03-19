package features.jep484_class_file_api;


public class Employee {
  private String employeeName;
  private double salary;
  private String department;
  private int yearsOfService;

  public static void main(String[] args) {
    var employee = new Employee("Alice Smith", 50000, "Engineering");
    employee.printDetails();
    employee.giveRaise(5000);
    employee.promote("Senior Engineer");
    employee.addYearOfService();
    employee.printDetails();
  }

  public Employee(String name, double salary, String department) {
    this.employeeName = name;
    this.salary = salary;
    this.department = department;
    this.yearsOfService = 0;
  }

  public void giveRaise(double amount) {
    this.salary += amount;
    System.out.printf("%s received a raise of $%.2f%n", employeeName, amount);
  }

  public void promote(String newTitle) {
    System.out.printf("%s in %s has been promoted to %s%n",
        employeeName, department, newTitle);
    department = newTitle;
  }

  public void addYearOfService() {
    yearsOfService++;
    System.out.printf("%s has completed %d year(s) of service%n",
        employeeName, yearsOfService);
  }

  public void printDetails() {
    System.out.printf("""
        Employee Details:
        Name: %s
        Department: %s
        Salary: $%.2f
        Years of Service: %d
        %n""", employeeName, department, salary, yearsOfService);
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public double getSalary() {
    return salary;
  }

  public String getDepartment() {
    return department;
  }

  public int getYearsOfService() {
    return yearsOfService;
  }
}
