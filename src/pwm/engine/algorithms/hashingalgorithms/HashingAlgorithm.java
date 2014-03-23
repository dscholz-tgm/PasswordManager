package pwm.engine.algorithms.hashingalgorithms;

/**
 * The interface used for replaceable hashing algorithms
 *
 * @author Adrian Bergler
 * @version 0.2
 */
public interface HashingAlgorithm {

    /**
     * Creates a hash using the given data as a root
     *
     * @param data the data to hash
     * @return the hash
     */
    public byte[] hash(byte[] data);

}
