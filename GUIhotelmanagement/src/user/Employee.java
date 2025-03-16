package user;

import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Employee extends User implements IAuthentication {
    private static int employeeCount = 0;
    protected int employeeID;
    protected String employeeRole;
    protected Double salary;
    protected String address;
    protected String dateOfBirth;
    protected String hireDate;
    protected String workStatus;
    protected String workSchedule;

    public Employee(int employeeID, String employeeRole, Double salary, String address, String dateOfBirth,
            String hireDate, String workStatus, String workSchedule, String firstName, String lastName,
            String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.employeeID = employeeID;
        this.employeeRole = employeeRole;
        this.salary = salary;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.workStatus = workStatus;
        this.workSchedule = workSchedule;
        employeeCount++;
    }

    @Override
    public boolean login() {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Enter your email: ");
            String email = sc.nextLine();
            System.out.println("Enter your password: ");
            String password = sc.nextLine();

            // Check if the employee exists in the database
            String sql = "SELECT u.id, u.email, u.password, e.employee_id, e.employee_role " +
                        "FROM users u JOIN employees e ON u.id = e.id " +
                        "WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Employee login successful!");
                loggedIn = true;
                this.employeeID = rs.getInt("employee_id");
                this.employeeRole = rs.getString("employee_role");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
            } else {
                System.out.println("Invalid email or password");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        } finally {
            sc.close();
        }
        return loggedIn;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }
}