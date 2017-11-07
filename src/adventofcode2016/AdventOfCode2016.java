package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64.Decoder;
import java.util.Scanner;

/**
 *
 * @author Sporax
 */
public class AdventOfCode2016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // calls advent of code days
        Scanner input = new Scanner(new File("numbers.txt"));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner data = new Scanner(line.replace(" ", ""));
            while (data.hasNextInt()) {
                int next = data.nextInt();
                System.out.println(next);
            }
        }
    }
}
