package guess;

import java.util.LinkedList;

/**
 * Class to render the guessing game.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class Renderer {
    
    /**
     * Print hello message.
     */
    public static void printHello() {
        System.out.println("Welcome to Lobster!");
    }
    
    /**
     * Print the lobster.
     *
     * @param guesses the number of wrong guesses
     */
    public static void printLobster(int guesses) {
        String[] lobster = new String[]{"|~~~~~~~~|",
                                        "| (]  [) |",
                                        "|  \\oo/  |",
                                        "| >{^^}< |",
                                        "| >{^^}< |",
                                        "|  {^^}  |",
                                        "|  {^^}  |",
                                        "|  /MM\\  |",
                                        "|________|"};
        String[] empty = new String[]{"|~~~~~~~~|",
                                      "|        |",
                                      "|        |",
                                      "|        |",
                                      "|        |",
                                      "|        |",
                                      "|        |",
                                      "|        |",
                                      "|________|"};
        for (int i = 0; i <= guesses; i++) {
            System.out.println(lobster[i]);
        }
        for (int i = guesses + 1; i < empty.length; i++) {
            System.out.println(empty[i]);
        }
    }
    
    /**
     * Print a result message.
     *
     * @param result the result to print
     */
    public static void printMessage(GuessResult result) {
        switch (result) {
            case CORRECT:
                System.out.println("Great Guess!");
                break;
            case INCORRECT:
                System.out.println("Oh no! Things are heating up!");
                break;
            case MULTIPLE:
                System.out.println("You've already guessed that letter!");
                break;
            case NOTLEGAL:
                System.out.println("That's not a letter!");
                break;
            default:
                System.out.println("");
                break;
        }
    }
    
    /**
     * Print the phrase to guess.
     *
     * @param phrase the phrase to guess
     */
    public static void printPhrase(String phrase) {
        System.out.println("Your phrase to guess:");
        System.out.println(phrase);
    }
    
    /**
     * Print the list of guesses.
     *
     * @param guesses the list of guesses.
     */
    public static void printGuesses(LinkedList<Character> guesses) {
        System.out.println("Your previous guesses are:");
        System.out.println(guesses.toString());
    }
    
    /**
     * Get a guess from the user.
     *
     * @return the guess
     */
    public static char getGuess() {
        System.out.println("Enter a letter to guess: ");
        try {
            char c = (char) System.in.read();
            System.in.read(new byte[System.in.available()]);
            return c;
        } catch (Exception e) {
            return ' ';
        }
    }
    
    /**
     * Clear the screen.
     */
    public static void clearScreen() {
        System.out.print("\033\143");
    }
    
    /**
     * Print the winning message.
     */
    public static void printWin() {
        System.out.println("You Won!");
    }
    
    /**
     * Print the losing message.
     */
    public static void printLoss() {
        System.out.println("You Lost!");
    }
}