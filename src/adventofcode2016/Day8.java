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
public class Day8 {
    static String[] screen = new String[6];
    
    public static void main(String[] args) throws FileNotFoundException {
        // domain: 0 <= x <= 50
        // range: 0 <= y <= 6
        // origin: 0, 0 -> top left
        
        // initialize all elements
        for (int i = 0; i < 6; i++) {
            screen[i] = "";
            for (int j = 0; j < 50; j++)
                screen[i] += ".";
        }
        // read instructions
        Scanner lineScanner = new Scanner(new File("day8.txt"));
        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();
            Scanner input = new Scanner(line);
            Pattern p = Pattern.compile("[A-Za-z =]*(\\d+)[A-Za-z ]+(\\d+)[A-Za-z ]*");
            Matcher m = p.matcher(line);
            // debugging tool
            if (!m.matches()) {
                System.out.println("no matches, line: " + line);
                continue; // upojflbcez
            }
            String command = input.next();
            int a = Integer.parseInt(m.group(1));
            int b = Integer.parseInt(m.group(2));

            if (command.equals("rect")) {
                rect(a, b);
            } else {
                command = input.next();
                if (command.equals("row")) {
                    rotateRow(a, b);
                } else {
                    rotateColumn(a, b);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 50; j++)
                if (screen[i].charAt(j) == '#')
                    count++;
        printScreen();
        System.out.println(count);
    }
    
    public static void rect(int a, int b) {
        // turn on rectangle a wide and b tall
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                screen[i] = screen[i].substring(0, j) + "#" + screen[i].substring(j + 1);
            }
        }
    }
    
    public static void rotateRow(int a, int b) {
        // shift all pixels in row a by b
        screen[a] = screen[a].substring(screen[a].length() - b) + screen[a].substring(0, screen[a].length() - b);
    }
    
    public static void rotateColumn(int a, int b) {
        // rotates column a by b
        // 1. get the column, removing from screen
        // 2. rotate the column
        // 3. put the column back one char at a time at the column no index
        String column = "";
        for (int i = 0; i < 6; i++) {
            column += screen[i].charAt(a);
//            screen[i] = screen[i].substring(0, a) + screen[i].substring(a + 1);
        }
        // 2
        column = column.substring(column.length() - b) + column.substring(0, column.length() - b);
        for (int i = 0; i < 6; i++) {
            screen[i] = screen[i].substring(0, a) + column.charAt(i) + screen[i].substring(a + 1);
        }
    }
    
    public static void printScreen() {
        for (String row : screen)
            System.out.println(row);
    }
}
