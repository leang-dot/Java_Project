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
import java.util.Scanner;

public class Customer extends User implements IAuthentication {
    private static int customerCount = 0;
    private int customerID;
    static List<Booking> Bookings = new ArrayList<>();

    public Customer(int customerID, String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.customerID = customerID;
        customerCount++;
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

            // Check if the customer exists in the database
            String sql = "SELECT u.id, u.email, u.password, c.customer_id " +
                        "FROM users u JOIN customers c ON u.id = c.id " +
                        "WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Customer login successful!");
                loggedIn = true;
                this.customerID = rs.getInt("customer_id");
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

    public static int getCustomerCount() {
        return customerCount;
    }

    public int getCustomerID() {
        return customerID;
    }
}