package adventofcode2016;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Sporax
 */
public class PathFinder {
    public static int[][] board;
    public static List<Integer> results = new ArrayList<>();
    public static Map<Integer, Point> mapping;
    public static Map<Integer, Map<Integer, Integer>> allDistances;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // create a grid of numbers and -1s, starting from origin (0).
        // needs to visit each integer. can repeat paths. 
        // terminating condition: all integers have been covered
        // 1. get coordinates of points and put them in a map
        String[] stringBoard = (new String(Files.readAllBytes(Paths.get("day24_1.txt")))).split("\n");
        board = new int[stringBoard.length][stringBoard[0].length()];
        mapping = new TreeMap<>();
        for (int i = 0; i < stringBoard.length; i++) {  // y-coord
            for (int j = 0; j < stringBoard[i].length(); j++) { // x-coord
                board[i][j] = stringBoard[i].charAt(j) == '#' ? -1 : 0;
                if (stringBoard[i].charAt(j) >= 48 && stringBoard[i].charAt(j) <= 57)
                    mapping.put(Integer.parseInt(stringBoard[i].substring(j, j + 1)), new Point(j, i));
            }
        }
        System.out.println(mapping);
        allDistances = new HashMap<>();
        getAllDistances();
        System.out.println(allDistances);
        backtrack();
        for (int i = 0; i < board.length; i++) {
            java.util.Arrays.toString(board[i]);
        }
    }
    
    private static void backtrack() {
        // deal with origin case
        List<Integer> l = new ArrayList<>(mapping.keySet());
        List<Integer> distances = new ArrayList<>();
        // remove origin
        l.remove((Integer)0);
        backtrack(l, distances, 0, 0, "0"); // backtrack from l starting at 0 with distance=0
        // add final distance to result
        System.out.println(distances);
        // get min distance
        int min = distances.get(0);
        for (int i = 1; i < distances.size(); i++)
            min = Math.min(min, distances.get(i));
        System.out.println(min);
    }
    
    private static void backtrack(List<Integer> l, List<Integer> result, int curr, int distance, String path) {
        if (l.isEmpty()) {
//                result.add(distance + allDistances.get(0).get(curr)); // PART 2
                result.add(distance);  // PART 1
                System.out.println(distance + ": " + path + ", 0");
//            }
        } else {
            // for each point, remove it
            for (int i = 0; i < l.size(); i++) {
                int temp = l.remove(i);
                // distance from current to selected point
                int d = allDistances.get(curr).get(temp);
                // backtrack
                backtrack(l, result, temp, distance + d, path + ", " + temp);
                // add it
                l.add(i, temp);
            }
        }
    }
    
    private static void getAllDistances() {
        // deal with origin
        Point origin = mapping.remove(0);
        Map<Integer, Integer> paths = new HashMap<>();
        Iterator<Integer> it = mapping.keySet().iterator();
        while (it.hasNext()) {
            int i = it.next();
            paths.put(i, findPath(mapping.get(i), origin.x, origin.y));
        }
        allDistances.put(0, paths);
        // for each point, get all distances to other points
        // store distances in allDistances
        for (int key = 1; key <= mapping.keySet().size(); key++) {
            Point p = mapping.remove(key);
            paths = new HashMap<>();
            for (int otherPoint : mapping.keySet()) {
                paths.put(otherPoint, findPath(mapping.get(otherPoint), p.x, p.y));
            }
            allDistances.put(key, paths);
            mapping.put(key, p);
        }
        mapping.put(0, origin);
    }
    
    private static int findPath(Point target, int x, int y) {
        // make an adjacent move, if that square is not 0, stop moving
        // else set that to the turn number, keep moving
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++)
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        
        HashMap<Integer, String> result = new HashMap<>();
        // m is a list of point tostrings of points to visit
        findPath(copy, target, x, y, 0, result, "");
        Iterator<Integer> iterate = result.keySet().iterator();
        int min = iterate.next();
        while (iterate.hasNext()) {
            int r = iterate.next();
            if (r < min)
                min = r;
        }
        System.out.println(result.get(min));
        return min;
    }
    
    private static void findPath(int[][] board, Point target, int x, int y, int turn, HashMap<Integer, String> result, String path) {
        if (x == target.x && y == target.y) {
            // return turn
            result.put(turn, path);
        } else if (x < 0 || y < 0 || x >= board[0].length || y >= board.length || 
                board[y][x] == -1)
            // at a wall or a place that has been visited
            ;
        else if (board[y][x] == 0 || board[y][x] > turn) {
            // check if this position is in the list
            // set this position to the turn number
            board[y][x] = turn;
            // move up
            findPath(board, target, x, y - 1, turn + 1, result, path + "D");
            // move down
            findPath(board, target, x, y + 1, turn + 1, result, path + "U");
            // move left
            findPath(board, target, x - 1, y, turn + 1, result, path + "L");
            // move right
            findPath(board, target, x + 1, y, turn + 1, result, path + "R");
        }
    }
    
    private static class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getDistance(Point other) {
            // vertical distance + horizontal distance
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }
        
        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
