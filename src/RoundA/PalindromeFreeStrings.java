package RoundA;

import java.util.*;

public class PalindromeFreeStrings {
    private static final Set<String> palindromes = new HashSet<>();
    private static final Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        fillWithPalindromes();

//        System.out.println(palindromes);
        System.out.println(isPossible("100???001"));
        System.out.println(isPossible("100??"));

//        try (Scanner scanner = new Scanner(System.in)) {
//            int numberOfCases = Integer.parseInt(scanner.nextLine());
//
//            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
//                String word = scanner.nextLine();
//                System.out.print("Case #" + caseNum + ": ");
//
//                if (word.length() <= 4) {
//                    System.out.println("POSSIBLE");
//                    continue;
//                }
//
//                if (isPossible(word)) {
//                    System.out.println("POSSIBLE");
//                } else {
//                    System.out.println("IMPOSSIBLE");
//                }
//            }
//        }
    }

    private static boolean isPossible(String word) {
        for (int i = 0, size = 5; i <= word.length()-size; i++) {
            String curWord = word.substring(i, i+size);

            if (palindromes.contains(curWord)) return false;

            if (visited.contains(curWord)) {
                continue;
            }
            visited.add(curWord);

            List<String> words = new ArrayList<>();
            generateWords("" , curWord, 0, words);
            System.out.println(words);

            for (String candidate : words) {
                if (palindromes.contains(candidate)) return false;
                visited.add(candidate);
            }
        }
        for (int i = 0, size = 6; i <= word.length()-size; i++) {
            String curWord = word.substring(i, i+size);

            if (palindromes.contains(curWord)) return false;

            if (visited.contains(curWord)) {
                continue;
            }
            visited.add(curWord);

            List<String> words = new ArrayList<>();
            generateWords("" , curWord, 0, words);
            System.out.println(words);

            for (String candidate : words) {
                if (palindromes.contains(candidate)) return false;
                visited.add(candidate);
            }
        }

        return true;
    }

    private static void generateWords(String curW, String word, int idx, List<String> words) {
        if (idx == word.length()) {
            words.add(curW);
            return;
        }

        if (word.charAt(idx) == '?') {
            generateWords(curW+"0", word, idx+1, words);
            generateWords(curW+"1", word, idx+1, words);
        } else {
            generateWords(curW+word.charAt(idx), word, idx+1, words);
        }
    }

    private static void fillWithPalindromes() {
        fillWithPalindromes5();
        fillWithPalindromes6();
    }
    private static void fillWithPalindromes5() {
        for (int i = 0; i < (int) Math.pow(2, 5); i++) {
            String curStr = String.format("%5s", Integer.toBinaryString(i)).replace(' ', '0');//Integer.toBinaryString(i);
            if (isPalindrome(curStr)) palindromes.add(curStr);
        }
    }
    private static void fillWithPalindromes6() {
        for (int i = 0; i < (int) Math.pow(2, 6); i++) {
            String curStr = String.format("%6s", Integer.toBinaryString(i)).replace(' ', '0');//Integer.toBinaryString(i);
            if (isPalindrome(curStr)) palindromes.add(curStr);
        }
    }

    private static boolean isPalindrome(String word) {
        int start = 0;
        int end = word.length()-1;

        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) return false;
        }

        return true;
    }
}
