package pwm.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * Creates the different Cipher to prevent from
 * catching the exceptions every time
 * 
 * @author Adrian Bergler
 * @version 0.1
 */
public class CipherFactory {

	private static final Cipher fallbackCipher = getCipher("AES");

    public static Cipher getCipher(String cipherName) {
        try {
        	return Cipher.getInstance(cipherName);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
        }
        return fallbackCipher;
    }
	
}
