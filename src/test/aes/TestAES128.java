package test.aes;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pwm.PWMException;
import pwm.engine.algorithms.encryptionalgorithms.*;
import pwm.engine.algorithms.hashingalgorithms.*;
import pwm.utils.PWMCharset;

/**
 * Testcases for the AES128 encrpytion with the SHA1Hash class
 *
 * @author Helmuth Brunner, Dominik Scholz
 * @version 0.2
 */
public class TestAES128 {

    private AES128Encryption aes;
    private byte[] in, out;
    private final Charset charset = PWMCharset.get();

    @Before
    public void prepare() throws PWMException {
        aes = new AES128Encryption(new SHA1Hash());
    }

    /**
     * Encrypts and later on decrypts String, checks if they are equal
     */
    @Test
    public void testEnDeSHA1() {

        byte[] text = "test".getBytes(charset);
        byte[] key = "key".getBytes(charset);

        try {
            in = aes.encrypt(text, key);
            out = aes.decrypt(in, key);
            Assert.assertArrayEquals(out, text);
        } catch (PWMException ex) {
            Assert.fail(ex.getMessage());
        }

    }

    /**
     * Tests different keys
     */
    @Test(expected = PWMException.class)
    public void testKey() throws PWMException {
        
        byte[] text = "test".getBytes(charset);
        byte[] encrypKey = "key".getBytes(charset);
        byte[] decrypKey = "key".getBytes(charset);
        
        in = aes.encrypt(text, encrypKey);
        out = aes.decrypt(in, decrypKey);
        Assert.assertArrayEquals(out, text);
    }
}
