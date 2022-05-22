package RoundC;

import java.util.Scanner;

public class NewPassword {
    private static final char[] specChars = {'#', '@', '*', '&'};

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfCases = scanner.nextInt();

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                int length = scanner.nextInt();
                String password = "";
                do {
                    password = scanner.nextLine();
                } while (password.length() != length);


                String result = getNewPassword(password);

                System.out.println("Case #" + caseNum + ": " + result);
            }
        }
    }

    private static String getNewPassword(String oldPassword) {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digits = false;
        boolean specialChars = false;

        char[] letters = oldPassword.toCharArray();
        for (char letter : letters) {
            if (Character.isUpperCase(letter)) {
                upperCase = true;
            } else if (Character.isLowerCase(letter)) {
                lowerCase = true;
            } else if (Character.isDigit(letter)) {
                digits = true;
            } else if (isSpecialCharacter(letter)) {
                specialChars = true;
            }
        }

        String result = new String(letters);
        if (!upperCase) {
            result = result + "A";
        }
        if (!lowerCase) {
            result = result + "a";
        }
        if (!digits) {
            result = result + "0";
        }
        if (!specialChars) {
            result = result + specChars[0];
        }
        if (result.length() < 7) {
            for (int i = result.length(); i < 7; i++) {
                result = result + "a";
            }
        }

        return result;
    }

    private static boolean isSpecialCharacter(char letter) {
        for (char ch : specChars) {
            if (ch == letter) return true;
        }
        return false;
    }
}
