package main;

import challenges.Challenges;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Challenges.runChallenge(3);
    }

    public static int[] nextInts() {
        String[] strings = scanner.nextLine().split("\\s+");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
}
