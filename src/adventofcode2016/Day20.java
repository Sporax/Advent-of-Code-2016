package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day20 {
    private static final long MAX = 20000L;
    private static HashSet<String> set;
    
    public static void main(String[] args) throws FileNotFoundException {
        set = new HashSet<>();
        Scanner input = new Scanner(new File("day20.txt"));
        while (input.hasNextLine()) {
            set.add(input.nextLine());
        }
        System.out.println(set);
        long min = findMin(0);
        int count = 0;
        System.out.println("c: " + count + ", min: " + min);
        while (min != -1) {
            count++;
            min = findMin(min + 1);
            System.out.println("c: " + count + ", min: " + min);
        }
        System.out.println(count);
    }
    
    private static long findMin(long n) {
        if (n >= 4294967295L)
            return -1;
        // search for a string starting with n
        Pattern p = Pattern.compile("(\\d+)-(\\d+)");
        for (String line : set) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                long i = Long.parseLong(m.group(1));
                long j = Long.parseLong(m.group(2));
                if (n >= i && n <= j) {  // n is in the range
                    // get the next value and try again
                    return findMin(j + 1);
                }
            }
        }
        return n;
    }
    
    private static long findMax() {
        long max = 0;
        Pattern p = Pattern.compile("(\\d+)-(\\d+)");
        Matcher m;
        for (String line : set) {
            m = p.matcher(line);
            if (m.matches()) {
                long j = Long.parseLong(m.group(2));
                if (j > max)
                    max = j;
            }
        }
        return max;
    }
    
    private static int countIPS(long n) {
        // search for a string starting with n
        Pattern p = Pattern.compile("(\\d+)-(\\d+)");
        int count = 0;
        for (String line : set) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                long i = Long.parseLong(m.group(1));
                long j = Long.parseLong(m.group(2));
                if (n >= i && n <= j) {  // n is in the range
                    // get the next value and try again
                    return countIPS(j + 1);
                } else if (n > j) {
                    count++;
                }
            }
        }
        if (count == set.size() - 1)
            return 0;
        // once this is found, increment count and find the rest
        return 1 + countIPS(n + 1);
    }
}
