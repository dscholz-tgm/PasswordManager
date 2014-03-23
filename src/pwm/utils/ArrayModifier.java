package pwm.utils;

import java.util.Arrays;

/**
 * Util class for all sorts of array modification
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class ArrayModifier {
    
    public static byte[] combine(byte[] firstArray, byte[] secondArray) {
        byte[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    public static <T> T[] combine(T[] firstArray, T[] secondArray) {
        T[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }
    
}
