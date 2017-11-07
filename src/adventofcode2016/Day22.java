package adventofcode2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sporax
 */
public class Day22 {
    public static void main(String[] args) throws FileNotFoundException {
        hackSolution(3014603);
        return;
        /*Node[][] nodes = new Node[25][37];
        for (int i = 0; i < nodes.length; i++)
            for (int j = 0; j < nodes[i].length; j++)
                nodes[i][j] = new Node(0, 0);
        // read data
        Scanner lineScanner = new Scanner(new File("day22.txt"));
        lineScanner.nextLine();
        lineScanner.nextLine();
        Pattern p = Pattern.compile("\\/dev\\/grid\\/node-x(\\d+)-y(\\d+)\\s+\\d+T\\s+(\\d+)T\\s+(\\d+)T.+");
        Matcher m;
        // set all nodes
        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();
            m = p.matcher(line);
            if (m.matches()) {
                int x = Integer.parseInt(m.group(1));
                int y = Integer.parseInt(m.group(2));
                int used = Integer.parseInt(m.group(3));
                int avail = Integer.parseInt(m.group(4));
                nodes[y][x].used = used;
                nodes[y][x].avail = avail;
            }
        }
        for (int i = 0; i < nodes.length; i++) {
            System.out.print("[(" + nodes[i][0].used + "," + nodes[i][0].avail + ")");
            for (int j = 1; j < nodes[i].length; j++)
                System.out.print(", (" + nodes[i][j].used + "," + nodes[i][j].avail + ")");
            System.out.println("]");
        }
        int pairs = 0;
        // find any nodes that match:
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[y].length; x++) {
                // first node
                Node n = nodes[y][x];
                // search all other nodes
                for (int i = 0; i < nodes.length; i++) {
                    for (int j = 0; j < nodes[i].length; j++) {
                        if (check(n, nodes[i][j])) {
                            n.pairs.add(nodes[i][j]);
                            pairs++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < nodes.length; i++) {
            Node n = nodes[i][0];
            if (i == 0)
                System.out.print("(");
            if (n.pairs.isEmpty())
                System.out.print("# ");
            else if (n.pairs.size() == 25 * 37 - 1)
                System.out.print("_");
            else
                System.out.print(". ");
            if (i == 0)
                System.out.print(") ");
            for (int j = 1; j < nodes[i].length; j++) {
                if (i == 0 && j == nodes[i].length - 1)
                    System.out.print("[");
                n = nodes[i][j];
                if (n.pairs.isEmpty())
                    System.out.print("# ");
                else if (n.pairs.size() == 25 * 37 - 1)
                    System.out.print("_");
                else
                    System.out.print(". ");
                if (i == 0 && j == nodes[i].length - 1)
                    System.out.print("] ");
            }
            System.out.println();
        }

        // print number of nodes
        System.out.println(pairs);*/
    }
    
    private static void hackSolution(int input) {
        String s = Integer.toBinaryString(input);
        s = s.substring(1) + s.charAt(0);
        input = Integer.parseInt(s, 2);
        System.out.println("1st part: " + input);
    }
    
    private static boolean check(Node a, Node b) {
        // 1. not the same node
        // 2. node 1 is not empty
        // 3. node 1's used <= node 2's avail
        if ((!a.equals(b)) && (a.used != 0) && (a.used <= b.avail))
            System.out.println("valid: " + a.used + " " + b.avail);
        return !a.equals(b) && a.used != 0 && a.used <= b.avail;
    }
    
    private static class Node {
        int used;
        int avail;
        String mark;
        ArrayList<Node> pairs;
        
        public Node(int a, int b) {
            used = a;
            avail = b;
            pairs = new ArrayList<>();
        }
    }
    
/*    private void checkAdjacent() {
                        // if a node has an above
                if (y > 0)
                    if (check(nodes[y][x], nodes[y - 1][x]))
                        pairs++;
                // if a node has a below
                if (y < nodes.length - 1)
                    if (check(nodes[y][x], nodes[y + 1][x]))
                        pairs++;
                // if a node has a left
                if (x > 0)
                    if (check(nodes[y][x], nodes[y][x - 1]))
                        pairs++;
                // if a node has a right
                if (x < nodes[y].length - 1)
                    if (check(nodes[y][x], nodes[y][x + 1]))
                        pairs++;
    }*/
}
