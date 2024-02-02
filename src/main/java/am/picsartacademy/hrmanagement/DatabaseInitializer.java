package am.picsartacademy.hrmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/HRManagementDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            // Department table
            statement.execute("CREATE TABLE IF NOT EXISTS department (" +
                    "department_id SERIAL PRIMARY KEY," +
                    "department_name VARCHAR(255) NOT NULL," +
                    "location VARCHAR(255));");

            // Employee table
            statement.execute("CREATE TABLE IF NOT EXISTS employee (" +
                    "employee_id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) UNIQUE NOT NULL," +
                    "phone_number VARCHAR(20)," +
                    "hire_date DATE," +
                    "job_title VARCHAR(255)," +
                    "department_id INTEGER," +
                    "FOREIGN KEY (department_id) REFERENCES department (department_id));");

            // Manager table
            statement.execute("CREATE TABLE IF NOT EXISTS manager (" +
                    "employee_id INTEGER PRIMARY KEY," +
                    "managed_department VARCHAR(255)," +
                    "management_level VARCHAR(50)," +
                    "FOREIGN KEY (employee_id) REFERENCES employee (employee_id));");

            // Project table
            statement.execute("CREATE TABLE IF NOT EXISTS project (" +
                    "project_id SERIAL PRIMARY KEY," +
                    "project_name VARCHAR(255) NOT NULL," +
                    "start_date DATE," +
                    "end_date DATE," +
                    "budget DECIMAL);");

            // Employee_Project join table
            statement.execute("CREATE TABLE IF NOT EXISTS employee_project (" +
                    "employee_id INTEGER," +
                    "project_id INTEGER," +
                    "PRIMARY KEY (employee_id, project_id)," +
                    "FOREIGN KEY (employee_id) REFERENCES employee (employee_id)," +
                    "FOREIGN KEY (project_id) REFERENCES project (project_id));");

            System.out.println("Database tables created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
