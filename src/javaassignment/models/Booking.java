/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javaassignment.file;
import javaassignment.verify;

/**
 *
 * @author damonng
 */
public class Booking {
    private String ID,guestID,roomID, bookingState;
    private LocalDate startDate,endDate;
    private double totalCost;
    private static final String FILENAME = "booking";
    private static int lastBookingID;
    public static final double SERVICETAX= 1.1;
    public static final int TOURISMTAX = 10;
    
    static{
        lastBookingID = file.getLatestID(3);
    }
    //fetch all data from file
    public static ArrayList listBookings(){
        final Object[] bookings = file.extract("booking");
        if (bookings == null) {
            // handle null case here
            System.out.println("Failed to extract booking from file, null detected");
            return new ArrayList<Booking>();
        }
        ArrayList<Booking> bookingList = new ArrayList<>();
        for (Object booking : bookings) {
            String[] record = booking.toString().split(",");
            
            String bookingID = record[0];
            String guest = record[1];
            String roomID = record[2];
            LocalDate start = LocalDate.parse(record[3],verify.formatter);
            LocalDate end = LocalDate.parse(record[4],verify.formatter);
            Double total = Double.valueOf(record[5]);
            String state = record[6];
            Booking bookingI = new Booking(bookingID, guest, start, end, roomID, total,state);
            bookingList.add(bookingI);
        }
        return bookingList;
    }
    //Constructors
    //when fetch/read booking
    public Booking(String ID, String guestID, LocalDate startDate, LocalDate endDate, String roomID, double totalCost, String bookingState) {
        this.ID = ID;
        this.guestID = guestID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
        this.totalCost = totalCost;
        this.bookingState = bookingState;
    }
    
    //when generating new booking, create booking
    public Booking(String guestID, LocalDate startDate, LocalDate endDate, String roomID, double totalCost) {
        this.ID = file.generateID(lastBookingID, FILENAME);
        this.guestID = guestID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.bookingState = "booked";
        lastBookingID++;
    }

    //GETTER
    public String getID() {
        return ID;
    }
    public String getGuestID() {
        return guestID;
    }
    public String getRoomID() {
        return roomID;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public String getBookingState(){
        return bookingState;
    }
    
    //SETTER
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public void setBookingState(String bookingState){
        this.bookingState = bookingState;
    }
    
    public String reserve(){
        List<String> content = new ArrayList<>();
        String start = this.getStartDate().format(verify.formatter);
        String end = this.getEndDate().format(verify.formatter);
        content.add(ID+','+guestID+','+roomID+','+start+','+end+','+totalCost+","+bookingState);
        String status = file.append(FILENAME, content);
        return status;
    }
    
    public static String updateBooking(ArrayList<Booking> records){
        List<String> updates = new ArrayList<>();
        for(Booking record : records){
            String start = record.getStartDate().format(verify.formatter);
            String end = record.getEndDate().format(verify.formatter);
            updates.add(record.getID()+','+record.getGuestID()+','+record.getRoomID()+','+start+','+end+','+record.getTotalCost()+","+record.getBookingState());
        }
        String status  = file.rewrite(FILENAME,updates);
        return status;
    }
}
