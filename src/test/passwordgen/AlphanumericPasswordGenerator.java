package test.passwordgen;

import java.util.Random;

/**
 * Generates a Password
 * 
 * @author Dominik
 * @version 0.1
 */
public class AlphanumericPasswordGenerator {
   
    private final static String salt = "TryToHeckUs";
    private String[] criterias = new String[]{
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 
        "abcdefghijklmnopqrstuvwxyz", 
        "0123456789",
    };
    
    public static void main(String[] args) {
        int length = 16;
        int genpws = 20;
        for(int i = 0; i < genpws; i++) System.out.println(new AlphanumericPasswordGenerator().generatePW(length,salt.hashCode()));
    }
    
    
    private String generatePW(int length, long salt) {
        String password = "";
        Random rand = new Random(System.nanoTime() + salt);
        String select;
        for(int i = 0; i < length; i++) {
            rand = new Random(System.nanoTime() + rand.nextInt() + salt);
            select = criterias[rand.nextInt(criterias.length)];
            password += select.charAt(rand.nextInt(select.length()));
        }
        return password;
    }
}
