package pwm.profilemodel.passwordfields;

import static pwm.profilemodel.passwordfields.EntryField.receiveName;

/**
 * An Entry-Field that contains the username
 *
 * @author Dominik Scholz
 * @version 0.2
 */
public class UsernameField extends EntryField {
    
    private static final String NAME = receiveName("username");

    public UsernameField(String value) {
        super(value);
    }
    
    @Override
    public String getName() { return NAME; }

}
