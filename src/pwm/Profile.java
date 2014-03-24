package pwm;

import pwm.engine.algorithms.encryptionalgorithms.EncryptionAlgorithm;
import pwm.profilemodel.RootEntry;
import pwm.utils.EncryptionFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A Profile which contains all Passwords
 * 
 * @author Adrian Bergler, Dominik Scholz
 * @version 0.1
 */
public class Profile {

    private File profileFile;
    private RootEntry profilemodel;
    private final String filename = "passwords.pwmp";
    
    private byte[] key;
    
    private static Profile instance;

    public Profile(File profileFile) throws PWMException {
        checkProfilFile(profileFile);
        this.profileFile = profileFile;
    }
    
    public static Profile get() { return instance; }

    /**
     * Encrypts the profilemodel and saves it to the file
     * @throws PWMException
     */
    public void encrypt() throws PWMException {
        byte[] toEncrypt = writeToArray();
        byte[] encrypted = null;
        
        try {
            EncryptionAlgorithm encryptionAlgorithm = EncryptionFactory.getEncryption();
            
            encrypted = encryptionAlgorithm.encrypt(toEncrypt, key);
        } catch (PWMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        writeToFile(encrypted);
    }

    /**
     * Decrypts the file and saves it to the profilemodel
     * @throws PWMException
     */
    public void decrypt() throws PWMException {
        byte[] ciphertext = readfromFile();
        
        byte[] decrypted = null;
        
        try {
            EncryptionAlgorithm encryptionAlgorithm = EncryptionFactory.getEncryption();
            
            decrypted = encryptionAlgorithm.decrypt(ciphertext, key);
        } catch (PWMException e) {
            wrongKey();     //Add more stuff?
        }
        
        profilemodel = readfromArray(decrypted);
    }

    /**
     * Parses the profilemodel into an array
     * @return the profilemodel as array
     */
    private byte[] writeToArray() {
        byte[] toReturn = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream o = new ObjectOutputStream(baos)) {
            o.writeObject(profilemodel);
            o.flush();

            toReturn = baos.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO
        }

        return toReturn;
    }

    /**
     * Writes an array to the file
     * @param toWrite the array
     */
    private void writeToFile(byte[] toWrite) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {

            fos.write(toWrite);

        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO Auto-generated catch block
        }
    }

    /**
     * Parses an array to a RootEntry
     * @param toRead the array to parse
     * @return a RootEntry that can be used as profileentry
     */
    public RootEntry readfromArray(byte[] toRead) {
        RootEntry toReturn = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(toRead);
            ObjectInputStream o = new ObjectInputStream(bais);
            toReturn = (RootEntry) o.readObject();

        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }

    /**
     * Reads an array from a file
     * @return the content of a file as array
     */
    public byte[] readfromFile() {
        byte[] toReturn = null;

        try (FileInputStream fis = new FileInputStream(filename);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[65536]; // buffersize: 2^16

            while ((nRead = fis.read(data, 0, data.length)) != -1) {
                baos.write(data, 0, nRead);
            }
            baos.flush();

            toReturn = baos.toByteArray();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return toReturn;
    }

    /**
     * Gets called when the key used for decryption is wrong
     */
    private void wrongKey(){
      //Add more stuff?
        System.out.println("Wrong Key!");
    }

    private void checkProfilFile(File profileFile) throws PWMException {
        // more advanced stuff to come!
        if (profileFile.getPath().endsWith(".pwmp"));
    }
}
