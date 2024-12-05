package Main;
import Challenges.*;
import java.util.Scanner;

public class Main {
    private static final int DAY = 4;
    public static void main(String[] args) {
        Challenge challenge = switch (DAY) {
            case 1 -> new HistorianHysteria(2, 1000);
            case 2 -> new RedNosedReports(2);
            case 3 -> new MullItOver(2, 6);
            case 4 -> new CeresSearch(1, 140);
            default -> throw new IllegalStateException();
        };
        System.out.println(challenge.solution);
    }

    public static Scanner scanner = new Scanner(System.in);
    public static int[] nextInts() {
        String[] strings = scanner.nextLine().split("\\s+");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
}
