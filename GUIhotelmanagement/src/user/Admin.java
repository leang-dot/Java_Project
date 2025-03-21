package user;

import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements IAuthentication {
    private String adminUserName;
    private String adminPassword;
    static List<User> Users = new ArrayList<>();

    public Admin(String adminUserName, String adminPassword, String firstName, String lastName, String phoneNumber,
            String email, String password) {
        super(firstName, lastName, phoneNumber, email, password); // Pass userType as "Admin"
        if (adminUserName == null || adminUserName.trim().isEmpty())
            throw new IllegalArgumentException("Admin username cannot be null or empty.");
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

    // Getters and Setters
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        if (adminUserName != null && !adminUserName.trim().isEmpty())
            this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public List<User> getUsers() {
        return new ArrayList<>(Users);
    }

    // IAuthentication Implementation

    @Override
    public boolean checkIn(String bookingId, int roomNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            String roomCheckSql = "SELECT status FROM rooms WHERE room_number = ? FOR UPDATE";
            PreparedStatement roomCheckStmt = conn.prepareStatement(roomCheckSql);
            roomCheckStmt.setInt(1, roomNumber);
            ResultSet roomRs = roomCheckStmt.executeQuery();

            if (roomRs.next() && "available".equals(roomRs.getString("status"))) {
                String bookingCheckSql = "SELECT b.id, b.status FROM bookings b WHERE b.booking_id = ? AND b.room_number = ? FOR UPDATE";
                PreparedStatement bookingCheckStmt = conn.prepareStatement(bookingCheckSql);
                bookingCheckStmt.setString(1, bookingId);
                bookingCheckStmt.setInt(2, roomNumber);
                ResultSet bookingRs = bookingCheckStmt.executeQuery();

                if (bookingRs.next() && "reserved".equals(bookingRs.getString("status"))) {
                    String updateBookingSql = "UPDATE bookings SET status = ?, check_in_date = ? WHERE booking_id = ? AND room_number = ?";
                    PreparedStatement updateBookingStmt = conn.prepareStatement(updateBookingSql);
                    updateBookingStmt.setString(1, "checked_in");
                    updateBookingStmt.setString(2, LocalDate.now().toString());
                    updateBookingStmt.setString(3, bookingId);
                    updateBookingStmt.setInt(4, roomNumber);
                    updateBookingStmt.executeUpdate();

                    String updateRoomSql = "UPDATE rooms SET status = 'occupied' WHERE room_number = ?";
                    PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomSql);
                    updateRoomStmt.setInt(1, roomNumber);
                    updateRoomStmt.executeUpdate();

                    conn.commit();
                    System.out.println("Check-in successful for booking " + bookingId + " in room " + roomNumber);
                    return true;
                } else {
                    System.out.println(
                            "Booking " + bookingId + " not found or not reserved for room " + roomNumber + ".");
                }
            } else {
                System.out.println("Room " + roomNumber + " is not available.");
            }
            conn.rollback();
            return false;
        } catch (SQLException e) {
            System.out.println("Error during check-in: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean checkOut(String bookingId, int roomNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            String bookingCheckSql = "SELECT b.id, b.status FROM bookings b WHERE b.booking_id = ? AND b.room_number = ? FOR UPDATE";
            PreparedStatement bookingCheckStmt = conn.prepareStatement(bookingCheckSql);
            bookingCheckStmt.setString(1, bookingId);
            bookingCheckStmt.setInt(2, roomNumber);
            ResultSet bookingRs = bookingCheckStmt.executeQuery();

            if (bookingRs.next() && "checked_in".equals(bookingRs.getString("status"))) {
                String updateBookingSql = "UPDATE bookings SET status = ?, check_out_date = ? WHERE booking_id = ? AND room_number = ?";
                PreparedStatement updateBookingStmt = conn.prepareStatement(updateBookingSql);
                updateBookingStmt.setString(1, "checked_out");
                updateBookingStmt.setString(2, LocalDate.now().toString());
                updateBookingStmt.setString(3, bookingId);
                updateBookingStmt.setInt(4, roomNumber);
                updateBookingStmt.executeUpdate();

                String updateRoomSql = "UPDATE rooms SET status = 'available' WHERE room_number = ?";
                PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomSql);
                updateRoomStmt.setInt(1, roomNumber);
                updateRoomStmt.executeUpdate();

                conn.commit();
                System.out.println("Check-out successful for booking " + bookingId + " from room " + roomNumber);
                return true;
            } else {
                System.out
                        .println("Booking " + bookingId + " not found or not checked in for room " + roomNumber + ".");
            }
            conn.rollback();
            return false;
        } catch (SQLException e) {
            System.out.println("Error during check-out: " + e.getMessage());
            return false;
        }
    }

    // Add Employee
    public void addEmployee(Employee employee) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO users (first_name, last_name, phone_number, email, password, user_type, employee_id, employee_role, salary, address, date_of_birth, hire_date, work_status, work_schedule) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getPhoneNumber());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPassword());
            stmt.setString(6, "Employee");
            stmt.setInt(7, employee.getEmployeeID());
            stmt.setString(8, employee.getEmployeeRole());
            stmt.setDouble(9, employee.getSalary());
            stmt.setString(10, employee.getAddress());
            stmt.setString(11, employee.getDateOfBirth());
            stmt.setString(12, employee.getHireDate());
            stmt.setString(13, employee.getWorkStatus());
            stmt.setString(14, employee.getWorkSchedule());
            stmt.executeUpdate();

            conn.commit();
            Users.add(employee);
            System.out.println("Employee " + employee.getFirstName() + " added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // Add Customer
    public void addCustomer(Customer customer) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO users (first_name, last_name, phone_number, email, password, user_type, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPassword());
            stmt.setString(6, "Customer");
            stmt.setInt(7, customer.getCustomerID());
            stmt.executeUpdate();

            conn.commit();
            Users.add(customer);
            System.out.println("Customer " + customer.getFirstName() + " added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    // Remove Employee
    public void removeEmployee(int employeeId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            User user = findUserById(employeeId, "Employee");
            if (user != null && !this.email.equals(user.getEmail())) {
                String deleteSql = "DELETE FROM users WHERE employee_id = ? AND user_type = 'Employee'";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setInt(1, employeeId);
                int rowsAffected = deleteStmt.executeUpdate();

                if (rowsAffected > 0) {
                    Users.remove(user);
                    System.out.println("Employee " + employeeId + " removed successfully!");
                } else {
                    System.out.println("Employee " + employeeId + " not found!");
                }
                conn.commit();
            } else {
                System.out.println("Cannot remove admin or self!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Error removing employee: " + e.getMessage());
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Employee " + employeeId
                        + " has existing bookings or other dependencies and cannot be removed.");
            }
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }

    // Remove Customer
    public void removeCustomer(int customerId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            User user = findUserById(customerId, "Customer");
            if (user != null && !this.email.equals(user.getEmail())) {
                String deleteSql = "DELETE FROM users WHERE customer_id = ? AND user_type = 'Customer'";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setInt(1, customerId);
                int rowsAffected = deleteStmt.executeUpdate();

                if (rowsAffected > 0) {
                    Users.remove(user);
                    System.out.println("Customer " + customerId + " removed successfully!");
                } else {
                    System.out.println("Customer " + customerId + " not found!");
                }
                conn.commit();
            } else {
                System.out.println("Cannot remove admin or self!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Error removing customer: " + e.getMessage());
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Customer " + customerId + " has existing bookings and cannot be removed.");
            }
            try {
                // Rollback on error
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    // Update Employee
    public void updateEmployee(int employeeId, String newFirstName, String newLastName, String newPhoneNumber,
            String newEmail) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            User user = findUserById(employeeId, "Employee");
            if (user != null && !this.email.equals(user.getEmail())) {
                String sql = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, email = ? WHERE employee_id = ? AND user_type = 'Employee'";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newFirstName);
                stmt.setString(2, newLastName);
                stmt.setString(3, newPhoneNumber);
                stmt.setString(4, newEmail);
                stmt.setInt(5, employeeId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    user.setFirstName(newFirstName);
                    user.setLastName(newLastName);
                    user.setPhoneNumber(newPhoneNumber);
                    user.setEmail(newEmail);
                    System.out.println("Employee " + employeeId + " updated successfully!");
                } else {
                    System.out.println("Employee " + employeeId + " not found or update failed!");
                }
                conn.commit();
            } else {
                System.out.println("Cannot update admin or self data!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }

    // Update Customer
    public void updateCustomer(int customerId, String newFirstName, String newLastName, String newPhoneNumber,
            String newEmail) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            User user = findUserById(customerId, "Customer");
            if (user != null && !this.email.equals(user.getEmail())) {
                String sql = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, email = ? WHERE customer_id = ? AND user_type = 'Customer'";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newFirstName);
                stmt.setString(2, newLastName);
                stmt.setString(3, newPhoneNumber);
                stmt.setString(4, newEmail);
                stmt.setInt(5, customerId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    user.setFirstName(newFirstName);
                    user.setLastName(newLastName);
                    user.setPhoneNumber(newPhoneNumber);
                    user.setEmail(newEmail);
                    System.out.println("Customer " + customerId + " updated successfully!");
                } else {
                    System.out.println("Customer " + customerId + " not found or update failed!");
                }
                conn.commit();
            } else {
                System.out.println("Cannot update admin or self data!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }

    // Helper method to find user by ID and type (made public for Main class access)
    public User findUserById(int id, String userType) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE ";
            if ("Employee".equals(userType)) {
                sql += "employee_id = ? AND user_type = 'Employee'";
            } else if ("Customer".equals(userType)) {
                sql += "customer_id = ? AND user_type = 'Customer'";
            } else {
                return null; // Invalid user type
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user;
                if ("Employee".equals(rs.getString("user_type"))) {
                    user = new Employee(
                            rs.getInt("employee_id"),
                            rs.getString("employee_role"),
                            rs.getDouble("salary"),
                            rs.getString("address"),
                            rs.getString("date_of_birth"),
                            rs.getString("hire_date"),
                            rs.getString("work_status"),
                            rs.getString("work_schedule"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("password"));
                } else if ("Customer".equals(rs.getString("user_type"))) {
                    user = new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("password"));
                } else {
                    return null; // Should not happen with proper user_type check
                }
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error finding user by ID: " + e.getMessage());
        }
        return null;
    }

    // View All Users
    public void viewAllUsers() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst())
                System.out.println("No users found.");
            else {
                System.out.println("========All Users=======");
                while (rs.next()) {
                    String userType = rs.getString("user_type");
                    System.out.println("Type: " + userType);
                    System.out.println("ID: " + (rs.getObject("employee_id") != null ? rs.getInt("employee_id")
                            : rs.getObject("customer_id") != null ? rs.getInt("customer_id") : "N/A"));
                    System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                    System.out.println("Phone: " + rs.getString("phone_number"));
                    System.out.println("Email: " + rs.getString("email"));
                    if ("Employee".equals(userType)) {
                        System.out.println("Role: " + rs.getString("employee_role"));
                        System.out.println("Salary: " + rs.getDouble("salary"));
                        System.out.println("Address: " + rs.getString("address"));
                        System.out.println("Date of Birth: " + rs.getString("date_of_birth"));
                        System.out.println("Hire Date: " + rs.getString("hire_date"));
                        System.out.println("Work Status: " + rs.getString("work_status"));
                        System.out.println("Work Schedule: " + rs.getString("work_schedule"));
                    } else if ("Customer".equals(userType)) {
                        System.out.println("Customer ID: " + rs.getInt("customer_id"));
                    } else if ("Admin".equals(userType)) {
                        System.out.println("Admin Username: " + rs.getString("admin_username"));
                    }
                    System.out.println("-----------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error viewing users: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Admin: " + getFirstName() + " " + getLastName() + " (" + adminUserName + ")";
    }
}