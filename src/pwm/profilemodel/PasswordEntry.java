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
public class PasswordEntry implements ProfileEntry {

    private EntryContainer parent;
    private List<EntryField> entryfields = new ArrayList<>();

    public PasswordEntry(EntryContainer parent, String password) {
        this.parent = parent;
        addEntry(new PasswordField(password));
    }
    
    @Override
    public EntryContainer getParent() { return parent; }

    @Override
    public void setParent(EntryContainer parent) { this.parent = parent; }

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
