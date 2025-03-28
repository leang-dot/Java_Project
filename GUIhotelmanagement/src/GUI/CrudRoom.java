
package GUI;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.DatabaseConnection;
public class CrudRoom extends javax.swing.JPanel {
    private int selectedRoomId = -1;
    
    public CrudRoom() {
        initComponents();
        loadRoomData();

        // Add table selection listener
        tblEmployee.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblEmployee.getSelectedRow() != -1) {
                int selectedRow = tblEmployee.getSelectedRow();
                selectedRoomId = Integer.parseInt(tblEmployee.getValueAt(selectedRow, 0).toString());
                txtRoomType.setText(tblEmployee.getValueAt(selectedRow, 1).toString());
                txtPrice.setText(tblEmployee.getValueAt(selectedRow, 2).toString());
                txtAvailability.setText(tblEmployee.getValueAt(selectedRow, 3).toString());
            }
        });
    }

    private void clearFields() {
        txtRoomType.setText("");
        txtPrice.setText("");
        txtAvailability.setText("");
        txtSearchId.setText("");
    }

    private void loadRoomData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT room_id, room_type, price, availability FROM room";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Room ID", "Room Type", "Price", "Availability"}
            );

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("room_id"),
                    rs.getString("room_type"),
                    rs.getDouble("price"),
                    rs.getString("availability")
                });
            }

            tblEmployee.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading room data: " + e.getMessage());
        }
    }

    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnAddRoom = new javax.swing.JButton();
        btnUpdateRoom = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        txtRoomType = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtAvailability = new javax.swing.JTextField();
        btnSearchRoomById = new javax.swing.JButton();
        txtSearchId = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 238, 220));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Availability");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Room Type");

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Room ID", "Room Type", "Price", "Availability"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEmployee);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Price");

        btnAddRoom.setText("Add");
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });

        btnUpdateRoom.setText("Update");
        btnUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRoomActionPerformed(evt);
            }
        });

        btnDeleteRoom.setText("Delete");
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });

        txtRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomTypeActionPerformed(evt);
            }
        });

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        txtAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAvailabilityActionPerformed(evt);
            }
        });

        btnSearchRoomById.setText("Search");
        btnSearchRoomById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRoomByIdActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRoomType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSearchRoomById)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchId)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddRoom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateRoom)
                        .addGap(77, 77, 77)
                        .addComponent(btnDeleteRoom))
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
                            .addComponent(txtRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(213, 213, 213)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRoom)
                    .addComponent(btnUpdateRoom)
                    .addComponent(btnDeleteRoom)
                    .addComponent(btnSearchRoomById)
                    .addComponent(txtSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>                        

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        String roomType = txtRoomType.getText().trim();
        String price = txtPrice.getText().trim();
        String availability = txtAvailability.getText().trim();

        // Validation
        if (roomType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Room Type is required");
            return;
        }
        if (price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Price is required");
            return;
        }
        if (availability.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Availability is required");
            return;
        }
        if (!price.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number");
            return;
        }
        if (!availability.matches("(?i)^(yes|no)$")) {
            JOptionPane.showMessageDialog(this, "Availability must be 'Yes' or 'No'");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO room (room_type, price, availability) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, roomType);
            pstmt.setDouble(2, Double.parseDouble(price));
            pstmt.setString(3, availability.toUpperCase());

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Room added successfully!");
                clearFields();
                loadRoomData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding room: " + e.getMessage());
        }
    }                                          

    private void txtRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void txtAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void btnUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        if (selectedRoomId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room from the table first");
            return;
        }

        String roomType = txtRoomType.getText().trim();
        String price = txtPrice.getText().trim();
        String availability = txtAvailability.getText().trim();

        // Validation
        if (roomType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Room Type is required");
            return;
        }
        if (price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Price is required");
            return;
        }
        if (availability.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Availability is required");
            return;
        }
        if (!price.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number");
            return;
        }
        if (!availability.matches("(?i)^(yes|no)$")) {
            JOptionPane.showMessageDialog(this, "Availability must be 'Yes' or 'No'");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE room SET room_type = ?, price = ?, availability = ? WHERE room_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, roomType);
            pstmt.setDouble(2, Double.parseDouble(price));
            pstmt.setString(3, availability.toUpperCase());
            pstmt.setInt(4, selectedRoomId);

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Room updated successfully!");
                clearFields();
                loadRoomData();
                selectedRoomId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update room");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating room: " + e.getMessage());
        }
    }                                             

    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        if (selectedRoomId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room from the table first");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete Room ID " + selectedRoomId + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "DELETE FROM room WHERE room_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, selectedRoomId);

                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Room deleted successfully!");
                    clearFields();
                    loadRoomData();
                    selectedRoomId = -1;
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete room");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting room: " + e.getMessage());
            }
        }
    }                                             

    private void txtSearchIdActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnSearchRoomByIdActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
        String searchId = txtSearchId.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Room ID to search");
            loadRoomData();
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT room_id, room_type, price, availability FROM room WHERE room_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(searchId));
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Room ID", "Room Type", "Price", "Availability"}
            );

            if (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("room_id"),
                    rs.getString("room_type"),
                    rs.getDouble("price"),
                    rs.getString("availability")
                });
                tblEmployee.setModel(model);
                selectedRoomId = rs.getInt("room_id");
                txtRoomType.setText(rs.getString("room_type"));
                txtPrice.setText(String.valueOf(rs.getDouble("price")));
                txtAvailability.setText(rs.getString("availability"));
            } else {
                JOptionPane.showMessageDialog(this, "No room found with ID: " + searchId);
                loadRoomData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching room: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Room ID number");
        }
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnSearchRoomById;
    private javax.swing.JButton btnUpdateRoom;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtAvailability;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRoomType;
    private javax.swing.JTextField txtSearchId;
    // End of variables declaration                   
}
