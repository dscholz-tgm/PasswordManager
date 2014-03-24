package test.independent.serialization.testobjects;

import java.io.Serializable;

public class Son implements Serializable{

    private static final long serialVersionUID = -8809753557285309933L;
    
    private String name;
    
    public Son(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString(){
        return name;
    }
    
}
