package guess;

import java.lang.Character;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;

/**
 * Guessing game.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class GuessingGame {
    
    private char[] secretPhrase;
    private char[] revealedPhrase;
    private LinkedList<Character> guesses;
    private int wrongGuesses;
    
    /**
     * Constructor.
     *
     * @param secret the secret phrase to use
     * @throws IllegalArgumentException if <code>secret</code> is shorter
     *     than 5 characters
     */
    public GuessingGame(String secret) {
        if (secret.length() < 5) {
            throw new IllegalArgumentException("Secret must be at least 5 characters");
        }
        this.secretPhrase = secret.toCharArray();
        this.revealedPhrase = new char[this.secretPhrase.length];
        for (int i = 0; i < this.secretPhrase.length; i++) {
            if (!Character.isLetter(this.secretPhrase[i])) {
                this.revealedPhrase[i] = this.secretPhrase[i];
            } else {
                this.revealedPhrase[i] = '_';
            }
        }
        this.guesses = new LinkedList<>();
        this.wrongGuesses = 0;
    }
    
    /**
     * Get the revealed phrase.
     */
    public String getRevealedPhrase() {
        return new String(this.revealedPhrase);
    }
       
    /**
     * Get the list of guesses.
     */
    public LinkedList<Character> getGuesses() {
        return new LinkedList<Character>(this.guesses);
    }
    
    /**
     * Get the number of wrong guesses.
     */
    public int getWrongGuesses() {
        return this.wrongGuesses;
    }
    
    /**
     * Get if the game is won.
     */
    public boolean isWon() {
        for (char c : this.revealedPhrase) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Get if the game is lost.
     */
    public boolean isLost() {
        return this.wrongGuesses >= 7;
    }
    
    /**
     * Get if the game is in progress.
     */
    public boolean inProgress() {
        return !(this.isWon() || this.isLost());
    }
    
    /**
     * Guess a character.
     *
     * @param c the character to guess
     * @return the result of the guess
     */
    public GuessResult guess(char c) {
        if (!Character.isLetter(c)) {
            return GuessResult.NOTLEGAL;
        }
        c = Character.toLowerCase(c);
        if (!this.guesses.contains(c)) {
            this.guesses.add(c);
            boolean found = false;
            for (int i = 0; i < this.secretPhrase.length; i++) {
                if (Character.toLowerCase(this.secretPhrase[i]) == c) {
                    this.revealedPhrase[i] = this.secretPhrase[i];
                    found = true;
                }
            }
            if (!found) {
                this.wrongGuesses++;
                return GuessResult.INCORRECT;
            } else {
                return GuessResult.CORRECT;
            }
            
        } else {
            return GuessResult.MULTIPLE;
        }
    }
    
    
    
}