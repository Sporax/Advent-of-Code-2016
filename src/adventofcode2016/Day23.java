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
public class Day23 {
    static boolean toggle = false;
    
    public static void main(String[] args) throws IOException {
        String[] instructions = (new String(Files.readAllBytes(Paths.get("day23.txt")))).split("\n");
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 12);
        registers.put("b", 0);
        registers.put("c", 0); // part 2
        registers.put("d", 0);
        
        for (int i = 0; i < instructions.length; i++) {
            Scanner lineScanner = new Scanner(instructions[i]);
            if (i == 4) {
                registers.put("a", registers.get("b") * registers.get("d"));
                registers.put("b", 0);
                registers.put("d", 0);
                i = 9;
                continue;
            }
            if (lineScanner.hasNext()) {
                String first, second;
                int s;
                
                switch (lineScanner.next()) {
                    case "cpy":
                        first = lineScanner.next();
                        second = lineScanner.next();
                        if (!Pattern.matches("-?\\d+", second)) {
                            if (Pattern.matches("-?\\d+", first))
                                registers.put(second, Integer.parseInt(first));
                            else
                                registers.put(second, registers.get(first));
                        }
                        break;
                    case "inc":
                        first = lineScanner.next();
                        if (!Pattern.matches("-?\\d+", first)) {
                            registers.put(first, registers.get(first) + 1);
                        }
                        break;
                    case "dec":
                        first = lineScanner.next();
                        if (!Pattern.matches("-?\\d+", first)) {
                            registers.put(first, registers.get(first) - 1);
                        }
                        break;
                    case "jnz":
                        first = lineScanner.next();
                        second = lineScanner.next();
                        s = Pattern.matches("-?\\d+", second) ? Integer.parseInt(second) : registers.get(second);
                        if (Pattern.matches("-?\\d+", first) && !first.equals("0") ||
                                registers.get(first) != 0) {
                            i += s - 1;
                        }
                        break;
                    case "tgl":
                        second = lineScanner.next();
                        s = Pattern.matches("-?\\d+", second) ? Integer.parseInt(second) : registers.get(second);
                        // toggle the line 'first' away from i
                        if (i + s < instructions.length) {
                            String nextInstr = instructions[i + s];
                            if (nextInstr.startsWith("inc")) {
                                instructions[i + s] = "dec" + nextInstr.substring(3);
                            } else if (Pattern.matches("\\w{3} [A-Za-z0-9]+", nextInstr)) {
                                instructions[i + s] = "inc" + nextInstr.substring(3);
                            } else if (nextInstr.startsWith("jnz")) {
                                instructions[i + s] = "cpy" + nextInstr.substring(3);
                            } else if (Pattern.matches("\\w{3} [A-Za-z0-9]+ [A-Za-z0-9]+", nextInstr)) {
                                instructions[i + s] = "jnz" + nextInstr.substring(3);
                            }
                        }
                }
            }
        }
        System.out.println(registers);
    }
}
