package test.independent.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import test.independent.serialization.testobjects.*;

public class TestReadSerialization {
    
    public static void main(String[] args){
        
        TestDatasource tds = TestDatasource.get();
        
        InputStream fis = null;
        Mother mom = null;
        
        try
        {
          fis = new FileInputStream(tds.getFilename());
          
          ObjectInputStream o = new ObjectInputStream( fis );
          mom = (Mother) o.readObject();
          
        }
        catch ( IOException e ) { System.err.println( e ); }
        catch ( ClassNotFoundException e ) { System.err.println( e ); }
        finally { try { fis.close(); } catch ( Exception e ) { } }
        
        System.out.println(mom);
        
    }
    
}
