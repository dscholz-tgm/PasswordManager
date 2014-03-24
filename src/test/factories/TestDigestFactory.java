package test.factories;

import static org.junit.Assert.*;
import org.junit.Test;
import pwm.utils.DigestFactory;

public class TestDigestFactory {

    @Test
    public void notNull() {
        assertNotNull(DigestFactory.getDigest("SHA-256"));
    }

}