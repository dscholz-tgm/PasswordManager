package pwm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Creates the different MessageDigest to prevent from
 * catching the exceptions every time
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class DigestFactory {
    
    private static final MessageDigest fallbackDigest = getDigest("MD5");

    public static MessageDigest getDigest(String hashName) {
        try {
            return MessageDigest.getInstance(hashName);
        } catch (NoSuchAlgorithmException ex) {
        }
        return fallbackDigest;
    }
    
}
