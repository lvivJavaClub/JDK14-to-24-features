package features.jep_476_module_import;

import module java.base;
import module java.sql;

import java.util.Date;


public class ModuleApp {
  public static void main(String[] args) {
    var now = new Date();


    record Employee(String name, String department, int salary) {}
    List<Employee> employees = List.of(
        new Employee("Alice", "HR", 70000),
        new Employee("Bob", "IT", 90000),
        new Employee("Charlie", "HR", 60000),
        new Employee("David", "IT", 80000)
    );

    Function<Employee, String> reportMapper = e -> e.name() + ": $" + e.salary();

    Map<String, List<String>> employeesReportByDept = employees.stream()
        .sorted(Comparator.comparingInt(Employee::salary).reversed())
        .collect(Collectors.groupingBy(
            Employee::department,
            Collectors.mapping(reportMapper, Collectors.toList()))
        );
  }
}

