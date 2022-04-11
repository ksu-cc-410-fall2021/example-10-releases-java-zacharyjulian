package guess;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Main class for guessing game.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class Main {
    
    /**
     * Main method.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();
        List<String> phrases = null;
        try {
            phrases = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int index = random.nextInt(phrases.size());
        GuessingGame game = new GuessingGame(phrases.get(index));
        Renderer.clearScreen();
        Renderer.printHello();
        
        while (game.inProgress()) {
            Renderer.printLobster(game.getWrongGuesses());
            Renderer.printPhrase(game.getRevealedPhrase());
            Renderer.printGuesses(game.getGuesses());
            char c = Renderer.getGuess();
            Renderer.clearScreen();
            Renderer.printMessage(game.guess(c));
        }
        if (game.isWon()) {
            Renderer.printWin();
        }
        if (game.isLost()) {
            Renderer.printLoss();
        }
    }
}