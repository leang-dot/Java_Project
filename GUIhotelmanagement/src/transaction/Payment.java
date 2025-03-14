package transaction;

import user.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment extends Customer {
    private double amount;
    private String paymentMethod;

    public Payment(int customerID, double amount, String paymentMethod, String firstName, String lastName,
            String phoneNumber, String email, String password) {
        super(customerID, firstName, lastName, phoneNumber, email, password);
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", customerID=" + getCustomerID() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Payment payment = (Payment) o;

        if (Double.compare(payment.amount, amount) != 0)
            return false;
        return paymentMethod != null ? paymentMethod.equals(payment.paymentMethod) : payment.paymentMethod == null;
    }

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    // Save payment to the database
    public void saveToDatabase() {
        String query = "INSERT INTO payments (customerID, amount, paymentMethod, firstName, lastName, phoneNumber, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, getCustomerID());
            stmt.setDouble(2, amount);
            stmt.setString(3, paymentMethod);
            stmt.setString(4, getFirstName());
            stmt.setString(5, getLastName());
            stmt.setString(6, getPhoneNumber());
            stmt.setString(7, getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update payment in the database
    public void updateInDatabase() {
        String query = "UPDATE payments SET amount = ?, paymentMethod = ?, firstName = ?, lastName = ?, phoneNumber = ?, email = ? WHERE customerID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, paymentMethod);
            stmt.setString(3, getFirstName());
            stmt.setString(4, getLastName());
            stmt.setString(5, getPhoneNumber());
            stmt.setString(6, getEmail());
            stmt.setInt(7, getCustomerID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete payment from the database
    public void deleteFromDatabase() {
        String query = "DELETE FROM payments WHERE customerID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, getCustomerID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve payment from the database
    public static Payment getFromDatabase(int customerID) {
        String query = "SELECT * FROM payments WHERE customerID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getInt("customerID"),
                        rs.getDouble("amount"),
                        rs.getString("paymentMethod"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        ""); // Password is not retrieved for security reasons
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
