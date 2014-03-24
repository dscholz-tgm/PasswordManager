package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the top piece of the profilemodel
 * 
 * @author Dominik Scholz
 * @version 0.3
 */
public class RootEntry implements EntryContainer {
    
    /**
     * Random generated serialID
     */
    private static final long serialVersionUID = -4354069793170425639L;
    
    private List<ProfileEntry> entries = new ArrayList<>();

    @Override
    public List<ProfileEntry> getEntries() { return entries; }

    @Override
    public void addEntry(ProfileEntry entry) {
        entries.add(entry);
    }

    @Override
    public String getName() {
        return "root";
    }
    
}