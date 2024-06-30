/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaassignment.views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javaassignment.models.Booking;
import javaassignment.models.Room;
import javaassignment.verify;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author damonng
 */
public class AvailableRooms extends javax.swing.JPanel {
    private DefaultTableModel temp;
    //fetch all rooms
    private ArrayList<Room> rooms = Room.listRooms();
    private ArrayList<Booking> bookings = Booking.listBookings();
    JButton btnBook  = new JButton();
    private mainPage parentFrame;
    private BookingSummary RegisterDestination;
    private EditBooking EditDataSource;
    
    /**
     * Creates new form Booking
     * @param parent
     * @param RegisterDestination
     * @param EditData
     */
    public AvailableRooms(mainPage parent,BookingSummary RegisterDestination, EditBooking EditData) {
        parentFrame = parent;
//        rooms = parentFrame.getRooms();
//        bookings = parentFrame.getBookings();
        this.RegisterDestination = RegisterDestination;
        this.EditDataSource = EditData;
        initComponents();
        startDateChooser.setDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // add one day
        Date tomorrow = calendar.getTime();
        endDateChooser.setDate(tomorrow); 
//        populateTable();
        btnSearch.doClick();
        tblBooking.getColumn("Action").setCellRenderer( new ButtonRenderer());
        tblBooking.getColumn("Action").setCellEditor( new ButtonEditor(new JCheckBox()));
        btnBook.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(this,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                int selectedRoom = tblBooking.getSelectedRow();
                Date start = startDateChooser.getDate(),
                        end = endDateChooser.getDate();
                Object roomid = tblBooking.getValueAt(selectedRoom, 0),
                 roomtype = tblBooking.getValueAt(selectedRoom, 1),
                 roomdescr = tblBooking.getValueAt(selectedRoom, 2),
                 roomprice = tblBooking.getValueAt(selectedRoom, 3),
                 startDateStr = verify.dateformat.format(start),
                 endDateStr = verify.dateformat.format(end),
                 floorNumber = tblBooking.getValueAt(selectedRoom, 4);
                
                if(parentFrame.getCurOps() == 2 ){// send the details to edit booking
                    EditDataSource.setRoomDetails(roomid, roomtype, roomdescr, startDateStr, endDateStr, roomprice, floorNumber);
                    parentFrame.changePanel("editBookingPanel");
                }else{ // send the details to booking Summary
                    BookingSummary.setRoomID(roomid);
                    BookingSummary.setRoomType(roomtype);
                    BookingSummary.setRoomPrice(roomprice);
                    BookingSummary.setStartDate(start);
                    BookingSummary.setEndDate(end);
                    BookingSummary.setFloorNum(floorNumber);
                    parentFrame.changePanel("searchCustomerPanel");
                }
            }
        });
    }
    public AvailableRooms(mainPage ParentFrame) {
        this.parentFrame = ParentFrame;
        initComponents();
        startDateChooser.setDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // add one day
        Date tomorrow = calendar.getTime();
        endDateChooser.setDate(tomorrow); 
//        populateTable();
        tblBooking.getColumn("Action").setCellRenderer( new ButtonRenderer());
        tblBooking.getColumn("Action").setCellEditor( new ButtonEditor(new JCheckBox()));
        btnBook.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(this,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this,"Saving room info");
            }
        });
    }
    public AvailableRooms() {
        initComponents();
        startDateChooser.setDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // add one day
        Date tomorrow = calendar.getTime();
        endDateChooser.setDate(tomorrow); 
//        populateTable();
        tblBooking.getColumn("Action").setCellRenderer( new ButtonRenderer());
        tblBooking.getColumn("Action").setCellEditor( new ButtonEditor(new JCheckBox()));
        btnBook.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(this,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this,"Saving room info");
                int selectedRoom = tblBooking.getSelectedRow();
                BookingSummary.setRoomID(tblBooking.getValueAt(selectedRoom, 0));
                Object roomPrice = tblBooking.getValueAt(selectedRoom, 3);
                Object startDate = verify.dateformat.format(startDateChooser.getDate());
                Object endDate = verify.dateformat.format(endDateChooser.getDate());
            }
        });
    }
    
//    private void populateTable(){
//        //reset temp table
//        this.temp = (DefaultTableModel) tblBooking.getModel();

