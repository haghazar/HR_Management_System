package am.picsartacademy.hrmanagement;

import am.picsartacademy.hrmanagement.cli.CommandLineInterface;
import am.picsartacademy.hrmanagement.service.DepartmentService;
import am.picsartacademy.hrmanagement.service.EmployeeService;

import static am.picsartacademy.hrmanagement.DatabaseInitializer.initializeDatabase;

public class Main {
    public static void main(String[] args) {
        initializeDatabase();
        DepartmentService departmentService = new DepartmentService();
        EmployeeService employeeService = new EmployeeService();
        CommandLineInterface cli = new CommandLineInterface(departmentService, employeeService);
        cli.start();
    }
}
