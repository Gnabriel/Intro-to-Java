import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        // Scanner to read input.
        Scanner scanner = new Scanner(System.in);
        // Print instructions.
        System.out.println("Skriv in en hastighet i knop:");
        // Get input from scanner.
        String input = scanner.nextLine();
        // Convert to km/h.
        double kmh = 1.852 * Float.parseFloat(input);
        // Print answer.
        System.out.println(input + " knop motsvarar " + kmh + " km/h");
        // Close scanner.
        scanner.close();
    }
}
