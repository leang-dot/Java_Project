
package GUI;

import DataBase.DatabaseConnection;
import java.sql.*;
import javax.swing.*;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    private void initComponents() {

        Main = new javax.swing.JPanel();
        Password = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        Password2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Main.setBackground(new java.awt.Color(204, 204, 204));
        Main.setPreferredSize(new java.awt.Dimension(618, 350));

        Password.setBackground(new java.awt.Color(255, 255, 255));
        Password.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Password.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-profile-login-68.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Email.setForeground(new java.awt.Color(102, 102, 102));
        Email.setText("Email");

        Password2.setBackground(new java.awt.Color(255, 255, 255));
        Password2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Password2.setForeground(new java.awt.Color(102, 102, 102));
        Password2.setText("Password");

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
        btnLogin.setForeground(new java.awt.Color(51, 51, 51));
        btnLogin.setText("Log In");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSignUp.setBackground(new java.awt.Color(204, 204, 204));
        btnSignUp.setForeground(new java.awt.Color(51, 51, 51));
        btnSignUp.setText("SIgn Up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainLayout = new javax.swing.GroupLayout(Main);
        Main.setLayout(MainLayout);
        MainLayout.setHorizontalGroup(
                MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainLayout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 41, Short.MAX_VALUE)
                                .addComponent(Password)
                                .addGap(262, 262, 262))
                        .addGroup(MainLayout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Password2)
                                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(MainLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(MainLayout.createSequentialGroup()
                                                        .addComponent(btnLogin)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnSignUp))

                                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 167,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        MainLayout.setVerticalGroup(
                MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Email)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Password2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSignUp)
                                        .addComponent(btnLogin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68,
                                        Short.MAX_VALUE)
                                .addComponent(Password)
                                .addGap(26, 26, 26)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE));

        pack();

        setLocationRelativeTo(null);
    }

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {
        
        SignUp.main(null);
        this.dispose();

    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());
        System.out.println(email + " " + password);

        // check data frome table Customer in database
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn
                        .prepareStatement(
                                "SELECT customer_id, email, password, first_name, last_name FROM Customer WHERE email = ? AND password = ?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String firstname = rs.getString("first_name");
                    String lastname = rs.getString("last_name");
                    int customerId = rs.getInt("customer_id");
                    JOptionPane.showMessageDialog(this, "Login Successfully!");
                    new Customer(firstname, lastname, customerId, email).setVisible(true);
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Email or Password!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    
    private javax.swing.JLabel Email;
    private javax.swing.JPanel Main;
    private javax.swing.JLabel Password;
    private javax.swing.JLabel Password2;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    
}
