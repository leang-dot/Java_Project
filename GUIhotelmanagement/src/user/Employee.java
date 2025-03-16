// package user;

// import DataBase.DatabaseConnection;
// import interfaces.IAuthentication;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.Scanner;

// public class Employee extends User implements IAuthentication {
//     private static int employeeCount = 0;
//     protected int employeeID;
//     protected String employeeRole;
//     protected Double salary;
//     protected String address;
//     protected String dateOfBirth;
//     protected String hireDate;
//     protected String workStatus;
//     protected String workSchedule;

//     public Employee(int employeeID, String employeeRole, Double salary, String address, String dateOfBirth,
//             String hireDate, String workStatus, String workSchedule, String firstName, String lastName,
//             String phoneNumber, String email, String password) {
//         super(firstName, lastName, phoneNumber, email, password);
//         this.employeeID = employeeID;
//         this.employeeRole = employeeRole;
//         this.salary = salary;
//         this.address = address;
//         this.dateOfBirth = dateOfBirth;
//         this.hireDate = hireDate;
//         this.workStatus = workStatus;
//         this.workSchedule = workSchedule;
//         employeeCount++;
//     }

//     @Override
//     public boolean login() {
//         Scanner sc = new Scanner(System.in);
//         boolean loggedIn = false;
//         try (Connection conn = DatabaseConnection.getConnection()) {
//             System.out.println("Enter your email: ");
//             String email = sc.nextLine();
//             System.out.println("Enter your password: ");
//             String password = sc.nextLine();

//             // Check if the employee exists in the database
//             String sql = "SELECT u.id, u.email, u.password, e.employee_id, e.employee_role " +
//                         "FROM users u JOIN employees e ON u.id = e.id " +
//                         "WHERE u.email = ? AND u.password = ?";
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             stmt.setString(1, email);
//             stmt.setString(2, password);
//             ResultSet rs = stmt.executeQuery();

//             if (rs.next()) {
//                 System.out.println("Employee login successful!");
//                 loggedIn = true;
//                 this.employeeID = rs.getInt("employee_id");
//                 this.employeeRole = rs.getString("employee_role");
//                 this.email = rs.getString("email");
//                 this.password = rs.getString("password");
//             } else {
//                 System.out.println("Invalid email or password");
//             }
//         } catch (SQLException e) {
//             System.out.println("Error during login: " + e.getMessage());
//         } finally {
//             sc.close();
//         }
//         return loggedIn;
//     }

//     public static int getEmployeeCount() {
//         return employeeCount;
//     }

//     public int getEmployeeID() {
//         return employeeID;
//     }

//     public void setEmployeeRole(String employeeRole) {
//         this.employeeRole = employeeRole;
//     }

//     public void setSalary(Double salary) {
//         this.salary = salary;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public void setDateOfBirth(String dateOfBirth) {
//         this.dateOfBirth = dateOfBirth;
//     }

//     public void setHireDate(String hireDate) {
//         this.hireDate = hireDate;
//     }

//     public void setWorkStatus(String workStatus) {
//         this.workStatus = workStatus;
//     }

//     public void setWorkSchedule(String workSchedule) {
//         this.workSchedule = workSchedule;
//     }
// }

package user;

import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        if (employeeID < 0)
            throw new IllegalArgumentException("Employee ID cannot be negative.");
        if (salary != null && salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative.");
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

    // Getters and Setters
    public static int getEmployeeCount() {
        return employeeCount;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        if (employeeID >= 0)
            this.employeeID = employeeID;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        if (salary == null || salary >= 0)
            this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    // IAuthentication Implementation
    @Override
    public boolean login() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.id, u.email, u.password, e.employee_id, e.employee_role " +
                    "FROM users u JOIN employees e ON u.id = e.id " +
                    "WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.employeeID = rs.getInt("employee_id");
                this.employeeRole = rs.getString("employee_role");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                System.out.println("Employee login successful!");
                return true;
            } else {
                System.out.println("Invalid email or password");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    // Utility Methods
    public void updateProfile(String address, String phoneNumber, String workSchedule) {
        if (address != null)
            this.address = address;
        if (phoneNumber != null)
            this.phoneNumber = phoneNumber;
        if (workSchedule != null)
            this.workSchedule = workSchedule;
        System.out.println("Profile updated for " + getFirstName());
    }

    @Override
    public String toString() {
        return "Employee " + employeeID + ": " + getFirstName() + " " + getLastName() + " (" + employeeRole + ") - $"
                + salary;
    }
}