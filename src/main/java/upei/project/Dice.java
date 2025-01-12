package upei.project;

/**
 * Represents a dice in the game.
 * The Dice class allows the rolling of a 6-sided dice.
 */
public class Dice {

    /**
     * Rolls a dice and returns a random number between 1 and 6.
     * @return a number between 1 and 6.
     */
    public int roll() {

        return (int) (Math.random() * 6) + 1;
    }
}
