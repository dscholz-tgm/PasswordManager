package pwm.engine.algorithms.encryptionalgorithms;

import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import pwm.PWMException;
import pwm.engine.algorithms.hashingalgorithms.HashingAlgorithm;
import pwm.utils.CipherFactory;

/**
 * AES: Advanced Encryption Standard-128
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
/*
 * TODO: Description! (how the algorithm works etc.)
 */
public class AES128Encryption extends EncryptionAlgorithm {

    private final Cipher cipher = CipherFactory.getCipher("AES"); //AESCBC/NoPadding

    public AES128Encryption(HashingAlgorithm hashAlg) throws PWMException {
        super(hashAlg);
        
        setKeylength(16);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws PWMException {
        byte[] toReturn = crypto(data, key, Cipher.ENCRYPT_MODE);
    	return toReturn;
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws PWMException {    	
        return crypto(data, key, Cipher.DECRYPT_MODE);
    }

    private byte[] crypto(byte[] data, byte[] key, int mode) throws PWMException{
        try {
        	//Hashing and cutting the key
            key = assimilateKey(key);

            //Preparing the algorithm
            SecretKeySpec k = new SecretKeySpec(key, "AES");

            //Setting the algorithm to decryption mode
            cipher.init(mode, k);
            
            //Encrypt/Decrypt!
			return cipher.doFinal(data);
		} catch (IllegalBlockSizeException | BadPaddingException | PWMException | InvalidKeyException ex) {
			throw new PWMException(ex);
		}
    }
    
}
