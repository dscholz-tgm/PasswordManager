package pwm.profilemodel;

import java.io.Serializable;
import java.util.List;

/**
 * Specifies a class which is capable of containing entries
 * @author Dominik Scholz
 * @version 0.1
 */
public interface EntryContainer extends Serializable {
    
    /**
     * Returns a list of all entries contained in this container
     * @return a list af all the contained entries
     */
    public List<ProfileEntry> getEntries();
}
