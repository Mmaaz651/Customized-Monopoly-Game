package upei.project;

/**
 * Represents a general place on the game board.
 * The Place class holds a name that is common for all places in the game.
 */
public class Place {
    protected String name;

    /**
     * Constructor to initialize the place with a name.
     * @param name The name of the place.
     */
    public Place(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the place.
     * @return the name of the place.
     */
    public String getName() {
        return name;
    }
}
