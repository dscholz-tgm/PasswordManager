package pwm.engine.algorithms.hashingalgorithms;

/**
 * Mock-Hashing-Algorithm used for testing
 * 
 * @author Adrian Bergler
 * @version 1.0
 */
public class MockHash implements HashingAlgorithm {

    @Override
    public byte[] hash(byte[] data) {
        return data;
    }

}
