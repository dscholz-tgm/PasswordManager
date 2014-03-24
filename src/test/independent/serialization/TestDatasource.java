package test.independent.serialization;

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
    
}
