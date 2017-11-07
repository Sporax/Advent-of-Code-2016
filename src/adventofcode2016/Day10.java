package adventofcode2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day10 {
    
    public static void main(String[] args) throws IOException {
        // read instructions as they can be decoded
        Bot[] bots = new Bot[300];
        int[] output = new int[300];
        for (int i = 0; i < 300; i++) {
             bots[i] = new Bot();
             output[i] = -1;
        }
        // do each instruction in sequence if it can be done
        String[] instructions = (new String(Files.readAllBytes(Paths.get("day10.txt")))).split("\n");
        boolean[] done = new boolean[instructions.length];
        for (int i = 0; i < done.length; i++)
            done[i] = false;
        boolean allDone = false;
        Pattern assign = Pattern.compile("value (\\d+) goes to bot (\\d+)");
        Pattern give = Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)");
        Matcher m;
        while (!allDone) {
            for (int i = 0; i < instructions.length; i++) {
                if (!done[i]) {
                    m = assign.matcher(instructions[i]);
                    if (m.matches()) {
                        int n = Integer.parseInt(m.group(2));
                        // value .. goes to .. -- if not isFull
                        if (!bots[n].isFull()) {
                            int value = Integer.parseInt(m.group(1));
                            bots[n].add(value);
//                            if (bots[n].low == 17 && bots[n].high == 61) {
//                                System.out.println(n);
//                                return;
//                            }
                            done[i] = true;
                        }
                    } else {
                        m = give.matcher(instructions[i]);
                        if (m.matches()) {
                            // bot .. gives .. to .. -- if isFull
                            int n = Integer.parseInt(m.group(1));
                            if (bots[n].isFull()) {
                                int i1 = Integer.parseInt(m.group(3));
                                int i2 = Integer.parseInt(m.group(5));
                                if (m.group(2).equals("output")) {
                                    output[i1] = bots[n].low;
                                } else {
                                    bots[i1].add(bots[n].low);
//                                    if (bots[i1].low == 17 && bots[i1].high == 61) {
//                                        System.out.println(i1);
//                                        return;
//                                    }
                                }
                                if (m.group(4).equals("output")) {
                                    output[i2] = bots[n].high;
                                } else {
                                    bots[i2].add(bots[n].high);
//                                    if (bots[i1].low == 17 && bots[i1].high == 61) {
//                                        System.out.println(i2);
//                                        return;
//                                    }
                                }
                                bots[n].low = -1;
                                bots[n].high = -1;
                                done[i] = true;
                            }
                        }
                    }
                }
//                printBots(bots);
            }
            allDone = true;
            for (int i = 0; i < done.length; i++) {
                allDone = allDone && done[i];
            }
        }
        System.out.println(Arrays.toString(output));
    }
    
    private static void printBots(Bot[] bots) {
        System.out.print("[");
        System.out.print("(" + bots[0].low + ", " + bots[0].high + ")");
        for (int i = 1; i < bots.length; i++) {
            System.out.print(", (" + bots[i].low + ", " + bots[i].high + ")");
        }
        System.out.println("]");
    }
    
    private static class Bot {
        int low;
        int high;
        
        public Bot() {
            this(-1, -1);
        }
        
        public Bot(int value) {
            this(value, -1);
        }
        
        public Bot(int low, int high) {
            this.low = low;
            this.high = high;
        }
        
        public boolean isFull() {
            return (low != -1) && (high != -1);
        }
        
        public void add(int value) {
            if (!isFull()) {
                if (low == -1)
                    low = value;
                else if (value > low)
                    high = value;
                else {
                    high = low;
                    low = value;
                }
            }
        }
    }
}
