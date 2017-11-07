package adventofcode2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day12 {
    public static void main(String[] args) throws IOException {
        test();
/*        String[] instructions = (new String(Files.readAllBytes(Paths.get("day12.txt")))).split("\n");
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1); // part 2
        registers.put("d", 0);
        
        for (int i = 0; i < instructions.length; i++) {
            Scanner lineScanner = new Scanner(instructions[i]);
            if (lineScanner.hasNext()) {
                String first, second;
                switch (lineScanner.next()) {
                    case "cpy":
                        first = lineScanner.next();
                        second = lineScanner.next();
                        if (Pattern.matches("-?\\d+", first))
                            registers.put(second, Integer.parseInt(first));
                        else
                            registers.put(second, registers.get(first));
                        break;
                    case "inc":
                        first = lineScanner.next();
                        registers.put(first, registers.get(first) + 1);
                        break;
                    case "dec":
                        first = lineScanner.next();
                        registers.put(first, registers.get(first) - 1);
                        break;
                    case "jnz":
                        first = lineScanner.next();
                        int s = lineScanner.nextInt();
                        if (Pattern.matches("-?\\d+", first) && !first.equals("0") ||
                                registers.get(first) != 0) {
                            i += s - 1;
                        }
                        break;
                }
            }
        }
        System.out.println(registers);
  */  }
    
    static int a;
    static int b;
    static int c;
    static int d;
    
    private static void test() {
        a = b = d = 0;
        c = 1;
        m1();
        System.out.println(a + ", " + b + ", " + c + ", " + d);
    }
    
    private static void m1() { // hard code solution
        a = 1;
        b = 1;
        d = 26;
        if (c != 0) {
            c = 7;
            do {
                d++;
                c--;
            } while (c != 0);
        }
        do {
            c = a;
            do {
                a++;
                b--;
            } while (b != 0);
            b = c;
            d--;
        } while (d != 0);
        c = 14;
        do {
            d = 14;
            do {
                a++;
                d--;
            } while (d != 0);
            c--;
        } while (c != 0);
    }
}
