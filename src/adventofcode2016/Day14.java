package adventofcode2016;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day14 {
    protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // Use predefined methods for MD5
        int password = 0;
        String key = "jlmsuwbz";
        String s = key + password;
        List<String> hashes = new ArrayList<>();
        
        while (password < 25000) {
            byte[] bytesOfMessage = s.getBytes("UTF-8");
            //convert to MD5 message
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            //convert back to string
            String hexOutput = bytesToHex(thedigest);
            // find 2016 hashes of this hash
            for (int i = 0; i < 2016; i++) {
                bytesOfMessage = hexOutput.getBytes("UTF-8");
                thedigest = md.digest(bytesOfMessage);
                hexOutput = bytesToHex(thedigest);
            }
            // add all hashes at index to the list
            hashes.add(hexOutput);
            //if not, increment loop
            password++;
            s = key + password;
        }
        System.out.println(hashes);
        // for each hash, if it matches a threepeat, check the next thousand hashes for a five repeat of
        // the same digit
        int index = 0;
        List<String> results = new ArrayList<>();
        Pattern p = Pattern.compile("[0-9a-z]*?(([0-9a-z])\\2{2})[0-9a-z]*");
        Matcher m;
        while (results.size() < 64 && index < 490000) {
            // search this hash for a match
            m = p.matcher(hashes.get(index));
            if (m.matches()) {
                // get the char that matches and search for a 5 group of it in the next thousand indexes
                String match = m.group(2);
                for (int i = index + 1; i <= index + 1000; i++) {
                    if (Pattern.matches("[0-9a-z]*" + match + "{5}[0-9a-z]*", hashes.get(i))) {
                        results.add(index + "");
                        System.out.println("matched: " + match + match + match + " " + hashes.get(index) + "\n\twith: " + hashes.get(i));
                        System.out.println("index: " + index + ", i: " + i);
                        break;
                    }
                }
            }
            index++;
        }
        System.out.println(results.size());
        System.out.println(results);
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
