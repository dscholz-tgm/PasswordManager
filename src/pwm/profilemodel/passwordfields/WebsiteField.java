package pwm.profilemodel.passwordfields;

import static pwm.profilemodel.passwordfields.EntryField.receiveName;

/**
 * An Entry-Field that contains the website
 *
 * @author Dominik Scholz
 * @version 0.2
 */
public class WebsiteField extends EntryField {
    
    private static final String NAME = receiveName("website");

    public WebsiteField(String value) {
        super(value);
    }
    
    @Override
    public String getName() { return NAME; }

}
