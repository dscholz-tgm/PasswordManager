package test.factories;

import static org.junit.Assert.*;

import org.junit.Test;

import pwm.utils.CipherFactory;

public class TestCipherFactory {

    @Test
    public void notNull() {
        assertNotNull(CipherFactory.getCipher("AES"));
    }

}
