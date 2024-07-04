/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.damon.views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import com.damon.models.Booking;
import com.damon.models.Customer;
import com.damon.models.Room;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.damon.verify;

/**
 *
 * @author damonng
 */
public class RoomChecking extends javax.swing.JPanel {
    private DefaultTableModel temp;
    private Object[] reservations;
    //fetch all rooms
    private ArrayList<Room> rooms = Room.listRooms();
    private ArrayList<Booking> bookings = Booking.listBookings();
    private ArrayList<Customer> customers = Customer.listCustomers();
    JButton btnBook  = new JButton();
    private mainPage parentFrame;
    private BookingSummary RegisterDestination;
    private int todaysBookings;
    
    /**
     * Creates new form Booking
     * @param parent
     * @param RegisterDestination
     * Displays the rooms that are booked for check-in and check-out today
     * Allows staff to mark room for check-in then check-out
     */
    public RoomChecking(mainPage parent,BookingSummary RegisterDestination) {
        parentFrame = parent;
        this.RegisterDestination = RegisterDestination;
        initComponents();
        populateTable();
        todaysBookings = tblBookedRooms.getRowCount();
        tblBookedRooms.getColumn("Action").setCellRenderer( new ButtonRenderer());
        tblBookedRooms.getColumn("Action").setCellEditor( new ButtonEditor(new JCheckBox()));
        btnBook.addActionListener((ActionEvent event) -> {
            int selectedBookedRoom = tblBookedRooms.getSelectedRow(),
                    originalRowCount = tblBookedRooms.getRowCount();
            String bookingID = tblBookedRooms.getValueAt(selectedBookedRoom, 0).toString(),
             roomNum = tblBookedRooms.getValueAt(selectedBookedRoom, 1).toString();
            boolean isCheckin = false;
            for(Booking record : bookings){
                if(record.getID().equals(bookingID)){
                    if(record.getBookingState().equals("booked")){
                        int n = JOptionPane.showConfirmDialog(this,"Check-in "+roomNum+"?");
                        if(n == JOptionPane.YES_OPTION){
                            record.setBookingState("checked-in");
                        }
                    }else if(record.getBookingState().equals("checked-in")){
                        int n = JOptionPane.showConfirmDialog(this,"Check-out "+roomNum+"?");
                        if(n == JOptionPane.YES_OPTION){
                            record.setBookingState("checked-out");
                            isCheckin = true;
                        }
                    }
                }
            }
            Booking.updateBooking(bookings);
            bookings = Booking.listBookings();
            populateTable();
            if ((selectedBookedRoom == (originalRowCount-1)) && (isCheckin == true)) {
                if(selectedBookedRoom == 0){

                }else{
                    tblBookedRooms.setRowSelectionInterval((0), (0)); 
                }
            } else {
                tblBookedRooms.setRowSelectionInterval(selectedBookedRoom, selectedBookedRoom);            
            }
        });
        parentFrame.setTodaysJobs(tblBookedRooms.getRowCount());       
    }
    public RoomChecking(mainPage ParentFrame) {
        this.parentFrame = ParentFrame;
        initComponents(); 
        populateTable();
        tblBookedRooms.getColumn("Action").setCellRenderer( new ButtonRenderer());
        tblBookedRooms.getColumn("Action").setCellEditor( new ButtonEditor(new JCheckBox()));
        btnBook.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(this,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this,"Saving room info");
            }
        });
    }
    public RoomChecking() {
        initComponents();
        populateTable();
        tblBookedRooms.getColumn("Action").setCellRenderer( new ButtonRenderer());
        tblBookedRooms.getColumn("Action").setCellEditor( new ButtonEditor(new JCheckBox()));
        btnBook.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(this,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this,"Saving room info");
                int selectedBookedRoom = tblBookedRooms.getSelectedRow();
                BookingSummary.setRoomID(tblBookedRooms.getValueAt(selectedBookedRoom, 0));
            }
        });
    }
    //populate table with 1 booking record of each room where the start date 
    private void populateTable(){
        //reset temp table
        temp = (DefaultTableModel) tblBookedRooms.getModel();
        temp.setRowCount(0);
        for(Room room : rooms){
        Object row[] = new Object[9];
                Booking bookedRoom = roomBooking(room.getId());
            //check for booked room for today and tomorrow
            row[2] = room.getType();
            if( bookedRoom != null){
                //add to new temp table if room is booked or checked-in for today
                    row[0] = bookedRoom.getID();
                    row[1] = bookedRoom.getRoomID();
                    row[3] = bookedRoom.getGuestID();
                    row[4] = bookedRoom.getStartDate().format(verify.formatter);
                    row[5] = bookedRoom.getEndDate().format(verify.formatter);
                    row[6] = Double.toString(bookedRoom.getTotalCost());
                    row[7] = bookedRoom.getBookingState();
                        for(Customer customer: customers){
                        if(bookedRoom.getGuestID().equals(customer.getID())){
                                row[3] = customer.getName();
                                break;
                            }
                        }
            }else{
                continue;
            }
        temp.addRow(row);
        }
    }
    //returns booking info of specified room if it is booked or checked in for today and tomorrow
    public Booking roomBooking(String roomNumber) {
//        LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.now();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
        //loop through records
            for(Booking booking : bookings){
                //assign values
                String reservationRoomNumber = booking.getRoomID();
                String Bookingstate = booking.getBookingState();
                LocalDate reservationStartDate = booking.getStartDate();
                LocalDate reservationEndDate = booking.getEndDate();
                if (reservationRoomNumber.equals(roomNumber) && (Bookingstate.equals("booked")||Bookingstate.equals("checked-in")||Bookingstate.equals("checked-out"))) { //matched roomnumber and state
                    // Check if the reservation overlaps with the given date range
                    if ((today.isBefore(reservationEndDate) || today.isEqual(reservationEndDate))
                    && (today.isAfter(reservationStartDate) || today.isEqual(reservationStartDate))) {
                    return booking; // Room is not available
                                }
                            }
                    }
        return null; // Room is available
                }

    // for checkin/checkout button
    class ButtonRenderer extends JButton implements TableCellRenderer{
        public ButtonRenderer(){
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null)? "Choose room" : value.toString());
            return this;
        }

    }
    class ButtonEditor extends DefaultCellEditor{
        private String label;
        
        public ButtonEditor(JCheckBox checkbox){
           super(checkbox);
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Choose room" : value.toString();
            btnBook.setText(label);
            return btnBook;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
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

        divider = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBookedRooms = new javax.swing.JTable();
        pageTitle = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();

        tblBookedRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking", "Room", "Type", "Customer", "Start Date", "End Date", "Price", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBookedRooms.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblBookedRooms.setRowHeight(40);
        tblBookedRooms.setSelectionBackground(new java.awt.Color(102, 204, 255));
        tblBookedRooms.getTableHeader().setReorderingAllowed(false);
        tblBookedRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblBookedRoomsMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblBookedRooms);
        if (tblBookedRooms.getColumnModel().getColumnCount() > 0) {
            tblBookedRooms.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblBookedRooms.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblBookedRooms.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        pageTitle.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        pageTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pageTitle.setText("Check-in & Check-out");
        pageTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pageTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBookedRoomsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookedRoomsMouseEntered
        
    }//GEN-LAST:event_tblBookedRoomsMouseEntered

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        rooms = Room.listRooms();
        bookings = Booking.listBookings();
        customers = Customer.listCustomers();
        populateTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JSeparator divider;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JTable tblBookedRooms;
    // End of variables declaration//GEN-END:variables
}

