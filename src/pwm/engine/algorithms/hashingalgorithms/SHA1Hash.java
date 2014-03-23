package pwm.engine.algorithms.hashingalgorithms;

import java.security.MessageDigest;
import pwm.utils.DigestFactory;

/**
 * SHA1: Secure Hash Algorithm 1 
 * Length: 160 bits (20 byte). 
 * The SHA-1 Algorithm is a 160-bit hash function which resembles the earlier 
 * MD5 algorithm. Cryptographic weaknesses were discovered in SHA-1 and so the
 * it is no longer approved for most cryptographic uses.
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
public class SHA1Hash implements HashingAlgorithm {

    private final static MessageDigest messageDigest = DigestFactory.getDigest("SHA-1");
    
    @Override
    public byte[] hash(byte[] data) {
        return messageDigest.digest(data);
    }

}
