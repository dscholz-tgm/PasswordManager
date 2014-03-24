package test.independent.serialization;

import pwm.PWMException;
import pwm.engine.algorithms.encryptionalgorithms.AES128Encryption;
import pwm.engine.algorithms.encryptionalgorithms.EncryptionAlgorithm;
import pwm.engine.algorithms.hashingalgorithms.SHA256Hash;
import test.independent.serialization.testobjects.Mother;

public class TestDatasource {

    private final String filename = "test.ser";
    
    private EncryptionAlgorithm encryptionalgorithm;
    
    private static TestDatasource tdsource;
    
    public static TestDatasource get(){
        if(tdsource == null){
            tdsource = new TestDatasource();
        }
        
        EncryptionAlgorithm ea = null;
        try {
            ea = new AES128Encryption(new SHA256Hash());
            System.out.println("algorithm selected.");
        } catch (PWMException e) {
            e.printStackTrace();
        }
        
        return tdsource;
    }

    public String getFilename() { return filename; }
    public EncryptionAlgorithm getEncryptionAlgorithm() { return encryptionalgorithm; }

    public Mother makeMother(){
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
        
        return mom;
    }
    
}
