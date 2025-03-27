package GUI;

import java.sql.Connection;
import java.sql.SQLException;
import DataBase.DatabaseConnection; // Add this import statement

public class App {
    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
        mainForm.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        mainForm.setVisible(true);     
    }

    private static void formWindowOpened(java.awt.event.WindowEvent evt) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        if (connection != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to make connection!");
        }
    } catch (SQLException e) {
        System.err.println("Connection error: " + e.getMessage());
        e.printStackTrace();
    }
} 
}