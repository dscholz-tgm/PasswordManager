package pwm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Cryptofile
 * File auf einem Pfad, random inhalt
 * google secure random algorithm
 * @author Samuel Schmidt
 * @version 0.1
 */
public class CryptFile {
    public static final String FILE_ENDING = ".pwmp";
    private int length; // File-Length
    private File cryptFile;
    
    public CryptFile(String name, int length){
//        System.out.println("file = " + profile.getFile().getCanonicalPath());
//        file = new File(profile.getFile().getCanonicalPath());
        this.cryptFile = new File(name + ".pwmp");
        this.length = length;
    }
    
    /**
     * Encrypts the CryptoFile 
     * with a Secure RNG
     * @throws PWMException
     */
    public void encrypt() throws PWMException {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[length];
        
        random.nextBytes(bytes);
        writeToFile(bytes);
    }
    
    /**
     * Writes an array to the file
     * @param toWrite the array
     */
    private void writeToFile(byte[] toWrite) {
        try (FileOutputStream fos = new FileOutputStream(cryptFile)) {
            fos.write(toWrite);
        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO Auto-generated catch block
        }
    }

    /**
     * @param cryptFile the cryptFile to set
     */
    public void setCryptFile(File cryptFile) {
        this.cryptFile = cryptFile;
    }
}
