package SampleProblem;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int numberOfCases = Integer.parseInt(scanner.nextLine());

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                int numberOfBags = scanner.nextInt();
                int numberOfKids = scanner.nextInt();

                int totalCandies = 0;

                for (int i = 0; i < numberOfBags; i++) {
                    totalCandies += scanner.nextInt();
                }

                int result = totalCandies % numberOfKids;

                System.out.println("Case #" + caseNum + ": " + result);
            }
        }
    }
}
