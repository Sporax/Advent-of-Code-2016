package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day7.txt"));
        int count = 0;
        while (input.hasNext()) {
            String line = input.next();
            String[] parts = line.split("\\[|\\]");
/*            boolean first = false;
            boolean second = false;
            // part 1
            for (int i = 0; i < parts.length; i++) {
                Pattern p = Pattern.compile("\\w*([a-z])([a-z])\\2\\1\\w*");
                Matcher m = p.matcher(parts[i]);
                if (m.matches() && !m.group(1).equals(m.group(2))) {
                    int start = line.indexOf(parts[i]);
                    int end = start + parts[i].length();
                    if ((start == 0) || (end >= line.length()) || ((line.charAt(start - 1) != '[') && (line.charAt(end) != ']'))) {
                        first = true;
                    } else {
                        second = true;
                    }
                }
            }
            if (first && !second)
                count++;
*/          // (([a-z])([a-z])\1.*\2\1\2)|(([a-z])([a-z])\w*\].*\6\5\6)


            // part 2
            Pattern p1 = Pattern.compile(".*([a-z])([a-z])\\1\\w*(\\[\\w+\\]\\w*)*\\[\\w*\\2\\1\\2\\w*\\].*");
            Matcher m1 = p1.matcher(line);
            Pattern p2 = Pattern.compile(".*\\[\\w*([a-z])([a-z])\\1\\w*\\]\\w*(\\[\\w+\\]\\w*)*\\2\\1\\2.*");
            Matcher m2 = p2.matcher(line);
            if ((m1.matches() && !m1.group(1).equals(m1.group(2))) || (m2.matches() && !m2.group(1).equals(m2.group(2))))
                count++;
        }
        System.out.println(count);
    }
    
    private static List<String> customSplit(String input) {
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == '[' || input.charAt(i) == ']') {
                result.add(input.substring(start, i));
            }
        }
        return result;
    }
}
