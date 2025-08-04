package dissys.smartservices;

import javax.swing.JTextArea;


/**
 *
 * @author Tsogzolmaa;
 */
public class ExceptionHandling {
    
    private boolean exceptionOccured;

    public ExceptionHandling() {
    }
    
    public boolean isValidName(String name, JTextArea resultOutput){
    
        exceptionOccured = false;
        try{
            int len = name.length();
            //Fiels cannot be empty
            if(name.isBlank()){
                throw new CustomException ("Exception: Field cannot be empty. Try again");
            }
            //This loop checks if user entered several input by counting space
            //And checks if it is only containing letters by traversing through
            //every character in the input
            for (int i = 0; i < len; i++) {
               if(name.charAt(i) == ' '){
                    throw new CustomException ("Exception: Space is not allowed. Try again");
               }
               if(!Character.isLetter(name.charAt(i))){                    
                    throw new CustomException ("Exception: City name should only contain letters. Try again");
               }
            }
                                      
        }catch(CustomException e){
            //if any custom exception occurs, boolean switches to true
            //and prints the message according to exception's message
            exceptionOccured = true;
            resultOutput.setText(e.getMessage());
        }
        //after checking user unput it returns boolean
        return exceptionOccured;
    }//isValidName method
    
       public boolean isValidNumber(int numPeople, JTextArea resultOutput){
    
        
        exceptionOccured = false;
        try{   
            if(numPeople > 4){
                throw new CustomException ("Exception: Number of people cannot be greater than 4. Try again");
            }else if(numPeople <= 0){
                throw new CustomException ();
            }else if(numPeople == 1){
                throw new CustomException ("Exception: Number of people cannot be one. Try again");
            }
        }catch(CustomException e){
            exceptionOccured = true;
            resultOutput.setText(e.getMessage());
        }
        return exceptionOccured;    
    }//isValidAge method
       
       
    public boolean isValidTaskID(int taskID, JTextArea resultOutput){
        exceptionOccured = false;
        try{   
            if(taskID > 11){
                throw new CustomException ("Exception: Task ID cannot be greater than 11. Try again");
            }else if(taskID <= 0){
                throw new CustomException ();
            }
        }catch(CustomException e){
            exceptionOccured = true;
            resultOutput.setText(e.getMessage());
        }
        return exceptionOccured;    
    }//isValidTaskID method
    
    public boolean isValidSectionName(String sectionName, JTextArea resultOutput){
    
        exceptionOccured = false;
        boolean found = false;
        String[] validSectionNames = {"Beverages", "Bakery", "Dairy", "Stationary"};
        try{
            int len = sectionName.length();
            //Fiels cannot be empty
            if(sectionName.isBlank()){
                throw new CustomException ("Exception: Field cannot be empty. Try again");
            }
            //This loop checks if user entered several input by counting space
            //And checks if it is only containing letters by traversing through
            //every character in the input
            for (int i = 0; i < len; i++) {
               if(sectionName.charAt(i) == ' '){
                    throw new CustomException ("Exception: Space is not allowed. Try again");
               }
               if(!Character.isLetter(sectionName.charAt(i))){                    
                    throw new CustomException ("Exception: Section name should only contain letters. Try again");
               }
            }
            
            //checks user input matches current valid section names
            for (String validSectionName : validSectionNames) {
                if(validSectionName.equalsIgnoreCase(sectionName)){
                    found = true;
                }
            }
            //if not found, throws exception
            //if found do nothing because if no exception occured boolean returns false
            if(!found){
                throw new CustomException ("Exception: Section name (" + sectionName + ") currently does not in available list");
            }                         
        }catch(CustomException e){
            //if any custom exception occurs, boolean switches to true
            //and prints the message according to exception's message
            exceptionOccured = true;
            resultOutput.setText(e.getMessage());
        }
        //after checking user unput it returns boolean
        return exceptionOccured;
    }//isValidSectionName method
}//class
