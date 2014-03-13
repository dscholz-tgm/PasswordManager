package pwm;

/**
 * @author Dominik Scholz
 * @version 0.1
 */
public class PWMException extends Exception {
    
    public static final String INVALID_KEY = "Ungültiger Masterkey!";
    private String message;

    public PWMException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
