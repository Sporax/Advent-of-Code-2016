package adventofcode2016;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sporax
 */
public class Day18 {
    private static List<String> tiles;
    private static final int ROWS = 400000;
    
    public static void main(String[] args) {
        String input = "......^.^^.....^^^^^^^^^...^.^..^^.^^^..^.^..^.^^^.^^^^..^^.^.^.....^^^^^..^..^^^..^^.^.^..^^..^^^..";
        // take input
        tiles = new ArrayList<>();
        tiles.add(input);
        // add a new row to the end using the last row
        for (int i = 1; i < ROWS; i++)
            addRow();
        System.out.println(countSafeTiles());
    }
    
    private static void addRow() {
        // take the last row, add a new row
        String last = tiles.get(tiles.size() - 1);
        String newRow = "";
        for (int i = 0; i < last.length(); i++) {
            boolean a, b, c;  // true if trap
            a = i == 0 ? false : last.charAt(i - 1) == '^';
            b = last.charAt(i) == '^';
            c = i == last.length() - 1 ? false : last.charAt(i + 1) == '^';
            if ((a && b && !c) || (!a && b & c) || (a && !b && !c) || (!a && !b && c))
                newRow += '^';
            else
                newRow += '.';
        }
        tiles.add(newRow);
    }
    
    private static int countSafeTiles() {
        int count = 0;
        for (String row : tiles)
            for (int i = 0; i < row.length(); i++)
                if (row.charAt(i) == '.')
                    count++;
        return count;
    }
}
