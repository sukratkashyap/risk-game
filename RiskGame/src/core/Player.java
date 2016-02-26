package core;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description class for storing details and other related data about the
 * player
 */
public class Player {

    private final int _nodeColor;
    private int _numberOfArmies;
    private final String _name;
    private final PlayerType _playerType;

    /**
     *
     * @param name name of the player
     * @param playerType the type of player
     * @param color the color that represent the player
     * @param noOfArmies the number of armies in the field
     */
    public Player(String name, PlayerType playerType, int color, int noOfArmies) {
        _name = name;
        _playerType = playerType;
        _nodeColor = color;
        _numberOfArmies = noOfArmies;
    }

    public Player(String name, PlayerType playerType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @set number of the armies
     */
    public int setNoOfArmies(int numberOfArmies) {
        _numberOfArmies = numberOfArmies;
        return _numberOfArmies;

    }

    /**
     *
     * @return number of the armies
     */
    public int getNoOfArmies() {
        return _numberOfArmies;
    }

    /**
     *
     * @return color of the node
     */
    public int getColor() {
        return _nodeColor;
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
