package test.independent.serialization;

import java.io.*;
import test.independent.serialization.testobjects.*;

/**
 * Tests writing objects directly to a file
 * 
 * @author Adrian Bergler
 * @version 0.1
 */
public class TestWriteSerialization {
    
    public static void main(String[] args){
        TestDatasource tds = TestDatasource.get();
        
        System.out.println("Initializing the mother...");
        
        Mother mom = tds.makeMother();
        
        System.out.println(mom.toString());
        
        System.out.println("Writing to file");
        
        write(mom);
    }
    
    public static void write(Mother mom){
        TestDatasource tds = TestDatasource.get();
        
        OutputStream fos = null;
        try
        {
          fos = new FileOutputStream(tds.getFilename());
          ObjectOutputStream o = new ObjectOutputStream( fos );
          o.writeObject(mom);
          o.flush();
          o.close();
        }
        catch ( IOException e ) { System.err.println( e ); }
        finally { try { fos.close(); } catch ( Exception e ) { e.printStackTrace(); } }
    }
    
}
