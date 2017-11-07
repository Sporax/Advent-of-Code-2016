package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sporax
 */
public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = part1();
        for (int i = 0; i < input.size(); i += 2) {
            String output = "";
            int shift = Integer.parseInt(input.get(i + 1)) % 26;
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                char current = line.charAt(j);
                if (current == '-')
                    current = ' ';
                else if (current >= 97 && current <= 122) {
                    for (int k = 0; k < shift; k++) {
                        // shift each char up by one
                        if (current == 'z')
                            current = 'a';
                        else
                            current++;
                    }
                }
                output += current;
            }
            System.out.println(output);
        }
    }
    
    public static String getMaxFive(int[] array) {
        String result = "[";
        while (result.length() < 6) {
            int max = 0;
            int index = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                    index = i;
                }
            }
            array[index] = 0;
            result += (char) (97 + index);
        }
        result += "]";
        return result;
    }
    
    public static List<String> part1() throws FileNotFoundException {
        Scanner input = new Scanner(new File("day4.txt"));
        int total = 0;
        List<String> result = new ArrayList<>();
        while (input.hasNext()) {
            String line = input.next();
            String checksum = line.substring(line.indexOf("["));
            line = line.substring(0, line.indexOf("["));
            int[] count = new int[26];
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= 97 && line.charAt(i) <= 122) {
                    count[line.charAt(i) - 97]++;
                }
            }
            String temp = getMaxFive(count);
            if (checksum.equals(temp)) {
                int amount = Integer.parseInt(line.split("\\D+")[1]);
                total += amount;
                result.add(line);
                result.add(amount + "");
            }
        }
        System.out.println(total);
        return result;
    }

}
