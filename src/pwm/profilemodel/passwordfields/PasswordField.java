package pwm.profilemodel.passwordfields;

/**
 * An Entry-Field that contains the password
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
public class PasswordField extends EntryField {
    
    private static final String NAME = receiveName("password");

    public PasswordField(String value) {
        super(value);
    }
    
    @Override
    public String getName() { return NAME; }
    
    @Override
    public String getDisplayValue() {
        return "**********";
    }

}
