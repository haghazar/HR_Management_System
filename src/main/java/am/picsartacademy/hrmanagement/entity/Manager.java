package am.picsartacademy.hrmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "employee_id")
public class Manager extends Employee {
    private String managedDepartment;

    @Enumerated(EnumType.STRING)
    private ManagementLevel managementLevel;

    // Default constructor
    public Manager() {
        super();
    }

    // Constructor with parameters
    public Manager(String name, String email, String phoneNumber, Date hireDate, String jobTitle, String managedDepartment, ManagementLevel managementLevel) {
        super(name, email, phoneNumber, hireDate, jobTitle);
        this.managedDepartment = managedDepartment;
        this.managementLevel = managementLevel;
    }

    // Getters
    public String getManagedDepartment() {
        return managedDepartment;
    }

    public ManagementLevel getManagementLevel() {
        return managementLevel;
    }

    // Setters
    public void setManagedDepartment(String managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public void setManagementLevel(ManagementLevel managementLevel) {
        this.managementLevel = managementLevel;
    }

    public enum ManagementLevel {
        TOP_LEVEL, MID_LEVEL, FIRST_LINE
    }
}
