package am.picsartacademy.hrmanagement.service;

import am.picsartacademy.hrmanagement.dao.DepartmentDao;
import am.picsartacademy.hrmanagement.entity.Department;

import java.util.List;

public class DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentService() {
        this.departmentDao = new DepartmentDao();
    }

    public void saveDepartment(Department department) {
        if (validateDepartment(department)) {
            departmentDao.saveDepartment(department);
            System.out.println("Department saved successfully.");
        } else {
            System.out.println("Department validation failed.");
        }
    }

    public void updateDepartment(Department department) {
        if (validateDepartment(department)) {
            departmentDao.updateDepartment(department);
            System.out.println("Department updated successfully.");
        } else {
            System.out.println("Department validation failed.");
        }
    }

    public Department findDepartmentById(Long id) {
        return departmentDao.findDepartmentById(id);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentDao.findDepartmentById(id);
        if (department != null) {
            departmentDao.deleteDepartment(department);
            System.out.println("Department deleted successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }

    public List<Department> findAllDepartments() {
        return departmentDao.findAllDepartments();
    }

    // Validation logic for Department
    private boolean validateDepartment(Department department) {

        if (department.getDepartmentName() == null || department.getDepartmentName().trim().isEmpty()) {
            System.out.println("Department name is required.");
            return false;
        }
        return true;
    }
}