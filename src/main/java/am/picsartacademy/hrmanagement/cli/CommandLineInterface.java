package am.picsartacademy.hrmanagement.cli;

import am.picsartacademy.hrmanagement.entity.Department;
import am.picsartacademy.hrmanagement.entity.Employee;
import am.picsartacademy.hrmanagement.service.DepartmentService;
import am.picsartacademy.hrmanagement.service.EmployeeService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private final Scanner scanner;
    private final DepartmentService departmentService; // Assuming this service exists
    private final EmployeeService employeeService;     // Assuming this service exists

    public CommandLineInterface(DepartmentService departmentService, EmployeeService employeeService) {
        this.scanner = new Scanner(System.in);
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    public void start() {
        while (true) {
            try {
                System.out.println("\n--- HR Management System ---");
                System.out.println("1. Manage Departments");
                System.out.println("2. Manage Employees");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        manageDepartments();
                        break;
                    case 2:
                        manageEmployees();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Consume any leftover input
            }
        }
    }

    private void manageDepartments() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Department Management ---");
            System.out.println("1. Add New Department");
            System.out.println("2. Update Existing Department");
            System.out.println("3. List All Departments");
            System.out.println("4. Delete a Department");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        addDepartment();
                        break;
                    case 2:
                        updateDepartment();
                        break;
                    case 3:
                        listDepartments();
                        break;
                    case 4:
                        deleteDepartment();
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.nextLine(); // Consume the incorrect input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Consume any leftover input
            }
        }
    }

    private void addDepartment() {
        System.out.print("Enter Department Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department Location: ");
        String location = scanner.nextLine();

        Department department = new Department();
        department.setDepartmentName(name);
        department.setLocation(location);

        departmentService.saveDepartment(department); // Assuming this method exists in your service
        System.out.println("Department added successfully.");
    }

    private void updateDepartment() {
        System.out.print("Enter Department ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Department department = departmentService.findDepartmentById(id); // Assuming this method exists
        if (department == null) {
            System.out.println("Department not found.");
            return;
        }

        System.out.print("Enter new Department Name: ");
        String name = scanner.nextLine();
        department.setDepartmentName(name);

        System.out.print("Enter new Department Location: ");
        String location = scanner.nextLine();
        department.setLocation(location);

        departmentService.updateDepartment(department); // Assuming this method exists in your service
        System.out.println("Department updated successfully.");
    }

    private void listDepartments() {
        List<Department> departments = departmentService.findAllDepartments(); // Assuming this method exists
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            departments.forEach(dept -> System.out.println("ID: " + dept.getDepartmentId() + ", Name: " + dept.getDepartmentName() + ", Location: " + dept.getLocation()));
        }
    }

    private void deleteDepartment() {
        System.out.print("Enter Department ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Department department = departmentService.findDepartmentById(id); // Assuming this method exists
        if (department == null) {
            System.out.println("Department not found.");
            return;
        }

        departmentService.deleteDepartment(id); // Assuming this method exists in your service
        System.out.println("Department deleted successfully.");
    }

    private void manageEmployees() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add New Employee");
            System.out.println("2. Update Existing Employee");
            System.out.println("3. List All Employees");
            System.out.println("4. Delete an Employee");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        updateEmployee();
                        break;
                    case 3:
                        listEmployees();
                        break;
                    case 4:
                        deleteEmployee();
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.nextLine(); // Consume the incorrect input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Consume any leftover input
            }
        }
    }

    private void addEmployee() {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Email: ");
        String email = scanner.nextLine();

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);

        employeeService.saveEmployee(employee); // Assuming this method exists in your service
        System.out.println("Employee added successfully.");
    }

    private void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.findEmployeeById(id); // Assuming this method exists
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new Employee Name: ");
        String name = scanner.nextLine();
        employee.setName(name);

        System.out.print("Enter new Employee Email: ");
        String email = scanner.nextLine();
        employee.setEmail(email);

        employeeService.updateEmployee(employee); // Assuming this method exists in your service
        System.out.println("Employee updated successfully.");
    }

    private void listEmployees() {
        List<Employee> employees = employeeService.findAllEmployees(); // Assuming this method exists
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(emp -> System.out.println("ID: " + emp.getEmployeeId() + ", Name: " + emp.getName() + ", Email: " + emp.getEmail()));
        }
    }

    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeService.findEmployeeById(id); // Assuming this method exists
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        employeeService.deleteEmployee(id); // Assuming this method exists in your service
        System.out.println("Employee deleted successfully.");
    }

    // Methods to add, update, list, delete departments
    // Methods to add, update, list, delete employees

}