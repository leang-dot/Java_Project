package transaction;

import user.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Payment extends Customer {
    private double amount;
    private String paymentMethod;
    private String paymentId;

    public Payment(int customerID, String firstName, String lastName, String phoneNumber, String email,
            String password, double amount, String paymentMethod, String paymentId) {
        super(customerID, firstName, lastName, phoneNumber, email, password);
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
    }


    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount >= 0)
            this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod != null && !paymentMethod.trim().isEmpty())
            this.paymentMethod = paymentMethod;
    }

    public String getPaymentId() {
        return paymentId;
    }

    // Database Functions
    public void makePayment() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "")) {
            String sql = "INSERT INTO payment (paymentId, customerID, amount, paymentMethod) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            stmt.setInt(2, getCustomerID());
            stmt.setDouble(3, amount);
            stmt.setString(4, paymentMethod);
            stmt.executeUpdate();
            System.out.println("Payment " + paymentId + " recorded for $" + amount + " by " + getFirstName());
        } catch (SQLException e) {
            System.err.println("Payment failed: " + e.getMessage());
        }
    }

    public void refundPayment() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "")) {
            String sql = "DELETE FROM payment WHERE paymentId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Payment " + paymentId + " refunded for " + getFirstName());
            else
                System.out.println("Payment " + paymentId + " not found for refund.");
        } catch (SQLException e) {
            System.err.println("Refund failed: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Payment " + paymentId + ": $" + amount + " via " + paymentMethod + " by " + getFirstName() + " "
                + getLastName();
    }
}