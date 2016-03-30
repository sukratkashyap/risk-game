package game.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description class for storing details and other related data about the
 * player
 */
public class Player {
    
    private final String _name;
    private final PlayerType _playerType;
    private final Color _nodeColor;
    private int _numberOfArmies;
    private int _order;
    private List<Card> _cardInHand;

    /**
     *
     * @param name name of the player
     * @param playerType the type of player
     * @param color the color that represent the player
     * @param noOfArmies the number of armies in the field
     */
    public Player(String name, PlayerType playerType, Color color, int noOfArmies) {
        _name = name;
        _playerType = playerType;
        _nodeColor = color;
        _numberOfArmies = noOfArmies;
        _cardInHand = new ArrayList<>();
    }

    /**
     *
     * @param noOfArmies no of armies to be added
     * @return the no of army added
     */
    public int addNoOfArmies(int noOfArmies) {
        _numberOfArmies += noOfArmies;
        return noOfArmies;
    }

    /**
     *
     * @param noOfArmies no of armies to be subtracted
     * @return the number of armies removed
     */
    public int removeNoOfArmies(int noOfArmies) {
        _numberOfArmies -= noOfArmies;
        return noOfArmies;
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
    public Color getColor() {
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

    /**
     *
     * @return gets the list of card that the player currently has
     */
    public List<Card> getCardList() {
        return _cardInHand.stream().collect(Collectors.toList());
    }

    /**
     *
     * @param card add the card to the player
     */
    public void addCard(Card card) {
        card.setWithPlayer(true);
        _cardInHand.add(card);
    }

    /**
     *
     * @param card removes the card from the player
     * @return
     */
    public boolean removeCard(Card card) {
        boolean isRemoved = _cardInHand
                .removeIf((c) -> c.getCountryName().equals(card.getCountryName()));
        if (isRemoved == true) {
            card.setWithPlayer(false);
        }
        return isRemoved;
    }

    /**
     * Sets the order in which the player will have their turns
     *
     * @param order
     */
    public void setOrder(int order) {
        _order = order;
    }

    /**
     *
     * @return get the order
     */
    public int getOrder() {
        return _order;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>");
        builder.append("Name: ").append(_name).append("<br>");
        builder.append("No of armies: ").append(_numberOfArmies).append("<br>");
        builder.append("</body></html>");
        return builder.toString();
    }
}
