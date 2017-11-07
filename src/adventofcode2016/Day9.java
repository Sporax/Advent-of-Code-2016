package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        // decompress the outermost markers
        
        // 1. find markers
        // 2. divide the sentence into strings of markers
        // 3. decode the first marker
        // 4. put it all back together
        // 5. get and print the length
        Scanner input = new Scanner(new File("day9.txt"));
        Pattern p = Pattern.compile("(\\w*)\\((\\d+)x(\\d+)\\)(.*)");
        int length = 0;
        String line = "";
        while (input.hasNextLine()) {
            line += input.nextLine();
        }
/*        // Part 1:
        StringBuilder result = new StringBuilder();
        while (line.length() > 0) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                result.append(m.group(1));
                int index = Integer.parseInt(m.group(2));
                int repetitions = Integer.parseInt(m.group(3));
                String rest = m.group(4);
                for (int i = 0; i < repetitions; i++)
                    result.append(rest.substring(0, index));
                line = rest.substring(index);
            } else {
                System.out.println("no match");
                result.append(line);
                line = "";
            }
        }
        System.out.println(result);
        System.out.println(result.length());
*/
        // Part 2
        // 1. find pattern
        // 2. add len(group(1)) to length
        // 3. add reptitions * len(group(2)) to length
        // 4. repeat until input is nothing
        System.out.println(computeLength(line));
    }
    
    private static long computeLength(String s) {
        if (s.length() == 0)
            return 0;
        Pattern p = Pattern.compile("([A-Za-z]*)\\((\\d+)x(\\d+)\\)(.*)");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            if (m.group(4).equals(""))
                return 0;
            int index = Integer.parseInt(m.group(2));
            String sub = m.group(4).substring(0, index);
            String rest = m.group(4).substring(index);
            long multiplier = Integer.parseInt(m.group(3));
            if (multiplier < 0) {
                System.out.println("index: " + index);
                System.out.println("first: " + sub);
                System.out.println("rest: " + rest);
                System.out.println("*: " + multiplier + ", len: " + Integer.parseInt(m.group(2)));
                System.out.println(s);
            }
            return m.group(1).length() + multiplier * computeLength(sub) + computeLength(rest);
        }
        return s.length();
    }
}
