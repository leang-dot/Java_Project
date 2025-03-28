package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import DataBase.DatabaseConnection;

public class Admin extends User {
    private int adminId;
    private String adminEmail = "Admin";
    private String adminPassword = "1234";

    public Admin(int adminId, String adminEmail, String adminPassword,String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.adminId = adminId;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public boolean checkIn(String bookingId, int roomNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String bookingSql = "SELECT room_id FROM booking WHERE booking_id = ?";
            PreparedStatement bookingStmt = conn.prepareStatement(bookingSql);
            bookingStmt.setInt(1, Integer.parseInt(bookingId));
            ResultSet rs = bookingStmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
            int roomId = rs.getInt("room_id");

            String roomSql = "SELECT room_id FROM room WHERE room_number = ? AND room_id = ?";
            PreparedStatement roomStmt = conn.prepareStatement(roomSql);
            roomStmt.setString(1, String.valueOf(roomNumber));
            roomStmt.setInt(2, roomId);
            ResultSet roomRs = roomStmt.executeQuery();
            if (!roomRs.next()) {
                return false;
            }

            String updateRoomSql = "UPDATE room SET is_booked = TRUE WHERE room_id = ?";
            PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomSql);
            updateRoomStmt.setInt(1, roomId);
            updateRoomStmt.executeUpdate();

            return true;
        } catch (SQLException | NumberFormatException e) {
            return false;
        }
    }

    public boolean checkOut(String bookingId, int roomNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String bookingSql = "SELECT room_id FROM booking WHERE booking_id = ?";
            PreparedStatement bookingStmt = conn.prepareStatement(bookingSql);
            bookingStmt.setInt(1, Integer.parseInt(bookingId));
            ResultSet rs = bookingStmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
            int roomId = rs.getInt("room_id");

            String roomSql = "SELECT room_id FROM room WHERE room_number = ? AND room_id = ?";
            PreparedStatement roomStmt = conn.prepareStatement(roomSql);
            roomStmt.setString(1, String.valueOf(roomNumber));
            roomStmt.setInt(2, roomId);
            ResultSet roomRs = roomStmt.executeQuery();
            if (!roomRs.next()) {
                return false;
            }

            String updateRoomSql = "UPDATE room SET is_booked = FALSE WHERE room_id = ?";
            PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomSql);
            updateRoomStmt.setInt(1, roomId);
            updateRoomStmt.executeUpdate();

            return true;
        } catch (SQLException | NumberFormatException e) {
            return false;
        }
    }

    public void addEmployee(Employee employee) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO employee (employee_id, phone_number, email, password, employee_role, salary, address, date_of_birth, hire_date, work_status, work_schedule) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employee.getEmployeeId());
            stmt.setString(3, employee.getPhoneNumber());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPassword());
            stmt.setString(6, employee.getRole());
            stmt.setDouble(7, employee.getSalary());  
            stmt.setString(9, employee.getDateOfBirth());
            stmt.setString(10, employee.getHireDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding employee: " + e.getMessage());
        }
    }

    public void addCustomer(Customer customer) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO customer (customer_id, username, phone_number, email, password) " +
                        "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding customer: " + e.getMessage());
        }
    }

    public void removeEmployee(int employeeId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM employee WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing employee: " + e.getMessage());
        }
    }

    public void removeCustomer(int customerId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing customer: " + e.getMessage());
        }
    }

    public void updateEmployee(int employeeId, String firstName, String lastName, String phoneNumber, String email) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE employee SET phone_number = ?, email = ? WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phoneNumber);
            stmt.setString(2, email);
            stmt.setInt(3, employeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee: " + e.getMessage());
        }
    }

    public void updateCustomer(int customerId, String firstName, String lastName, String phoneNumber, String email) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE customer SET phone_number = ?, email = ? WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phoneNumber);
            stmt.setString(2, email);
            stmt.setInt(3, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer: " + e.getMessage());
        }
    }
}