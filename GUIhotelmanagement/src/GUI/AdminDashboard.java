package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import user.Admin;
import user.Customer;
import user.Employee;
import DataBase.DatabaseConnection;

public class AdminDashboard extends JFrame {
    private Admin admin;
    private JPanel contentPane;

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 633);

        contentPane = new JPanel();
        
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 153, 204));
        headerPanel.setBounds(168, 30, 811, 172);
        contentPane.add(headerPanel);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(0, 153, 204));
        sidePanel.setBounds(23, 172, 183, 357);
        contentPane.add(sidePanel);

        JLabel userIcon = new JLabel("");
        userIcon.setIcon(new ImageIcon("images/user (1).png"));
        userIcon.setBounds(0, 0, 253, 221);
        contentPane.add(userIcon);

        JButton btnCheckInOut = new JButton("CHECK-IN/CHECK-OUT");
        btnCheckInOut.setIcon(new ImageIcon("images/checkin.png"));
        btnCheckInOut.setFont(new Font("High Tower Text", Font.BOLD, 20));
        btnCheckInOut.setBounds(229, 227, 383, 256);
        btnCheckInOut.addActionListener(e -> openCheckInOutDialog());
        contentPane.add(btnCheckInOut);

        JButton btnManageUsers = new JButton("MANAGE USERS");
        btnManageUsers.setIcon(new ImageIcon("images/users.png"));
        btnManageUsers.setFont(new Font("High Tower Text", Font.BOLD, 20));
        btnManageUsers.setBounds(622, 227, 369, 256);
        btnManageUsers.addActionListener(e -> openManageUsersDialog());
        contentPane.add(btnManageUsers);

        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("High Tower Text", Font.BOLD, 22));
        btnBack.setBounds(651, 528, 169, 45);
        btnBack.addActionListener(e -> {
            dispose();
            new LoginFormGUI().setVisible(true);
        });
        contentPane.add(btnBack);
    }

    private void openCheckInOutDialog() {
        JDialog dialog = new JDialog(this, "Check-In/Check-Out", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        JLabel lblBookingId = new JLabel("Booking ID:");
        JTextField tfBookingId = new JTextField();
        JLabel lblRoomNumber = new JLabel("Room Number:");
        JTextField tfRoomNumber = new JTextField();

        JButton btnCheckIn = new JButton("Check In");
        btnCheckIn.addActionListener(e -> {
            try {
                String bookingId = tfBookingId.getText();
                int roomNumber = Integer.parseInt(tfRoomNumber.getText());
                if (admin.checkIn(bookingId, roomNumber)) {
                    JOptionPane.showMessageDialog(dialog, "Check-In Successful!");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Check-In Failed!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Room Number!");
            }
        });

        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.addActionListener(e -> {
            try {
                String bookingId = tfBookingId.getText();
                int roomNumber = Integer.parseInt(tfRoomNumber.getText());
                if (admin.checkOut(bookingId, roomNumber)) {
                    JOptionPane.showMessageDialog(dialog, "Check-Out Successful!");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Check-Out Failed!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Room Number!");
            }
        });

        dialog.add(lblBookingId);
        dialog.add(tfBookingId);
        dialog.add(lblRoomNumber);
        dialog.add(tfRoomNumber);
        dialog.add(btnCheckIn);
        dialog.add(btnCheckOut);
        dialog.setVisible(true);
    }

    private void openManageUsersDialog() {
        JDialog dialog = new JDialog(this, "Manage Users", true);
        dialog.setLayout(null);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);

        JButton btnAddEmp = new JButton("Add Employee");
        btnAddEmp.setFont(new Font("High Tower Text", Font.BOLD, 16));
        btnAddEmp.setBounds(50, 50, 150, 40);
        btnAddEmp.addActionListener(e -> openAddEmployeeDialog(dialog));
        dialog.add(btnAddEmp);

        JButton btnAddCust = new JButton("Add Customer");
        btnAddCust.setFont(new Font("High Tower Text", Font.BOLD, 16));
        btnAddCust.setBounds(220, 50, 150, 40);
        btnAddCust.addActionListener(e -> openAddCustomerDialog(dialog));
        dialog.add(btnAddCust);

        JLabel lblEmpId = new JLabel("Employee ID:");
        lblEmpId.setBounds(50, 100, 100, 20);
        JTextField tfEmpId = new JTextField();
        tfEmpId.setBounds(150, 100, 100, 25);
        JButton btnRemoveEmp = new JButton("Remove");
        btnRemoveEmp.setBounds(260, 100, 100, 25);
        btnRemoveEmp.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(tfEmpId.getText());
                admin.removeEmployee(empId);
                JOptionPane.showMessageDialog(dialog, "Employee Removed!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Employee ID!");
            }
        });
        dialog.add(lblEmpId);
        dialog.add(tfEmpId);
        dialog.add(btnRemoveEmp);

        JLabel lblCustId = new JLabel("Customer ID:");
        lblCustId.setBounds(50, 140, 100, 20);
        JTextField tfCustId = new JTextField();
        tfCustId.setBounds(150, 140, 100, 25);
        JButton btnRemoveCust = new JButton("Remove");
        btnRemoveCust.setBounds(260, 140, 100, 25);
        btnRemoveCust.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(tfCustId.getText());
                admin.removeCustomer(custId);
                JOptionPane.showMessageDialog(dialog, "Customer Removed!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Customer ID!");
            }
        });
        dialog.add(lblCustId);
        dialog.add(tfCustId);
        dialog.add(btnRemoveCust);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(50, 180, 100, 20);
        JTextField tfPhone = new JTextField();
        tfPhone.setBounds(150, 180, 100, 25);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 220, 100, 20);
        JTextField tfEmail = new JTextField();
        tfEmail.setBounds(150, 220, 100, 25);

        JButton btnUpdateEmp = new JButton("Update Employee");
        btnUpdateEmp.setBounds(260, 200, 150, 25);
        btnUpdateEmp.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(tfEmpId.getText());
                admin.updateEmployee(empId, tfPhone.getText(), tfEmail.getText(), "additionalParam1", "additionalParam2");
                JOptionPane.showMessageDialog(dialog, "Employee Updated!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Employee ID!");
            }
        });

        JButton btnUpdateCust = new JButton("Update Customer");
        btnUpdateCust.setBounds(420, 200, 150, 25);
        btnUpdateCust.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(tfCustId.getText());
                admin.updateCustomer(custId, tfPhone.getText(), tfEmail.getText(), "additionalParam1", "additionalParam2");
                JOptionPane.showMessageDialog(dialog, "Customer Updated!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Customer ID!");
            }
        });

        dialog.add(lblPhone);
        dialog.add(tfPhone);
        dialog.add(lblEmail);
        dialog.add(tfEmail);
        dialog.add(btnUpdateEmp);
        dialog.add(btnUpdateCust);

        JButton btnViewUsers = new JButton("View All Users");
        btnViewUsers.setBounds(260, 240, 150, 25);
        btnViewUsers.addActionListener(e -> displayAllUsers(dialog));
        dialog.add(btnViewUsers);

        dialog.setVisible(true);
    }

    private void openAddEmployeeDialog(JDialog parent) {
        JDialog dialog = new JDialog(parent, "Add Employee", true);
        dialog.setLayout(new GridLayout(12, 2, 10, 10));
        dialog.setSize(400, 400);

        JTextField tfUsername = new JTextField();
        JTextField tfPhone = new JTextField();
        JTextField tfEmail = new JTextField();
        JTextField tfPassword = new JTextField();
        JTextField tfEmpId = new JTextField();
        JTextField tfRole = new JTextField();
        JTextField tfSalary = new JTextField();
        JTextField tfAddress = new JTextField();
        JTextField tfDob = new JTextField();
        JTextField tfHireDate = new JTextField();
        JTextField tfStatus = new JTextField();
        JTextField tfSchedule = new JTextField();

        dialog.add(new JLabel("Username:"));
        dialog.add(tfUsername);
        dialog.add(new JLabel("Phone:"));
        dialog.add(tfPhone);
        dialog.add(new JLabel("Email:"));
        dialog.add(tfEmail);
        dialog.add(new JLabel("Password:"));
        dialog.add(tfPassword);
        dialog.add(new JLabel("Employee ID:"));
        dialog.add(tfEmpId);
        dialog.add(new JLabel("Role:"));
        dialog.add(tfRole);
        dialog.add(new JLabel("Salary:"));
        dialog.add(tfSalary);
        dialog.add(new JLabel("Address:"));
        dialog.add(tfAddress);
        dialog.add(new JLabel("Date of Birth:"));
        dialog.add(tfDob);
        dialog.add(new JLabel("Hire Date:"));
        dialog.add(tfHireDate);
        dialog.add(new JLabel("Work Status:"));
        dialog.add(tfStatus);
        dialog.add(new JLabel("Work Schedule:"));
        dialog.add(tfSchedule);

        JButton btnSubmit = new JButton("Add");
        btnSubmit.addActionListener(e -> {
            try {
                Employee emp = new Employee(
                    Integer.parseInt(tfEmpId.getText()), tfRole.getText(), Double.parseDouble(tfSalary.getText()),
                    tfAddress.getText(), tfDob.getText(), tfHireDate.getText(), tfStatus.getText(), tfSchedule.getText(),
                    tfUsername.getText(), tfPhone.getText(), tfEmail.getText(), tfPassword.getText()
                );
                admin.addEmployee(emp);
                JOptionPane.showMessageDialog(dialog, "Employee Added!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Input!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });
        dialog.add(btnSubmit);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void openAddCustomerDialog(JDialog parent) {
        JDialog dialog = new JDialog(parent, "Add Customer", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(300, 200);

        JTextField tfUsername = new JTextField();
        JTextField tfPhone = new JTextField();
        JTextField tfEmail = new JTextField();
        JTextField tfPassword = new JTextField();
        JTextField tfCustId = new JTextField();

        dialog.add(new JLabel("Username:"));
        dialog.add(tfUsername);
        dialog.add(new JLabel("Phone:"));
        dialog.add(tfPhone);
        dialog.add(new JLabel("Email:"));
        dialog.add(tfEmail);
        dialog.add(new JLabel("Password:"));
        dialog.add(tfPassword);
        dialog.add(new JLabel("Customer ID:"));
        dialog.add(tfCustId);

        JButton btnSubmit = new JButton("Add");
        btnSubmit.addActionListener(e -> {
            try {
                Customer cust = new Customer(
                    Integer.parseInt(tfCustId.getText()), tfUsername.getText(),
                    tfPhone.getText(), tfEmail.getText(), tfPassword.getText()
                );
                admin.addCustomer(cust);
                JOptionPane.showMessageDialog(dialog, "Customer Added!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Customer ID!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });
        dialog.add(btnSubmit);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void displayAllUsers(JDialog parent) {
        JDialog dialog = new JDialog(parent, "All Users", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(600, 400);

        JTable userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Fetch employees
            String empSql = "SELECT 'Employee' AS user_type, employee_id AS id, username, phone_number, email FROM employee";
            PreparedStatement empStmt = conn.prepareStatement(empSql);
            ResultSet empRs = empStmt.executeQuery();

            // Fetch customers
            String custSql = "SELECT 'Customer' AS user_type, customer_id AS id, username, phone_number, email FROM customer";
            PreparedStatement custStmt = conn.prepareStatement(custSql);
            ResultSet custRs = custStmt.executeQuery();

            java.util.List<String[]> data = new java.util.ArrayList<>();
            String[] columns = {"Type", "ID", "Username", "Phone", "Email"};

            // Add employees to the table
            while (empRs.next()) {
                data.add(new String[]{
                    empRs.getString("user_type"),
                    empRs.getString("id"),
                    empRs.getString("username"),
                    empRs.getString("phone_number"),
                    empRs.getString("email")
                });
            }

            // Add customers to the table
            while (custRs.next()) {
                data.add(new String[]{
                    custRs.getString("user_type"),
                    custRs.getString("id"),
                    custRs.getString("username"),
                    custRs.getString("phone_number"),
                    custRs.getString("email")
                });
            }

            String[][] tableData = data.toArray(new String[0][]);
            userTable.setModel(new javax.swing.table.DefaultTableModel(tableData, columns));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, "Error fetching users: " + ex.getMessage());
        }

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        Admin admin = new Admin(1, "admin", "1234567890", "admin@example.com", "password", "additionalParam1");
        new AdminDashboard(admin).setVisible(true);
    }
}