package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

import pwm.profilemodel.passwordfields.EntryField;
import pwm.profilemodel.passwordfields.PasswordField;

/**
 * A Password-Entry
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.3
 */
public class PasswordEntry implements ProfileEntry {

    /**
     * Random generated serialID
     */
    private static final long serialVersionUID = 5035181717357532638L;
    
    private EntryContainer parent;
    private List<EntryField> entryfields = new ArrayList<>();

    public PasswordEntry(EntryContainer parent, String password) {
        setParent(parent);
        addEntry(new PasswordField(password));
    }
    
    @Override
    public EntryContainer getParent() { return parent; }

    @Override
    public void setParent(EntryContainer parent) { 
        this.parent = parent;  
        parent.addEntry(this);
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
