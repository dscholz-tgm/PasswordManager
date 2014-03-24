package pwm.engine.algorithms.encryptionalgorithms;

import pwm.PWMException;
import pwm.engine.algorithms.hashingalgorithms.HashingAlgorithm;

/**
 * The interface used for replaceable encryption algorithms
 *
 * @author Adrian Bergler
 * @version 0.1
 */
public abstract class EncryptionAlgorithm {

    protected HashingAlgorithm hashAlg;

    public EncryptionAlgorithm(HashingAlgorithm hashAlg) {
        this.hashAlg = hashAlg;
    }

    /**
     * Encrypts the given data using the given key
     *
     * @param data the data to encrypt (plaintext)
     * @param key the key used to encrypt the data
     * @return the encrypted data (ciphertext)
     * @throws PWMException 
     */
    public abstract byte[] encrypt(byte[] data, byte[] key) throws PWMException;

    /**
     * Decrypts the given data using the given key
     *
     * @param data the data to decrypt (ciphertext)
     * @param key the key used to decrypt the data
     * @return the decrypted data (plaintext)
     * @throws PWMException 
     */
    public abstract byte[] decrypt(byte[] data, byte[] key) throws PWMException;
}
