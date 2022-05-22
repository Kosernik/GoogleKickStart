package RoundA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ChallengeNine {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfCases = Integer.parseInt(scanner.nextLine());

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                String number = scanner.nextLine();

                System.out.println("Case #" + caseNum + ": " + getSmallestMultipleOfNine(number));
            }
        }
    }

    private static String getSmallestMultipleOfNine(String number) {
        int extraDigit = digitNeededToBeDivisibleByNine(number);
        if (extraDigit == 0) {
            return number.charAt(0) + "0" + number.substring(1);
        }

        int idx = 0;
        while (idx < number.length() && (number.charAt(idx) - '0') <= extraDigit) idx++;

        return number.substring(0, idx) + extraDigit + number.substring(idx);
    }

    private static String getSmallestMultipleOfNineTLE(String number) {
        int length = number.length();

        List<String> candidates = new ArrayList<>();

        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= 9; j++) {
                String cand = number.substring(0, i) + j + number.substring(i);
                if (cand.charAt(0) != '0' && isDivisibleByNine(cand)) {
                    candidates.add(cand);
                }
            }
        }

        Collections.sort(candidates);
        return candidates.get(0);
    }

    private static boolean isDivisibleByNine(String number) {
        int sum = 0;

        for (char digit : number.toCharArray()) {
            sum += (digit - '0');
            sum %= 9;
        }

        return sum % 9 == 0;
    }

    private static String getSmallestMultipleOfNineAlt(String number) {
        int extraDigit = digitNeededToBeDivisibleByNine(number);

        String result = "99" + number;

        for (int i = 0; i <= number.length(); i++) {
            String candidate = number.substring(0, i) + extraDigit + number.substring(i);
            if (candidate.charAt(0) != '0') {
                if (smaller(result, candidate)) {
                    result = candidate;
                }
            }
            if (extraDigit == 0) {
                String nine = number.substring(0, i) + '9' + number.substring(i);
                if (smaller(result, nine)) {
                    result = nine;
                }
            }
        }

        return result;
    }

    private static boolean smaller(String first, String second) {
        if (first.length() < second.length()) return false;
        if (first.length() > second.length()) return true;

        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) > second.charAt(i)) return true;
            if (first.charAt(i) < second.charAt(i)) return false;
        }

        return false;
    }

    private static int digitNeededToBeDivisibleByNine(String number) {
        int sum = 0;

        for (char digit : number.toCharArray()) {
            sum += (digit - '0');
            sum %= 9;
        }

        if (sum % 9 == 0) return 0;
        return 9 - (sum % 9);
    }
}
