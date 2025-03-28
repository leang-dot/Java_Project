
package GUI;

import java.sql.*;

import javax.swing.JOptionPane;

import DataBase.DatabaseConnection;
public class CrudEmployee extends javax.swing.JPanel {

    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;
    String employeeRole;
    String salary;
    private int selectedEmployeeId = -1; 
    public CrudEmployee() {
        initComponents();
        loadEmployeeData();

        tblEmployee.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblEmployee.getSelectedRow() != -1) {
                int selectedRow = tblEmployee.getSelectedRow();
                selectedEmployeeId = Integer.parseInt(tblEmployee.getValueAt(selectedRow, 0).toString());
                txtFirstName.setText(tblEmployee.getValueAt(selectedRow, 1).toString());
                txtLastName.setText(tblEmployee.getValueAt(selectedRow, 2).toString());
                txtPhoneNumber.setText(tblEmployee.getValueAt(selectedRow, 3).toString());
                txtEmail.setText(tblEmployee.getValueAt(selectedRow, 4).toString());
                txtPassword.setText(tblEmployee.getValueAt(selectedRow, 5).toString());
                cmbEmployeeRole.setSelectedItem(tblEmployee.getValueAt(selectedRow, 6).toString());
                txtSalary.setText(tblEmployee.getValueAt(selectedRow, 7).toString());
            }
        });
    }

    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtSalary.setText("");
        txtSearchId.setText("");
    }
    
    private void loadEmployeeData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT employee_id, first_name, last_name, phone_number, email, password, employee_role, salary FROM employee";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
    
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Employee ID", "First Name", "Last Name", "Phone Number", "Email", "Password", "Employee Role", "Salary"}
            );
    
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("employee_role"),
                    rs.getDouble("salary")
                });
            }
    
            tblEmployee.setModel(model);
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage());
        }
    }
                        
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbEmployeeRole = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnAddEmployee = new javax.swing.JButton();
        btnUpdateEmployee = new javax.swing.JButton();
        btnDeleteEmployee = new javax.swing.JButton();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        txtSearchId = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 238, 220));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("Employee Role");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Phone Number");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setText("Salary");

        txtSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalaryActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Password");

        cmbEmployeeRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Receptionist", "Housekeeping" }));
        cmbEmployeeRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmployeeRoleActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("First Name");

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Phone Number", "Email", "Password", "Employee Role", "Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEmployee);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Last Name");

        btnAddEmployee.setText("Add");
        btnAddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmployeeActionPerformed(evt);
            }
        });

        btnUpdateEmployee.setText("Update");
        btnUpdateEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateEmployeeActionPerformed(evt);
            }
        });

        btnDeleteEmployee.setText("Delete");
        btnDeleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEmployeeActionPerformed(evt);
            }
        });

        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSearchId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(cmbEmployeeRole, 0, 0, Short.MAX_VALUE)
                            .addComponent(txtSalary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchId)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddEmployee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateEmployee)
                        .addGap(77, 77, 77)
                        .addComponent(btnDeleteEmployee))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbEmployeeRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddEmployee)
                    .addComponent(btnUpdateEmployee)
                    .addComponent(btnDeleteEmployee)
                    .addComponent(btnSearch)
                    .addComponent(txtSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>                        

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {                                          
    }                                         

    private void cmbEmployeeRoleActionPerformed(java.awt.event.ActionEvent evt) {                                                
    }                                               

    private void btnAddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {
        firstName = txtFirstName.getText();
        if (!firstName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "First name can only contain letters");
            return;
        }

        lastName = txtLastName.getText();
        if (!lastName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Last name can only contain letters");
            return;
        }

        phoneNumber = txtPhoneNumber.getText();
        if(!phoneNumber.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Phone number can only contain numbers");
            return;
        }

        email = txtEmail.getText();
        if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Invalid email");
            return;
        }

        password = String.valueOf(txtPassword.getPassword());
        employeeRole = cmbEmployeeRole.getSelectedItem().toString();
        salary = txtSalary.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO employee (first_name, last_name, phone_number, email, password, employee_role, salary) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, employeeRole);
            pstmt.setDouble(7, Double.parseDouble(salary));
            
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            
            // Clear the input fields
            txtFirstName.setText("");
            txtLastName.setText("");
            txtPhoneNumber.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtSalary.setText("");
            
            // Refresh the table
            loadEmployeeData();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding employee: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salary must be a valid number");
        }
    }                                             

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {                                            
    }                                           

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {                                               
    }                                              

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {    }                                        

    private void btnUpdateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (selectedEmployeeId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee from the table or search for an employee first");
            return;
        }

        firstName = txtFirstName.getText();
        if (!firstName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "First name can only contain letters");
            return;
        }

        lastName = txtLastName.getText();
        if (!lastName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Last name can only contain letters");
            return;
        }

        phoneNumber = txtPhoneNumber.getText();
        if (!phoneNumber.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Phone number can only contain numbers");
            return;
        }

        email = txtEmail.getText();
        if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Invalid email");
            return;
        }

        password = String.valueOf(txtPassword.getPassword());
        employeeRole = cmbEmployeeRole.getSelectedItem().toString();
        salary = txtSalary.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE employee SET first_name = ?, last_name = ?, phone_number = ?, " +
                          "email = ?, password = ?, employee_role = ?, salary = ? " +
                          "WHERE employee_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, employeeRole);
            pstmt.setDouble(7, Double.parseDouble(salary));
            pstmt.setInt(8, selectedEmployeeId);

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                clearFields();
                loadEmployeeData();
                selectedEmployeeId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update employee");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating employee: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salary must be a valid number");
        }
    }                                                 

    private void btnDeleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (selectedEmployeeId == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee from the table or search for an employee first");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + txtFirstName.getText() + " " + txtLastName.getText() + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "DELETE FROM employee WHERE employee_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, selectedEmployeeId);
                
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                    clearFields();
                    loadEmployeeData();
                    selectedEmployeeId = -1;
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete employee");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting employee: " + e.getMessage());
            }
        }

        
    }                                                 

    private void txtSearchIdActionPerformed(java.awt.event.ActionEvent evt) {                                            
    
    }                                           

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String searchId = txtSearchId.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID to search");
            loadEmployeeData(); // Reset to show all employees
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT employee_id, first_name, last_name, phone_number, email, password, employee_role, salary " +
                          "FROM employee WHERE employee_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(searchId));
            ResultSet rs = pstmt.executeQuery();
            
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Employee ID", "First Name", "Last Name", "Phone Number", "Email", "Password", "Employee Role", "Salary"}
            );

            if (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("employee_role"),
                    rs.getDouble("salary")
                });
                tblEmployee.setModel(model);
                selectedEmployeeId = rs.getInt("employee_id");
                // Populate fields with found employee
                txtFirstName.setText(rs.getString("first_name"));
                txtLastName.setText(rs.getString("last_name"));
                txtPhoneNumber.setText(rs.getString("phone_number"));
                txtEmail.setText(rs.getString("email"));
                txtPassword.setText(rs.getString("password"));
                cmbEmployeeRole.setSelectedItem(rs.getString("employee_role"));
                txtSalary.setText(String.valueOf(rs.getDouble("salary")));
            } else {
                JOptionPane.showMessageDialog(this, "No employee found with ID: " + searchId);
                loadEmployeeData(); // Reset table
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching employee: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID number");
        }
    }                                         

                 
    private javax.swing.JButton btnAddEmployee;
    private javax.swing.JButton btnDeleteEmployee;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateEmployee;
    private javax.swing.JComboBox<String> cmbEmployeeRole;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearchId;                
}
