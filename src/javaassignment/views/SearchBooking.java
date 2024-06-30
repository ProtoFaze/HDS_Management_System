/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaassignment.views;

import components.TableActionCellRender;
import components.TableCellEditor;
import components.cellPanelEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javaassignment.models.Booking;
import javaassignment.models.Customer;
import javaassignment.models.Room;
import javaassignment.verify;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author damonng
 */
public class SearchBooking extends javax.swing.JPanel {
    private EditBooking dest;
    private mainPage parentFrame;
    private ArrayList<Booking> bookings = Booking.listBookings();
    private ArrayList<Customer> customers = Customer.listCustomers();
    private ArrayList<Room>rooms = Room.listRooms();
    DefaultTableModel temp;
    private ArrayList<String> CustomerIDs;
    JButton btnEdit  = new JButton();
    /**
     * Creates new form SearchCustomer
     * 
     * @param parent
     * @param destination
     */
    //integrated state
    public SearchBooking(mainPage parent,EditBooking destination) {
        this.CustomerIDs = new ArrayList<>();
        parentFrame = parent;
        this.dest = destination;
        initComponents();
        populateTable();
        cellPanelEvent evt = new cellPanelEvent(){
            @Override
            public void runBtnLeft(int row){
                int n = JOptionPane.showConfirmDialog(null,"Edit this booking?");
                if(n == JOptionPane.YES_OPTION){
                    int selectedBooking = tblBooking.getSelectedRow();
                    String guestID = CustomerIDs.get(selectedBooking);
                    Object  targetBookingID = tblBooking.getValueAt(selectedBooking, 0),
                            roomID = tblBooking.getValueAt(selectedBooking, 2),
                            startDate = tblBooking.getValueAt(selectedBooking, 4),
                            endDate = tblBooking.getValueAt(selectedBooking, 5);
                    //set over the target Booking ID to edit
                    dest.setEditTarget(targetBookingID);
                    //send over customer information
                    for (Customer customer: customers){
                        if (customer.getID().equals(guestID)){
                            Object cusname = customer.getName(),
                                    cusmail = customer.getmail(),
                                    cusgen = customer.getGender(),
                                    cusdob = customer.getdob(),
                                    cusIc = customer.getIC(),
                                    cusPnum = customer.getPhoneNum();
                            dest.setCustomerDetails(guestID, cusname, cusmail, cusgen,cusdob, cusIc, cusPnum);
                            break;
                        }
                    }
                    //send over room information
                    for (Room room : rooms){
                        if(room.getId().equals(roomID)){
                            Object type = tblBooking.getValueAt(selectedBooking, 3),
                                    descr = room.getDescription(),
                                    price = room.getPrice().toString(),
                                    floor = Integer.toString(room.getFloor());
                            dest.setRoomDetails(roomID, type, descr, startDate, endDate, price, floor);
                            break;
                        }
                    }
                    parentFrame.changePanel("editBookingPanel");
                }
            }
            @Override
            public void runBtnRight(int row){
                if(tblBooking.isEditing()){
                    tblBooking.getCellEditor().stopCellEditing();
                }
                int n = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel this booking?\n You will not be able to undo this change.");
                if(n == JOptionPane.YES_OPTION){
                        int selectedBooking = tblBooking.getSelectedRow(),
                        lastIndex = (tblBooking.getRowCount()-1);
                    String targetBookingID = tblBooking.getValueAt(selectedBooking, 0).toString();

                    for(Booking booking:bookings){
                        if(booking.getID().equals(targetBookingID)){
                            booking.setBookingState("canceled");
                            break;
                        }
                    }
                    Booking.updateBooking(bookings);
                    //update with new info
                    bookings = Booking.listBookings();
                    populateTable();
                    if(selectedBooking==lastIndex){
                        tblBooking.setRowSelectionInterval((selectedBooking-1), (selectedBooking-1));
                    }else{
                        tblBooking.setRowSelectionInterval(selectedBooking, selectedBooking);
                    }
                }
            }
        };
        tblBooking.getColumn("Action").setCellRenderer(new TableActionCellRender());
        tblBooking.getColumn("Action").setCellEditor(new TableCellEditor(evt));
    }
    //initial state
    public SearchBooking(mainPage parent) {
        this.CustomerIDs = new ArrayList<>();
        parentFrame = parent;
        initComponents();
        populateTable();
        cellPanelEvent evt = new cellPanelEvent(){
            
            @Override
            public void runBtnLeft(int row){                
                parentFrame.changePanel("editBookingPanel");
            }
            @Override
            public void runBtnRight(int row){
                Booking.updateBooking(bookings);
            }
            
        };
        tblBooking.getColumn("Action").setCellRenderer(
                new TableActionCellRender()
        );
        tblBooking.getColumn("Action").setCellEditor(
                new TableCellEditor(evt)
        );

    }
    //allow component adding
    public SearchBooking() {
        this.CustomerIDs = new ArrayList<>();
        initComponents();
        populateTable();
    }
    private void populateTable(){
        //set temp table
        temp =  (DefaultTableModel) tblBooking.getModel();
        temp.setRowCount(0);
        CustomerIDs.clear();
        Object row[] = new Object[8];
        for(Booking booking : bookings){
            if(booking.getBookingState().equals("canceled")){
                continue;
            }
            boolean noName = true,noRoomType = true;
            row[0] = booking.getID();
            //get customer name
            row[1] = null;
            row[2]=null;
            row[3] = null;
            for(Customer customer : customers){

                if (booking.getGuestID().equals(customer.getID())){
                    row[1] = customer.getName();
                    CustomerIDs.add(customer.getID());
                    noName = false;
                    break;
                }
            }if (noName == true){
                row[1] = "notFound";}
            row[2] = booking.getRoomID();
            for(Room room : rooms){
                if (row[2].equals(room.getId())){
                    row[3] = room.getType();
                    noRoomType = false;
                    break;
                }
            }if (noRoomType == true)
                row[3] = "notFound";
            row[4] = booking.getStartDate().format(verify.formatter);
            row[5] = booking.getEndDate().format(verify.formatter);
            row[6] = booking.getTotalCost();

            //addrows when pplan to add more attributes or actions
            temp.addRow(row);
        }
    }
    //for specific searches
    private void populateTable(int columnNumber, String info, String fileName){
        temp.setRowCount(0);
        CustomerIDs.clear();
        Object row[] = new Object[8];
        for(Booking booking : bookings){
            if(booking.getBookingState().equals("canceled")){
                continue;
            }
            boolean noName = true,noRoomType = true, noMatch = true;
            String cusIDTemp=null;
            row[0] = booking.getID();
            //get customer name
            row[1] = null;
            row[2]=null;
            row[3] = null;
            for(Customer customer : customers){
                if (booking.getGuestID().equals(customer.getID())){
                    if((fileName.equals("customer")) && (columnNumber == 1)){ 
                        if(customer.getName().toLowerCase().contains(info.toLowerCase())){
                            row[1] = customer.getName();
                            cusIDTemp = customer.getID();
                            noName = false;
                            noMatch = false;
                            break;
                        }
                    }else{
                        row[1] = customer.getName();
                        cusIDTemp = customer.getID();
                        noName = false;
                        noMatch = false;
                        break;
                    }
                }
            }if(noMatch == true){
                continue;
            }if(noName == true){
                row[1] = "notFound";
            }if ((fileName.equals("room")) && (columnNumber == 2)){                
                if(booking.getRoomID().toLowerCase().contains(info.toLowerCase())){
                    row[2] = booking.getRoomID();
                }else{
                    continue;
                }
            }else{
                row[2] = booking.getRoomID();
            }
            noMatch = true;
            for(Room room : rooms){
                if (row[2].equals(room.getId())){
                    if((fileName.equals("room")) && (columnNumber == 1)){
                        if (room.getType().toLowerCase().contains(info.toLowerCase())){
                            row[3] = room.getType();
                            noRoomType = false;
                            noMatch = false;
                            break;
                        }
                    }else{
                        row[3] = room.getType();
                        noRoomType = false;
                        noMatch = false;
                        break;
                    }
                }
            }if(noMatch == true){
                continue;
            }if (noRoomType == true){
                row[3] = "notFound";
            }
            if ((fileName.equals("booking")) && (columnNumber == 3)){
                if (booking.getStartDate().format(verify.formatter).contains(info)){
                    row[4] = booking.getStartDate().format(verify.formatter);
                }else{
                    continue;
                }
            }else{
                row[4] = booking.getStartDate().format(verify.formatter);

            }if((fileName.equals("booking")) && (columnNumber == 4)){
                if(booking.getEndDate().format(verify.formatter).contains(info)){
                    row[5] = booking.getEndDate().format(verify.formatter);
                }else{
                    continue;
                }
            }else{
                row[5] = booking.getEndDate().format(verify.formatter);
            }if((fileName.equals("booking")) && (columnNumber == 5)){
                if(Double.toString(booking.getTotalCost()).contains(info)){
                    row[6] = booking.getTotalCost();
                    noMatch = false;
                }else{
                    continue;
                }
            }else{
                row[6] = booking.getTotalCost();
                noMatch = false;
            }
            if(noMatch == false){
                CustomerIDs.add(cusIDTemp);
                temp.addRow(row);
            }
        }
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
        cmbInfoType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();
        errInfo = new javax.swing.JLabel();
        txtInfo = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnNewCus = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        pageTitle.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        pageTitle.setText("Search-Booking");
        pageTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cmbInfoType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer Name", "Room Number", "Room Type", "Start Date", "End Date", "Total Cost" }));
        cmbInfoType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfoTypeActionPerformed(evt);
            }
        });

        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer", "Room Number", "Type", "Start Date", "End Date", "Cost", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBooking.setRowHeight(40);
        tblBooking.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblBooking);

        errInfo.setForeground(new java.awt.Color(255, 102, 51));

        txtInfo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInfoKeyPressed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnNewCus.setText("New Booking");
        btnNewCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCusActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(divider, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pageTitle)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbInfoType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addComponent(errInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(8, 8, 8)
                .addComponent(btnNewCus)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divider, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbInfoType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnNewCus)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errInfo)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String info = String.valueOf(cmbInfoType.getSelectedItem());
        String data = txtInfo.getText();
        int colNum = cmbInfoType.getSelectedIndex();
        String searchFile = "";
        switch(info){
            case "Customer Name"->{
                colNum = 1;
                errInfo.setText(verify.validateString(data));
                searchFile = "customer";
                break;
            }
            case "Room Number"->{
                colNum = 2;
                errInfo.setText(verify.validateAlNum(data));
                searchFile = "room";
                break;
            }
            case "Room Type"->{
                colNum = 1;
                errInfo.setText(verify.validateString(data));
                searchFile = "room";
                break;
            }
            case "Start Date"->{
                colNum =3;
                errInfo.setText(verify.validateDate(data, true));
                searchFile = "booking";
                break;
            }
            case "End Date"->{
                colNum =4;
                errInfo.setText(verify.validateDate(data, true));
                searchFile = "booking";
                break;
            }
            case "Total Cost"->{
                colNum =5;
                errInfo.setText(verify.validateCurrency(data));
                searchFile = "booking";
                break;
            }

            default->{
                errInfo.setText(verify.validateString(data));
                break;
            }
        }
        populateTable(colNum,data,searchFile);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbInfoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfoTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbInfoTypeActionPerformed

    private void btnNewCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCusActionPerformed
        parentFrame.changePanel("availableRoomsPanel");
    }//GEN-LAST:event_btnNewCusActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        bookings = Booking.listBookings();
        rooms = Room.listRooms();
        customers = Customer.listCustomers();
        populateTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtInfoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInfoKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            // enter key pressed
            btnSearch.doClick();
        }
    }//GEN-LAST:event_txtInfoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewCus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbInfoType;
    private javax.swing.JSeparator divider;
    private javax.swing.JLabel errInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JTable tblBooking;
    private javax.swing.JTextField txtInfo;
    // End of variables declaration//GEN-END:variables
}
