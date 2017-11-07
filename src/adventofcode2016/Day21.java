package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day21 {
    static String result = "fbgdceah";
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // read an instruction and do it
        Scanner input = new Scanner(new File("day21.txt"));
        Pattern[] p = {Pattern.compile("swap position (\\d+) with position (\\d+)"), 
                       Pattern.compile("swap letter ([A-Za-z]) with letter ([A-Za-z])"),
                       Pattern.compile("rotate left (\\d+) step[s]?"),
                       Pattern.compile("rotate right (\\d+) step[s]?"),
                       Pattern.compile("rotate based on position of letter ([A-Za-z])"),
                       Pattern.compile("reverse positions (\\d+) through (\\d+)"),
                       Pattern.compile("move position (\\d+) to position (\\d+)")};
        Matcher m;
        int count = 0;
        int matches = 0;
/*        // PART 1:
        while (input.hasNextLine()) {
            count++;
            String line = input.nextLine();
            for (int i = 0; i < p.length; i++) {
                m = p[i].matcher(line);
                if (m.matches()) {
                    matches++;
                    switch (i) {
                        case 0: 
                            swapPosition(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                            break;
                        case 1:
                            swapLetter(m.group(1), m.group(2));
                            break;
                        case 2:
                            rotateLeft(Integer.parseInt(m.group(1)));
                            break;
                        case 3:
                            rotateRight(Integer.parseInt(m.group(1)));
                            break;
                        case 4:
                            rotateFromPosition(m.group(1));
                            break;
                        case 5:
                            reverse(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                            break;
                        case 6:
                            move(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                            break;
                        default:
                            System.out.println(line);
                    }
                    break;
                }
            }
            if (matches < count)
                System.out.println(line);
        }
        System.out.println(result);
        System.out.println(count + " " + matches);*/


        // PART 2:
        String[] instructions = (new String(Files.readAllBytes(Paths.get("day21.txt")))).split("\n");
        for (int b = instructions.length - 1; b >= 0; b--) {
            count++;
            // read instructions in reverse
            String line = instructions[b];
            for (int i = 0; i < p.length; i++) {
                m = p[i].matcher(line);
                if (m.matches()) {
                    matches++;
                    switch (i) {
                        case 0: 
                            swapPosition(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                            break;
                        case 1:
                            swapLetter(m.group(1), m.group(2));
                            break;
                        case 2:
                            rotateRight(Integer.parseInt(m.group(1)));
                            break;
                        case 3:
                            rotateLeft(Integer.parseInt(m.group(1)));
                            break;
                        case 4:
                            reverseRotateFromPosition(m.group(1));
                            break;
                        case 5:
                            reverse(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                            break;
                        case 6:
                            move(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(1)));
                            break;
                        default:
                            System.out.println(line);
                    }
                    break;
                }
            }
            if (matches < count)
                System.out.println(line);

        }
        System.out.println(result);
        System.out.println(count + " " + matches);
    }
    
    private static void swapPosition(int x, int y) {
        // swap index x and index y
        if (x == y)
            ;
        else if (x < y) {
            String first = "" + result.charAt(x);
            String second = "" + result.charAt(y);
            // set index x to second, index y to first
            if (y == result.length() - 1)
                result = result.substring(0, x) + second + result.substring(x + 1, y) + first;
            else
                result = result.substring(0, x) + second + result.substring(x + 1, y) + first + result.substring(y + 1);
        } else {
            // y > x: hack
            swapPosition(y, x);
        }
    }
    
    private static void swapLetter(String x, String y) {
        String output = "";
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == x.charAt(0))
                output += y;
            else if (result.charAt(i) == y.charAt(0))
                output += x;
            else
                output += result.charAt(i);
        }
        result = output;
    }
    
    private static void reverseRotateFromPosition(String s) {
        /* POSITION TO MOVEMENT:
         * index 7: moves 1 right - index 0
         * index 0: moves 1 right - index 1
         * index 4: moves 2 left  - index 2
         * index 1: moves 2 right - index 3
         * index 5: moves 1 left  - index 4
         * index 2: moves 3 right - index 5
         * index 6: moves 0 left  - index 6
         * index 3: moves 4 right - index 7
         */
        int index = result.indexOf(s);
        switch (index) {
            case 0:
                rotateLeft();
                break;
            case 1:
                rotateLeft();
                break;
            case 2:
                rotateRight(2);
                break;
            case 3:
                rotateLeft(2);
                break;
            case 4:
                rotateRight();
                break;
            case 5:
                rotateLeft(3);
                break;
            case 6:
                ;
                // do nothing
                break;
            case 7:
                rotateLeft(4);
                break;
        }
    }
    
    private static void rotateFromPosition(String s) {
        int index = result.indexOf(s);
        rotateRight();
        rotateRight(index);
        if (index >= 4)
            rotateRight();
    }
    
    private static void rotateRight(int offset) {
        for (int i = 0; i < offset; i++)
            rotateRight();
    }
    
    private static void rotateRight() {
        int index = result.length() - 1;
        result = result.substring(index) + result.substring(0, index);
    }
    
    private static void rotateLeft(int offset) {
        for (int i = 0; i < offset; i++)
            rotateLeft();
    }
    
    private static void rotateLeft() {
        result = result.substring(1) + result.charAt(0);
    }
    
    private static void reverse(int x, int y) {
        String first = result.substring(0, x);
        String last = result.substring(y + 1);
        result = first + reverse(result.substring(x, y + 1)) + last;
    }
    
    private static String reverse(String s) {
        if (s.length() == 1)
            return s;
        return reverse(s.substring(1)) + s.charAt(0);
        
    }
    
    private static void move(int x, int y) {
        StringBuilder sb = new StringBuilder(result);
        char c = sb.charAt(x);
        sb.deleteCharAt(x);
        sb.insert(y, c);
        result = sb.toString();
    }
}
