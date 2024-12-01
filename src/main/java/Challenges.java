import java.util.Arrays;
import java.util.Scanner;

public class Challenges {
    static Scanner keyboard = new Scanner(System.in);
    public static void HistorianHysteria() {
        /*01/12/24:
        * The first challenge, where we have to reconcile two different lists.
        * This challenge felt quite easy, however I did end up getting stuck because I misread distances as "difference of indices", not "difference of values" for part 1.
        * I'd say the hardest part for someone new to programming would be parsing the string values, because unless you know how to use the scanner and split strings, it could be very difficult.
        * Part one could also be challenging, I solved it using my knowledge of linear search and the idea of using a companion bool array to keep track of what hasn't been compared yet.
        * Part two was dead simple, I think anyone with a knowledge of for loops could easily solve that part on its own.
        * Overall, good warm-up for the rest of the challenge
        * */
        final int size = 1000;
        int solution;

        int[][] lists = new int[2][size]; // The parsed lists of locations
        String lineInput; // What each line of input is written to
        String[] splitInput; // What takes the input once it has been split by spaces

        for (int i = 0; i < size; i++) { // I used a word processor to find how many pairs of numbers there were
            lineInput = keyboard.nextLine();
            splitInput = lineInput.split("\\s+"); // This regex code just looks for sequential spaces
            lists[0][i] = Integer.parseInt(splitInput[0]);
            lists[1][i] = Integer.parseInt(splitInput[1]);
        }

        // PART ONE
        boolean[][] compared = new boolean[2][size]; // Whether or not each list item has been compared already
        int[] lowest = new int[2]; // Stores the position of the next lowest elements from the linear search
        solution = 0;

        for (int i = 0; i < size; i++) {
            // Linear searches both lists to find the smallest uncompared value
            for (int l = 0; l < 2; l++) { // This line means I don't have to copy and paste my code to check the other list. Read lists[l] as the list we are looking at, and so on
                lowest[l] = size+1; // Having a number above size is the flag that we do not have a current lowest yet

                for (int s = 0; s < size; s++) {
                    if (!compared[l][s] && (lowest[l] > size || lists[l][lowest[l]] > lists[l][s])) { // If the current value has not been compared, and the previous lowest is either missing or smaller than the current value
                        lowest[l] = s;
                    }
                }
                compared[l][lowest[l]] = true; // Sets the current lowest to have been searched
            }

            solution += Math.abs(lists[1][lowest[1]] - lists[0][lowest[0]]); // Adds the distance to the solution
        }
        System.out.println(solution);

        // PART TWO
        int duplicates;
        solution = 0;
        for (int i = 0; i < size; i++) { // Loops through all items in the first list
            duplicates = 0;
            for (int d = 0; d < size; d++) { // Loops through all items in the second list
                if (lists[0][i] == lists[1][d]) { // If a match, increment duplicates
                    duplicates++;
                }
            }
            solution += lists[0][i] * duplicates;
        }
        System.out.println(solution);
    }

}