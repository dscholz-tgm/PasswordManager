package test.aes;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pwm.PWMException;
import pwm.engine.algorithms.encryptionalgorithms.*;
import pwm.engine.algorithms.hashingalgorithms.*;

/**
 * Testfaelle fuer AES128 mit SHA1 HashingAlgorithm.
 * @author Brunner Helmuth
 * @version 0.1
 */

public class TestAES128 {

	private AES128Encryption aes;
	private byte[] in, out;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Ein Testfall der einfach einen Text verschluesselt und wieder entschluesselt.
	 * 
	 * Test Ver- Entschluesselung ist erfolgreich.
	 */
	@Test
	public void testEnDeSHA1() {
		
		aes= new AES128Encryption(new SHA1Hash());
		
		try {

			in= aes.encrypt("test".getBytes("UTF-8"), "key".getBytes("UTF-8"));
			out= aes.decrypt(in, "key".getBytes("UTF-8"));

			Assert.assertArrayEquals(out, "test".getBytes("UTF-8"));

		} catch (UnsupportedEncodingException | PWMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Ein Testfall der einfach einen Text verschluesselt und wieder entschluesselt.
	 * 
	 * Test Ver- Entschluesselung ist erfolgreich.
	 */
	@Test
	public void testEnDeSHA256() {
		
		aes= new AES128Encryption(new SHA256Hash());

		try {

			in= aes.encrypt("test".getBytes("UTF-8"), "key".getBytes("UTF-8"));
			out= aes.decrypt(in, "key".getBytes("UTF-8"));

			Assert.assertArrayEquals(out, "test".getBytes("UTF-8"));

		} catch (UnsupportedEncodingException | PWMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/*
	 * TODO TestCase ueberarbeiten
	 */
	
	/**
	 * Test unterschiedliche Ent- und Verschluesselungskeys
	 */
	@Test(expected= PWMException.class)
	public void testKey() {

		aes= new AES128Encryption(new SHA1Hash());
		
		try {

			in= aes.encrypt("test".getBytes("UTF-8"), "key".getBytes("UTF-8"));
			out= aes.decrypt(in, "tra".getBytes("UTF-8"));

			Assert.assertArrayEquals(out, "test".getBytes("UTF-8"));

		} catch (UnsupportedEncodingException | PWMException e) {
			
		}
	}
}
