package javaassignment.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static javaassignment.verify.*;
import javaassignment.file;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author damonng
 */

public class Staff extends User {
    private String identifier,userName,password;    
    private String [] preference;
    private static final String fileName = "staff";
    private static int lastStaffID;

    
    static{
        lastStaffID = file.getLatestID(1);
    }
    
    //Constructor
    //for existing records
    public Staff(String identifier, String userName, String password, String fullName, String emailAddress, char gender, String dob){
        super(fullName, emailAddress, gender,dob);
        this.password = password;
        this.identifier = identifier;   
    } 
    //for new records, registering new staff
    public Staff(String fullName, String emailAddress,char gender,String dob,String password,String userName){
        super(fullName, emailAddress, gender,dob);
        this.password = password;
        this.userName = userName;
        //fetch new ID
        identifier = file.generateID(lastStaffID, fileName);
        lastStaffID++;
    }
    
    public String setPassword(String password){
        String errorMessage = validatePass(password);
        if (errorMessage != null){
            return errorMessage;
        }else{
            this.password = password;
            return "success";
        }       
    }
    public String setUserName(String userName){
        String errorMessage = validateString(userName);
        if (errorMessage != null){
            return errorMessage;
        }else{
            this.userName = userName;
            return "success";
        }       
    }
    public String getPassword(){
        return password;
    }
    public String getUserName(){
        return userName;
    }
    
    
    
    public static String[] login(String userName, String password) {
        //Read file
        String[] item = null;
        try (final BufferedReader br = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            //Store into variable for easy access
            ArrayList<String> file = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                file.add(line);
            }
            //loop and check for match
            for (String record : file) {
                String[] data = record.split(",");
                String uName = data[1];
                String passcode = data[2];
                if (uName.equals(userName) && passcode.equals(password)) {
                    //login and return staff info
                    item = data;
                }
            }
        } catch (IOException e) {
            item[0] = e.toString();
        }
        return item;
    }
    
    @Override
    //returns an array specifying staff info
    public String[] getPerson(){
        String g = Character.toString(gender);
        String[] staff = {identifier,userName,password,fullName,emailAddress,g,dob};
        file.data.put("user", this);
        return staff;
    }
    @Override
    public String writeToFile(){
        List<String> content = new ArrayList<>();
        content.add(identifier+','+userName+','+password+','+fullName+','+emailAddress+','+gender+','+dob);
        String status = file.append(fileName, content);
        return status;
    }
}

