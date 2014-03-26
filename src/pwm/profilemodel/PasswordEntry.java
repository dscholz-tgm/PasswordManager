package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

import pwm.profilemodel.passwordfields.EntryField;
import pwm.profilemodel.passwordfields.PasswordField;
import pwm.profilemodel.passwordfields.TitleField;
import pwm.profilemodel.passwordfields.UsernameField;
import pwm.profilemodel.passwordfields.WebsiteField;

/**
 * A Password-Entry
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.5
 */
public class PasswordEntry implements ProfileEntry {

    /**
     * Random generated serialID
     */
    private static final long serialVersionUID = 5035181717357532638L;
    
    public static final EntryField[] tableFields = new EntryField[]{new TitleField(""), new UsernameField(""), new PasswordField(""),new WebsiteField("")};
    
    private EntryContainer parent;
    private List<EntryField> entryfields = new ArrayList<>();

    public PasswordEntry(EntryContainer parent, String title, String username, String password, String website) {
        setParent(parent);
        addEntryField(new TitleField(title));
        addEntryField(new UsernameField(username));
        addEntryField(new PasswordField(password));
        addEntryField(new WebsiteField(website));
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
     * 
     * @param field the field which should be added
     * @return if the adding was successful
     */
    public boolean addEntryField(EntryField field) { 
        return entryfields.add(field); 
    }
    
}
