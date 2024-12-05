/*  02/12/24:
 *  The second challenge, where we have to analyse the safety of different reports.
 *  Got stuck again, this time because I didn't think about an edge case in part 2.
 *  If you have completed day 1, you can reuse the code from that to parse the input. I decided to pull it out into a static function, so I can reuse it for later challenges.
 *  Part one was fairly easy, check the direction of the first two, then compare each pair for wrong direction or gap size.
 *  Part two made me have to rewrite all of my code. I started by pulling the logic out into its own function to remove clutter.
 *  The main part of the function is basically the same, but if the dampener is activated, it checks for what to dampen, then continues without a dampen.
 *  The thing that took me the longest was finding the direction. I was about to do something way more complicated, but taking the most common direction just works.
 *  I'd say part 1 is fairly easy, but part 2 was quite rough.  */

package Challenges;
import Main.Main;
public class RedNosedReports extends Challenge {
    private boolean direction;

    public RedNosedReports(int part) {
        assert part == 1 || part == 2;
        solution = 0;
        for (int i = 0; i < 1000; i++) { // Runs the analyse function on every report.
            if (analyse(Main.nextInts(), part == 2)) {
                solution ++;
            }
        }
    }
    private boolean analyse(int[] report, boolean dampener) { // Analyses whether a given report is safe or not
        // This code checks the first 3 pairs, and sets the direction to the most common direction in the sample.
        // This is guaranteed to work because there has to be at least 2 pairs with the direction, and you can't turn that to 0 with one dampen, so if the rest of the report goes the other direction, it would have been unsafe anyways
        int increases = 0;
        for (int i = 0; i < 3; i++) {
            if (report[i+1] > report[i]) {
                increases++;
            }
        }
        // True if the levels are increasing and vice versa.
        direction = (increases > 1);

        for (int l = 1; l < report.length; l++) { // For each pair of levels, check if it is invalid
            if (invalid(report[l-1], report[l])) {
                if (dampener) { // If there is a dampener charge, use it, otherwise fail
                    dampener = false;
                    // We now get to decide which level to dampen. If we dampen the first level, it doesn't directly affect the future checks, so we don't have to change any levels.
                    // However, we need to check if that would mess up previous or future checks. If it will, we dampen the second level, and let the next loop see if that fails.
                    if ((l < report.length - 1 && invalid(report[l], report[l + 1])) || (l > 1 && invalid(report[l - 2], report[l]))) { // Clunky because we have to make sure not to index out of bounds. If I used try-ignored, it would need 2 ifs in parallel, which would be even more clunky
                        report[l] = report[l - 1];
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean invalid(int a, int b) { // Checks if a given pair is invalid.
        return ((b > a) ^ direction) || Math.abs(b - a) < 1 || Math.abs(b - a) > 3;
    }
}
