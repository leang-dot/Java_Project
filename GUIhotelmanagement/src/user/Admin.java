package user;

import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements IAuthentication {
    private String adminUserName;
    private String adminPassword;
    static List<User> Users = new ArrayList<>();

    public Admin(String adminUserName, String adminPassword, String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
        this.isAdmin = true;
    }

    @Override
    public boolean login() {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;
        try {
            System.out.print("Enter the admin username: ");
            String username = sc.nextLine();
            System.out.print("Enter the admin password: ");
            String password = sc.nextLine();
            if (username.equals(this.adminUserName) && password.equals(this.adminPassword)) {
                loggedIn = true;
                while (loggedIn) {
                    System.out.println("========Admin Menu=======");
                    System.out.println("1. Add Employee");
                    System.out.println("2. Remove Employee");
                    System.out.println("3. View All Employees");
                    System.out.println("4. Exit");
                    System.out.println("Please choose an option (1-4): ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1:
                            addEmployeeFromInput(sc);
                            break;
                        case 2:
                            removeEmployeeFromInput(sc);
                            break;
                        case 3:
                            viewEmployees();
                            break;
                        case 4:
                            System.out.println("Exiting Admin Menu");
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                }
            } else {
                System.out.println("Invalid username or password");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
        return loggedIn;
    }

    // Add Employee to Database
    private void addEmployeeFromInput(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Enter employee details:");
            System.out.print("First Name: ");
            String firstName = sc.nextLine();
            System.out.print("Last Name: ");
            String lastName = sc.nextLine();
            System.out.print("Phone Number: ");
            String phoneNumber = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            System.out.print("Employee ID: ");
            int employeeId = sc.nextInt();
            sc.nextLine();
            System.out.print("Role: ");
            String role = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = sc.nextLine();
            System.out.print("Hire Date (YYYY-MM-DD): ");
            String hireDate = sc.nextLine();
            System.out.print("Work Status: ");
            String workStatus = sc.nextLine();
            System.out.print("Work Schedule: ");
            String workSchedule = sc.nextLine();

            String userSql = "INSERT INTO users (first_name, last_name, phone_number, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement userStmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, firstName);
            userStmt.setString(2, lastName);
            userStmt.setString(3, phoneNumber);
            userStmt.setString(4, email);
            userStmt.setString(5, password);
            userStmt.executeUpdate();

            ResultSet rs = userStmt.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            String employeeSql = "INSERT INTO employees (id, employee_id, employee_role, salary, address, date_of_birth, hire_date, work_status, work_schedule) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement employeeStmt = conn.prepareStatement(employeeSql);
            employeeStmt.setInt(1, userId);
            employeeStmt.setInt(2, employeeId);
            employeeStmt.setString(3, role);
            employeeStmt.setDouble(4, salary);
            employeeStmt.setString(5, address);
            employeeStmt.setString(6, dob);
            employeeStmt.setString(7, hireDate);
            employeeStmt.setString(8, workStatus);
            employeeStmt.setString(9, workSchedule);
            employeeStmt.executeUpdate();

            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // Remove Employee from Database
    private void removeEmployeeFromInput(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.print("Enter Employee ID to remove: ");
            int employeeId = sc.nextInt();
            sc.nextLine();

            String deleteEmployeeSql = "DELETE FROM employees WHERE employee_id = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteEmployeeSql);
            deleteStmt.setInt(1, employeeId);
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee removed successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error removing employee: " + e.getMessage());
        }
    }

    // View All Employees from Database
    public void viewEmployees() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.id, u.first_name, u.last_name, u.phone_number, u.email, e.employee_id, e.employee_role, e.salary, e.address, e.date_of_birth, e.hire_date, e.work_status, e.work_schedule " +
                        "FROM users u JOIN employees e ON u.id = e.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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
        } catch (SQLException e) {
            System.out.println("Error viewing employees: " + e.getMessage());
        }
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}