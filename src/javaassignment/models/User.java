/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.models;

/**
 *
 * @author damonng
 */
abstract class User {
    //Declare variables
    protected String fullName, emailAddress,dob;
    protected char gender;
    public static String dateFormat = "dd/mm/yyyy";
    
    //constructors
    User(String fullName, String emailAddress, char gender,String dob){
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.dob = dob;
    }
    //SETTERS
    public void setName(String fullName){
        this.fullName = fullName;
    }
    public void setMail(String emailAddress){
        this.emailAddress = emailAddress;
    }
    public void setGender(char gender){
        char original = this.gender;
        this.gender = (Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F') 
            ? original 
            : Character.toUpperCase(gender) ;
    }
    
    
    //GETTERS
    public String getName(){
        return this.fullName;
    }
    public String getmail(){
        return this.emailAddress;
    }
    public char getGender(){
        return this.gender;
    }
    public String getdob(){
        return this.dob;
    }
    abstract String[] getPerson();
    abstract String writeToFile();
    
}