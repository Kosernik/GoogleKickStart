package CentauriPrime;

import java.util.*;

public class Solution {
    private static final Set<Character> aliceLetters = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));
    private static final Set<Character> nobodyLetters = new HashSet<>(Arrays.asList('Y', 'y'));

    private static String getRuler(String kingdom) {
        String ruler;

        if (aliceLetters.contains(kingdom.charAt(kingdom.length()-1))) {
            ruler = "Alice";
        } else if (nobodyLetters.contains(kingdom.charAt(kingdom.length()-1))) {
            ruler = "nobody";
        } else {
            ruler = "Bob";
        }
        return ruler;
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int T = in.nextInt();

            for (int t = 1; t <= T; ++t) {
                String kingdom = in.next();
                System.out.println(
                        "Case #" + t + ": " + kingdom + " is ruled by " + getRuler(kingdom) + ".");
            }
        }
    }
}

