package challenges;

import main.Main;

public class RedNosedReports {
    final int reports = 1000;
    int solution;

    boolean direction; // True if the levels are increasing and vice versa, declared here to avoid variable hot potato

    public RedNosedReports() {
        solution = 0;
        for (int i = 0; i < reports; i++) { // Runs the analyse function on every report.
            if (analyse(Main.nextInts(), true)) {
                solution ++;
            }
        }
        System.out.println(solution);
    }
    private boolean analyse(int[] report, boolean dampener) { // Analyses whether a given report is safe or not
        // This code checks the first 3 pairs, and sets the direction to the most common direction in the sample.
        // This is guaranteed to work because there has to be at least 2 pairs with the direction, and you can't turn that to 0 with one dampen, so it would have been unsafe anyways
        int increases = 0;
        for (int i = 0; i < 3; i++) {
            if (report[i+1] > report[i]) {
                increases++;
            }
        }
        direction = (increases > 1);

        for (int l = 1; l < report.length; l++) { // For each pair of levels, check if it is invalid
            if (invalid(report[l-1], report[l])) {
                if (dampener) { // If there is a dampener charge, use it, otherwise fail
                    dampener = false;
                    // We now get to decide which level to dampen. If we dampen the first level, it doesn't directly affect the future checks, so we don't have to change any levels.
                    // However, we need to check if that would mess up previous or future checks. If it will, we dampen the second level, and let the next loop see if that fails.
                    if ((l < report.length - 1 && invalid(report[l], report[l + 1])) || (l > 1 && invalid(report[l - 2], report[l]))) { // Clunky because we have to make sure not to index out of bounds. If I used try-catch, it would need 2 ifs in parallel, which would be even more clunky
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
