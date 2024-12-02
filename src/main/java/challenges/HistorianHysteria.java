/*01/12/24:
 * The first challenge, where we have to reconcile two different lists.
 * This challenge felt quite easy, however I did end up getting stuck because I misread distances as "difference of indices", not "difference of values" for part 1.
 * I'd say the hardest part for someone new to programming would be parsing the string values, because unless you know how to use the scanner and split strings, it could be very difficult.
 * Part one could also be challenging, I solved it using my knowledge of linear search and the idea of using a companion bool array to keep track of what hasn't been compared yet.
 * Part two was dead simple, I think anyone with a knowledge of for loops could easily solve that part on its own.
 * Overall, good warm-up for the rest of the challenge
 * */

package challenges;
import main.Main;

public class HistorianHysteria {
    final int items = 1000;
    int solution;

    int[][] lists = new int[items][2];

    boolean[][] compared = new boolean[items][2]; // Whether or not each list item has been compared already
    int[] lowest = new int[2]; // Stores the position of the next lowest elements from the linear search

    int duplicates;

    public HistorianHysteria() {
        // PART ZERO
        for (int i = 0; i < items; i++) { // Loops through all inputs to parse the numbers
            lists[i] = Main.nextInts();
        }

        // PART ONE

        solution = 0;
        for (int i = 0; i < items; i++) { // Repeats enough time to check all items

            // Linear searches both lists to find the smallest uncompared value
            for (int l = 0; l < 2; l++) { // This line means I don't have to copy and paste my code to check the other list. Read lists[l] as the list we are looking at, and so on
                lowest[l] = items +1; // Having a number above size is the flag that we do not have a current lowest yet

                for (int s = 0; s < items; s++) {
                    if (!compared[s][l] && (lowest[l] > items || lists[lowest[l]][l] > lists[s][l])) { // If the current value has not been compared, and the previous lowest is either missing or smaller than the current value
                        lowest[l] = s;
                    }
                }
                compared[lowest[l]][l] = true; // Sets the current lowest to have been searched
            }

            solution += Math.abs(lists[lowest[1]][1] - lists[lowest[0]][0]); // Adds the distance to the solution
        }
        System.out.println(solution); // Solution to part 1

        // PART TWO
        solution = 0;
        for (int i = 0; i < items; i++) { // Loops through all items in the first list
            duplicates = 0;
            for (int d = 0; d < items; d++) { // Loops through all items in the second list
                if (lists[i][0] == lists[d][1]) { // If a match, increment duplicates
                    duplicates++;
                }
            }
            solution += lists[i][0] * duplicates; // Adds the product to the solution
        }
        System.out.println(solution); // Solution to part 2
    }

}