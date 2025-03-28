
package GUI;

import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.DatabaseConnection;

public class BookingConfirm extends javax.swing.JPanel {
    private int selectedBookingId = -1;
    public BookingConfirm() {
        initComponents();
        loadBookingData();

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                int selectedRow = jTable1.getSelectedRow();
                selectedBookingId = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
                populateFields(selectedBookingId);
            }
        });
    }
            
    private void populateFields(int bookingId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT b.booking_date, b.room_type, b.total_price, c.first_name, c.last_name, c.phone_number, c.email " +
                          "FROM booking b JOIN Customer c ON b.customer_id = c.customer_id " +
                          "WHERE b.booking_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtBookingDate.setText(rs.getDate("booking_date").toString());
                txtRoomType.setText(rs.getString("room_type"));
                txtTotalPrice.setText(String.valueOf(rs.getDouble("total_price")));
                txtFirstName.setText(rs.getString("first_name"));
                txtLastName.setText(rs.getString("last_name"));
                txtPhoneNumber.setText(rs.getString("phone_number"));
                txtEmail.setText(rs.getString("email"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error populating fields: " + e.getMessage());
        }
    }
    private void loadBookingData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT booking_id, booking_date, customer_id, room_type, total_price FROM booking";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Booking ID", "Booking Date", "Customer ID", "Room Type", "Total Price"}
            );

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("booking_id"),
                    rs.getDate("booking_date"),
                    rs.getInt("customer_id"),
                    rs.getString("room_type"),
                    rs.getDouble("total_price")
                });
            }

            jTable1.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading booking data: " + e.getMessage());
        }
    }

    private void performCheckIn(int bookingId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if already checked in
            String checkQuery = "SELECT COUNT(*) FROM check_in WHERE booking_id = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkQuery);
            checkPstmt.setInt(1, bookingId);
            ResultSet checkRs = checkPstmt.executeQuery();
            if (checkRs.next() && checkRs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "This booking has already been checked in");
                return;
            }

            // Get booking details
            String bookingQuery = "SELECT booking_date, room_type, total_price FROM booking WHERE booking_id = ?";
            PreparedStatement bookingPstmt = conn.prepareStatement(bookingQuery);
            bookingPstmt.setInt(1, bookingId);
            ResultSet bookingRs = bookingPstmt.executeQuery();

            if (bookingRs.next()) {
                java.sql.Date bookingDate = bookingRs.getDate("booking_date");
                String roomType = bookingRs.getString("room_type");
                double totalPrice = bookingRs.getDouble("total_price");

                // Get random employee
                String employeeQuery = "SELECT employee_id FROM employee ORDER BY RAND() LIMIT 1";
                PreparedStatement employeePstmt = conn.prepareStatement(employeeQuery);
                ResultSet employeeRs = employeePstmt.executeQuery();

                if (employeeRs.next()) {
                    int employeeId = employeeRs.getInt("employee_id");

                    // Insert into check_in (no check_out_date)
                    String insertQuery = "INSERT INTO check_in (booking_id, employee_id, check_in_date, room_type, total_price) " +
                                       "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertPstmt = conn.prepareStatement(insertQuery);
                    insertPstmt.setInt(1, bookingId);
                    insertPstmt.setInt(2, employeeId);
                    insertPstmt.setDate(3, bookingDate); // Check-in date only
                    insertPstmt.setString(4, roomType);
                    insertPstmt.setDouble(5, totalPrice);

                    int rowsAffected = insertPstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Check-in confirmed successfully for Booking ID: " + bookingId);
                        loadBookingData(); // Refresh table
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to confirm check-in");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No employees available for check-in");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Booking not found");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error during check-in: " + e.getMessage());
        }
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBookingDate = new javax.swing.JTextField();
        txtRoomType = new javax.swing.JTextField();
        txtTotalPrice = new javax.swing.JTextField();
        txtSearchId = new javax.swing.JTextField();
        btnCustomerSearchById = new javax.swing.JButton();
        btnConfirmBooking = new javax.swing.JButton();

        setBackground(new java.awt.Color(245, 238, 220));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Booking Date", "Customer ID", "Room Type", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Phone Number");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Email");

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

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("First Name");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Last Name");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Booking Date");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("Room Type");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setText("Total Price");

        txtBookingDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookingDateActionPerformed(evt);
            }
        });

        txtRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomTypeActionPerformed(evt);
            }
        });

        txtTotalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPriceActionPerformed(evt);
            }
        });

        txtSearchId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchIdActionPerformed(evt);
            }
        });

        btnCustomerSearchById.setText("Search");
        btnCustomerSearchById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerSearchByIdActionPerformed(evt);
            }
        });

        btnConfirmBooking.setText("Confirm Booking");
        btnConfirmBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(30, 30, 30)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel6))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnCustomerSearchById)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearchId))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGap(25, 25, 25))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBookingDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRoomType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotalPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConfirmBooking)
                        .addContainerGap(325, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCustomerSearchById)
                            .addComponent(txtSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirmBooking)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void txtBookingDateActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void txtRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void txtTotalPriceActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void txtSearchIdActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnCustomerSearchByIdActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        // TODO add your handling code here:
        String searchId = txtSearchId.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Booking ID to search");
            loadBookingData();
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT booking_id, booking_date, customer_id, room_type, total_price FROM booking WHERE booking_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(searchId));
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Booking ID", "Booking Date", "Customer ID", "Room Type", "Total Price"}
            );

            if (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("booking_id"),
                    rs.getDate("booking_date"),
                    rs.getInt("customer_id"),
                    rs.getString("room_type"),
                    rs.getDouble("total_price")
                });
                jTable1.setModel(model);
                selectedBookingId = rs.getInt("booking_id");
                populateFields(selectedBookingId);
            } else {
                JOptionPane.showMessageDialog(this, "No booking found with ID: " + searchId);
                loadBookingData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching booking: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Booking ID number");
        }
    }                                                     

    private void btnConfirmBookingActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
        if (selectedBookingId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking from the table first");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Confirm check-in for Booking ID: " + selectedBookingId + "?",
            "Confirm Check-In",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            performCheckIn(selectedBookingId);
        }
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnConfirmBooking;
    private javax.swing.JButton btnCustomerSearchById;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBookingDate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtRoomType;
    private javax.swing.JTextField txtSearchId;
    private javax.swing.JTextField txtTotalPrice;
    // End of variables declaration                   
}
