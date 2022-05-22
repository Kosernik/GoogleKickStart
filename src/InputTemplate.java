import java.util.Scanner;

public class InputTemplate {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int numberOfCases = scanner.nextInt();

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();

                int[] candyBags = new int[n];

                for (int i = 0; i < n; i++) {
                    candyBags[i] = scanner.nextInt();
                }

                int result = -1;

                System.out.println("Case #" + caseNum + ": " + result);
            }
        }
    }
}
