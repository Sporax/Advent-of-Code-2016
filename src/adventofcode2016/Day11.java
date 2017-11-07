package adventofcode2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sporax
 */
public class Day11 {
    /* recursive backtracking algorithm:
        * elevator can either go up or down
        * must carry one thing to move elevator
        * type 1 chip without type1 generator in same floor as type 2 generator blows up
        * type 1 chip with type 1 generator is safe
        * don't 'undo' moves -- if chip 1 moved up, don't move it back down
        * stop pursuing threads when a chip blows up
        * elevator can carry: one thing only or two things max.
        * can't combine type 2 chip with type 1 gen in elevator
        * permitted in elevator: two chips, two gens, a paired chip-gen
    */
    static String[][] items;
    static Map<String, Integer> references;
    static String[] elevator = new String[2];
    static int elevatorFloor;
    static ArrayList<String> previousMoves;  // append each items state after moving to previousMoves. if a current state
                                             // matches, don't continue
    static String[][] pairs;
    
    public static void main(String[] args) {
        elevatorFloor = 0;
        items = new String[][]{{"E", ".",  "HM", ".",  "LM"},
                               {".", "HG", ".",  ".",  "."},
                               {".", ".",  ".",  "LG", "."},
                               {".", ".",  ".",  ".",  "."}};
        pairs = new String[][]{{"HG", "HM"}, {"LG", "LM"}};
        references = new HashMap<String, Integer>(){{
           put("E", 1);
           put("HG", 1);
           put("HM", 2);
           put("LG", 3);
           put("LM", 4);
        }};
        previousMoves = new ArrayList<>();
        // finishing condition: top floor is full
        System.out.println(printItems());
        backTrack();
        System.out.println(printItems());
    }
    
    private static void backTrack() {
        // if frying condition, don't continue
        if (!isValidState())
            return;
        // if everything is on the top floor, finish
        String topFloor = "";
        for (int i = 1; i < items[0].length; i++)
            topFloor += items[3][i];
        if (!topFloor.contains(".")) {
            // finished
            System.out.println("done");
        } else {
            // get things
            ArrayList<String> things = new ArrayList<>();
            for (int i = 1; i < items[0].length; i++)
                if (!items[elevatorFloor][i].equals("."))
                    things.add(items[elevatorFloor][i]);
            System.out.println("things: " + things);
            // select a thing
            for (String first : things) {
                // move up
                System.out.println("selected: " + first);
                boolean canMove = moveElevator(first, null, 1);
                if (canMove) {
                    // if moved up and this move has been made before, don't recurse
                    System.out.println("moved");
                    System.out.print("new board:\n" + printItems());
//                    if (!previousMoves.contains(printItems())) {
                        // otherwise, add state and recurse
//                        System.out.println("repeated move; not recursing");
                        previousMoves.add(printItems());
                        backTrack();
//                    }
                    // unchoose
                    moveElevator(first, null, -1);
                    System.out.println("unmoved");
                    System.out.print(printItems());
                    System.out.println("elevator: " + elevatorFloor);
                }
                // move down
                canMove = moveElevator(first, null, -1);
                if (canMove) {
//                    if (!previousMoves.contains(printItems())) {
                        previousMoves.add(printItems());
                        backTrack();
//                    }
                    // unchoose
                    moveElevator(first, null, 1);
                }
            }
            // select valid combinations on the floor: two chips/two generators/a pair
            for (int i = 0; i < things.size(); i++) {
                for (int j = i + 1; j < things.size(); j++) {
                    // if valid pair,
                    String first = things.get(i);
                    String second = things.get(j);
                    // move up
                    System.out.println("selected: " + first + "," + second);
                    boolean canMove = moveElevator(first, second, 1);
                    System.out.println("elevator: " + elevatorFloor);
                    if (canMove) {
                        // if moved up and this move has been made before, don't recurse
                        System.out.println("moved");
                        System.out.print("new board:\n" + printItems());
    //                    if (!previousMoves.contains(printItems())) {
                            // otherwise, add state and recurse
    //                        System.out.println("repeated move; not recursing");
                            previousMoves.add(printItems());
                            backTrack();
    //                    }
                        // unchoose
                        moveElevator(first, second, -1);
                        System.out.println("unmoved");
                        System.out.print(printItems());
                    }
                    // move down
                    canMove = moveElevator(first, second, -1);
                    if (canMove) {
//                        if (!previousMoves.contains(printItems())) {
                            previousMoves.add(printItems());
                            backTrack();
//                        }
                        // unchoose
                        moveElevator(first, second, 1);
                    }
                }
            }
            // don't make the move if it repeats a previous move
            // deselect, repeat with other valid combinations
        }
    }
    
    private static String printItems() {
        String result = "";
        for (String[] s : items) {
            result += Arrays.toString(s) + "\n";
        }
        return result;
    }
    
    private static boolean moveElevator(String first, String second, int direction) {
        // check if what the elevator is carrying is valid
        ArrayList<String> elevatorItems = new ArrayList<>();
        elevatorItems.add(first);
        if (second != null)
            elevatorItems.add(second);
        if (!checkSpace(elevatorItems))
            return false;
        // if direction == 1, move up, else move down
        if ((elevatorFloor + direction == 4) || (elevatorFloor + direction == -1))
            return false;
        // remove elevator items from floor
        loadElevator(first, second, ".");
        // move elevator up
        if (!isValidState()) { // if the previous floor will be messed up, put items back and say it can't be done
            loadElevator(first, second, first, second);
            return false;
        }
        items[elevatorFloor][0] = ".";
        elevatorFloor += direction;  // +1 or -1 = go up or go down
        // add items to floor
        items[elevatorFloor][0] = "E";
        loadElevator(first, second, first, second);
        previousMoves.add(printItems());
        return true;
    }

    private static void loadElevator(String first, String second, String set) {
        loadElevator(first, second, set, set);
    }
    
    private static void loadElevator(String first, String second, String setFirstTo, String setSecondTo) {
        items[elevatorFloor][references.get(first)] = setFirstTo;
        if (second != null)
            items[elevatorFloor][references.get(second)] = setSecondTo;
    }
    
    private static boolean isValidState() {
        // check the board. if this floor (the one the elevator is on) contains
        // either: 
        //  * one generator with another chip, both unpaired
        //  * one chip with another generator, both unpaired
        // then return false
        ArrayList<String> floorItems = new ArrayList<>();
        for (int i = 1; i < items[0].length; i++) {
            if (!items[elevatorFloor][i].equals("."))
                floorItems.add(items[elevatorFloor][i]);
        }
        return checkSpace(floorItems);
    }
    
    private static boolean checkSpace(ArrayList<String> floorItems) {
        // if it contains a pair, remove it from the list
        for (String[] pair : pairs) {
            if (floorItems.contains(pair[0]) && floorItems.contains(pair[1])) {
                floorItems.remove(pair[0]);
                floorItems.remove(pair[1]);
            }
        }
        if (floorItems.size() > 1) {
            boolean generators = false;
            boolean chips = false;
            for (String item : floorItems) {
                if (item.charAt(item.length() - 1) == 'M')
                    chips = true;
                if (item.charAt(item.length() - 1) == 'G')
                    generators = true;
            }
            if (chips && generators)
                return false;
        }
        return true;
    }
}
