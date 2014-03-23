package pwm.utils;

import java.nio.charset.Charset;

/**
 * Shortcut for the charset used for encoding.
 * Use this method instead of .getBytes(String)!
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class PWMCharset {
    
    private static final Charset pwmCharset = Charset.forName("UTF-8");
    
    public static Charset get() {
        return pwmCharset;
    }
    
}
