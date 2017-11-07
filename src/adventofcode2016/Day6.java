package adventofcode2016;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sporax
 */
public class Day6 {
    public static void main(String[] args) throws IOException  {
        int[] letters;
        List<String> input = new ArrayList<>(Arrays.asList(new String(Files.readAllBytes(Paths.get("day6.txt"))).split("\n")));
        input = rotateListClockwise(input);
        String result = "";
        for (int l = 0; l < input.size(); l++) {
            letters = new int[26];
            String line = input.get(l);
            // add frequency of all chars
            for (int i = 0; i < line.length(); i++) {
                letters[line.charAt(i) - 97]++;
            }
            // find max
            int max = letters[0];
            int index = 0;
            for (int i = 1; i < 26; i++) {
                if (letters[i] > max) { // Day 2: change > to <
                    max = letters[i];
                    index = i;
                }
            }
            result += (char) (index + 97);
        }
        System.out.println(result);
    }
    
    public static List<String> rotateListClockwise(List<String> list) {
        ArrayList<String> result = new ArrayList<>();
        // keep a count of the index
        int index = 0;
        while (list.get(0).length() > 0) {
            // remove the first element from each element in the list, append it to a result string
            // add that string to the result
            String append = "";
            for (int e = 0; e < list.size(); e++) {
                append += list.get(e).charAt(0);
                list.set(e, list.get(e).substring(1));
            }
            result.add(append);
        }
        return result;
    }
}
