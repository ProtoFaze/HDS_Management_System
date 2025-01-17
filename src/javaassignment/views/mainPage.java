/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaassignment.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javaassignment.models.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author damonng
 */
public class mainPage extends javax.swing.JFrame {
//    public static String latestSID, latestBID;
            
    Staff staff;
    static CardLayout cardLayout;
    private int currentOperation = 1;
    private int todaysJobs;
    /**
     * Creates new form mainPage
     * @param staffInfo
     */
    public mainPage(Staff staffInfo) {
        //set to fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        staff = staffInfo;
        initComponents();
        cardLayout = (CardLayout) contentPanel.getLayout();
        
        String[] name = staff.getName().split(" ");
        lblDisplayName.setText("Welcome back, "+name[0]);
        //reset panel with new constructors
        availableRooms1 = new javaassignment.views.AvailableRooms(this,(BookingSummary) contentPanel.getComponent(3),(EditBooking) contentPanel.getComponent(5)); 
        newCustomer1 = new javaassignment.views.NewCustomer(this,(BookingSummary) contentPanel.getComponent(3));
        searchCustomer1 = new javaassignment.views.SearchCustomer(this,(BookingSummary) contentPanel.getComponent(3),(EditBooking) contentPanel.getComponent(5));
        bookingSummary1 = new javaassignment.views.BookingSummary(this);
        searchBooking1 = new javaassignment.views.SearchBooking(this, (EditBooking) contentPanel.getComponent(5));
        editBooking1 = new javaassignment.views.EditBooking(this);
        roomChecking1 = new javaassignment.views.RoomChecking(this,(BookingSummary) contentPanel.getComponent(3));
        contentPanel.removeAll();
        contentPanel.add(availableRooms1, "availableRoomsPanel");
        contentPanel.add(newCustomer1, "newCustomerPanel");
        contentPanel.add(searchCustomer1, "searchCustomerPanel");
        contentPanel.add(bookingSummary1, "bookingSummaryPanel");
        contentPanel.add(searchBooking1, "searchBookingPanel");
        contentPanel.add(editBooking1, "editBookingPanel");
        contentPanel.add(roomChecking1, "roomCheckingPanel");
        
        if(todaysJobs<1){
            JOptionPane.showMessageDialog(null, """
                                                For now, There are no bookings for Check-in and Check-out today,
                                                but please pay attention for any last-minute bookings,
                                                you will likely receive them from 
                                                the manager,
                                                the Reception desk telephone or 
                                                a message from the branch's whatsapp account""");
        }
    }
    /**
     *
     * @param OpsCode
     * sets currentOperation to the specified opsCode,
     * 0 for create, 
     * 1 for read, 
     * 2 for edit, 
     * 3 for delete
     * use as shared identifiers to running specific functions
     */
    public void setCurOps(int OpsCode){
        currentOperation = OpsCode;
    }

    /**
     *Sets the todaysJobs integer
     * @param UsedRoomsForToday
     * used for displaying a no check-ins or check-outs today JOptionPane
     */
    public void setTodaysJobs(int UsedRoomsForToday){
        this.todaysJobs = UsedRoomsForToday;
    }
    /**
     *resets CurrentOperations back to the default readMode
     */
    public void resetCurOps(){
        currentOperation = 1;
    }
    public int getCurOps(){
        return currentOperation;
    }
    public void changePanel(String LayoutKey){
        cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, LayoutKey);
    }
