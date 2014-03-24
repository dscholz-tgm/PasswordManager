package pwm.profilemodel;

import java.util.List;

/**
 * Represents a container, cabable of storing different
 * profile entries
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public interface EntryContainer {
    
    /**
     * Returns a list of all child entries
     * @return a list of the child entries
     */
    public List getEntries();

    /**
     * Returns the identifier of this entry container
     * @return the indentifier of this entry container
     */
    public String getIdentifier();
}
