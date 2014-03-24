package test.independent.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import test.independent.serialization.testobjects.*;

public class TestReadSerialization {
    
    public static void main(String[] args){
        
        Mother mom = read();
        
        System.out.println(mom);
    }
    
    public static Mother read(){
        TestDatasource tds = TestDatasource.get();
        
        Mother mom = null;
        
        try(FileInputStream fis = new FileInputStream(tds.getFilename()))
        {
          
          ObjectInputStream o = new ObjectInputStream( fis );
          mom = (Mother) o.readObject();
          
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return mom;
    }
    
}