//    this,(BookingSummary) contentPanel.getComponent(3)
//    this,(AvailableRooms) contentPanel.getComponent(0),(NewCustomer) contentPanel.getComponent(1),(SearchCustomer)contentPanel.getComponent(2)
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        topPanel = new javax.swing.JPanel();
        lblDisplayName = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        navPanel = new javax.swing.JPanel();
        btnAvailableRooms = new javax.swing.JButton();
        btnSrchCustomer = new javax.swing.JButton();
        btnNewCustomer = new javax.swing.JButton();
        btnBookingSummary = new javax.swing.JButton();
        searchBooking = new javax.swing.JButton();
        btnEditBooking = new javax.swing.JButton();
        btnCheckRooms = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        availableRooms1 = new javaassignment.views.AvailableRooms(this);
        newCustomer1 = new javaassignment.views.NewCustomer(this);
        searchCustomer1 = new javaassignment.views.SearchCustomer(this);
        bookingSummary1 = new javaassignment.views.BookingSummary();
        searchBooking1 = new javaassignment.views.SearchBooking(this);
        editBooking1 = new javaassignment.views.EditBooking(this);
        roomChecking1 = new javaassignment.views.RoomChecking(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topPanel.setBackground(new java.awt.Color(173, 222, 253));
        topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(255, 153, 51);
                Color color2 = new Color(230, 230, 230);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, 0, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };

        lblDisplayName.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblDisplayName.setForeground(new java.awt.Color(75, 75, 75));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaassignment/pictures/logo.jpg"))); // NOI18N

        Name.setFont(new java.awt.Font("PT Sans Caption", 0, 24)); // NOI18N
        Name.setText("Hotel Diving Sunset");

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDisplayName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(103, 103, 103)
                .addComponent(btnExit))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(Name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Logo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(lblDisplayName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        navPanel.setBackground(new java.awt.Color(102, 153, 255));
        navPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(102, 102, 255);
                Color color2 = new Color(153, 255, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };

        btnAvailableRooms.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnAvailableRooms.setText("AvailableRooms");
        btnAvailableRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvailableRoomsActionPerformed(evt);
            }
        });

        btnSrchCustomer.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnSrchCustomer.setText("Search Customer");
        btnSrchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrchCustomerActionPerformed(evt);
            }
        });

        btnNewCustomer.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnNewCustomer.setText("New Customer");
        btnNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCustomerActionPerformed(evt);
            }
        });

        btnBookingSummary.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnBookingSummary.setText("Booking Summary");
        btnBookingSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingSummaryActionPerformed(evt);
            }
        });

        searchBooking.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        searchBooking.setText("Search Bookings");
        searchBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBookingActionPerformed(evt);
            }
        });

        btnEditBooking.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnEditBooking.setText("Edit Booking");
        btnEditBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBookingActionPerformed(evt);
            }
        });

        btnCheckRooms.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        btnCheckRooms.setText("Manage Rooms");
        btnCheckRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckRoomsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navPanelLayout = new javax.swing.GroupLayout(navPanel);
        navPanel.setLayout(navPanelLayout);
        navPanelLayout.setHorizontalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAvailableRooms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSrchCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNewCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBookingSummary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEditBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCheckRooms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        navPanelLayout.setVerticalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navPanelLayout.createSequentialGroup()
                .addComponent(btnAvailableRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSrchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNewCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBookingSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCheckRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        contentPanel.setBackground(new java.awt.Color(153, 153, 255));
        contentPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(69, 69, 69), 1, true));
        contentPanel.setLayout(new java.awt.CardLayout());
        contentPanel.add(availableRooms1, "availableRoomsPanel");
        contentPanel.add(newCustomer1, "newCustomerPanel");
        contentPanel.add(searchCustomer1, "searchCustomerPanel");
        contentPanel.add(bookingSummary1, "bookingSummaryPanel");
        contentPanel.add(searchBooking1, "searchBookingPanel");
        contentPanel.add(editBooking1, "editBookingPanel");
        contentPanel.add(roomChecking1, "roomCheckingPanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCustomerActionPerformed
        cardLayout.show(contentPanel,"newCustomerPanel");
    }//GEN-LAST:event_btnNewCustomerActionPerformed

    private void btnSrchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrchCustomerActionPerformed
        cardLayout.show(contentPanel,"searchCustomerPanel");
    }//GEN-LAST:event_btnSrchCustomerActionPerformed

    private void btnEditBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBookingActionPerformed
        this.changePanel("editBookingPanel");
    }//GEN-LAST:event_btnEditBookingActionPerformed

    private void btnCheckRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckRoomsActionPerformed
        cardLayout.show(contentPanel,"roomCheckingPanel");
    }//GEN-LAST:event_btnCheckRoomsActionPerformed

    private void btnAvailableRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvailableRoomsActionPerformed
        cardLayout.show(contentPanel,"availableRoomsPanel");
    }//GEN-LAST:event_btnAvailableRoomsActionPerformed

    private void btnBookingSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingSummaryActionPerformed
        cardLayout.show(contentPanel,"bookingSummaryPanel");
    }//GEN-LAST:event_btnBookingSummaryActionPerformed

    private void searchBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookingActionPerformed
        cardLayout.show(contentPanel,"searchBookingPanel");
    }//GEN-LAST:event_searchBookingActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int n = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit the system.","Confirm Exit",JOptionPane.YES_NO_OPTION);
        if(n==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"Thank you for using the system.","Exiting registration",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new mainPage().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Name;
    private javaassignment.views.AvailableRooms availableRooms1;
    private javaassignment.views.BookingSummary bookingSummary1;
    private javax.swing.JButton btnAvailableRooms;
    private javax.swing.JButton btnBookingSummary;
    private javax.swing.JButton btnCheckRooms;
    private javax.swing.JButton btnEditBooking;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNewCustomer;
    private javax.swing.JButton btnSrchCustomer;
    private javax.swing.JPanel contentPanel;
    private javaassignment.views.EditBooking editBooking1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDisplayName;
    private javax.swing.JPanel navPanel;
    private javaassignment.views.NewCustomer newCustomer1;
    private javaassignment.views.RoomChecking roomChecking1;
    private javax.swing.JButton searchBooking;
    private javaassignment.views.SearchBooking searchBooking1;
    private javaassignment.views.SearchCustomer searchCustomer1;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
