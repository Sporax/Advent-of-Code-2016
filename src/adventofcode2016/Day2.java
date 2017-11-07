package adventofcode2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Sporax
 */
public class Day2 {
    public static void main(String[] args) throws IOException {
        char[][] keypad = {{'0', '0', '1', '0', '0'},
                           {'0', '2', '3', '4', '0'},
                           {'5', '6', '7', '8', '9'},
                           {'0', 'A', 'B', 'C', '0'},
                           {'0', '0', 'D', '0', '0'}};
        int x = 0;
        int y = 2;
        String[] instructions = (new String(Files.readAllBytes(Paths.get("day2.txt")))).split("\n");
        for (String instruction : instructions) {
            for (int i = 0; i < instruction.length(); i++) {
                if (instruction.charAt(i) == 'U' && y > 0 && keypad[y - 1][x] != '0')
                    y--;
                else if (instruction.charAt(i) == 'D' && y < 4 && keypad[y + 1][x] != '0')
                    y++;
                else if (instruction.charAt(i) == 'R' && x < 4 && keypad[y][x + 1] != '0')
                    x++;
                else if (instruction.charAt(i) == 'L' && x > 0 && keypad[y][x - 1] != '0')
                    x--;
            }
            System.out.println(keypad[y][x]);
        }
    }
}
