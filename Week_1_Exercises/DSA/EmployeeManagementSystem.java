class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Getters for employee details
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int size) {
        employees = new Employee[size];
        count = 0;
    }

    // Add an employee
    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
            System.out.println("Employee added: " + employee.getName());
        } else {
            System.out.println("Employee array is full. Cannot add more employees.");
        }
    }

    // Search for an employee by ID
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null; // Employee not found
    }

    // Traverse and display all employees
    public void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println("ID: " + employees[i].getEmployeeId() + 
                               ", Name: " + employees[i].getName() + 
                               ", Position: " + employees[i].getPosition() + 
                               ", Salary: $" + employees[i].getSalary());
        }
    }

    // Delete an employee by ID
    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                employees[i] = employees[count - 1]; // Replace with the last employee
                employees[count - 1] = null; // Clear the last employee
                count--;
                System.out.println("Employee deleted: " + employeeId);
                return true;
            }
        }
        System.out.println("Employee not found: " + employeeId);
        return false;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);
        
        ems.addEmployee(new Employee(1, "Alice", "Manager", 70000));
        ems.addEmployee(new Employee(2, "Bob", "Developer", 60000));
        ems.addEmployee(new Employee(3, "Charlie", "Designer", 50000));

        System.out.println("\nAll Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearching for Employee ID 2:");
        Employee foundEmployee = ems.searchEmployee(2);
        if (foundEmployee != null) {
            System.out.println("Found: " + foundEmployee.getName());
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting Employee ID 2:");
        ems.deleteEmployee(2);

        System.out.println("\nAll Employees after deletion:");
        ems.traverseEmployees();
    }
}
