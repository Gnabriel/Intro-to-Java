import java.util.*;

/**
 * Text-based program which lets the user input a number of integers in
 * the terminal and then outputs the mean of the integers and how many of
 * the integers that are unique.
 * 
 * Assignment 3 in the course Inledande programmering med Java VT2021.
 *
 * @author Gabriel Ellebrink
 */
public class Lab3 {
    public static void main(String[] args) {
        // Scanner to read inputs.
        Scanner scanner = new Scanner(System.in);

        int integerCount = getIntegerCount(scanner);
        int[] integers = getIntegers(scanner, integerCount);
        printIntegersInfo(integerCount, integers);
    }

    /**
     * Gets an integer as input from the user.
     * @param scanner       Scanner object to receive inputs.
     * @param prompt        user instructions for the input.
     * @return              integer provided by the user.
     */
    private static int getIntegerInput(Scanner scanner, String prompt) {
        int integerInput;
        while (true) {
            // Print input instructions.
            System.out.println(prompt);
            try {
                // Get input.
                integerInput = scanner.nextInt();
                break;
            }
            // Catch error and print error message.
            catch (java.util.InputMismatchException e) {
                System.out.println("Ogiltigt värde. Ange ett heltal.");
                scanner.nextLine();
            }
        }
        return integerInput;
    }

    /**
     * Gets the number of integers that the user wants to provide.
     * @param scanner       Scanner object to receive inputs.
     * @return              integer quantity.
     */
    private static int getIntegerCount(Scanner scanner) {
        return getIntegerInput(scanner, "Hur många antal vill du ange:");
    }

    /**
     * Gets a specified number of integers as input from the user.
     * @param scanner           Scanner object to receive inputs.
     * @param integerCount      integer quantity.
     * @return                  all integers provided by the user.
     */
    public static int[] getIntegers(Scanner scanner, int integerCount) {
        int integer;
        int[] integers = new int[integerCount];
        for (int i = 0; i < integerCount; i++) {
            integer = getIntegerInput(scanner, "Ange heltal:");
            integers[i] = integer;
        }
        return integers;
    }

    /**
     * Gets the unique elements from an array of integers.
     * @param integerCount      integer quantity.
     * @param integers          array of integers.
     * @return                  unique integers in the array.
     */
    private static List<Integer> getUniqueIntegers(int integerCount, int[] integers) {
        boolean unique;
        List<Integer> uniqueIntegers = new ArrayList<>();
        for (int i = 0; i < integerCount; i++) {
            unique = true;
            for (int j = 0; j < integerCount; j++) {
                if (i != j && integers[i] == integers[j]) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                uniqueIntegers.add(integers[i]);
            }
        }
        return uniqueIntegers;
    }

    /**
     * Prints count, number of unique elements and mean of an array of integers..
     * @param integerCount      integer quantity.
     * @param integers          array of integers.
     */
    private static void printIntegersInfo(int integerCount, int[] integers) {
        // Print the count.
        System.out.println(String.format("Du angav %s tal.", integerCount));

        // Get and print the unique elements count.
        int uniqueIntegersCount = getUniqueIntegers(integerCount, integers).size();
        System.out.println(String.format("Varav %s tal är unika.", uniqueIntegersCount));

        // Compute and print the mean.
        float integersMean = (float)Arrays.stream(integers).sum() / integerCount;
        System.out.println(String.format("Medelvärdet för talen är %s.", integersMean));
    }
}
