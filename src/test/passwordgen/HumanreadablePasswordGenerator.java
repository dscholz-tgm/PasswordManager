package test.passwordgen;

import java.util.Random;

/**
 * Generates a Password
 * 
 * @author Dominik
 * @version 0.1
 */
public class HumanreadablePasswordGenerator {
   
    private final static String salt = "TryToHeckUs";
    private String[] criterias = new String[]{
        "BCDFGHJKLMNPQRSTVWXYZ",
        "AEIOU",
        "0123456789",
    };
    
    public static void main(String[] args) {
        int length = 10;
        int genpws = 20;
        for(int i = 0; i < genpws; i++) System.out.println(new HumanreadablePasswordGenerator().generatePW(length,salt.hashCode()));
    }
    
    
    private String generatePW(int length, long salt) {
        String password = "";
        Random rand = new Random(System.nanoTime() + salt);
        int select = 0;
        char add = ' ';
        for(int i = 0; i < length; i++) {
            rand = new Random(System.nanoTime() + rand.nextInt() + salt);
            if(select == 0) {
                add = criterias[select].charAt(rand.nextInt(criterias[select].length()));
                select = rand.nextInt(2) + 1;
            }
            else if(select == 1) {
                add = criterias[select].charAt(rand.nextInt(criterias[select].length()));
                select = rand.nextInt(3);
            }
            else if(select == 2) {
                add = criterias[select].charAt(rand.nextInt(criterias[select].length()));
                select = rand.nextInt(2);
            }
            password += rand.nextBoolean() ? add : (add + "").toLowerCase();
            if(select != 1) select = rand.nextBoolean() ? 1 : select;
            else if(select == 2) select = rand.nextInt(3);
        }
        return password;
    }
}
