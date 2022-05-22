package RoundC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RangePartition {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfCases = scanner.nextInt();

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                int numberOfInts = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                List<Integer> result = getSubSet(numberOfInts, x, y);
                if (result == null || result.size() == 0) {
                    System.out.println("Case #" + caseNum + ": " + "IMPOSSIBLE");
                } else {
                    System.out.println("Case #" + caseNum + ": " + "POSSIBLE");
                    System.out.println(result.size());
                    System.out.print(result.get(0));
                    for (int i = 1; i < result.size(); i++) {
                        System.out.print(" " + result.get(i));
                    }
                    System.out.println();
                }
            }
        }
    }

    private static List<Integer> subset;
    private static boolean foundSubset = false;

    private static List<Integer> getSubSet(int numberOfInts, int x, int y) {
        long totalSum = numberOfInts * (numberOfInts + 1L) / 2;

        if (totalSum % (x+y) != 0) {
            return null;
        }

        boolean[] usedIntegers = new boolean[numberOfInts+1];
        long target = totalSum * (x / y);

        backTrack(0, numberOfInts, target, 0L, usedIntegers);

        return subset;
    }

    private static void backTrack(int number, int numberOfInts, long target, long sum, boolean[] usedIntegers) {
        if (foundSubset) return;
        if (sum > target) return;
        if (sum == target) {
            subset = new ArrayList<>();
            for (int i = 1; i < usedIntegers.length; i++) {
                if (usedIntegers[i]) {
                    subset.add(i);
                }
            }
            foundSubset = true;
            return;
        }

        for (int i = number+1; i <= numberOfInts; i++) {
            if (!usedIntegers[i]) {
                usedIntegers[i] = true;
                backTrack(i, numberOfInts, target, sum + i, usedIntegers);
                usedIntegers[i] = false;
            }
        }
    }
}
