    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.models;

import java.util.ArrayList;
import java.util.List;
import javaassignment.file;

/**
 *
 * @author damonng
 */
public class Customer extends User{
    private String ID,IC, phoneNum;
    private static final String fileName = "customer";
    private static int lastCustomerID;
    
    static{
        lastCustomerID = file.getLatestID(2);
    }
    
    public static ArrayList listCustomers(){
        final Object[] Customers = file.extract(fileName);
        if (Customers == null) {
            // handle null case here
            System.out.println("Failed to extract Customer info from file, null detected");
            return new ArrayList<Room>();
        }
        ArrayList<Customer> customerList = new ArrayList<>();
        for (Object customer : Customers) {
            String[] record = customer.toString().split(",");
            
            String id = record[0];
            String name = record[1];
            String mail = record[2];
            char gen = record[3].charAt(0);
            String birthdate = record[4];
            String cardNum = record[5];
            String pNum = record[6];
            Customer customerI = new Customer(id, name, mail, gen, birthdate, cardNum, pNum);
            customerList.add(customerI);
        }
        return customerList;
    }
    //constructors 
    //fetch from file, read customer
    public Customer(String ID,String fullName,String emailAddress,char gender,String dob,String IC,String phoneNum){
        super(fullName, emailAddress,gender,dob);
        this.ID = ID;
        this.IC = IC;
        this.phoneNum = phoneNum;           
    }
    //Register new customer, create customer
    public Customer(String fullName,String emailAddress,char gender,String dob, String IC, String phoneNum){
        super(fullName, emailAddress,gender,dob);
        ID = file.generateID(lastCustomerID,fileName);
        this.IC = IC;
        this.phoneNum = phoneNum;  
        lastCustomerID++;
    }
    
    //SETTERS
    public void setID(String ID){
        this.ID = ID;
    }
    public void setIC(String IC){
        this.IC = IC;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
    
    //GETTERS
    public String getID(){
        return ID;
    }
    public String getIC(){
        return IC;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    @Override
    public String[] getPerson(){
        String g = Character.toString(this.gender);
        String [] customer = {fullName , emailAddress , g , IC, dob, phoneNum};
        file.data.put("customer", this);

        return customer;
    }
    @Override
    public String writeToFile(){
        List<String> content = new ArrayList<>();
        content.add(ID+","+fullName+","+emailAddress+","+gender+","+dob+","+IC+","+phoneNum);
        String status = file.append(fileName, content);
        return status;
    }
}