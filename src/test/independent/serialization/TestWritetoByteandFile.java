package test.independent.serialization;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import pwm.PWMException;
import pwm.engine.algorithms.encryptionalgorithms.AES128Encryption;
import pwm.engine.algorithms.encryptionalgorithms.EncryptionAlgorithm;
import pwm.engine.algorithms.hashingalgorithms.SHA256Hash;
import test.independent.serialization.testobjects.Mother;

/**
 * Tests parsing objects to a byte array and then write into a file
 * 
 * @author Adrian Bergler
 * @version 0.1
 */
public class TestWritetoByteandFile {
    
    public static void main(String[] args){
        TestDatasource tds = TestDatasource.get();
        
        Mother mom = tds.makeMother();
        
        byte[] array = writetoArray(mom);
        
        if(array == null) System.out.println("Arraybug");
        
        byte[] key = "test".getBytes();
        
        byte[] encrypted = null;
        try {
            EncryptionAlgorithm ea = null;
            ea = new AES128Encryption(new SHA256Hash());
            
            encrypted = ea.encrypt(array, key);
        } catch (PWMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        writetoFile(encrypted);
        
    }
    
    public static byte[] writetoArray(Mother mom){
        byte[] toReturn = null;
        
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream o = new ObjectOutputStream(baos)){
          o.writeObject(mom);
          o.flush();
          
          toReturn = baos.toByteArray();
        }
        catch ( IOException e ) { System.err.println( e ); }
        
        return toReturn;
    }
    
    public static void writetoFile(byte[] toWrite){
        TestDatasource tds = TestDatasource.get();
        
        try(FileOutputStream fos = new FileOutputStream(tds.getFilename())){
            
            fos.write(toWrite);
            
        }catch ( IOException e ) { System.err.println( e ); }
    }
    
    
}
