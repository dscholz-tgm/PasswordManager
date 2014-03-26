package pwm.profilemodel.passwordfields;

/**
 * A generic Password-Field
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
public abstract class EntryField {

    private static final String ENTRYNAME = "entryfield.";
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
    
    public static String getName(String name) {
        return ENTRYNAME + name;
    }

}
