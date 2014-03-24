package pwm.engine.algorithms.encryptionalgorithms;

import java.util.Arrays;

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
    protected int keylength;

    public EncryptionAlgorithm(HashingAlgorithm hashAlg) {
        this.hashAlg = hashAlg;
    }

    public int getKeylength() { return keylength; }
	public void setKeylength(int keylength) { this.keylength = keylength; }
    
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
    
    /**
     * Assimilates the key to match the keylength.
     * Resistance is futile!
     * @param key
     * @return the matched key
     * @throws PWMException 
     */
    public byte[] assimilateKey(byte[] key) throws PWMException{
    	key = hashAlg.hash(key);
        if (key.length < keylength) {
            throw new PWMException("Invalid hashlength!");
        }
        key = Arrays.copyOf(key, keylength);
        
        return key;
    }
}