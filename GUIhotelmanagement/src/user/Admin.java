package user;

import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements IAuthentication {
    private String adminUserName;
    private String adminPassword;
    static List<User> Users = new ArrayList<>();

    public Admin(String adminUserName, String adminPassword, String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        if (adminUserName == null || adminUserName.trim().isEmpty()) throw new IllegalArgumentException("Admin username cannot be null or empty.");
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
        this.isAdmin = true;
    }

    // Getters and Setters
    public String getAdminUserName() { return adminUserName; }
    public void setAdminUserName(String adminUserName) { if (adminUserName != null && !adminUserName.trim().isEmpty()) this.adminUserName = adminUserName; }
    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }
    public List<User> getUsers() { return new ArrayList<>(Users); }

    // IAuthentication Implementation
    @Override
    public boolean login() {
        try {
            if (adminUserName.equals("admin") && adminPassword.equals("admin123")) { // Example hardcoded check
                System.out.println("Admin login successful!");
                return true;
            } else {
                System.out.println("Invalid admin username or password");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    // Employee Management (Database)
    public void addEmployee(Employee employee) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String userSql = "INSERT INTO users (first_name, last_name, phone_number, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement userStmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, employee.getFirstName());
            userStmt.setString(2, employee.getLastName());
            userStmt.setString(3, employee.getPhoneNumber());
            userStmt.setString(4, employee.getEmail());
            userStmt.setString(5, employee.getPassword());
            userStmt.executeUpdate();

            ResultSet rs = userStmt.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) userId = rs.getInt(1);

            String employeeSql = "INSERT INTO employees (id, employee_id, employee_role, salary, address, date_of_birth, hire_date, work_status, work_schedule) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement employeeStmt = conn.prepareStatement(employeeSql);
            employeeStmt.setInt(1, userId);
            employeeStmt.setInt(2, employee.getEmployeeID());
            employeeStmt.setString(3, employee.employeeRole);
            employeeStmt.setDouble(4, employee.salary);
            employeeStmt.setString(5, employee.address);
            employeeStmt.setString(6, employee.dateOfBirth);
            employeeStmt.setString(7, employee.hireDate);
            employeeStmt.setString(8, employee.workStatus);
            employeeStmt.setString(9, employee.workSchedule);
            employeeStmt.executeUpdate();

            Users.add(employee);
            System.out.println("Employee " + employee.getFirstName() + " added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public void removeEmployee(int employeeId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String deleteEmployeeSql = "DELETE FROM employees WHERE employee_id = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteEmployeeSql);
            deleteStmt.setInt(1, employeeId);
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                Employee employee = findEmployeeById(employeeId);
                if (employee != null) Users.remove(employee);
                System.out.println("Employee " + employeeId + " removed successfully!");
            } else {
                System.out.println("Employee " + employeeId + " not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error removing employee: " + e.getMessage());
        }
    }

    public Employee findEmployeeById(int employeeId) {
        for (User user : Users) {
            if (user instanceof Employee && ((Employee) user).getEmployeeID() == employeeId) return (Employee) user;
        }
        return null;
    }

    public void viewEmployees() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.id, u.first_name, u.last_name, u.phone_number, u.email, e.employee_id, e.employee_role, e.salary, e.address, e.date_of_birth, e.hire_date, e.work_status, e.work_schedule " +
                         "FROM users u JOIN employees e ON u.id = e.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) System.out.println("No employees found.");
            else {
                System.out.println("========Employees=======");
                while (rs.next()) {
                    System.out.println("Employee ID: " + rs.getInt("employee_id"));
                    System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                    System.out.println("Phone: " + rs.getString("phone_number"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Role: " + rs.getString("employee_role"));
                    System.out.println("Salary: " + rs.getDouble("salary"));
                    System.out.println("Address: " + rs.getString("address"));
                    System.out.println("Date of Birth: " + rs.getString("date_of_birth"));
                    System.out.println("Hire Date: " + rs.getString("hire_date"));
                    System.out.println("Work Status: " + rs.getString("work_status"));
                    System.out.println("Work Schedule: " + rs.getString("work_schedule"));
                    System.out.println("-----------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employees: " + e.getMessage());
        }
    }

    // Additional Admin Functions
    public void viewAllUsers() {
        if (Users.isEmpty()) System.out.println("No users registered.");
        else {
            System.out.println("========All Users=======");
            for (User user : Users) System.out.println(user);
        }
    }

    @Override
    public String toString() {
        return "Admin: " + getFirstName() + " " + getLastName() + " (" + adminUserName + ")";
    }
}