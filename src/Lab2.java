import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        // Scanner to read input.
        Scanner scanner = new Scanner(System.in);

        // Get 1st input from scanner.
        String sentence;
        while (true) {
            // Print 1st instructions.
            System.out.println("Skriv in en mening:");
            try {
                sentence = scanner.nextLine();
                if (sentence.length() == 0) {
                    throw new java.lang.RuntimeException("Felaktig input.");
                }
                break;
            }
            catch (java.lang.RuntimeException e) {
                System.out.println("Felaktig input. Ange en mening.");
            }
        }

        // Get 2nd input from scanner.
        String input;
        char character;
        while (true) {
            // Print 2nd instructions.
            System.out.println("Skriv in ett tecken:");
            try {
                input = scanner.nextLine();
                if (input.length() == 1) {
                    character = input.charAt(0);
                }
                else {
                    throw new java.lang.RuntimeException("Felaktig input.");
                }
                break;
            }
            catch (java.lang.RuntimeException e) {
                System.out.println("Felaktig input. Ange ett tecken.");
            }
        }

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
