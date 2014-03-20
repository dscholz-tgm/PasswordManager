package pwm.engine.algorithms.encryptionalgorithms;

import pwm.engine.algorithms.hashingalgorithms.HashingAlgorithm;

/**
 * Mock-Encryption-Algorithm used for testing
 * "Der wahrscheinlich schlechteste Verschlüsselungsalgorithmus der Welt"
 * @author Adrian Bergler
 * @version 1.0
 */
public class MockEncryption extends EncryptionAlgorithm{

	public MockEncryption(HashingAlgorithm hashAlg) {
		super(hashAlg);
	}

	@Override
	public byte[] encrypt(byte[] data, byte[] key) {
		return data;
	}

	@Override
	public byte[] decrypt(byte[] data, byte[] key) {
		return data;
	}

}
