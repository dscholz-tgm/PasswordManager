package pwm;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pwm.profilemodel.ProfileEntry;

/**
 * A Profile which contains all Passwords
 * @author Dominik Scholz
 * @version 0.1
 */
public class Profile {

    private File profileFile;
    private boolean encrypted;
    private List<ProfileEntry> profileEntries;
    
    public Profile(File profileFile) throws PWMException {
        checkProfilFile(profileFile);
        this.profileFile = profileFile;
    }
    
    public void encrypt(Masterkey masterkey) {
        encrypted = true;
    }
    
    public void decrypt(Masterkey masterkey) throws PWMException {
        if(isValid(masterkey)) {
            encrypted = false;
        } else throw new PWMException(PWMException.INVALID_KEY);
    }

    private boolean isValid(Masterkey masterkey) {
        return true;
    }

    private void checkProfilFile(File profileFile) throws PWMException {
        //more advanced stuff to come!
        if(profileFile.getPath().endsWith(".pwmp"));
    }
}
