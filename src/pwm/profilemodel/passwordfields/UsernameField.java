package pwm.profilemodel.passwordfields;

import static pwm.profilemodel.passwordfields.EntryField.getName;

/**
 * An Entry-Field that contains the username
 *
 * @author Dominik Scholz
 * @version 0.1
 */
public class UsernameField extends EntryField {
    
    private static final String NAME = getName("username");

    public UsernameField(String value) {
        super(value);
    }

}
