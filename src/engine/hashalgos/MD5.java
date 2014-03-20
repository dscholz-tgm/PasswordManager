package engine.hashalgos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import pwm.PWMException;

/**
 * MD5: Message-Digest Algorithm 5
 * Length: 128 bits (16 byte)
 * @author Schmidt Samuel
 * @version 0.1
 */
/*
 * The MD5 message-digest algorithm is a widely used cryptographic hash function producing a 128-bit (16-byte) hash value
 */
public class MD5 implements HashingAlgorithm{

	@Override
	public byte[] hash(byte[] data) throws PWMException{
		
		try {
			MessageDigest md5;
			md5 = MessageDigest.getInstance("MD5");
			data = md5.digest(data);
			return data;
			
		} catch (NoSuchAlgorithmException ex) {
			throw new PWMException(ex.getMessage());
		}
	}
	
}
