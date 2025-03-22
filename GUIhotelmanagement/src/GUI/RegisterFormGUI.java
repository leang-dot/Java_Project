package GUI;

import constants.CommonConstants;
import DataBase.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormGUI extends JFrame {
    public RegisterFormGUI() {
        super("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
        setLayout(null);
        addGuiComponents();
    }

    private void addGuiComponents() {
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(30, 255, 400, 25);
        firstNameLabel.setForeground(CommonConstants.TEXT_COLOR);
        firstNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(30, 285, 450, 55);
        firstNameField.setBackground(CommonConstants.SECONDARY_COLOR);
        firstNameField.setForeground(CommonConstants.TEXT_COLOR);
        firstNameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(30, 355, 400, 25);
        lastNameLabel.setForeground(CommonConstants.TEXT_COLOR);
        lastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(30, 385, 450, 55);
        lastNameField.setBackground(CommonConstants.SECONDARY_COLOR);
        lastNameField.setForeground(CommonConstants.TEXT_COLOR);
        lastNameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(lastNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 455, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 485, 450, 55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordField);

        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(30, 555, 400, 25);
        rePasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(rePasswordLabel);

        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(30, 585, 450, 55);
        rePasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        rePasswordField.setForeground(CommonConstants.TEXT_COLOR);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(rePasswordField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(30, 655, 400, 25);
        phoneLabel.setForeground(CommonConstants.TEXT_COLOR);
        phoneLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(30, 685, 450, 55);
        phoneField.setBackground(CommonConstants.SECONDARY_COLOR);
        phoneField.setForeground(CommonConstants.TEXT_COLOR);
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(phoneField);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setBounds(125, 760, 250, 50);
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = new String(passwordField.getPassword());
            String rePassword = new String(rePasswordField.getPassword());
            String phoneNumber = phoneField.getText();

            if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || rePassword.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            if (!password.equals(rePassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
                return;
            }

            if (!phoneNumber.matches("^[0-9]{10,15}$")) {
                JOptionPane.showMessageDialog(this, "Phone number must be 10-15 digits!");
                return;
            }

            if (registerAdmin(username, firstName, lastName, password, phoneNumber)) {
                JOptionPane.showMessageDialog(this, "Registration Successful! Please log in.");
                dispose();
                new LoginFormGUI().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed!");
            }
        });
        add(registerButton);

        JLabel loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginFormGUI().setVisible(true);
            }
        });
        loginLabel.setBounds(125, 820, 250, 30);
        add(loginLabel);
    }

    private boolean registerAdmin(String username, String firstName, String lastName, String password, String phoneNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkSql = "SELECT COUNT(*) FROM admin WHERE admin_username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
                return false;
            }

            String insertSql = "INSERT INTO admin (admin_username, first_name, last_name, phone_number, admin_email, admin_password) " +
                              "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, firstName);
            insertStmt.setString(3, lastName);
            insertStmt.setString(4, phoneNumber);
            insertStmt.setString(5, username + "@admin.com");
            insertStmt.setString(6, password);
            int rowsAffected = insertStmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        new RegisterFormGUI().setVisible(true);
    }
}