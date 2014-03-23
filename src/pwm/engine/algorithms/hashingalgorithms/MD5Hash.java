package pwm.engine.algorithms.hashingalgorithms;

import pwm.utils.DigestFactory;
import java.security.MessageDigest;

/**
 * MD5: Message-Digest Algorithm 
 * Length: 128 bits (16 byte). 
 * The MD5 message-digest algorithm is a widely used cryptographic hash function
 * producing a 128-bit (16-byte) hash value.
 *
 * @author Samuel Schmidt, Dominik Scholz
 * @version 0.2
 */
public class MD5Hash implements HashingAlgorithm {
    
    private final static MessageDigest messageDigest = DigestFactory.getDigest("MD5");
    
    @Override
    public byte[] hash(byte[] data) {
        return messageDigest.digest(data);
    }

}
