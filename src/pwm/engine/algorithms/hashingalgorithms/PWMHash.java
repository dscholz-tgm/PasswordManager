package pwm.engine.algorithms.hashingalgorithms;

import pwm.utils.ArrayModifier;
import pwm.utils.PWMCharset;

/**
 * Special salted hash algorithm,
 * hashes as follows:
 * baseHash(salt + baseHash(data + salt))
 *
 * @author Dominik Scholz
 * @version 0.1
 */
public class PWMHash implements HashingAlgorithm {

    private static final byte[] salt = "PsSWrddMnnngrr".getBytes(PWMCharset.get()); //don't tpuch the salt!
    private final HashingAlgorithm baseHash = new SHA256Hash();

    @Override
    public byte[] hash(byte[] data) {
        return baseHash.hash(ArrayModifier.combine(salt, ArrayModifier.combine(baseHash.hash(data), salt)));
    }

}
