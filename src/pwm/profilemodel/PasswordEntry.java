package pwm.profilemodel;

import java.util.List;

import pwm.profilemodel.passwordfields.EntryField;

/**
 * A Password-Entry
 *
 * @author Adrian Bergler
 * @version 0.1
 */
public class PasswordEntry extends ProfileEntry {

    protected List<EntryField> entryfields;

    public PasswordEntry() {
        //TODO
    }

    public List<EntryField> getEntryfields() {
        return entryfields;
    }

    public void setEntryfields(List<EntryField> entryfields) {
        this.entryfields = entryfields;
    }

    /*
     * Stub!
     * TODO
     */
}
