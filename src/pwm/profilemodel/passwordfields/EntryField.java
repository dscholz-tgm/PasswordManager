package pwm.profilemodel.passwordfields;

import java.io.Serializable;

/**
 * A generic Password-Field
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.3
 */
public abstract class EntryField implements Serializable {

    private static final String NAME = "entryfield.";
    protected String value;

    public EntryField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public String getDisplayValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public static String receiveName(String name) {
        return NAME + name;
    }
    
    public String getName() { return NAME; }

}
