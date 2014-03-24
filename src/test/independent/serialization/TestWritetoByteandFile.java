package test.independent.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import test.independent.serialization.testobjects.Mother;

public class TestWritetoByteandFile {
    
    public static void main(String[] args){
        TestDatasource tds = TestDatasource.get();
        
        Mother mom = tds.makeMother();
        
        byte[] array = writetoArray(mom);
        
        writetoFile(array);
        
    }
    
    public static byte[] writetoArray(Mother mom){
        byte[] toReturn = null;
        
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
          ObjectOutputStream o = new ObjectOutputStream(baos);
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
