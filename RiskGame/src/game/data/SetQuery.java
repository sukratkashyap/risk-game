package game.data;

import game.core.Card;
import game.core.CardType;
import game.core.Constants;
import game.core.Country;
import game.core.DataFactory;
import game.core.Player;
import game.core.PlayerType;
import game.core.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description
 */
public class SetQuery {

    private final DataFactory _dataFactory;

    public SetQuery() {
        _dataFactory = DataFactory.getInstance();
    }

    /**
     *
     * @param name name of the player
     * @param playerType type of player whether neutral or main
     * @return result telling whether the operation was successful
     */
    public Result<Player> addPlayer(String name, PlayerType playerType) {
        Result<Player> result;
        Map<String, Player> playerMap = _dataFactory.getPlayerMap();

        int noOfArmies = playerType == PlayerType.MainPlayer
                ? Constants.INIT_UNITS_PLAYER : Constants.INIT_UNITS_NEUTRAL;

        Player player = new Player(name, playerType,
                Constants.COLOR_PLAYER[playerMap.size()], noOfArmies);
        if (playerMap.containsKey(name)) {
            result = new Result<>(false, "Player already exists!", null);
        } else {
            playerMap.put(player.getName(), player);
            result = new Result<>(true, "", player);
        }
        return result;
    }
    
    /**
     *
     * @param playerName
     * @param noOfCardsexchanged
     * @return
     */
    public void exchangeInfantryCard(String playerName, int noOfCardsexchanged) {
        
        

        
    }
    

    /**
     *
     * @param playerName
     * @return
     */
    public List<Card> assignRandomCardsToPlayer(String playerName, int noOfCards) {
        Player player = _dataFactory.getPlayerMap().get(playerName);

        List<Card> cardList = new ArrayList<>(noOfCards);
        for (int i = 0; i < noOfCards; i++) {
            Card card = _dataFactory.getCardMap().entrySet()
                    .stream()
                    .map((cardEntry) -> cardEntry.getValue())
                    .filter((cardEntry) -> cardEntry.getCardType() != CardType.Wild
                            && cardEntry.isWithPlayer() == false)
                    .findAny()
                    .get();
            player.addCard(card);
            cardList.add(card);
        }

        return cardList;
    }

    public void assignCountryByName(String playerName, String countryName) {
        Country country = _dataFactory.getCountryNameMap().get(countryName);
        Player player = _dataFactory.getPlayerMap().get(playerName);

        country.setOwnerOfTheCountry(player);
        country.addNoOfArmyInCountry(player.removeNoOfArmies(1));
    }

    /**
     *
     * @param playerName
     * @param abbreviation
     * @param units
     * @return
     */
    public Result addUnitToCountry(String playerName, String abbreviation, int units) {
        Country country = _dataFactory.getCountryAbbreviationMap().get(abbreviation);
        Player player = _dataFactory.getPlayerMap().get(playerName);
        country.addNoOfArmyInCountry(player.removeNoOfArmies(units));
        return new Result(true, "");
    }

    /**
     *
     * @param playerName
     * @param countryName
     * @param units
     * @return
     */
    public Result removeUnitToCountry(String playerName, String countryName, int units) {
        Country country = _dataFactory.getCountryNameMap().get(countryName);
        Player player = _dataFactory.getPlayerMap().get(playerName);
        player.addNoOfArmies(country.removeNoOfArmyInCountry(units));
        return new Result(true, "");
    }

    /**
     *
     * @param playerName
     * @return
     */
    public Result removePlayer(String playerName) {
        if (_dataFactory.getPlayerMap().containsKey(playerName)) {
            _dataFactory.getPlayerMap().remove(playerName);
        }
        return new Result(true, "");
    }

}
