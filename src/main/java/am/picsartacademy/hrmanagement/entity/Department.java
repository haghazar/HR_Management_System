package am.picsartacademy.hrmanagement.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false)
    private String departmentName;

    private String location;

    @OneToOne(mappedBy = "managedDepartment")
    private Manager departmentHead;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees = new HashSet<>();

    // Default constructor
    public Department() {
    }

    // Constructor with parameters
    public Department(String departmentName, String location) {
        this.departmentName = departmentName;
        this.location = location;
    }

    // Getters
    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getLocation() {
        return location;
    }

    public Manager getDepartmentHead() {
        return departmentHead;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    // Setters
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDepartmentHead(Manager departmentHead) {
        this.departmentHead = departmentHead;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    // Helper methods for bidirectional relationships
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
}