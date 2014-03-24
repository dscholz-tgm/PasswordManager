package pwm.profilemodel;

/**
 * A Profil-Entry
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
public abstract class ProfileEntry {

    protected EntryContainer parent;
    protected String name;
    protected String identifier;
    
    public ProfileEntry(EntryContainer parent) {
        this(parent,"Entry " + parent.getEntries().size() + 1);
    }
    
    public ProfileEntry(EntryContainer parent, String name) {
        this.name = name;
        updateParent(parent);
    }
    
    public EntryContainer getParent() { return parent; };
    public String getName() { return name; };
    public String getIdentifier() { return identifier; };
    
    public void updateParent(EntryContainer parent) {
        this.parent = parent;
        buildIdentifier();
    }
    
    public void updateName(String name) {
        this.name = name;
        buildIdentifier();
    }
    
    private void buildIdentifier() {
        identifier = parent.getIdentifier() + "." + name;
    }
}
