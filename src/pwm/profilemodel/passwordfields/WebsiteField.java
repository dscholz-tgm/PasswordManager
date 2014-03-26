package pwm.profilemodel.passwordfields;

import static pwm.profilemodel.passwordfields.EntryField.getName;

/**
 * An Entry-Field that contains the website
 *
 * @author Dominik Scholz
 * @version 0.1
 */
public class WebsiteField extends EntryField {
    
    private static final String NAME = getName("website");

    public WebsiteField(String value) {
        super(value);
    }

}
