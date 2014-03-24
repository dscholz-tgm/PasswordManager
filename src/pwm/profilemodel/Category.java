package pwm.profilemodel;

import java.util.List;

/**
 * Password-Category
 *
 * @author Adrian Bergler
 * @version 0.1
 */
public class Category extends ProfileEntry {

    private List<ProfileEntry> profileentries;

    public Category() {
        //TODO
    }

    public List<ProfileEntry> getProfileentries() {
        return profileentries;
    }

    public void setProfileentries(List<ProfileEntry> profileentries) {
        this.profileentries = profileentries;
    }

    /*
     * Stub!
     * TODO
     */
}
