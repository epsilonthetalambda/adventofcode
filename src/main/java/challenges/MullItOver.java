/*  03/12/24:
 *  The third challenge, where we have to execute a corrupted program.
 *  Today's challenge is similar to yesterday's, in that I had to refactor everything for part 2.
 *  This one was a lot more straightforward though, but only because I turned the string into a char[].
 *  Part one was a massive switch statement using a "state" variable, where 0 meant looking for 'm', and so on.
 *  I realised that that would not work for part 2, so I refactored it to a set of functions that check ahead to verify any potential instructions.
 *  After completing the challenge, I added the while loop to skip past characters that are not the start of a function.
 *  Overall, I'd say it was about as hard as Red Nosed Reports, but less difficult (if that makes sense).  */

package challenges;

import main.Main;

public class MullItOver {
    int solution;

    char[] input;
    int[] number = new int[2];
    boolean enabled;
    public MullItOver() {
        solution = 0;
        enabled = true;
        for (int i = 0; i < 6; i++) {
            input = Main.scanner.nextLine().toCharArray();
            for (int c = 0; c < input.length; c++) {
                while (!((input[c] == 'd') || (enabled && input[c] == 'm') || c + 1 == input.length)) c++;
                if (checkDo(c)) enabled = true;
                else if (checkDont(c)) enabled = false;
                if (enabled) checkMul(c);
            }
        }
        System.out.println(solution);
    }
    private boolean checkDo(int i) {
        try { // Try-ignored my beloved
            if (input[i] == 'd') i++;
            else return false;

            if (input[i] == 'o') i++;
            else return false;

            if (input[i] == '(') i++;
            else return false;

            return input[i] == ')';

        } catch (Exception ignored) {
            return false;
        }
    }
    private boolean checkDont(int i) {
        try { // Try-ignored my beloved
            if (input[i] == 'd') i++;
            else return false;

            if (input[i] == 'o') i++;
            else return false;

            if (input[i] == 'n') i++;
            else return false;

            if (input[i] == '\'') i++;
            else return false;

            if (input[i] == 't') i++;
            else return false;

            if (input[i] == '(') i++;
            else return false;

            return input[i] == ')';

        } catch (Exception ignored) {
            return false;
        }
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

        } catch (Exception ignored) {}
    }
}