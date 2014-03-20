package pwm.engine.algorithms.hashingalgorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import pwm.PWMException;

/**
 * SHA-256: Secure Hash Algorithm 256
 * Length: 256 bits (32 byte)
 * @author Schmidt Samuel
 * @version 0.1
 */
/*
 * SHA-256 is a novel hash functions computed with 32 and 64-bit words, respectively.
 */
public class SHA256Hash implements HashingAlgorithm{

	@Override
	public byte[] hash(byte[] data) throws PWMException{
		
		try {
			MessageDigest md5;
			md5 = MessageDigest.getInstance("SHA-256");
			data = md5.digest(data);
			return data;
			
		} catch (NoSuchAlgorithmException ex) {
			throw new PWMException(ex.getMessage());
		}
	}
	
}