//        Object row[] = new Object[5];
//        for(Room room : rooms){
//            row[0] = room.getId();
//            row[1] = room.getType();
//            row[2] = room.getDescription();
//            row[3] = room.getPrice();
//            
//            //addrows when pplan to add more attributes or actions
//            temp.addRow(row);
//        }
//    }
    //for specific date range
    public void populateTable(String startDate, String endDate){
        this.temp = (DefaultTableModel) tblBooking.getModel();
        temp.setRowCount(0);
        Object row[] = new Object[5]; 
        for (Room room : rooms) {           //iterate through all rooms
            //get room id to check availability
            String roomNum = room.getId();
//            System.out.println("Checking"+roomNum);

            if(isRoomAvailable(roomNum,startDate,endDate) == true){
                //add to new temp table if room is available
                row[0] = roomNum;
                row[1] = room.getType();
                row[2] = room.getDescription();
                row[3] = room.getPrice();
                row[4] = room.getFloor();
                temp.addRow(row);
//                System.out.println("yes");
//            }else{
//                System.out.println("no");
            }
            
        }
    }


    public boolean isRoomAvailable(String roomNumber, String inputStartDate, String inputEndDate) {
        
        LocalDate searchStart = LocalDate.parse(inputStartDate, verify.formatter),
                searchEnd = LocalDate.parse(inputEndDate, verify.formatter);
        //save file to variable
        //loop through records
        for(Booking booking : bookings){
            
            String[] data = booking.toString().split(",");
            //assign values
            String reservationRoomNumber = booking.getRoomID();
            String Bookingstate = booking.getBookingState();
            LocalDate reservationStartDate = booking.getStartDate();
            LocalDate reservationEndDate = booking.getEndDate();
            //booking status check
            if (reservationRoomNumber.equals(roomNumber) && (Bookingstate.equals("booked")||Bookingstate.equals("checked-out"))) { //matched roomnumber and state
                // Check if the reservation overlaps with the given date range
                if ((searchStart.isBefore(reservationEndDate) || searchStart.isEqual(reservationEndDate))
                        && (searchEnd.isAfter(reservationStartDate) || searchEnd.isEqual(reservationStartDate))) {
                    return false; // Room is not available
                }
            }
        }
        return true; // Room is available
    }
    // for Book button
    class ButtonRenderer extends JButton implements TableCellRenderer{
        public ButtonRenderer(){
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null)? "Book" : value.toString());
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
            label = (value == null) ? "Book" : value.toString();
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
        btnSearch = new javax.swing.JButton();
        dateInfo = new javax.swing.JPanel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        dateInfo1 = new javax.swing.JPanel();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();
        errDateEnd = new javax.swing.JLabel();
        errDateStart = new javax.swing.JLabel();
        lblDateEnd = new javax.swing.JLabel();
        lblDateStart = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        startDateChooser.setDateFormatString("d/MMM/y");
        startDateChooser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                startDateChooserFocusLost(evt);
            }
        });

        javax.swing.GroupLayout dateInfoLayout = new javax.swing.GroupLayout(dateInfo);
        dateInfo.setLayout(dateInfoLayout);
        dateInfoLayout.setHorizontalGroup(
            dateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        dateInfoLayout.setVerticalGroup(
            dateInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dateInfoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        endDateChooser.setDateFormatString("d/MMM/y");
        endDateChooser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                endDateChooserFocusLost(evt);
            }
        });

        javax.swing.GroupLayout dateInfo1Layout = new javax.swing.GroupLayout(dateInfo1);
        dateInfo1.setLayout(dateInfo1Layout);
        dateInfo1Layout.setHorizontalGroup(
            dateInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(endDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
        );
        dateInfo1Layout.setVerticalGroup(
            dateInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dateInfo1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "Type", "Description", "Price", "Floor", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBooking.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblBooking.setRowHeight(40);
        tblBooking.setSelectionBackground(new java.awt.Color(102, 204, 255));
        tblBooking.getTableHeader().setReorderingAllowed(false);
        tblBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblBookingMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblBooking);
        if (tblBooking.getColumnModel().getColumnCount() > 0) {
            tblBooking.getColumnModel().getColumn(0).setPreferredWidth(25);
            tblBooking.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblBooking.getColumnModel().getColumn(2).setPreferredWidth(270);
            tblBooking.getColumnModel().getColumn(3).setPreferredWidth(30);
            tblBooking.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblBooking.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        errDateEnd.setForeground(new java.awt.Color(255, 102, 51));

        errDateStart.setForeground(new java.awt.Color(255, 102, 51));

        lblDateEnd.setText("End");

        lblDateStart.setText("Start");

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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDateStart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(errDateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 67, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDateEnd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(errDateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateInfo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRefresh))
                        .addComponent(lblDateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblDateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //Declare Strings
        String start,end, validDateStart,validDateEnd;
        //set values
        Date startinput = startDateChooser.getDate();
        Date endinput = endDateChooser.getDate();
        if (startinput != null && endinput !=null){
        end=verify.dateformat.format(endinput);
        start=verify.dateformat.format(startinput);
        
        //  run validation
        validDateStart = verify.validateDate(start, true);
        validDateEnd = verify.validateDate(end,true);

            if(validDateStart == null && validDateEnd == null){
                //get available rooms
                populateTable(start,end);
                tblBooking.setModel(temp);
            }else{
                //show error
                errDateStart.setText( validDateStart);
                errDateEnd.setText( validDateEnd);
            }
        }else{
            errDateStart.setText( "one of the dates are empty");
            errDateEnd.setText( "one of the dates are empty");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblBookingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookingMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBookingMouseEntered

    private void startDateChooserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_startDateChooserFocusLost
        if (endDateChooser.getDate() == null){
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();
            endDateChooser.setDate(today); 
        }
    }//GEN-LAST:event_startDateChooserFocusLost

    private void endDateChooserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_endDateChooserFocusLost
        if (endDateChooser.getDate() == null){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1); // add one day
            Date tomorrow = calendar.getTime();
            endDateChooser.setDate(tomorrow); 
        }
    }//GEN-LAST:event_endDateChooserFocusLost

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
         rooms = Room.listRooms();
        bookings = Booking.listBookings();
        String tdy = LocalDate.now().format(verify.formatter),
                tmr = LocalDate.now().plusDays(1).format(verify.formatter);
        populateTable(tdy,tmr);
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel dateInfo;
    private javax.swing.JPanel dateInfo1;
    private javax.swing.JSeparator divider;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JLabel errDateEnd;
    private javax.swing.JLabel errDateStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDateEnd;
    private javax.swing.JLabel lblDateStart;
    private com.toedter.calendar.JDateChooser startDateChooser;
    private javax.swing.JTable tblBooking;
    // End of variables declaration//GEN-END:variables
}
