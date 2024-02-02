package am.picsartacademy.hrmanagement.service;

import am.picsartacademy.hrmanagement.dao.EmployeeDao;
import am.picsartacademy.hrmanagement.entity.Employee;

import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDao();
    }

    public void saveEmployee(Employee employee) {
        if (validateEmployee(employee)) {
            employeeDao.saveEmployee(employee);
            System.out.println("Employee saved successfully.");
        } else {
            System.out.println("Employee validation failed.");
        }
    }

    public void updateEmployee(Employee employee) {
        if (validateEmployee(employee)) {
            employeeDao.updateEmployee(employee);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee validation failed.");
        }
    }

    public Employee findEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeDao.findEmployeeById(id);
        if (employee != null) {
            employeeDao.deleteEmployee(employee);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    // Validation logic for Employee
    private boolean validateEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            System.out.println("Employee name is required.");
            return false;
        }
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            System.out.println("Employee email is required.");
            return false;
        }
        return true;
    }
}