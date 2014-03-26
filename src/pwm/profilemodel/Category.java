package pwm.profilemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Category which stores other entries
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.6
 */
public class Category implements ProfileEntry, EntryContainer {
    
    /**
     * Random generated serialID
     */
    private static final long serialVersionUID = 2069284891251627767L;
    
    private EntryContainer parent;
    private List<ProfileEntry> entries = new ArrayList<>();
    private String name;
    private String desc;

    /**
     * Create a category with a parent and a name
     * 
     * @param parent the parent of the category
     */
    public Category(EntryContainer parent) {
        this(parent, "Category " + parent.getEntries().size()+1);
    }
    
    /**
     * Create a category with a parent and a name
     * 
     * @param parent the parent of the category
     * @param name the name of the category
     */
    public Category(EntryContainer parent, String name) {
        this(parent, name, "");
    }
    
    /**
     * Creates a category with a name and a description
     * 
     * @param parent the parent of the category
     * @param name the name of the category
     * @param desc the description
     */
    public Category(EntryContainer parent, String name, String desc) {
        this.name = name;
        this.desc = desc;
        setParent(parent);
    }

    @Override
    public String getName() { return name; }
    public String getDescription() { return desc; }
    
    @Override
    public List<ProfileEntry> getEntries() { return entries; }
    
    public void setName(String name) { this.name = name; }
    public void setDescription(String desc) { this.desc = desc; }

    @Override
    public EntryContainer getParent() {  return parent; }
    
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

    @Override
    public void setParent(EntryContainer parent) { 
        this.parent = parent;  
        parent.addEntry(this);
    }

    @Override
    public void addEntry(ProfileEntry entry) {
        entries.add(entry);
    }

}
