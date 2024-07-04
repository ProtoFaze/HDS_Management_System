/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.damon.models;

import java.util.ArrayList;

import com.damon.file;

/**
 *
 * @author damonng
 */
public class Room {
    private String id,type,description;
    private double price;
    private int floorNumber;
    
    public static ArrayList listRooms(){
        final Object[] rooms = file.extract("room");
        if (rooms == null) {
            // handle null case here
            System.out.println("Failed to extract rooms from file, null detected");
            return new ArrayList<Room>();
        }
        ArrayList<Room> roomList = new ArrayList<>();
        for (Object room : rooms) {
            String[] record = room.toString().split(",");
            
            String roomNum = record[0];
            String roomType = record[1];
            String Descr = record[2];
            Double roomPrice = Double.valueOf(record[3]);
            int floorNum = Integer.parseInt(record[4]);
            Room roomI = new Room(roomNum, roomType, Descr, roomPrice,floorNum);
            roomList.add(roomI);
        }
        return roomList;
    }
        
    //construct for one room, loop from file record , read file
    public Room(String id,String type,String description,Double price, int floorNumber){
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.floorNumber = floorNumber;
    }

    
    
    //SETTER
    public void setId(String id){
        this.id = id;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public void setFloor(int floorNumber){
        this.floorNumber = floorNumber;
    }
    
    //GETTER
    public String getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getDescription(){
        return description;
    }
    public Double getPrice(){
        return price;
    }
    public int getFloor(){
        return floorNumber;
    }
    public Object[] getRoom(){
        Object[] room = {id,type,description,price,floorNumber};
        return room;
    } 

}