package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.DatabaseConnection;

public class Customer extends javax.swing.JFrame {

        public Customer() {
                initComponents();
        }

        public Customer(String firstname, String lastname, String email) { // Constructor for login
                initComponents();
                jLabel1.setText("<html>Welcome <br>" + firstname + " " + lastname + "</html>");
                jLabel7.setText("Email: " + email);
                loadRoomData();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

                panelBorder2 = new GUI.PanelBorder();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jLabel6 = new javax.swing.JLabel();
                menu1 = new GUI.Menu();
                jLabel1 = new javax.swing.JLabel();
                btnLogOut = new javax.swing.JButton();
                btnViewRoom = new javax.swing.JButton();
                btnBooking = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setUndecorated(true);

                panelBorder2.setBackground(new java.awt.Color(204, 204, 204));
                panelBorder2.setBorder(
                                javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null,
                                                null, java.awt.Color.darkGray, java.awt.Color.darkGray));
                panelBorder2.setOpaque(true);

                jTable1.setBackground(new java.awt.Color(255, 255, 255));
                jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                                new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255),
                                new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
                jTable1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null }
                                },
                                new String[] {
                                                "Room Type", "Price", "Availability"
                                }) {
                        Class[] types = new Class[] {
                                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class,
                                        java.lang.String.class
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }
                });
                jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jTable1MouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(jTable1);

                jLabel6.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                jLabel6.setText("ROOM");

                javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
                panelBorder2.setLayout(panelBorder2Layout);
                panelBorder2Layout.setHorizontalGroup(
                                panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelBorder2Layout.createSequentialGroup()
                                                                .addGroup(panelBorder2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelBorder2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                648,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panelBorder2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(331, 331, 331)
                                                                                                .addComponent(jLabel6)))
                                                                .addContainerGap(60, Short.MAX_VALUE)));
                panelBorder2Layout.setVerticalGroup(
                                panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelBorder2Layout.createSequentialGroup()
                                                                .addGap(48, 48, 48)
                                                                .addComponent(jLabel6)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(110, Short.MAX_VALUE)));

                jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(245, 238, 220));
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/User.png"))); // NOI18N
                jLabel1.setText(" Customer");

                btnLogOut.setBackground(new java.awt.Color(39, 84, 138));
                btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
                btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
                btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logout.png"))); // NOI18N
                btnLogOut.setText("LOGOUT");
                btnLogOut.setActionCommand("LogOut");
                btnLogOut.setBorder(null);
                btnLogOut.setBorderPainted(false);
                btnLogOut.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnLogOutActionPerformed(evt);
                        }
                });

                btnViewRoom.setBackground(new java.awt.Color(39, 84, 138));
                btnViewRoom.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
                btnViewRoom.setForeground(new java.awt.Color(245, 238, 220));
                btnViewRoom.setText("View Room");
                btnViewRoom.setBorder(null);
                btnViewRoom.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnViewRoomActionPerformed(evt);
                        }
                });

                btnBooking.setBackground(new java.awt.Color(39, 84, 138));
                btnBooking.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
                btnBooking.setForeground(new java.awt.Color(245, 238, 220));
                btnBooking.setText("Booking History ");
                btnBooking.setBorder(null);
                btnBooking.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBookingActionPerformed(evt);
                        }
                });

                jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
                jLabel2.setText("Menu");

                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/menu.png"))); // NOI18N

                jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
                jLabel7.setText("Email:");

                javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
                menu1.setLayout(menu1Layout);
                menu1Layout.setHorizontalGroup(
                                menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(menu1Layout.createSequentialGroup()
                                                                .addGroup(menu1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(menu1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jLabel3)
                                                                                                .addGroup(menu1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(menu1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(146, 146,
                                                                                                                                                146)
                                                                                                                                .addComponent(jLabel4))
                                                                                                                .addGroup(menu1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(20, 20, 20)
                                                                                                                                .addGroup(menu1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel7)
                                                                                                                                                .addGroup(menu1Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addComponent(jLabel5)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                .addGroup(menu1Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(btnViewRoom)
                                                                                                                                                                                .addComponent(jLabel2)
                                                                                                                                                                                .addComponent(btnBooking)))
                                                                                                                                                .addComponent(btnLogOut,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                92,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                                .addGroup(menu1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(15, 15, 15)
                                                                                                .addComponent(jLabel1)))
                                                                .addContainerGap(39, Short.MAX_VALUE)));
                menu1Layout.setVerticalGroup(
                                menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(menu1Layout.createSequentialGroup()
                                                                .addGap(15, 15, 15)
                                                                .addComponent(jLabel1)
                                                                .addGap(15, 15, 15)
                                                                .addGroup(menu1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jLabel3))
                                                                .addGap(14, 14, 14)
                                                                .addComponent(jLabel7)
                                                                .addGap(49, 49, 49)
                                                                .addGroup(menu1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(menu1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel2)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnViewRoom)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnBooking))
                                                                                .addComponent(jLabel5))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnLogOut,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                49,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addComponent(menu1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(panelBorder2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
                setLocationRelativeTo(null);
        }

        private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void btnViewRoomActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
                MainForm.main(null);
                this.dispose();
        }

        private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int column = jTable1.columnAtPoint(evt.getPoint());

                // Check if the clicked column is roomType (index 0)
                if (column == 0) {
                        String roomType = jTable1.getValueAt(row, 0).toString();
                        int availability = (int) jTable1.getValueAt(row, 2);

                        // Assuming you already have user info and booking date
                        String userName = "John Doe"; // Replace with actual user data
                        String userEmail = "johndoe@example.com"; // Replace with actual user data
                        String bookingDate = java.time.LocalDate.now().toString(); // Current date as booking date

                        // Show the booking details first (before confirmation)
                        int detailsOption = showBookingDetails(roomType, userName, userEmail, bookingDate);

                        // If details window is closed without clicking OK, skip confirmation
                        if (detailsOption == JOptionPane.OK_OPTION) {
                                // Show confirmation dialog (popup modal)
                                int confirmOption = JOptionPane.showConfirmDialog(
                                                this, // parent component
                                                "Do you want to confirm booking for room " + roomType + "?",
                                                "Confirm Booking", // title
                                                JOptionPane.YES_NO_OPTION, // options (Yes/No)
                                                JOptionPane.QUESTION_MESSAGE); // message type

                                // Handle the user's response
                                if (confirmOption == JOptionPane.YES_OPTION) {
                                        // Proceed with booking logic
                                        System.out.println("Booking confirmed for room " + roomType);
                                        updateRoomAvailability(roomType, availability);
                                        loadRoomData();
                                        // You can also open another page or proceed with saving the booking data
                                } else {
                                        // Handle cancellation
                                        System.out.println("Booking cancelled for room " + roomType);
                                }
                        } else {
                                // If user closed details popup, skip confirmation
                                System.out.println("Booking details modal closed without confirmation.");
                        }
                }
        }

        private int showBookingDetails(String roomType, String userName, String userEmail, String bookingDate) {
                // Create the message for the booking details popup
                String message = "Room: " + roomType + "\n"
                                + "User: " + userName + "\n"
                                + "Email: " + userEmail + "\n"
                                + "Booking Date: " + bookingDate;

                // Show the booking details in a JOptionPane and capture the user's response
                return JOptionPane.showOptionDialog(
                                this,
                                message,
                                "Booking Details",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[] { "Booking" }, // Only "OK" button for the user to close the details
                                "Confirm Booking"); // Default button title
        }

        private void updateRoomAvailability(String roomType, int availability) {
                if (availability > 0) {
                        // Decrease availability by 1
                        availability--;

                        // Database update to decrement availability (assuming column is called
                        // 'availability')
                        String sql = "UPDATE room SET availability = ? WHERE room_type = ?";

                        try (Connection conn = DatabaseConnection.getConnection();
                                        PreparedStatement ps = conn.prepareStatement(sql)) {

                                // Set the new availability and room type in the query
                                ps.setInt(1, availability);
                                ps.setString(2, roomType);

                                // Execute the update query
                                int rowsAffected = ps.executeUpdate();

                                if (rowsAffected > 0) {
                                        System.out.println("Room type " + roomType + " availability updated to "
                                                        + availability);
                                } else {
                                        System.out.println("Error updating availability for room type " + roomType);
                                }
                        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, "Error updating availability: " + e.getMessage());
                        }
                } else {
                        JOptionPane.showMessageDialog(this, "No rooms available for " + roomType);
                }
        }

        public void loadRoomData() {
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(new String[] { "RoomType", "RoomPrice", "Availability" });

                try (Connection conn = DatabaseConnection.getConnection();
                                PreparedStatement ps = conn.prepareStatement(
                                                "SELECT room_type, price, availability FROM room");
                                ResultSet rs = ps.executeQuery()) {

                        while (rs.next()) {
                                String roomType = rs.getString("room_type");
                                double price = rs.getDouble("price");
                                int availability = rs.getInt("availability");

                                model.addRow(new Object[] { roomType, price, availability });
                        }

                        jTable1.setModel(model); // Set data to jTable1

                } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
                }
        }

        public static void main(String args[]) {

                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new Customer().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify

        private javax.swing.JButton btnBooking;
        private javax.swing.JButton btnLogOut;
        private javax.swing.JButton btnViewRoom;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;
        private GUI.Menu menu1;
        private GUI.PanelBorder panelBorder2;
}
