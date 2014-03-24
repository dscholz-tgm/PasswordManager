package test.independent.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import pwm.PWMException;
import pwm.engine.algorithms.encryptionalgorithms.AES128Encryption;
import pwm.engine.algorithms.encryptionalgorithms.EncryptionAlgorithm;
import pwm.engine.algorithms.hashingalgorithms.SHA256Hash;
import test.independent.serialization.testobjects.Mother;

/**
 * Tests reading the content of a file into a byte array and then parsing it to objects
 * 
 * @author Adrian Bergler
 * @version 0.1
 */
public class TestReadfromByteandFile {
    
    public static void main(String[] args){
        Mother mom = null;
        
        byte[] array = readfromFile();
        
        byte[] decrypted = null;
        try {
            EncryptionAlgorithm ea = null;
            ea = new AES128Encryption(new SHA256Hash());
            
            decrypted = ea.decrypt(array, "test".getBytes());
        } catch (PWMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        mom = readfromArray(decrypted);
        
        System.out.println(mom);
    }
    
    public static byte[] readfromFile(){
        byte[] toReturn = null;
        TestDatasource tds = TestDatasource.get();
        
        
        
        try(FileInputStream fis = new FileInputStream(tds.getFilename()); ByteArrayOutputStream bais = new ByteArrayOutputStream()){
            int nRead;
            byte[] data = new byte[65536]; //buffersize: 2^16

            while ((nRead = fis.read(data, 0, data.length)) != -1) {
                bais.write(data, 0, nRead);
            }
            bais.flush();

            toReturn = bais.toByteArray();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return toReturn;
    }
    
    public static Mother readfromArray(byte[] toRead){
        Mother mom = null;
        
        try{
          ByteArrayInputStream bais = new ByteArrayInputStream(toRead);
          ObjectInputStream o = new ObjectInputStream(bais);
          mom = (Mother) o.readObject();
          
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return mom;
    }
}
