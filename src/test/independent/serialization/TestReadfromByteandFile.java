package test.independent.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import test.independent.serialization.testobjects.Mother;

public class TestReadfromByteandFile {
    
    public static void main(String[] args){
        Mother mom = null;
        
        byte[] array = readfromFile();
        
        mom = readfromArray(array);
        
        System.out.println(mom);
    }
    
    public static byte[] readfromFile(){
        byte[] toReturn = null;
        TestDatasource tds = TestDatasource.get();
        
        
        
        try(FileInputStream fis = new FileInputStream(tds.getFilename())){
            
            ByteArrayOutputStream bais = new ByteArrayOutputStream();
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
        TestDatasource tds = TestDatasource.get();
        
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
