package pwm;

/**
 * Our own exception type, used for rethrowing
 * 
 * @author Dominik Scholz
 * @version 0.2
 */
public class PWMException extends Exception {
    
    public static final String INVALID_KEY = "Invalid Masterkey!";
    private String message;

    public PWMException(String message) {
        this.message = message;
    }
    
    public PWMException(Exception ex) {
        this.message = ex.getMessage();
    }
    
    @Override
    public String getMessage() { 
        return message; 
    }
}
