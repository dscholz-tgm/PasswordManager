package pwm;

import pwm.profilemodel.RootEntry;
import java.io.File;

/**
 * A Profile which contains all Passwords
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.1
 */
public class Profile {

    private File profileFile;
    private boolean encrypted;
    private RootEntry profilemodel;
    
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
