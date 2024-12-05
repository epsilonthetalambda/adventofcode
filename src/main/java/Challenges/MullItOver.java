/*  03/12/24:
 *  The third challenge, where we have to execute a corrupted program.
 *  Today's challenge is similar to yesterday's, in that I had to refactor everything for part 2.
 *  This one was a lot more straightforward though, but only because I turned the string into a char[].
 *  Part one was a massive switch statement using a "state" variable, where 0 meant looking for 'm', and so on.
 *  I realised that that would not work for part 2, so I refactored it to a set of functions that check ahead to verify any potential instructions.
 *  A previous improvement was to skip the index forwards until it found a 'd' or 'm', but that isn't very helpful since the functions already check those first
 *  Instead I ended up combining the do() and don't() checks together, since they start with the same 2 letters. This also perfectly replicates the skip I had before.
 *  Overall, I'd say it was about as hard as Red Nosed Reports, but less difficult (if that makes sense).  */

package Challenges;
import Main.Main;
public class MullItOver extends Challenge {

    private char[] input;
    private final int[] number = new int[2];
    private boolean enabled;

    public MullItOver(int part, int lines) {
        assert part == 1 || part == 2;
        solution = 0;
        enabled = true;
        for (int i = 0; i < lines; i++) {
            input = Main.scanner.nextLine().toCharArray();
            if (part == 1) {
                partOne(input);
            } else {
                partTwo(input);
            }

        }
    }
    private void partOne(char[] input) {
        try { // Try-ignored my beloved
            for (int c = 0; c + 7 < input.length; c++) { // Because the smallest command is 8 chars long, we don't need to bother checking the last 7 chars for starts of instructions
                checkMul(c);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }
    private void partTwo(char[] input) {
        try { // Try-ignored my beloved
            for (int c = 0; c + 3 < input.length; c++) {
                checkDoDont(c);
                if (enabled) checkMul(c);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    private void checkDoDont(int i) {
        try { // Try-ignored my beloved
            if (input[i] == 'd') i++;
            else return;

            if (input[i] == 'o') i++;
            else return;

            switch (input[i]) {
                case '(':
                    i++;
                    if (input[i] == ')') enabled = true;
                    return;
                case 'n':
                    i++;
                    if (input[i] == '\'') i++;
                    else return;

                    if (input[i] == 't') i++;
                    else return;

                    if (input[i] == '(') i++;
                    else return;

                    if (input[i] == ')') enabled = false;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }
    private void checkMul(int i) {
        try { // Try-ignored my beloved
            if (input[i] == 'm') i++;
            else return;

            if (input[i] == 'u') i++;
            else return;

            if (input[i] == 'l') i++;
            else return;

            if (input[i] == '(') i++;
            else return;

            number[0] = 0;
            for (int d = 0; d < 3; d++) {
                if (input[i] >= '0' && input[i] <= '9') {
                    number[0] *= 10;
                    number[0] += input[i] - '0';
                    i++;
                } else break;
            }
            if (input[i] == ',') i++;
            else return;

            number[1] = 0;
            for (int d = 0; d < 3; d++) {
                if (input[i] >= '0' && input[i] <= '9') {
                    number[1] *= 10;
                    number[1] += input[i] - '0';
                    i++;
                } else break;
            }
            if (input[i] == ')') solution += number[0] * number[1];

        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }
}