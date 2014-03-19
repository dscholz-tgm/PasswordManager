package engine.hashalgos;

import pwm.PWMException;

/**
 * The interface used for replaceable hashing algorithms
 * @author Adrian Bergler
 * @version 0.1
 */
public interface HashingAlgorithm {

	/**
	 * Creates a hash using the given data as a root
	 * @param data the data to hash
	 * @return the hash
	 * @throws PWMException if an error occurs
	 */
	public byte[] hash(byte[] data) throws PWMException;
	
}
