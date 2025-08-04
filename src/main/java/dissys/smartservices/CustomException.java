package dissys.smartservices;

/**
 *
 * @author Tsogzolmaa;
 */
public class CustomException extends Exception {
   
    protected String message = "Exception: Negative value or zero is not allowed. Try again";

    public CustomException() {
    }

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    
}
