package pwm.engine.algorithms.hashingalgorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import pwm.PWMException;

/**
 * SHA1: Secure Hash Algorithm 1
 * Length: 160 bits (20 byte)
 * @author Adrian Bergler
 * @version 0.1
 */
/*
 * TODO: Description! (how the algorithm works etc.)
 */
public class SHA1Hash implements HashingAlgorithm{

	@Override
	public byte[] hash(byte[] data) throws PWMException{
		
		try {
			MessageDigest sha;
			sha = MessageDigest.getInstance("SHA-1");
			data = sha.digest(data);
			return data;
			
		} catch (NoSuchAlgorithmException ex) {
			throw new PWMException(ex.getMessage());
		}
	}
	
}
