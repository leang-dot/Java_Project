/*
 * MIT License
 *
 * Copyright (c) [Year] [Your Name]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */
package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DataBase.DatabaseConnection;
import com.toedter.calendar.JDateChooser;


public class CrudViewRoom extends javax.swing.JPanel {

    
    String firstname, lastname, customerEmail;
    int customerId;
    String bookingDate = java.time.LocalDate.now().toString();

    public CrudViewRoom(String firstname, String lastname, int customerId, String customerEmail) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        initComponents();
        loadRoomData();
    }


    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        JLabel bookDateLabel = new JLabel("Book Date:");
        JDateChooser bookDateChooser = new JDateChooser();
        bookDateChooser.setDateFormatString("yyyy-MM-dd"); 
        bookDateChooser.setDate(new Date());

        // Set the minimum selectable date to today
        bookDateChooser.setMinSelectableDate(new Date()); 

        // Create a button next to the Book Date field
        JButton bookDateButton = new JButton("Search");
        // You can add an action listener to this button for handling clicks
        bookDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Action to perform when the button is clicked
                if (bookDateChooser.getDate() != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    bookingDate = dateFormat.format(bookDateChooser.getDate());
                    loadRoomData();
                }
            }
        });
        setBackground(new java.awt.Color(245, 238, 220));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        // Layout setup for the panel
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(bookDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bookDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bookDateButton) // Add the button next to the date chooser
                                .addContainerGap(52, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(54, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bookDateLabel)
                                        .addComponent(bookDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bookDateButton)) // Add the button next to the date chooser
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE)));
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.rowAtPoint(evt.getPoint());
        int column = jTable1.columnAtPoint(evt.getPoint());

        // Check if the clicked column is roomType (index 0)
        if (column == 0) {
            String roomType = jTable1.getValueAt(row, 0).toString();
            String roomPrice = jTable1.getValueAt(row, 1).toString();
            int availability = (int) jTable1.getValueAt(row, 2);

            if (availability == 0) {
                // Show a message modal indicating that the room is unavailable
                JOptionPane.showMessageDialog(
                        this, // parent component
                        "Sorry, the room " + roomType + " is currently unavailable.",
                        "Room Unavailable", // title
                        JOptionPane.INFORMATION_MESSAGE // message type
                );
                return; // Exit the method if the room is unavailable
            }

            // Assuming you already have user info and booking date
            String customerName = lastname + " " + firstname;

            // Show the booking details first (before confirmation)
            int detailsOption = showBookingDetails(roomType, customerName, customerEmail, bookingDate);

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
                    insertBookingIntoDatabase(roomType, customerId, bookingDate,
                            Double.parseDouble(roomPrice));
                    jTable1.setValueAt(availability - 1, row, 2);
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
                new Object[] { "Booking" }, 
                "Confirm Booking"); 
    }

    private void insertBookingIntoDatabase(String roomType, int customerId,
            String bookingDate, double totalPrice) {

        String sql = "INSERT INTO booking (booking_date, customer_id, total_price, room_type) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            pst.setString(1, bookingDate); 
            pst.setInt(2, customerId); 
            pst.setDouble(3, totalPrice); 
            pst.setString(4, roomType); 

            pst.executeUpdate();
            System.out.println("Booking inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
        }
    }

    public void loadRoomData() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] { "Room Type", "Room Price", "Availability" });

        String sql = "SELECT r.room_type, r.price, (r.availability - " +
                "(SELECT COUNT(*) FROM booking WHERE booking_date = ? AND room_type = r.room_type)) AS available_rooms "
                +
                "FROM room r";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bookingDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        Math.max(rs.getInt("available_rooms"), 0)
                });
            }

            jTable1.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
