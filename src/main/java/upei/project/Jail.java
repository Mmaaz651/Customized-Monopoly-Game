package upei.project;

/**
 * Represents a Jail place on the game board.
 * A Jail extends Place and marks the jail space on the board.
 */
public class Jail extends Place {
    private String name;

    /**
     * Constructor to initialize the jail with its name.
     * @param name The name of the jail.
     */
    public Jail(String name){
        super(name);
    }
}
