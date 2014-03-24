package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

import pwm.profilemodel.passwordfields.EntryField;
import pwm.profilemodel.passwordfields.PasswordField;

/**
 * A Password-Entry
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
public class PasswordEntry extends ProfileEntry {

    private List<EntryField> entryfields = new ArrayList<>();

    public PasswordEntry(EntryContainer parent, String name, String password) {
        super(parent,name);
        addEntry(new PasswordField(password));
    }

    public List<EntryField> getEntryfields() { return entryfields;  }
    public void setEntryfields(List<EntryField> entryfields) { this.entryfields = entryfields; }
    
    /**
     * Adds an field to the entryfields
     * @param field the field which should be added
     * @return if the adding was successful
     */
    public boolean addEntry(EntryField field) { 
        return entryfields.add(field); 
    }
}
