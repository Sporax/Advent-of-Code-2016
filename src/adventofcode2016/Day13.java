package adventofcode2016;

/**
 *
 * @author Sporax
 */
public class Day13 {
    static int[][] board;
    static final int CODE = 1362;
    
    public static void main(String[] args) {
        board = new int[40][40];  // wall is -1, empty is 0, walked is 1
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                int value = x * x + 3 * x + 2 * x * y + y + y * y + CODE;
                String binary = Integer.toBinaryString(value);
                int count1s = 0;
                for (int i = 0; i < binary.length(); i++)
                    if (binary.charAt(i) == '1')
                        count1s++;
                board[y][x] = count1s % 2 == 0 ? 0 : -1;
            }
        }
        printBoard();
        System.out.println();
        findPaths();
        printBoard();
        System.out.println(board[39][31]);
        
        // part 2:
        // find all locations __including starting location__ you can reach in at most 50 steps
        int count = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] > 0 && board[y][x] <= 51)
                    count++;
            }
        }
        System.out.println("within 50 spaces: " + count);
    }
    
    public static void printBoard() {
        for (int[] b : board) {
            System.out.print("[");
            if (b[0] == -1)
                System.out.print("#");
            else
                System.out.print(b[0]);
            for (int i = 1; i < b.length; i++) {
                if (b[i] == -1)
                    System.out.print(",\t#");
                else
                    System.out.print(",\t" + b[i]);
            }
            System.out.println("]");
        }
    }
    
    public static void findPaths() {
        // make an adjacent move, if that square is not 0, stop moving
        // else set that to the turn number, keep moving
        findPaths(1, 1, 1);
    }
    
    private static void findPaths(int x, int y, int turn) {
        if (x < 0 || y < 0 || x >= board[0].length || y >= board.length || 
                board[y][x] == -1)
            // at a wall or a place that has been visited
            ;
        else if (board[y][x] > turn) {
            board[y][x] = turn;
            findPaths(x, y - 1, turn + 1);
            // move down
            findPaths(x, y + 1, turn + 1);
            // move left
            findPaths(x - 1, y, turn + 1);
            // move right
            findPaths(x + 1, y, turn + 1);
        } else if (board[y][x] == 0) {
            // set this position to the turn number
            board[y][x] = turn;
            // move up
            findPaths(x, y - 1, turn + 1);
            // move down
            findPaths(x, y + 1, turn + 1);
            // move left
            findPaths(x - 1, y, turn + 1);
            // move right
            findPaths(x + 1, y, turn + 1);
        }
    }
}
