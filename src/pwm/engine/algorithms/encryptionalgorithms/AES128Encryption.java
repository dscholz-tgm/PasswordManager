package pwm.engine.algorithms.encryptionalgorithms;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import pwm.PWMException;
import pwm.engine.algorithms.hashingalgorithms.HashingAlgorithm;
import pwm.utils.CipherFactory;

/**
 * AES: Advanced Encryption Standard-128
 *
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.2
 */
/*
 * TODO: Description! (how the algorithm works etc.)
 */
public class AES128Encryption extends EncryptionAlgorithm {

    private final Cipher cipher = CipherFactory.getCipher("AES"); //AESCBC/NoPadding
    private final int KEYLENGTH = 16;

    public AES128Encryption(HashingAlgorithm hashAlg) throws PWMException {
        super(hashAlg);        
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws PWMException {
        try {
            key = hashAlg.hash(key);
            if (key.length < KEYLENGTH) {
                throw new PWMException("Invalid hashlength!");
            }
            key = Arrays.copyOf(key, 16);
            SecretKeySpec k = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, k);
            byte[] encryptedData = cipher.doFinal(data);
            return encryptedData;
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            throw new PWMException(ex);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws PWMException {
        try {
            //Hashing the key
            key = hashAlg.hash(key);

            if (key.length < KEYLENGTH) {
                throw new PWMException("Invalid hashlength!");
            }

            key = Arrays.copyOf(key, 16); //TODO REMOVE LATER. This is just to cut the SHA1 hash to 128bit.

            //Preparing the algorithm
            SecretKeySpec k = new SecretKeySpec(key, "AES");

            //Setting the algorithm to decryption mode
            cipher.init(Cipher.DECRYPT_MODE, k);

            //Decrypt!
            byte[] decryptedData = cipher.doFinal(data);

            return decryptedData;

        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            throw new PWMException(ex);
        }
    }

}
