package test.independent.serialization.testobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mother implements Serializable{
    
    private static final long serialVersionUID = 1533732485592758945L;
    
    private List<Daughter> daughters;
    private List<Son> sons;
    private String name;
    
    
    public Mother(String name){
       daughters = new ArrayList<Daughter>();
       sons = new ArrayList<Son>();
       
       this.name = name;
    }
    
    @Override
    public String toString(){
        String toReturn = "";
        
        toReturn += name + "(";
        
        toReturn += "Daughters: ";
        for(int i = 0; i < daughters.size(); i++){
            toReturn += daughters.get(i).toString();
            if(i+1 < daughters.size()) toReturn += ", ";
        }
        
        for(int i = 0; i < sons.size(); i++){
            toReturn += sons.get(i).toString();
            if(i+1 < sons.size()) toReturn += ", ";
        }
        
        return toReturn;
    }
    
    public void makeDaughter(String name){
        daughters.add(new Daughter(name));
    }

    public void makeSon(String name){
        sons.add(new Son(name));
    }
    
    public List<Daughter> getDaughters() {
        return daughters;
    }

    public void setDaughters(List<Daughter> daughters) {
        this.daughters = daughters;
    }

    public List<Son> getSons() {
        return sons;
    }

    public void setSons(List<Son> sons) {
        this.sons = sons;
    }
    
    
    
}
