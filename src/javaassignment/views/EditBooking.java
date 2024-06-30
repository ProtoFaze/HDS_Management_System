/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaassignment.views;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javaassignment.models.Booking;
import javaassignment.verify;
import javax.swing.JOptionPane;

/**
 *
 * @author damonng
 */
public class EditBooking extends javax.swing.JPanel {
    private static String roomNum,type,descr,cusID,cusName,cusEmail,cusIC,cusPNum,targetBookingID;
    private static LocalDate start, end, cusBD;
    private static int floor;
    private static long daysDiff;
    private static char cusGender;
    private static double total, roomPrice;
    private mainPage ParentFrame;
    private ArrayList<Booking> bookings = Booking.listBookings();
    /**
     * Creates new form EditBooking
     */
    public EditBooking() {
        initComponents();
    }
    //integrates with mainframe to use its data and methods
    public EditBooking(mainPage ParentFrame) {
        this.ParentFrame = ParentFrame;
        initComponents();
        lblRoomDescr.setEnabled(false);
    }

    public void setRoomDetails(Object ID, Object Type, Object Description, Object startDate, Object endDate, Object RoomPrice, Object FloorNumber){
        roomNum = ID.toString();
        type = Type.toString();
        descr = Description.toString();
        roomPrice = Double.parseDouble(RoomPrice.toString());
        floor = Integer.parseInt(FloorNumber.toString());
        lblRoomNum.setText("Room Number: "+roomNum);
        lblRoomType.setText("Type: "+type);
        lblRoomDescr.setText("Description: \n"+descr);
        lblBookingDateStart.setText("From: "+startDate.toString());
        lblBookingDateEnd.setText("To: "+endDate.toString());
        lblRoomPrice.setText("RM"+RoomPrice.toString());
        lblFloorNumber.setText("Floor: "+floor);
        // Set the dates in the JDateChooser components
        start = LocalDate.parse(startDate.toString(), verify.formatter);
        end = LocalDate.parse(endDate.toString(), verify.formatter);

        // Calculate the time difference between the start and end dates in days
        daysDiff = ChronoUnit.DAYS.between(start, end);
        double pretax = (daysDiff*(roomPrice+Booking.TOURISMTAX));
        double postTax=pretax*Booking.SERVICETAX;
        DecimalFormat df = new DecimalFormat("#.##"); // round off to 2 decimal places
        total = Double.parseDouble(df.format(postTax));
        lblTotal.setText("RM"+Double.toString(total));        
    }
    public void resetRoomDetails(){
        lblRoomNum.setText("Room Number: ");
        lblRoomType.setText("Type: ");
        lblRoomDescr.setText("Description: \nthere is nothing to describe as there is no Booking details submitted, or the previous booking edit has completed");
        lblBookingDateStart.setText("From: ");
        lblBookingDateEnd.setText("To: ");
        lblRoomPrice.setText("RM0.00");
        lblFloorNumber.setText("Floor: ");
        lblTotal.setText("RM0.00");
        targetBookingID = null;
    }
        
    public void setCustomerDetails(Object ID,Object CusName, Object CusMail, Object CusGen, Object cusBDate, Object CusIC, Object CusPNum){
        cusID = ID.toString();
        cusName = CusName.toString();
        cusEmail = CusMail.toString();
        cusGender = CusGen.toString().charAt(0);
        cusBD = LocalDate.parse(cusBDate.toString(), verify.formatter);;
        cusIC = CusIC.toString();
        cusPNum = CusPNum.toString();
        lblCustomerID.setText("Customer's ID: "+cusID);
        lblCustomerName.setText("Customer's name: "+cusName);
        lblCustomerEmail.setText("Customer's E-mail: "+cusEmail);
        lblCustomerGender.setText("Customer's Gender: "+CusGen.toString());
        lblCustomerBirthDate.setText("Customer's Birth Date: "+cusBDate.toString());
        lblCustomerIC.setText("Customer's IC: "+cusIC);
        lblCustomerPNum.setText("Customer's Phone Number: "+cusPNum);
    }
    public void resetCustomerDetails(){
        lblCustomerID.setText("Customer's ID: ");
        lblCustomerName.setText("Customer's name: ");
        lblCustomerEmail.setText("Customer's E-mail: ");
        lblCustomerGender.setText("Customer's Gender: ");
        lblCustomerBirthDate.setText("Customer's Birth Date: ");
        lblCustomerIC.setText("Customer's IC: ");
        lblCustomerPNum.setText("Customer's Phone Number: ");
    }
    public void setEditTarget(Object BookingID){
        targetBookingID = BookingID.toString();
    }
    
