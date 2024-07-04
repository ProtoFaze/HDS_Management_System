package com.damon;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author damonng
 */
public class file {
    public static HashMap<String, Object> data = new HashMap<>();
    private static final Logger logger = Logger.getLogger(file.class.getName());
    public static final String SRC = "./src/main/java/com/damon/";

    public static Object[] extract(String filename){
        try(BufferedReader br = new BufferedReader(new FileReader(SRC+filename+".txt"))){
            Object[] records = br.lines().toArray();
            
            return records;
            
        } catch (IOException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static int getLatestID(int IDFileLine){
        int lastID = -1;

        try(BufferedReader br = new BufferedReader(new FileReader(SRC+"ID.txt"))){
            for(int i =0; i<IDFileLine;i++){
                String line = br.readLine();
                if(line != null && !line.isEmpty()){
                    String id = line.trim();
                    String numericStr = id.substring(1).replaceAll("^0+", " ");
                    lastID = Integer.parseInt(numericStr.trim());
                }
            }
            return lastID;
        }catch (IOException e){
            logger.log(Level.SEVERE,"An error occured",e);
        }
        return lastID;
    }

    
    public static String generateID(int lastID, String IDtype) {
        int position, newID = lastID + 1;
        String spaces, prefix;
        switch (IDtype) {
            case "staff" : {
                position = 0;
                spaces = "3";
                prefix = "S";
                break;
            }
            case "customer" : {
                position = 1;
                spaces = "8";
                prefix = "C";
                break;
            }
            case "room" : {
                position = 3;
                spaces = "3";
                prefix = "R";
                break;
            }
            case "booking" : {
                position = 2;
                spaces = "10";
                prefix = "B";
                break;
            }
            // for floors
            default : {
                position = 4;
                spaces = "2";
                prefix = "F";
                break;
            }
        }
        String id = prefix + String.format("%0" + spaces + "d", newID);
        try (BufferedReader br = new BufferedReader(new FileReader(SRC+"ID.txt"))){
            String[] ids = new String[5];
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                ids[i] = line.trim();
                i+=1;
            }
            ids[position] = id;
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(SRC+"ID.txt", false))){
                for (i = 0; i < 5; i++) {
                
                bw.write(ids[i] + "\n"); // append the new ID to the file
                }
            }
        } catch (IOException e) {
            return e.toString();
        }
        return id;
    }

    /**
     *
     * @param name
     * @param content
     * @return status of append operation
     */
    public static String append(String name, List<String> content){
        String file = SRC+name+".txt";
        try(PrintWriter outputFile = new PrintWriter(new FileWriter(file, true))){
            for(String record: content){
                outputFile.println(record);
            }
            return "Success";
        }catch (IOException ex){
            return ex.toString();
        }
    }
    public static String rewrite(String name, List<String> content){
        String file = SRC+name+".txt";
        try(PrintWriter outputFile = new PrintWriter(new FileWriter(file, false))){
            for(String record: content){
                outputFile.println(record);
            }
            return "Success";
        }catch (IOException ex){
            return ex.toString();
        }
    }
    
}
