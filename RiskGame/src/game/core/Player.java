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
    private List<Card> _cardInHand = new ArrayList<>();

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
    }
    
    public Player(String name, PlayerType playerType) {
        _name = name;
        _playerType = playerType;
        _nodeColor = Color.RED;
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
    
    public List<Card> getCardList() {
        return Collections.unmodifiableList(_cardInHand);
    }
    
    public void addCard(Card card) {
        _cardInHand.add(card);
    }
    
    public void removeCard(Card card) {
        _cardInHand.removeIf((c) -> c.getCountryName().equals(card.getCountryName()));
    }
    
    public void setOrder(int order) {
        _order = order;
    }
    
    public int getOrder() {
        return _order;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>");
        builder.append("Name: ").append(_name).append("<br>");
        builder.append("No of armies: ").append(_numberOfArmies).append("<br>");
        builder.append("Cards: ").append(_cardInHand.stream().map((card) -> card.getCountryName()).collect(Collectors.toList()));
        builder.append("</body></html>");
        return builder.toString();
    }
}