    //getters
    public String getRoomId(){
        return roomNum;
    }
    public String getFloorNum(){
        return Integer.toString(floor);
    }
    public String getRoomType(){
        return type;
    }
    public String getRoomPrice(){
        return Double.toString(roomPrice);
    }
    public String getCustomerName(){
        return cusName;
    }
    public String getStartDate(){
        return start.format(verify.formatter);
    }
    public String getEndDate(){
        return end.format(verify.formatter);
    }
    public String getDaysDiff(){
        return Integer.toString((int) daysDiff);
    }
    public String getTotal(){
        return Double.toString(roomPrice);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageTitle = new javax.swing.JLabel();
        divider = new javax.swing.JSeparator();
        roomDetails = new javax.swing.JPanel();
        lblRoomNum = new javax.swing.JLabel();
        lblRoomType = new javax.swing.JLabel();
        lblBookingDateStart = new javax.swing.JLabel();
        lblDash = new javax.swing.JLabel();
        lblBookingDateEnd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblRoomDescr = new javax.swing.JTextArea();
        lblRoomPrice = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblFloorNumber = new javax.swing.JLabel();
        customerDetails = new javax.swing.JPanel();
        lblCustomerID = new javax.swing.JLabel();
        lblCustomerName = new javax.swing.JLabel();
        lblCustomerEmail = new javax.swing.JLabel();
        lblCustomerGender = new javax.swing.JLabel();
        lblCustomerIC = new javax.swing.JLabel();
        lblCustomerPNum = new javax.swing.JLabel();
        lblCustomerBirthDate = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnEditRoom = new javax.swing.JButton();
        btnEditCustomer = new javax.swing.JButton();
        btnConfirmUpdate = new javax.swing.JButton();

        pageTitle.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        pageTitle.setText("Change Details");
        pageTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        roomDetails.setBackground(new java.awt.Color(161, 225, 250));

        lblRoomNum.setText("Room no.");

        lblRoomType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRoomType.setText("Room Type");

        lblBookingDateStart.setText("Start Date");

        lblDash.setText("-");

        lblBookingDateEnd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBookingDateEnd.setText("End Date");

        lblRoomDescr.setBackground(new java.awt.Color(0, 255, 255));
        lblRoomDescr.setColumns(20);
        lblRoomDescr.setLineWrap(true);
        lblRoomDescr.setRows(5);
        lblRoomDescr.setText("this represents the text that will appear as room description");
        lblRoomDescr.setWrapStyleWord(true);
        jScrollPane1.setViewportView(lblRoomDescr);

        lblRoomPrice.setText("RM###.##");

        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("RM####.##");
        lblTotal.setToolTipText("");
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblFloorNumber.setText("Floor");

        javax.swing.GroupLayout roomDetailsLayout = new javax.swing.GroupLayout(roomDetails);
        roomDetails.setLayout(roomDetailsLayout);
        roomDetailsLayout.setHorizontalGroup(
            roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roomDetailsLayout.createSequentialGroup()
                        .addComponent(lblRoomNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFloorNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRoomType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roomDetailsLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(roomDetailsLayout.createSequentialGroup()
                        .addGroup(roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roomDetailsLayout.createSequentialGroup()
                                .addComponent(lblBookingDateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDash)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(roomDetailsLayout.createSequentialGroup()
                                .addComponent(lblRoomPrice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBookingDateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        roomDetailsLayout.setVerticalGroup(
            roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRoomNum)
                    .addComponent(lblRoomType)
                    .addComponent(lblFloorNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBookingDateStart)
                    .addComponent(lblDash)
                    .addComponent(lblBookingDateEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roomDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRoomPrice)
                    .addComponent(lblTotal))
                .addGap(6, 6, 6))
        );

        customerDetails.setBackground(new java.awt.Color(161, 225, 250));

        lblCustomerID.setText("ID");

        lblCustomerName.setText("Name");

        lblCustomerEmail.setText("Email");

        lblCustomerGender.setText("Gender");

        lblCustomerIC.setText("IC");

        lblCustomerPNum.setText("Phone");

        lblCustomerBirthDate.setText("BirthDate");

        javax.swing.GroupLayout customerDetailsLayout = new javax.swing.GroupLayout(customerDetails);
        customerDetails.setLayout(customerDetailsLayout);
        customerDetailsLayout.setHorizontalGroup(
            customerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCustomerPNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustomerIC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustomerGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustomerID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustomerBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        customerDetailsLayout.setVerticalGroup(
            customerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustomerID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCustomerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCustomerEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCustomerGender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCustomerBirthDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(lblCustomerIC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCustomerPNum)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnEditRoom.setText("Change Room");
        btnEditRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRoomActionPerformed(evt);
            }
        });

        btnEditCustomer.setText("Change Customer");
        btnEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomerActionPerformed(evt);
            }
        });

