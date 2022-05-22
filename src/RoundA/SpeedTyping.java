package RoundA;

import java.util.Scanner;

public class SpeedTyping {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int numberOfCases = Integer.parseInt(scanner.nextLine());

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                String target = scanner.nextLine();
                String actual = scanner.nextLine();

                int result = extraLetters(target, actual);
                if (result == -1) {
                    System.out.println("Case #" + caseNum + ": " + "IMPOSSIBLE");
                } else {
                    System.out.println("Case #" + caseNum + ": " + result);
                }
            }
        }
    }

    private static int extraLetters(String target, String actual) {
        int result = 0;

        char[] lettersTarget = target.toCharArray();
        char[] lettersActual = actual.toCharArray();

        int idxTarget = 0;

        for (char c : lettersActual) {
            if (idxTarget < lettersTarget.length && lettersTarget[idxTarget] == c) {
                idxTarget++;
            } else {
                result++;
            }
        }

        if (idxTarget == lettersTarget.length) return result;
        else return -1;
    }
}
