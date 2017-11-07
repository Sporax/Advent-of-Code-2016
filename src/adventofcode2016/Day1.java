package adventofcode2016;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sporax
 */
public class Day1 {        
    public char[] directions = {'n', 'e', 's', 'w'};

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int x = 0;
        int y = 0;
        int oldX = 0;
        int oldY = 0;
        String directions = "nesw";
        char direction = 'n';
        String commands = new String(Files.readAllBytes(Paths.get("day1.txt")));
        Set<String> set = new HashSet<>();
        for (String code : commands.split(",")) {
            code = code.trim();
            if (code.charAt(0) == 'R') {
                // turn right
                if (direction == 'w')
                    direction = 'n';
                else
                    direction = directions.charAt(directions.indexOf(direction) + 1);
            } else {
                // turn left
                if (direction == 'n')
                    direction = 'w';
                else
                    direction = directions.charAt(directions.indexOf(direction) - 1);
            }
            // go forward x units in direction
            int forward = Integer.parseInt(code.substring(1));
            switch (direction) {
                case 'n': y -= forward;
                    break;
                case 'e': x += forward;
                    break;
                case 's': y += forward;
                    break;
                case 'w': x -= forward;
            }
            // part 2:
            if (oldX != x) {
                if (x < oldX) {
                    while (oldX > x) {
                        String location = oldX + " " + y;
                        if (set.contains(location)) {
                            System.out.println(Math.abs(oldX) + Math.abs(y));
                            return;
                        }
                        set.add(location);
                        oldX--;
                    }
                } else {
                    while (oldX < x) {
                        String location = oldX + " " + y;
                        if (set.contains(location)) {
                            System.out.println(Math.abs(oldX) + Math.abs(y));
                            return;
                        }
                        set.add(location);
                        oldX++;
                    }
                }
            } else {
                if (y < oldY) {
                    while (oldY > y) {
                        String location = x + " " + oldY;
                        if (set.contains(location)) {
                            System.out.println(Math.abs(oldY) + Math.abs(x));
                            return;
                        }
                        set.add(location);
                        oldY--;
                    }
                } else {
                    while (oldY < y) {
                        String location = x + " " + oldY;
                        if (set.contains(location)) {
                            System.out.println(Math.abs(oldY) + Math.abs(x));
                            return;
                        }
                        set.add(location);
                        oldY++;
                    }
                }
            }
        }
        System.out.println("x: " + x + ", y: " + y);
        System.out.println("distance: " + (Math.abs(x) + Math.abs(y)));
        
    }
}
