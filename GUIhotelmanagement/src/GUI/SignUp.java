package GUI;

import javax.swing.*;
import java.sql.*;

import DataBase.DatabaseConnection;
public class SignUp extends javax.swing.JFrame {

    public SignUp() {
        initComponents();
    }

    private void initComponents() {

        Main = new javax.swing.JPanel();
        Password = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        Firstname = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        Lastname = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        Password1 = new javax.swing.JLabel();
        Password2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmpassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        btnSignUp = new javax.swing.JButton();
        txtPhoneNumber = new javax.swing.JTextField();
        Firstname1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main.setBackground(new java.awt.Color(204, 204, 204));
        Main.setPreferredSize(new java.awt.Dimension(618, 350));

        Password.setBackground(new java.awt.Color(255, 255, 255));
        Password.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Password.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-profile-login-68.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        txtFirstname.setBackground(new java.awt.Color(255, 255, 255));
        txtFirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstnameActionPerformed(evt);
            }
        });

        Firstname.setBackground(new java.awt.Color(255, 255, 255));
        Firstname.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Firstname.setForeground(new java.awt.Color(102, 102, 102));
        Firstname.setText("Firstname");

        txtLastname.setBackground(new java.awt.Color(255, 255, 255));
        txtLastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastnameActionPerformed(evt);
            }
        });

        Lastname.setBackground(new java.awt.Color(255, 255, 255));
        Lastname.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Lastname.setForeground(new java.awt.Color(102, 102, 102));
        Lastname.setText("Lastname");

        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Email.setForeground(new java.awt.Color(102, 102, 102));
        Email.setText("Email");

        Password1.setBackground(new java.awt.Color(255, 255, 255));
        Password1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Password1.setForeground(new java.awt.Color(102, 102, 102));
        Password1.setText("Confirmpassword");

        Password2.setBackground(new java.awt.Color(255, 255, 255));
        Password2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Password2.setForeground(new java.awt.Color(102, 102, 102));
        Password2.setText("Password");

        txtPassword.setEditable(true);
        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));

        txtConfirmpassword.setBackground(new java.awt.Color(255, 255, 255));
        txtConfirmpassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtConfirmpassword.setForeground(new java.awt.Color(0, 0, 0));
        txtConfirmpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmpasswordActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
        btnLogin.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(51, 51, 51));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
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

        txtPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        Firstname1.setBackground(new java.awt.Color(255, 255, 255));
        Firstname1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Firstname1.setForeground(new java.awt.Color(102, 102, 102));
        Firstname1.setText("Phone Number");

        javax.swing.GroupLayout MainLayout = new javax.swing.GroupLayout(Main);
        Main.setLayout(MainLayout);
        MainLayout.setHorizontalGroup(
            MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainLayout.createSequentialGroup()
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainLayout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLogin)
                            .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Firstname)
                                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Firstname1)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Password2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainLayout.createSequentialGroup()
                            .addComponent(Password)
                            .addGap(262, 262, 262))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainLayout.createSequentialGroup()
                            .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lastname, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(txtConfirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Password1)
                                .addComponent(btnSignUp))
                            .addGap(118, 118, 118)))))
        );
        MainLayout.setVerticalGroup(
            MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Firstname)
                    .addComponent(Lastname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MainLayout.createSequentialGroup()
                        .addComponent(Email)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainLayout.createSequentialGroup()
                        .addComponent(Firstname1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password1)
                    .addComponent(Password2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConfirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnSignUp))
                .addGap(18, 18, 18)
                .addComponent(Password)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void txtFirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstnameActionPerformed

    private void txtLastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastnameActionPerformed

    private void txtConfirmpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmpasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Login.main(null);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    
    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {
        String firstName = txtFirstname.getText();
        if (!firstName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "First name can only contain letters");
            return;
        }

        String lastName = txtLastname.getText();
        if (!lastName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Last name can only contain letters");
            return;
        }


        String phoneNumber = txtPhoneNumber.getText();
        if (!phoneNumber.matches("^[0-9]{9,10}$")) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits");
            return;
        }
      
        String email = txtEmail.getText();
        if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Invalid email");
            return;
        }

        String password = String.valueOf(txtPassword.getPassword());
        String confirmpassword = String.valueOf(txtConfirmpassword.getPassword());

        if (password.equals(confirmpassword)) {
            try (Connection conn = DatabaseConnection.getConnection()) {

                Statement s = conn.createStatement();
                s.executeUpdate("INSERT INTO Customer (first_name, last_name, phone_number, email, password) VALUES ('"
                        + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + email + "', '" + password
                        + "')");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                return;
            }
            JOptionPane.showMessageDialog(this, "Sign up successfully");
            Login.main(null);
            this.dispose();
        } 
        else if (password.equals("") || confirmpassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password and cornfirm password");
        } else {
            JOptionPane.showMessageDialog(this, "anything else is wrong");

        }

    }

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new SignUp().setVisible(true);
        });
    }

    // Variables declaration 
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Firstname;
    private javax.swing.JLabel Firstname1;
    private javax.swing.JLabel Lastname;
    private javax.swing.JPanel Main;
    private javax.swing.JLabel Password;
    private javax.swing.JLabel Password1;
    private javax.swing.JLabel Password2;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtConfirmpassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    // End of variables declaration
}
