/*  05/12/24:
 *  The fifth challenge, where we have to verify the validity of updates.
 *  This challenge is the first where I have no idea how to fix it. I can find a solution, but apparently not the right one.
 *  I wasn't sure whether the rules could be turned into a linear order, so I sorted the rules and utilised a jump table to search through it quicker.
 *  Part one is a matter of going through each update, then through each pair of numbers, checking for a violation of the rules. O(n^2 logn) because of the pairs and the binary search
 *  Part two was similar to part 1, but whenever there was a violation, I swap the numbers and reset the search. It outputs a valid solution that apparently is wrong.
 *  Might retry it to find the second star later.  */

package Challenges;
import Main.Main;
import java.util.Arrays;
import java.util.Comparator;
public class PrintQueue extends Challenge {
    private final int[][] rule;
    private final int[][] jumpTable = new int[100][2]; // Stores the starting and ending index of the rules with a given earlier number, to make lookups quicker
    public PrintQueue(int part, int rules, int updates) {
        assert part == 1 || part == 2;
        solution = 0;
        // Creating the rule table
        rule = new int[rules][2];
        for (int i = 0; i < rules; i++) {
            rule[i] = Main.nextInts("\\|");
        }
        Main.scanner.nextLine(); // For the empty line between rules and updates
        // Sorting rules for binary search
        Arrays.sort(rule, Comparator.comparing(x -> x[1]));
        Arrays.sort(rule, Comparator.comparing(x -> x[0]));
        // Creating the jump table
        int currentRule = 0;
        for (int i = 0; i < rules; i++) {
            if (rule[i][0] != currentRule) {
                jumpTable[currentRule][1] = i;
                currentRule = rule[i][0];
                jumpTable[currentRule][0] = i;
            }
            jumpTable[currentRule][1] = rules;
        }
        if (part == 1) {
            partOne(updates);
        } else {
            partTwo(updates);
        }
    }
    private void partOne(int updates) {
        for (int i = 0; i < updates; i++) {
            int[] update = Main.nextInts(",");
            if (check(update) == null) solution += update[(update.length - 1)/2];
        }
    }
    private void partTwo(int updates) {
        for (int i = 0; i < updates; i++) {
            int[] update = Main.nextInts(",");
            int[] invalid = check(update);
            if (invalid == null) continue;
            while (invalid != null) {
                int temp = update[invalid[0]];
                update[invalid[0]] = update[invalid[1]];
                update[invalid[1]] = temp;
                invalid = check(update);
            }
            solution += update[(update.length - 1)/2];
        }
    }
    private int[] check(int[] update) {
        for (int a = 0; a < update.length; a++) {
            for (int b = a+1; b < update.length; b++) {
                int low = jumpTable[update[b]][0];
                int high = jumpTable[update[b]][1] - 1;

                while (low <= high) {
                    int mid = (low + high) >>> 1;
                    int midVal = rule[mid][1];

                    if (midVal < update[a])
                        low = mid + 1;
                    else if (midVal > update[a])
                        high = mid - 1;
                    else
                        return new int[]{a, b}; // fail
                }
            }
        }
        return null; // success
    }
}