        btnConfirmUpdate.setText("Update");
        btnConfirmUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pageTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(divider)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditRoom))
                    .addComponent(roomDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnEditCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmUpdate)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(divider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roomDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnEditRoom)
                    .addComponent(btnEditCustomer)
                    .addComponent(btnConfirmUpdate))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRoomActionPerformed
        ParentFrame.setCurOps(2);
        ParentFrame.changePanel("availableRoomsPanel");
    }//GEN-LAST:event_btnEditRoomActionPerformed

    private void btnEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomerActionPerformed
        ParentFrame.setCurOps(2);
        ParentFrame.changePanel("searchCustomerPanel");
    }//GEN-LAST:event_btnEditCustomerActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        ParentFrame.resetCurOps();
        ParentFrame.changePanel("searchBookingPanel");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmUpdateActionPerformed
        String targetRoomID, targetCustomerID;
        System.out.println(targetBookingID);
        targetRoomID = roomNum;
        targetCustomerID = cusID;
        if(targetBookingID!=null){
            int n = JOptionPane.showConfirmDialog(null,"Are you sure the new information is correct?");
            if(n == JOptionPane.YES_OPTION){
                System.out.println("Target is "+this.targetBookingID);
                //find specific object and set its new values
                for (Booking booking : bookings){
                    if(booking.getID().equals(targetBookingID)){
                        System.out.println("Found match");
                        booking.setRoomID(targetRoomID);
                        booking.setGuestID(targetCustomerID);
                        booking.setStartDate(start);
                        booking.setEndDate(end);
                        booking.setTotalCost(total);
                        break;
                    }
                }
                String status = Booking.updateBooking(bookings);
                if (status.equals("Success")){
                    JOptionPane.showMessageDialog(ParentFrame, "Update Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    //update with new info
                    bookings = Booking.listBookings();
                    GenerateReceipt dataDestination = new GenerateReceipt(this);
                    dataDestination.setVisible(true);
                    resetRoomDetails();
                    resetCustomerDetails();
                }else{
                    JOptionPane.showMessageDialog(ParentFrame, "Update Failed due to "+status, "Error", JOptionPane.ERROR_MESSAGE);
                }
                ParentFrame.changePanel("availableRoomsPanel");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Please select a booking record to edit.");
            ParentFrame.changePanel("searchBookingPanel");
            
        }
    }//GEN-LAST:event_btnConfirmUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirmUpdate;
    private javax.swing.JButton btnEditCustomer;
    private javax.swing.JButton btnEditRoom;
    private javax.swing.JPanel customerDetails;
    private javax.swing.JSeparator divider;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lblBookingDateEnd;
    private static javax.swing.JLabel lblBookingDateStart;
    private static javax.swing.JLabel lblCustomerBirthDate;
    private static javax.swing.JLabel lblCustomerEmail;
    private static javax.swing.JLabel lblCustomerGender;
    private static javax.swing.JLabel lblCustomerIC;
    private static javax.swing.JLabel lblCustomerID;
    private static javax.swing.JLabel lblCustomerName;
    private static javax.swing.JLabel lblCustomerPNum;
    private static javax.swing.JLabel lblDash;
    private static javax.swing.JLabel lblFloorNumber;
    private static javax.swing.JTextArea lblRoomDescr;
    private static javax.swing.JLabel lblRoomNum;
    private static javax.swing.JLabel lblRoomPrice;
    private static javax.swing.JLabel lblRoomType;
    private static javax.swing.JLabel lblTotal;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JPanel roomDetails;
    // End of variables declaration//GEN-END:variables
}
