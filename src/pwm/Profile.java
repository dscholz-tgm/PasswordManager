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
import pwm.utils.PWMCharset;

/**
 * A Profile which contains all Passwords
 * 
 * @author Adrian Bergler, Dominik Scholz, Samuel Schmidt
 * @version 0.6
 */
public class Profile {

    public static final String FILE_ENDING = ".pwmp";
    private File profileFile;
    private RootEntry profileModel;
    private byte[] key;

    /**
     * Constructor for opening an existing profile, calls normal constructor
     * 
     * @param masterkey the key to encrpyt the profile
     * @param profileFile the file where the profile is stored
     * @throws PWMException when the profile couldn't be loaded (wrong key, etc.)
     */
    public Profile(String masterkey, File profileFile) throws PWMException {
        this.profileFile = profileFile;
        key = masterkey.getBytes(PWMCharset.get());
        decrypt();
        profileModel.setName(profileFile.getName());
    }
    
    /**
     * Constructor
     * 
     * @param masterkey the key in plaintext
     */
    public Profile(String masterkey) {
        key = masterkey.getBytes(PWMCharset.get());
        profileModel = new RootEntry();
        profileModel.setName("/");
    }

    /**
     * Encrypts the profilemodel and saves it to the file
     * @throws PWMException
     */
    public void encrypt() throws PWMException {
        byte[] toEncrypt = writeToArray();
        byte[] encrypted = null;
        
        EncryptionAlgorithm encryptionAlgorithm = EncryptionFactory.getEncryption();
        encrypted = encryptionAlgorithm.encrypt(toEncrypt, key);
        
        writeToFile(encrypted);
    }

    /**
     * Decrypts the file and saves it to the profilemodel
     * @throws PWMException
     */
    public void decrypt() throws PWMException {
        byte[] ciphertext = readFromFile();
        byte[] decrypted = null;
        
        EncryptionAlgorithm encryptionAlgorithm = EncryptionFactory.getEncryption();
        decrypted = encryptionAlgorithm.decrypt(ciphertext, key);
        profileModel = readFromArray(decrypted);
    }

    /**
     * Parses the profilemodel into an array
     * @return the profilemodel as array
     */
    private byte[] writeToArray() {
        byte[] toReturn = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream o = new ObjectOutputStream(baos)) {
            o.writeObject(profileModel);
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
        try (FileOutputStream fos = new FileOutputStream(profileFile)) {

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
    public RootEntry readFromArray(byte[] toRead) throws PWMException {
        RootEntry toReturn = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(toRead);
            ObjectInputStream o = new ObjectInputStream(bais);
            toReturn = (RootEntry) o.readObject();

        } catch (ClassNotFoundException | IOException ex) {
            throw new PWMException("error.readProfile");
        }
        
        return toReturn;
    }

    /**
     * Reads an array from a file
     * @return the content of a file as array
     */
    public byte[] readFromFile() throws PWMException {
        byte[] toReturn = null;

        try (FileInputStream fis = new FileInputStream(profileFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[65536]; // buffersize: 2^16

            while ((nRead = fis.read(data, 0, data.length)) != -1) {
                baos.write(data, 0, nRead);
            }
            baos.flush();

            toReturn = baos.toByteArray();

        } catch (IOException ex) {
            throw new PWMException("error.readProfile");
        }

        return toReturn;
    }

    public RootEntry getRootEntry() { return profileModel; }
    
    public byte[] getKey() {return key;}

    public void setKey(byte[] key) {this.key = key;}

    public File getFile() {return profileFile;}

    public void setFile(File file) { profileFile = file; }
}
