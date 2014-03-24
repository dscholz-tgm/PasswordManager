package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the top piece of the profilemodel
 * 
 * @author Dominik Scholz
 * @version 0.2
 */
public class RootEntry implements EntryContainer {
    
    private List<ProfileEntry> entries = new ArrayList<>();

    @Override
    public List getEntries() { return entries; }
    
}