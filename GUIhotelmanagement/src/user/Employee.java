package user;

import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends User implements IAuthentication {
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
        if (employeeID < 0) throw new IllegalArgumentException("Employee ID cannot be negative.");
        if (salary != null && salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.employeeID = employeeID;
        this.employeeRole = employeeRole;
        this.salary = salary;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.workStatus = workStatus;
        this.workSchedule = workSchedule;
    }

    // Getters and Setters
    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { if (employeeID >= 0) this.employeeID = employeeID; }
    public String getEmployeeRole() { return employeeRole; }
    public void setEmployeeRole(String employeeRole) { this.employeeRole = employeeRole; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { if (salary == null || salary >= 0) this.salary = salary; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }
    public String getWorkStatus() { return workStatus; }
    public void setWorkStatus(String workStatus) { this.workStatus = workStatus; }
    public String getWorkSchedule() { return workSchedule; }
    public void setWorkSchedule(String workSchedule) { this.workSchedule = workSchedule; }

    

    @Override
    public boolean checkIn(String bookingId, int roomNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if the booking exists and room is available
            String checkSql = "SELECT b.id, b.room_number, b.status FROM bookings b WHERE b.booking_id = ? AND b.room_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, bookingId);
            checkStmt.setInt(2, roomNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && "reserved".equals(rs.getString("status"))) {
                // Update booking status to "checked_in"
                String updateSql = "UPDATE bookings SET status = ?, check_in_date = ? WHERE booking_id = ? AND room_number = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, "checked_in");
                updateStmt.setString(2, java.time.LocalDate.now().toString()); // Example date
                updateStmt.setString(3, bookingId);
                updateStmt.setInt(4, roomNumber);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Check-in successful for booking " + bookingId + " in room " + roomNumber);
                    return true;
                }
            } else {
                System.out.println("Booking " + bookingId + " not found or room " + roomNumber + " is unavailable.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during check-in: " + e.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public boolean checkOut(String bookingId, int roomNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if the booking exists and is checked in
            String checkSql = "SELECT b.id, b.room_number, b.status FROM bookings b WHERE b.booking_id = ? AND b.room_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, bookingId);
            checkStmt.setInt(2, roomNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && "checked_in".equals(rs.getString("status"))) {
                // Update booking status to "checked_out"
                String updateSql = "UPDATE bookings SET status = ?, check_out_date = ? WHERE booking_id = ? AND room_number = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, "checked_out");
                updateStmt.setString(2, java.time.LocalDate.now().toString()); // Example date
                updateStmt.setString(3, bookingId);
                updateStmt.setInt(4, roomNumber);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Check-out successful for booking " + bookingId + " from room " + roomNumber);
                    return true;
                }
            } else {
                System.out.println("Booking " + bookingId + " not found or not checked in for room " + roomNumber + ".");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during check-out: " + e.getMessage());
            return false;
        }
        return false;
    }

    public void updateProfile(String address, String phoneNumber, String workSchedule) {
        if (address != null) this.address = address;
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (workSchedule != null) this.workSchedule = workSchedule;
        System.out.println("Profile updated for " + getFirstName());
    }

    @Override
    public String toString() {
        return "Employee " + employeeID + ": " + getFirstName() + " " + getLastName() + " (" + employeeRole + ") - $" + salary;
    }
}
