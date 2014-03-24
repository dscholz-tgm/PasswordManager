package pwm.engine.algorithms.hashingalgorithms;

import java.security.MessageDigest;
import pwm.utils.DigestFactory;

/**
 * SHA-256: Secure Hash Algorithm 256 
 * Length: 256 bits (32 byte).
 * SHA-256 is a novel hash functions computed with 32 and 64-bit words, respectively.
 *
 * @author Samuel Schmidt, Scholz Dominik
 * @version 0.2
 */
public class SHA256Hash implements HashingAlgorithm {

	private static final MessageDigest messageDigest = DigestFactory.getDigest("SHA-256");
    
    @Override
    public byte[] hash(byte[] data) {
        return messageDigest.digest(data);
    }

}
