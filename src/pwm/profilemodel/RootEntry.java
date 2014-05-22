package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the top piece of the profilemodel
 * 
 * @author Dominik Scholz, Samuel Schmidt
 * @version 0.5
 */
public class RootEntry implements EntryContainer {
    
    /**
     * Random generated serialID
     */
    private static final long serialVersionUID = -4354069793170425639L;
    private List<ProfileEntry> entries = new ArrayList<>();;
    private String name;

    @Override
    public List<ProfileEntry> getEntries() { return entries; }

    @Override
    public void addEntry(ProfileEntry entry) {
        entries.add(entry);
    }
    
    @Override
    public String getName() {
        return name;
    }

    /**
     * Dirty, please remove in future versions!
     * @return the entrycontainerlist
     */
    @Override
    public List<EntryContainer> getContainerEntries() {
        List<EntryContainer> containerEntries = new ArrayList<>();
        for(ProfileEntry pe : entries) if(pe instanceof Category) containerEntries.add((Category) pe);
        return containerEntries; 
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
}