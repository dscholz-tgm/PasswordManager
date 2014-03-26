package pwm.profilemodel.passwordfields;

import static pwm.profilemodel.passwordfields.EntryField.receiveName;

/**
 * An Entry-Field that contains the title
 *
 * @author Dominik Scholz
 * @version 0.2
 */
public class TitleField extends EntryField {
    
    private static final String NAME = receiveName("title");

    public TitleField(String value) {
        super(value);
    }
    
    @Override
    public String getName() { return NAME; }

}
