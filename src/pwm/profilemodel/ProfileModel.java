package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the top piece of the profilemodel
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class ProfileModel implements EntryContainer {
    
    private List<ProfileEntry> entries = new ArrayList<>();
    private String name;
    
    public ProfileModel(String name) {
        this.name = name;
    }

    @Override
    public List getEntries() { return entries; }
    @Override
    public String getIdentifier() { return name; }
}