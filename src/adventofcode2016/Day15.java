package adventofcode2016;

/**
 *
 * @author Sporax
 */
public class Day15 {
    public static void main(String[] args) {
        // initialize disks
        Disk[] disks = {new Disk(1, 17, 5), new Disk(2, 19, 8), new Disk(3, 7, 1),
            new Disk(4, 13, 7), new Disk(5, 5, 1), new Disk(6, 3, 0), new Disk(7, 11, 0)};
        int minTime = -1;
        boolean flag = false;
        while (!flag) {
            minTime++;
            // if all disks are at position 0 for time, set flag to true
            flag = true;
            for (Disk d : disks) {
                flag = flag && d.getPosition(minTime) == 0;
            }
        }
        System.out.println(minTime);
    }
    
    private static class Disk {
        int positions;
        int position;
        int number;
        
        public Disk(int n, int positions, int position) {
            this.positions = positions;
            this.position = position;
            number = n;
        }
        
        public int getPosition(int time) {
            return (position + time + number) % positions;
        }
    }
}
