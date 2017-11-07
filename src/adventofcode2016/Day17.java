package adventofcode2016;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sporax
 */
public class Day17 {
    protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static final String HASH = "lpvhkcbi";
    public static String result = "";
    public static int longest = -1;
    
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // start at 0,0; get to ...
        traverse("", 0, 0);
        System.out.println(result);
        System.out.println(longest);
    }
    
    private static void traverse(String path, int x, int y) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // base case: x = 3, y = 3
        if (x == 3 && y == 3) {
            if (result.equals("") || result.length() > path.length())
                result = path;
            if (longest == -1 || path.length() > longest)
                longest = path.length();
        } else if (x <= 3 && y <= 3) {
            // get a hash, choose possible directions
            String temp = generateHash(HASH + path).substring(0, 4);
            // if can go left, go left
            if (y > 0 && (temp.charAt(0) > 97 && temp.charAt(0) < 103)) {
                traverse(path + "U", x, y - 1);
            }
            if (y < 4 && (temp.charAt(1) > 97 && temp.charAt(1) < 103)) {
                traverse(path + "D", x, y + 1);
            }
            if (x > 0 && (temp.charAt(2) > 97 && temp.charAt(2) < 103)) {
                traverse(path + "L", x - 1, y);
            }
            if (x < 4 && (temp.charAt(3) > 97 && temp.charAt(3) < 103)) {
                traverse(path + "R", x + 1, y);
            }
        }
    }
    
    private static String generateHash(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = s.getBytes("UTF-8");
        //convert to MD5 message
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        //convert back to string
        String hexOutput = bytesToHex(thedigest);
        return hexOutput;
    }
    
    //convert bytes to hex
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return (new String(hexChars)).toLowerCase();
    }
}
