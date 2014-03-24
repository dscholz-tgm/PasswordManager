package test.independent.serialization;

import test.independent.serialization.testobjects.Mother;

public class TestDatasource {

    private final String filename = "test.ser";
    
    private static TestDatasource tdsource;
    
    public static TestDatasource get(){
        if(tdsource == null){
            tdsource = new TestDatasource();
        }
        
        return tdsource;
    }

    public String getFilename() {
        return filename;
    }
    
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
