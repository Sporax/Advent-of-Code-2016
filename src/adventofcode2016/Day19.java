package adventofcode2016;

import java.util.Arrays;

/**
 *
 * @author Sporax
 */
public class Day19 {
    private static final int NUM = 3014603;
    
    public static void main(String[] args) {
        // make an array of int - until condition, keep passing presents
        int[] elves = new int[NUM];
        for (int i = 0; i < elves.length; i++)
            elves[i] = 1;  // each starts with one
        boolean condition = false;
        int i = 0;
        while (!condition) {
            // pass the gift around
            if (elves[i] > 0) {
                // take the next one's present
                int next = i == elves.length - 1 ? 0 : i + 1;
                while (elves[next] < 1) {
                    next = next == elves.length - 1 ? 0 : next + 1;
                }
                elves[i] += elves[next];
                elves[next] = 0;
            }
            if (i == elves.length - 1) {  // after one pass, check if done
                int count = 0;
                for (int j = 0; j < elves.length; j++)
                    if (elves[j] > 0)
                        count++;
                if (count == 1)
                    condition = true;
            }
            i = i == elves.length - 1 ? 0 : i + 1;
        }
        for (i = 0; i < elves.length; i++)
            if (elves[i] > 0)
                break;
        System.out.println("elf: " + (i + 1));
        System.out.println(Arrays.toString(elves));
    }
}
