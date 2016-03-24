package game.data;

import game.core.Card;
import game.core.CardType;
import game.core.Constants;
import game.core.Country;
import game.core.CountryIndex;
import game.core.DataFactory;
import game.core.Player;
import game.core.PlayerType;
import game.core.Result;
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
     * @param noOfArmies no of armies the player should initially have
     * @param playerType type of player whether neutral or main
     * @return result telling whether the operation was successful
     */
    public Result<Player> addPlayer(String name, int noOfArmies, PlayerType playerType) {
        Result<Player> result;
        Map<String, Player> playerMap = _dataFactory.getPlayerMap();

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

    public Card assignRandomCardToPlayer(String playerName) {
        Player player = _dataFactory.getPlayerMap().get(playerName);
        Card card = _dataFactory.getCardMap().entrySet()
                .stream()
                .filter((cardEntry) -> cardEntry.getValue().getCardType() == CardType.Territory)
                .filter((cardEntry) -> cardEntry.getValue().getWithPlayer() == false)
                .findAny().get().getValue();
        player.addCard(card);
        card.setWithPlayer(true);

        //setting the country to the owner
        Country country = _dataFactory.getCountryNameMap().get(card.getCountryName());
        country.setOwnerOfTheCountry(player);
        country.setArmyInCountry(1);
        player.setNoOfArmies(player.getNoOfArmies() - 1);
        return card;
    }

    public Result addUnitToCountry(String playerName, String countryName, int units) {
        Country country = _dataFactory.getCountryNameMap().get(countryName);
        Player player = _dataFactory.getPlayerMap().get(playerName);
        country.setArmyInCountry(country.getArmyInCountry() + units);
        player.setNoOfArmies(player.getNoOfArmies() - units);
        return new Result(true, "");
    }

    public void removePlayer(String playerName) {
        _dataFactory.getPlayerMap().containsKey(playerName);
        _dataFactory.getPlayerMap().remove(playerName);
    }
}
