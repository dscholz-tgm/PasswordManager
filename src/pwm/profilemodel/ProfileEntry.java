package pwm.profilemodel;

import java.io.Serializable;

/**
 * A Profil-Entry
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.3
 */
public interface ProfileEntry extends Serializable {
    
    /**
     * Returns the parent of this profile entry
     * @return the parent
     */
    public EntryContainer getParent();

        
    /**
     * Sets the parent of this
     * @param parent the parent of this profile entry
     */
    public void setParent(EntryContainer parent);
    
}
