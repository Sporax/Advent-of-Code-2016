package adventofcode2016;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sporax
 */
public class Day5 {
    protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // Using predefined methods for MD5
        int password = 0;
        String key = "ugkcyxxp";
        String s = key + password;
        String result = "--------";
        while(result.contains("-")) {
            byte[] bytesOfMessage = s.getBytes("UTF-8");
            //convert to MD5 message
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            //convert back to string
            String hexOutput = bytesToHex(thedigest);
            
            //check if there are 5 zeroes at the beginning
            String compareZeros = hexOutput + "";
            compareZeros = compareZeros.substring(0,5); //part 2
            if(compareZeros.equals("00000")) {
                System.out.println(hexOutput);
                int index = Integer.decode("0x" + hexOutput.charAt(5));
                char c = hexOutput.charAt(6);
                if (index < result.length() && result.charAt(index) == '-')
                    result = result.substring(0, index) + c + result.substring(index + 1);
                System.out.println(c + " " + index);
                System.out.println(result);
//                result += hexOutput.charAt(5);
            }
            //if not, increment loop
//            System.out.println(password+" "+hexOutput); -- slows down operations
            password++;
            s = key + password;
        }
        System.out.println(result);
    }

    //convert bytes to hex
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
