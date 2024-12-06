package Main;
import Challenges.*;
import java.util.Scanner;

public class Main {
    private static final int DAY = 5;
    public static void main(String[] args) {
        Challenge challenge = switch (DAY) {
            case 1 -> new HistorianHysteria(2, 1000);
            case 2 -> new RedNosedReports(2);
            case 3 -> new MullItOver(2, 6);
            case 4 -> new CeresSearch(2, 140);
            case 5 -> new PrintQueue(2,1176,192);
            default -> throw new IllegalStateException();
        };
        System.out.println(challenge.solution);
    }

    public static Scanner scanner = new Scanner(System.in);
    public static int[] nextInts(String split) {
        String[] strings = scanner.nextLine().split(split);
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
}
