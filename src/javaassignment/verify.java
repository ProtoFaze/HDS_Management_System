/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author damonng
 * Class-Shared validation
 * use within GUI for error labels or console logging
 * return error message when given invalid input, otherwise return null
 */
public class verify {
    //formats
    public static String dateRegex = "dd/MM/yyyy";
    /**
     *used to turn inputs from JDateChooser to String via dateformat.format(input)
     */
    public static final SimpleDateFormat dateformat = new SimpleDateFormat(dateRegex);
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateRegex);

    //
    public static String validateAlNum(String input){
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty() || trimmedInput.length() < 2 || "".equals(trimmedInput)) {
            return "Input must be more than 2 characters";
        }
        //returns error if any specified characters are detected
        else if (trimmedInput.matches("^[A-Z0-9]*$")) {
            return "Input contains invalid characters";
        }else{
            return null;
        }
    }
    public static String validateString(String input){
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty() || trimmedInput.length() < 2 || "".equals(trimmedInput) 
            ||"Enter username".equals(trimmedInput)|| "Your name".equals(trimmedInput)
            || "example@mail.my".equals(trimmedInput)) {
            return "Input must be more than 2 characters";
        }
        //returns error if any specified characters are detected
        else if (trimmedInput.matches(".*[,!#$%^&*()+=|<>?{}\\[\\]~].*")) {
            return "Input contains invalid characters";
        }else{
            return null;
        }
    }
    public static String validateString(String input,String PlaceHolder){
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty() || trimmedInput.length() < 2 || "".equals(trimmedInput) 
            || PlaceHolder.equals(trimmedInput)) {
            return "Input must be more than 2 characters";
        }
        //returns error if any specified characters are detected
        else if (trimmedInput.matches(".*[,!#$%^&*()+=|<>?{}\\[\\]~].*")) {
            return "Input contains invalid characters";
        }else{
            return null;
        }
    }
    public static String validatePass(String inputPassword){
        if (inputPassword.length() < 8 || inputPassword.length() > 24) {
            return "Password must be between 8 and 24 characters";
        }
        // returns error if there is an incorrect format when matching the specified regex
        else if(!inputPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,24}$")){
            return "At least 1 uppercase letter,lowercase letter, digit,and special character (@$!%*?&)";
        }else{
            return null;
        }
    }
    //checks if the date is from the past
    public static String validateDate(String date, boolean allowFutureDate){
        
        if((!date.matches("\\d{2}/\\d{2}/\\d{4}")) && (!"".equals(date)))
            return "Format day/month/year in digits only.";
        try {
            LocalDate input = LocalDate.parse(date, formatter);
            if(allowFutureDate == false){
                if (input.isAfter(LocalDate.now())){
                    return "Cannot set date from the future";
                }else{
                    return null;
                }
            }else{
                if (input.isBefore(LocalDate.now())){
                    return "Cannot set date from the past";
                }else{
                    return null;
                }
            }
        }catch(Exception e){
            return e.toString();
        }    
    }
    //allows future date, only checks if date is valid
    public static String validateDate(String date){
        if((!date.matches("\\d{2}/\\d{2}/\\d{4}")) && (!"".equals(date))){
            return "Format day/month/year in digits only.";
        }else{
            return null;
        }
    }
    public static String validateNum(String numbers){
        if(numbers.matches("\\d+")){
            return null;
        }else{
            return "Only numbers allowed";
        }
    }
    public static String validateCurrency(String numbers){
        if(numbers.matches("\\d+\\.?")){
            return null;
        }else{
            return "Numbers and ( . ) Only";
        }
    }
    //detects empty gender input
//    public static String validateGender(char gender){
//        if(gender.isEmpty() || gender.length() < 1 )
//            return "Please select a gender.";
//        else
//            return null;
//    }
}
