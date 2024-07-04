/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.damon.views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.damon.models.Customer;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.damon.file;
import com.damon.verify;

/**
 *
 * @author damonng
 */
public class SearchCustomer extends javax.swing.JPanel {
    private BookingSummary dest;
    private EditBooking edest;
    private mainPage parentFrame;
//    private ArrayList<Customer> customers;
    private ArrayList<Customer> customers = Customer.listCustomers();
    DefaultTableModel temp;
    JButton btnGetData  = new JButton();

    /**
     * Creates new form SearchCustomer
     * 
     * @param parent
     * @param dest
     * @param editDest
     */
    public SearchCustomer(mainPage parent,BookingSummary dest,EditBooking editDest) {
        parentFrame = parent;
        this.dest = dest;
        this.edest = editDest;
        initComponents();
        populateTable();
        tblCustomer.getColumn("Action").setCellRenderer( new SearchCustomer.ButtonRenderer());
        tblCustomer.getColumn("Action").setCellEditor( new SearchCustomer.ButtonEditor(new JCheckBox()));
        btnGetData.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(null,"Is this the correct customer?");
            if(n == JOptionPane.YES_OPTION){
                int selectedCustomer = tblCustomer.getSelectedRow();
                if(parentFrame.getCurOps() == 2){
                    Object cusID = tblCustomer.getValueAt(selectedCustomer, 0),
                        fullname = tblCustomer.getValueAt(selectedCustomer, 1),
                        email = tblCustomer.getValueAt(selectedCustomer, 2),
                        gender = tblCustomer.getValueAt(selectedCustomer, 3),
                        dob = tblCustomer.getValueAt(selectedCustomer, 4),
                        ic = tblCustomer.getValueAt(selectedCustomer, 5),
                        pnum = tblCustomer.getValueAt(selectedCustomer, 6);
                    edest.setCustomerDetails(cusID, fullname, email, gender,dob, ic, pnum);
                    parentFrame.changePanel("editBookingPanel");
                }else{
                    BookingSummary.setCustomerID(tblCustomer.getValueAt(selectedCustomer, 0));
                    BookingSummary.setCustomerName(tblCustomer.getValueAt(selectedCustomer, 1));
                    parentFrame.changePanel("bookingSummaryPanel");
                }
            }
        });
    }

    public SearchCustomer(mainPage parent,BookingSummary dest) {
        parentFrame = parent;
        this.dest = dest;
        initComponents();
        populateTable();
        tblCustomer.getColumn("Action").setCellRenderer( new SearchCustomer.ButtonRenderer());
        tblCustomer.getColumn("Action").setCellEditor( new SearchCustomer.ButtonEditor(new JCheckBox()));
        btnGetData.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(null,"Is this the correct customer?");
            if(n == JOptionPane.YES_OPTION){
                int selectedCustomer = tblCustomer.getSelectedRow();
                BookingSummary.setCustomerID(tblCustomer.getValueAt(selectedCustomer, 0));
                BookingSummary.setCustomerName(tblCustomer.getValueAt(selectedCustomer, 1));
                parentFrame.changePanel("bookingSummaryPanel");
                
            }
        });
    }
    public SearchCustomer(mainPage parent) {
        parentFrame = parent;
        initComponents();
        populateTable();
        tblCustomer.getColumn("Action").setCellRenderer( new SearchCustomer.ButtonRenderer());
        tblCustomer.getColumn("Action").setCellEditor( new SearchCustomer.ButtonEditor(new JCheckBox()));
        btnGetData.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(null,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                int selectedCustomer = tblCustomer.getSelectedRow();
                
                parentFrame.changePanel("bookingSummaryPanel");
                
            }
        });
    }
    public SearchCustomer() {
        initComponents();
        populateTable();
        tblCustomer.getColumn("Action").setCellRenderer( new SearchCustomer.ButtonRenderer());
        tblCustomer.getColumn("Action").setCellEditor( new SearchCustomer.ButtonEditor(new JCheckBox()));
        btnGetData.addActionListener((ActionEvent event) -> {
            int n = JOptionPane.showConfirmDialog(null,"Do you want to book this room?");
            if(n == JOptionPane.YES_OPTION){
                System.out.println("yes detected");
            }
        });
    }
    private void populateTable(){
        //set temp table
        temp =  (DefaultTableModel) tblCustomer.getModel();
        temp.setRowCount(0);
        Object row[] = new Object[7];
        for(Customer customer : customers){
            row[0] = customer.getID();
            row[1] = customer.getName();
            row[2] = customer.getmail();
            row[3] = customer.getGender();
            row[4] = customer.getdob();
            row[5] = customer.getIC();
            row[6] = customer.getPhoneNum();

            
            //addrows when pplan to add more attributes or actions
            temp.addRow(row);
        }
    }
    //for specific date range
    private void populateTable(int columnNumber, String info){
        temp.setRowCount(0);
        Object[] Customers = file.extract("customer");
        for (Object customer : Customers) {           //iterate through all rooms
            //get room id to check availability
            String[] record = customer.toString().split(",");
            if (record[columnNumber].toLowerCase().contains(info.toLowerCase())){
                String[] row = {record[0],record[1],record[2],record[3],record[4],record[5],record[6]};
                temp.addRow(row);
            }
        }
    }
//    for table button
        class ButtonRenderer extends JButton implements TableCellRenderer{
        public ButtonRenderer(){
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null)? "Get Info" : value.toString());
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
            label = (value == null) ? "Get Info" : value.toString();
            btnGetData.setText(label);
            return btnGetData;
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

        pageTitle = new javax.swing.JLabel();
        divider = new javax.swing.JSeparator();
        cmbInfoType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        errInfo = new javax.swing.JLabel();
        txtInfo = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnNewCus = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        pageTitle.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        pageTitle.setText("Customer search");
        pageTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cmbInfoType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Email", "Birth Date", "IC", "Contact" }));
        cmbInfoType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfoTypeActionPerformed(evt);
            }
        });

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Gender", "BirthDate", "IC", "Contact", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.setRowHeight(40);
        tblCustomer.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCustomer);
        if (tblCustomer.getColumnModel().getColumnCount() > 0) {
            tblCustomer.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

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

        btnNewCus.setText("New Customer");
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
                        .addGap(15, 15, 15)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh))
                    .addComponent(errInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        switch(info){
            case "Name":
            case "Email":{
                colNum++;
                errInfo.setText(verify.validateString(data));
                break;
            }
            case "Birth Date":{
                colNum+=2;
                errInfo.setText(verify.validateDate(data, false));
                break;
            }
            case "IC":
            case "Contact":{
                colNum+=2;
                errInfo.setText(verify.validateNum(data));
                break;
            }
            default:{
                errInfo.setText("");
                break;
            }
        }
        populateTable(colNum,data);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbInfoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfoTypeActionPerformed
        
        
    }//GEN-LAST:event_cmbInfoTypeActionPerformed

    private void btnNewCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCusActionPerformed
        parentFrame.changePanel("newCustomerPanel");
    }//GEN-LAST:event_btnNewCusActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
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
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtInfo;
    // End of variables declaration//GEN-END:variables
}
