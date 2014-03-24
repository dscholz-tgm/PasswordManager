package test.independent.serialization.testobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Daughter implements Serializable{

    private static final long serialVersionUID = 8800480933605350153L;
    
    private String name;
    private List<Daughter> daughters;
    
    public Daughter(String name){
        this.name = name;
        
        daughters = new ArrayList<Daughter>();
    }
    
    @Override
    public String toString(){
        String toReturn = "";
        
        toReturn += name + "(";
        
        for(int i = 0; i < daughters.size(); i++){
            toReturn += daughters.get(i).toString();
            if(i+1 < daughters.size()) toReturn += ", ";
        }
        
        toReturn += ")";
        
        return toReturn;
    }
    
    public void makeDaughter(String name){
        daughters.add(new Daughter(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Daughter> getDaughters() {
        return daughters;
    }

    public void setDaughters(List<Daughter> daughters) {
        this.daughters = daughters;
    }
    
    
    
}
