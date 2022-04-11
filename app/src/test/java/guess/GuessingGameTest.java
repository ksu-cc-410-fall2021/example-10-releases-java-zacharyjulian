package guess;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.IllegalArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


/** 
 * The Guessing Game Test class.
 *
 * <p>This is the test class for the Guessing Game program.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class GuessingGameTest {
    
    @Test 
    public void testGuessesShouldBeEmptyAtStartOfGame() {
        GuessingGame game = new GuessingGame("secret");
        // assertEquals(0, game.getGuesses().size());
        assertTrue(game.getGuesses().isEmpty());
        // assertThat(game.getGuesses(), is(empty()));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "a", "aa", "aaa", "aaaa"})
    public void testSecretPhraseMustBeAtLeast5CharactersLong(String secret) {
        assertThrows(IllegalArgumentException.class, () -> new GuessingGame(secret));
    }
    
    @Test
    public void testWrongGuessesShouldStartAtZero() {
        GuessingGame game = new GuessingGame("secret");
        assertEquals(0, game.getWrongGuesses());
    }
    
    @Test
    public void testNewGameShouldNotBeWon() {
        GuessingGame game = new GuessingGame("secret");
        assertFalse(game.isWon());
    }
    
    @Test
    public void testNewGameShouldNotBeLost() {
        GuessingGame game = new GuessingGame("secret");
        assertFalse(game.isLost());
    }
    
    @Test
    public void testNewGameShouldBeInProgress() {
        GuessingGame game = new GuessingGame("secret");
        assertTrue(game.inProgress());
    }
    
    @ParameterizedTest
    @CsvSource({"Hello World,_____ _____",
                "Don't Forget Your Towel,___'_ ______ ____ _____",
                "John Jacob Jingleheimer Schmidt,____ _____ ____________ _______"})
    public void testSecretPhraseShouldEncodeProvidedSecret(String secret, String revealed) {
        GuessingGame game = new GuessingGame(secret);
        assertEquals(revealed, game.getRevealedPhrase());
    }
    
    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(chars = {'k', 's', 'u'})
    public void testGuessesShouldBeAddedToPreviousGuesses(char guess) {
        GuessingGame game = new GuessingGame("secret");
        game.guess(guess);
        assertTrue(game.getGuesses().contains(guess));
    }
    
    @Test
    public void testWrongGuessesShouldIncrementWrongGuesses() {
        GuessingGame game = new GuessingGame("secret");
        game.guess('a');
        assertEquals(1, game.getWrongGuesses());
        game.guess('b');
        assertEquals(2, game.getWrongGuesses());
        game.guess('d');
        assertEquals(3, game.getWrongGuesses());
        game.guess('f');
        assertEquals(4, game.getWrongGuesses());
        game.guess('g');
        assertEquals(5, game.getWrongGuesses());
        game.guess('h');
        assertEquals(6, game.getWrongGuesses());
    }
    
    @Test
    public void testSevenWrongGuessesShouldEndGame() {
        GuessingGame game = new GuessingGame("secret");
        game.guess('a');
        game.guess('b');
        game.guess('d');
        game.guess('f');
        game.guess('g');
        game.guess('h');
        game.guess('i');
        assertFalse(game.inProgress());
    }
    
    @Test
    public void testSevenWrongGuessesShouldLose() {
        GuessingGame game = new GuessingGame("secret");
        game.guess('a');
        game.guess('b');
        game.guess('d');
        game.guess('f');
        game.guess('g');
        game.guess('h');
        game.guess('i');
        assertTrue(game.isLost());
    }
    
    @Test
    public void testCorrectGuessesShouldEndGame() {
        GuessingGame game = new GuessingGame("secret");
        game.guess('s');
        game.guess('e');
        game.guess('c');
        //game.guess('e');
        game.guess('r');
        game.guess('t');
        assertFalse(game.inProgress());
    }
    
    @Test
    public void testCorrectGuessesShouldWin() {
        GuessingGame game = new GuessingGame("secret");
        game.guess('s');
        game.guess('e');
        game.guess('c');
        //game.guess('e');
        game.guess('r');
        game.guess('t');
        assertTrue(game.isWon());
    }
    
    @Test
    public void testGuessingSameCharacterShouldNotCount() {
        GuessingGame game = new GuessingGame("secret");
        game.guess('s');
        assertEquals(1, game.getGuesses().size());
        game.guess('s');
        assertEquals(1, game.getGuesses().size());
        game.guess('a');
        assertEquals(1, game.getWrongGuesses());
        game.guess('a');
        assertEquals(1, game.getWrongGuesses());
    }
    
    @ParameterizedTest
    @ValueSource(chars = {'!', '3', ' '})
    public void testGuessShouldBeaLetter(char guess) {
        GuessingGame game = new GuessingGame("secret");
        assertEquals(GuessResult.NOTLEGAL, game.guess(guess));
    }
    
    @ParameterizedTest
    @ValueSource(chars = {'s', 'e', 'c', 'r', 't'})
    public void testCorrectGuessShouldReturnCorrect(char guess) {
        GuessingGame game = new GuessingGame("secret");
        assertEquals(GuessResult.CORRECT, game.guess(guess));
    }
    
    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'd', 'f', 'g'})
    public void testIncorrectGuessShouldReturnIncorrect(char guess) {
        GuessingGame game = new GuessingGame("secret");
        assertEquals(GuessResult.INCORRECT, game.guess(guess));
    }
    
    /**
     * Test Method.
     */
    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e'})
    public void testMultipleGuessShouldReturnMultiple(char guess) {
        GuessingGame game = new GuessingGame("secret");
        game.guess(guess);
        assertEquals(GuessResult.MULTIPLE, game.guess(guess));
    }
    
    @Test
    public void testGuessesShouldBeCaseInsensitive() {
        GuessingGame game = new GuessingGame("secret");
        assertEquals(GuessResult.CORRECT, game.guess('s'));
        assertEquals(GuessResult.CORRECT, game.guess('E'));
    }
    
}