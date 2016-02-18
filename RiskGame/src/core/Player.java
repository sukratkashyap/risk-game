package core;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description class for storing details and other related data about the
 * player
 */
public class Player {

    private final String _name;
    private final PlayerType _playerType;

    /**
     *
     * @param name name of the player
     * @param playerType the type of player
     */
    public Player(String name, PlayerType playerType) {
        _name = name;
        _playerType = playerType;
    }

    /**
     *
     * @return name of the person
     */
    public String getName() {
        return _name;
    }

    /**
     *
     * @return the player type
     */
    public PlayerType getPlayerType() {
        return _playerType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(_name);
        return builder.toString();
    }
}
