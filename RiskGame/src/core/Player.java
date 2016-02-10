package core;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description class for storing details and other related data about the
 * player
 */
public class Player {

    private final String _name;

    /**
     *
     * @param name name of the player
     */
    public Player(String name) {
        _name = name;
    }

    /**
     *
     * @return name of the person
     */
    public String getName() {
        return _name;
    }
}
