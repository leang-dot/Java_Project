// package user;

// import booking.Booking;
// import DataBase.DatabaseConnection;
// import interfaces.IAuthentication;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// public class Customer extends User implements IAuthentication {
//     private static int customerCount = 0;
//     private int customerID;
//     static List<Booking> Bookings = new ArrayList<>();

//     public Customer(int customerID, String firstName, String lastName, String phoneNumber, String email, String password) {
//         super(firstName, lastName, phoneNumber, email, password);
//         this.customerID = customerID;
//         customerCount++;
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

//             // Check if the customer exists in the database
//             String sql = "SELECT u.id, u.email, u.password, c.customer_id " +
//                         "FROM users u JOIN customers c ON u.id = c.id " +
//                         "WHERE u.email = ? AND u.password = ?";
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             stmt.setString(1, email);
//             stmt.setString(2, password);
//             ResultSet rs = stmt.executeQuery();

//             if (rs.next()) {
//                 System.out.println("Customer login successful!");
//                 loggedIn = true;
//                 this.customerID = rs.getInt("customer_id");
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

//     public static int getCustomerCount() {
//         return customerCount;
//     }

//     public int getCustomerID() {
//         return customerID;
//     }
// }

package user;

import booking.Booking;
import DataBase.DatabaseConnection;
import interfaces.IAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements IAuthentication {
    private static int customerCount = 0;
    private int customerID;
    private static List<Booking> Bookings = new ArrayList<>(); // Now explicitly private

    public Customer(int customerID, String firstName, String lastName, String phoneNumber, String email,
            String password) {
        super(firstName, lastName, phoneNumber, email, password);
        if (customerID < 0)
            throw new IllegalArgumentException("Customer ID cannot be negative.");
        this.customerID = customerID;
        customerCount++;
    }

    // Getters and Setters
    public static int getCustomerCount() {
        return customerCount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        if (customerID >= 0)
            this.customerID = customerID;
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(Bookings);
    }

    // IAuthentication Implementation
    @Override
    public boolean login() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.id, u.email, u.password, c.customer_id " +
                    "FROM users u JOIN customers c ON u.id = c.id " +
                    "WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.customerID = rs.getInt("customer_id");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                System.out.println("Customer login successful!");
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

    // Utility Methods for Bookings
    public void addBooking(Booking booking) {
        if (booking != null && !Bookings.contains(booking)) {
            Bookings.add(booking);
            System.out.println("Booking " + booking.getBookingId() + " added for " + getFirstName());
        }
    }

    public void removeBooking(Booking booking) {
        if (booking != null && Bookings.remove(booking)) {
            System.out.println("Booking " + booking.getBookingId() + " removed for " + getFirstName());
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking != null && Bookings.remove(booking)) {
            booking.cancelBooking();
            System.out.println("Booking " + bookingId + " cancelled by " + getFirstName());
        } else {
            System.out.println("Booking " + bookingId + " not found for " + getFirstName());
        }
    }

    public Booking findBookingById(String bookingId) {
        for (Booking booking : Bookings) {
            if (booking.getBookingId().equals(bookingId))
                return booking;
        }
        return null;
    }

    public void viewBookings() {
        if (Bookings.isEmpty())
            System.out.println("No bookings for " + getFirstName() + ".");
        else {
            System.out.println("Bookings for " + getFirstName() + " " + getLastName() + ":");
            for (Booking booking : Bookings)
                System.out.println(booking);
        }
    }

    @Override
    public String toString() {
        return "Customer " + customerID + ": " + getFirstName() + " " + getLastName() + " (" + getEmail() + ")";
    }
}