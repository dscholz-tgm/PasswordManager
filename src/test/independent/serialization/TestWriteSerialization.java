package test.independent.serialization;

import java.io.*;
import test.independent.serialization.testobjects.*;

public class TestWriteSerialization {
    
    public static void main(String[] args){
        
        TestDatasource tds = TestDatasource.get();
        
        System.out.println("Initializing the mother...");
        
        Mother mom = new Mother("Mom1");
        
        mom.makeDaughter("Daughter1");
            mom.getDaughters().get(0).makeDaughter("Daughter1.1");
                mom.getDaughters().get(0).getDaughters().get(0).makeDaughter("Daughter1.1.1");
            mom.getDaughters().get(0).makeDaughter("Daughter1.2");
            mom.getDaughters().get(0).makeDaughter("Daughter1.3");
        
        mom.makeDaughter("Daughter2");
            mom.getDaughters().get(1).makeDaughter("Daughter2.1");
            mom.getDaughters().get(1).makeDaughter("Daughter2.2");
        
        
        mom.makeSon("Son1");
        mom.makeSon("Son2");
        mom.makeSon("Son3");
        
        System.out.println(mom.toString());
        
        OutputStream fos = null;
        
        System.out.println("Writing to file");
        
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
