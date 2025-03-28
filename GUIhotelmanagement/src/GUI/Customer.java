package GUI;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JComponent;

public class Customer extends javax.swing.JFrame {
    String firstname, lastname, email;
    int customerId;

    public Customer(String firstname, String lastname, int customerId, String email) { // Constructor for login
        initComponents();
        jLabel1.setText("<html>Welcome! <br>" + lastname + " " + firstname + "</html>");
        txtSearch.setText("");
        txtSearch.setForeground(Color.GRAY);
        this.firstname = firstname;
        this.lastname = lastname;
        this.customerId = customerId;
        this.email = email;
        setForm(new CrudViewRoom(firstname, lastname, customerId, email));
    }

    private void initComponents() {

        menu = new GUI.Menu();
        userIcon = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menuList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        userIcon.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        userIcon.setForeground(new java.awt.Color(245, 238, 220));
        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/User.png"))); // NOI18N

        btnLogOut.setBackground(new java.awt.Color(39, 84, 138));
        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(245, 238, 220));
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

        menuList.setBackground(new java.awt.Color(39, 84, 138));
        menuList.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 30, 5, 5));
        menuList.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        menuList.setForeground(new java.awt.Color(245, 238, 220));
        menuList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "View Room", "Booking History" };

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        menuList.setOpaque(false);
        menuList.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                menuListComponentAdded(evt);
            }
        });
        menuList.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menuListMouseMoved(evt);
            }
        });
        menuList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuListMouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/menu.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Menu");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(245, 238, 220));
        jLabel1.setText("Customer");

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGap(295, 295, 295)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGroup(menuLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(menuLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(userIcon)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(menuLayout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2))
                                        .addComponent(menuList, javax.swing.GroupLayout.PREFERRED_SIZE, 215,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(menuLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnLogOut)))
                                .addGap(0, 0, Short.MAX_VALUE)));
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(menuLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(userIcon))
                                        .addGroup(menuLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel2))
                                .addGap(0, 0, 0)
                                .addComponent(menuList, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                .addGap(13, 13, 13)
                                .addComponent(btnLogOut)
                                .addContainerGap()));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(4, 4, 4))
                        .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING));

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 215,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {
        Login.main(null);
        this.dispose();

    }

    private void menuListComponentAdded(java.awt.event.ContainerEvent evt) {
    }

    private void menuListMouseMoved(java.awt.event.MouseEvent evt) {
        // Get the index of the item under the mouse cursor
        Point point = evt.getPoint();
        int index = menuList.locationToIndex(point);

        // If the mouse is over a valid item, select it (highlight it)
        if (index >= 0 && menuList.getCellBounds(index, index).contains(point)) {
            menuList.setSelectedIndex(index);
        } else {
            // Clear selection if the mouse is not over any item
            menuList.clearSelection();
        }

    }

    private void menuListMouseClicked(java.awt.event.MouseEvent evt) {
        // Get the index of the clicked item
        int index = menuList.locationToIndex(evt.getPoint());

        // Check if a valid item was clicked
        if (index >= 0 && menuList.getCellBounds(index, index).contains(evt.getPoint())) {
            String selectedItem = menuList.getModel().getElementAt(index);
            switch (selectedItem) {
                case "Booking History":
                    setForm(new Booking_History());
                    break;
                case "View Room":
                    setForm(new CrudViewRoom(firstname, lastname, customerId, email));
                    break;
            }
        }
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private boolean isPlaceholder = true;

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private GUI.Menu menu;
    private javax.swing.JList<String> menuList;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JLabel userIcon;
    // End of variables declaration
}
