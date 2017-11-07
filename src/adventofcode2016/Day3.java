package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Sporax
 */
public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day3.txt"));
        int count = 0;
        while (input.hasNextLine()) {
            int i1 = input.nextInt();
            int j1 = input.nextInt();
            int k1 = input.nextInt();
            int i2 = input.nextInt();
            int j2 = input.nextInt();
            int k2 = input.nextInt();
            int i3 = input.nextInt();
            int j3 = input.nextInt();
            int k3 = input.nextInt();
            if ((i1 + i2 > i3) && (i2 + i3 > i1) && (i1 + i3 > i2))
                count++;
            if ((j1 + j2 > j3) && (j2 + j3 > j1) && (j1 + j3 > j2))
                count++;
            if ((k1 + k2 > k3) && (k2 + k3 > k1) && (k1 + k3 > k2))
                count++;

        }
        System.out.println(count);
    }
}
