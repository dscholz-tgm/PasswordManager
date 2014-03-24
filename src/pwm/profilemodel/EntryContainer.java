package pwm.profilemodel;

import java.io.Serializable;
import java.util.List;

/**
 * Specifies a class which is capable of containing entries
 * @author Dominik Scholz
 * @version 0.2
 */
public interface EntryContainer extends Serializable {
    
    /**
     * Adds an entry to this container
     * @param entry the entry
     */
    public void addEntry(ProfileEntry entry);
    
    /**
     * Returns a list of all entries contained in this container
     * @return a list af all the contained entries
     */
    public List<ProfileEntry> getEntries();
    
    /**
     * Returns the name of this entry container
     * @return the name
     */
    public String getName();
}
