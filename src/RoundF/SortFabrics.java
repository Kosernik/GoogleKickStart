package RoundF;

import java.util.Arrays;
import java.util.Scanner;

public class SortFabrics {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            int numberOfCases = Integer.parseInt(scanner.nextLine());

            for (int caseNum = 1; caseNum <= numberOfCases; caseNum++) {
                int numberOfFabrics = Integer.parseInt(scanner.nextLine());

                Fabric[] fabrics = new Fabric[numberOfFabrics];
                for (int i = 0; i < numberOfFabrics; i++) {
                    String line = scanner.nextLine();
                    String[] splt = line.split("\\s+");
                    String color = splt[0];
                    int durability = Integer.parseInt(splt[1]);
                    int id = Integer.parseInt(splt[2]);

                    fabrics[i] = new Fabric(color, durability, id);
                }

                Arrays.sort(fabrics, (a, b) -> a.color.equals(b.color) ? Integer.compare(a.id, b.id) : a.color.compareTo(b.color));

                int[] ids = new int[numberOfFabrics];
                int i = 0;
                for (Fabric f : fabrics) {
                    ids[i++] = f.id;
                }

                Arrays.sort(fabrics, (a, b) -> a.durability == b.durability ? Integer.compare(a.id, b.id) : Integer.compare(a.durability, b.durability));

                int result = 0;

                for (i = 0; i < fabrics.length; i++) {
                    if (fabrics[i].id == ids[i]) result++;
                }

                System.out.println("Case #" + caseNum + ": " + result);
            }
        }
    }

    public static class Fabric {
        String color;
        int durability;
        int id;

        Fabric(String color, int durability, int id) {
            this.color = color;
            this.durability = durability;
            this.id = id;
        }
    }
}
