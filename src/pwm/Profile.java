package pwm;


import java.io.File;

/**
 * A Profile wich contains all Passwords
 * @author Dominik Scholz
 * @version 0.1
 */
public class Profile {

    private File profileFile;
    private boolean enrypted;
    
    public Profile(File profileFile) throws PWMException {
        checkProfilFile(profileFile);
        this.profileFile = profileFile;
    }
    
    public void encrypt(Masterkey masterkey) {
        enrypted = true;
    }
    
    public void decrypt(Masterkey masterkey) throws PWMException {
        if(isValid(masterkey)) {
            enrypted = false;
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
