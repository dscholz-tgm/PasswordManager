package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Category which stores other entries
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
public class Category extends ProfileEntry implements EntryContainer {
    
    private List<ProfileEntry> entries = new ArrayList<>();
    private String desc;

    /**
     * Create a category with a parent and a name
     * 
     * @param parent the parent of the category
     */
    public Category(EntryContainer parent) {
        super(parent);
    }
    
    /**
     * Create a category with a parent and a name
     * 
     * @param parent the parent of the category
     * @param name the name of the category
     */
    public Category(EntryContainer parent, String name) {
        super(parent,name);
    }
    
    /**
     * Creates a category with a name and a description
     * 
     * @param parent the parent of the category
     * @param name the name of the category
     * @param desc the description
     */
    public Category(EntryContainer parent, String name, String desc) {
        super(parent,name);
        this.desc = desc;
    }

    @Override
    public List getEntries() { return entries; }
    
    @Override
    public String getIdentifier() { return identifier; }

}
