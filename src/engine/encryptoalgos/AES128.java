package engine.encryptoalgos;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import pwm.PWMException;
import engine.hashalgos.HashingAlgorithm;

/**
 * AES: Advanced Encryption Standard-128
 * @author Adrian Bergler
 * @version 0.1
 */
/*
 * TODO: Description! (how the algorithm works etc.)
 */
public class AES128 extends EncryptionAlgorithm{
	
	private final int KEYLENGTH = 16;
	
	public AES128(HashingAlgorithm hashAlg) {
		super(hashAlg);
	}

	@Override
	public byte[] encrypt(byte[] data, byte[] key) throws PWMException{
		try {
			//Hashing the key
			key = hashAlg.hash(key);
			
			if(key.length < KEYLENGTH){
				throw new PWMException("Invalid hashlength!");
			}
			
			key = Arrays.copyOf(key,16);
			
			/*
			 * You could probably extend this to make it easier to adapt by adding the KEYLENGTH to the interface
			 * and initialize it in the constructor. You could then add a "prepareHash"-method to the interface that works dynamically using the KEYLENGTH.
			 */
			
			//Preparing the algorithm
			Cipher cipher = Cipher.getInstance("AES");	// AESCBC/NoPadding
			SecretKeySpec k = new SecretKeySpec(key, "AES");
			
			//Setting the algorithm to encryption mode
			cipher.init(Cipher.ENCRYPT_MODE, k);
			
			//Encrypt!
			byte[] encryptedData = cipher.doFinal(data);
			
			return encryptedData;

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
			throw new PWMException(ex.getMessage());
		}
	}

	@Override
	public byte[] decrypt(byte[] data, byte[] key) throws PWMException {
		try {
			//Hashing the key
			key = hashAlg.hash(key);
			
			if(key.length < KEYLENGTH){
				throw new PWMException("Invalid hashlength!");
			}
			
			key = Arrays.copyOf(key, 16); //TODO REMOVE LATER. This is just to cut the SHA1 hash to 128bit.
			
			//Preparing the algorithm
			Cipher cipher = Cipher.getInstance("AES"); // AESCBC/NoPadding
			SecretKeySpec k = new SecretKeySpec(key, "AES");
			
			//Setting the algorithm to decryption mode
			cipher.init(Cipher.DECRYPT_MODE, k);
			
			//Decrypt!
			byte[] decryptedData = cipher.doFinal(data);
			
			return decryptedData;

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
			throw new PWMException(ex.getClass() + ex.getMessage());
		}
	}
	
}
