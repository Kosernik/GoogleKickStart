package RoundG;

import java.util.Scanner;

public class Walktober {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfCases = Integer.parseInt(scanner.nextLine());

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                String input = scanner.nextLine();

                //  M   N   P
                Integer[] numbers = splitLine(input);
                Integer[][] stats = new Integer[numbers[0]][];

                for (int i = 0; i < numbers[0]; i++) {
                    String line = scanner.nextLine();
                    stats[i] = splitLine(line);
                }

                int result = countRequiredSteps(stats, numbers[2]);
                System.out.println("Case #" + caseNum + ": " + result);
            }
        }
    }

    private static Integer[] splitLine(String input) {
        String[] splited = input.split("\\s+");

        Integer[] result = new Integer[splited.length];
        for (int i = 0; i < splited.length; i++) {
            result[i] = Integer.parseInt(splited[i]);
        }
        return result;
    }

    private static int countRequiredSteps(Integer[][] stats, Integer id) {
        int result = 0;

        for (int day = 0; day < stats[0].length; day++) {
            int maxSteps = 0;

            for (int walker = 0; walker < stats.length; walker++) {
                maxSteps = Math.max(maxSteps, stats[walker][day]);
            }

            result += maxSteps - stats[id-1][day];
        }

        return result;
    }
}

