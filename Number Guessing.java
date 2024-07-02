import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Welcome message and range input
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.print("Enter the minimum number of the range: ");
        int minRange = scanner.nextInt();
        System.out.print("Enter the maximum number of the range: ");
        int maxRange = scanner.nextInt();

        // Generate random number within the specified range
        int secretNumber = random.nextInt(maxRange - minRange + 1) + minRange;

        // Number of attempts input
        System.out.print("Enter the maximum number of attempts: ");
        int maxAttempts = scanner.nextInt();

        // Initialize attempt counter
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Guess the number between " + minRange + " and " + maxRange + ". You have " + maxAttempts + " attempts.");

        // Game loop
        while (attempts < maxAttempts) {
            System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < secretNumber) {
                System.out.println("Too low!");
            } else if (guess > secretNumber) {
                System.out.println("Too high!");
            } else {
                guessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the correct number.");
                break;
            }
        }

        // End of game messages
        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + secretNumber + ".");
        }

        System.out.println("Game Over. You made " + attempts + " attempts.");

        scanner.close();
    }
}