import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        // Scanner to read input.
        Scanner scanner = new Scanner(System.in);
        // Get 1st input from scanner.
        while (true) {
            System.out.println("Skriv in en mening:");
            try {
                String sentence = scanner.nextLine();
                break;
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Felaktig input. Ange en mening.");
                scanner.nextLine();
            }
        }

        // Print 2nd instructions.
        System.out.println("Skriv in ett tecken:");
        // Get 2nd input from scanner.
        char character = scanner.next(".").charAt(0);

        // Count character occurrences.
        int charCount = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == character) {
                charCount++;
            }
        }

        // Print information.
        System.out.println(String.format("Meningen har totalt %s tecken.", sentence.length()));
        System.out.println(String.format("Tecknet %s förekommer %s gånger.", character, charCount));
        System.out.println(String.format("Första gången på indexplats %s.", sentence.indexOf(character)));
        System.out.println(String.format("Sista gången på indexplats %s.", sentence.lastIndexOf(character)));
    }
}
