package pwm.profilemodel.passwordfields;

import static pwm.profilemodel.passwordfields.EntryField.getName;

/**
 * An Entry-Field that contains the title
 *
 * @author Dominik Scholz
 * @version 0.1
 */
public class TitleField extends EntryField {
    
    private static final String NAME = getName("title");

    public TitleField(String value) {
        super(value);
    }

}
