package adventofcode2016;

/**
 *
 * @author Sporax
 */
public class Day16 {
    public static void main(String[] args) {
        // until the length criteria is met, use dragon algorithm
        String input = "01110110101001000";
        int length = 35651584;
        while (input.length() < length) {
            StringBuilder sb = new StringBuilder(input);
            sb.reverse();
            // set 1 to 0, 0 to 1
            String temp = sb.toString();
            temp = temp.replace("0", "2");
            temp = temp.replace("1", "0");
            temp = temp.replace("2", "1");
            input += "0" + temp;
        }
        // ((a 0 b) 0 (a 1 b)) 0 (()
        input = input.substring(0, length);
        System.out.println(input);
        String checksum = input;
        do {
            String temp = "";
            for (int i = 0; i < checksum.length() - 1; i += 2) {
                if (checksum.charAt(i) == checksum.charAt(i + 1))
                    temp += "1";
                else
                    temp += "0";
            }
            checksum = temp;
        } while (checksum.length() % 2 == 0);
        System.out.println(input);
        System.out.println(checksum);
    }
}
