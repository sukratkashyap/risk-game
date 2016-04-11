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
public class SetQuery extends BaseQuery {

    /**
     *
     * @param name name of the player
     * @param playerType type of player whether neutral or main
     * @return result telling whether the operation was successful
     */
    public Result<Player> addPlayer(String name, PlayerType playerType) {
        Result<Player> result;
        int noOfArmies = playerType == PlayerType.MainPlayer
                ? Constants.INIT_UNITS_PLAYER : Constants.INIT_UNITS_NEUTRAL;

        Player player = new Player(name, playerType,
                Constants.COLOR_PLAYER[playerMap().size()], noOfArmies);
        if (playerMap().containsKey(name)) {
            result = new Result<>(false, "Player already exists!", null);
        } else {
            playerMap().put(player.getName(), player);
            result = new Result<>(true, "", player);
        }
        return result;
    }

    /**
     *
     * @param playerName
     * @param input
     * @return
     */
    public Result exchangeInfantryCard(String playerName, String input) {
        Result result = null;
        String[] cardTypes = input.toLowerCase().split("");
        Player player = playerByName(playerName);
        if (cardTypes.length == 3) {

        } else {
            result = new Result(false, "3 Cards needed!");
        }
        return result;
    }

    /**
     *
     * @param playerName
     * @param noOfCards
     * @param wildAlso
     * @return
     */
    public List<Card> assignRandomCardsToPlayer(String playerName, int noOfCards, boolean wildAlso) {
        Player player = playerByName(playerName);

        List<Card> cardList = new ArrayList<>(noOfCards);
        for (int i = 0; i < noOfCards; i++) {
            Card randomCard = cardMap().entrySet()
                    .parallelStream()
                    .map((entry) -> entry.getValue())
                    .filter((card) -> {
                        boolean wild = false;
                        if (!wildAlso) {
                            wild = card.getCardType() != CardType.Wild;
                        }
                        return card.isWithPlayer() == false && wild;
                    })
                    .findAny()
                    .get();
            player.addCard(randomCard);
            cardList.add(randomCard);
        }
        return cardList;
    }

    public void assignCountryByName(String playerName, String countryName) {
        Country country = countryByName(countryName);
        Player player = playerByName(playerName);
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
        Country country = countryByAbb(abbreviation);
        Player player = playerByName(playerName);
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
        Country country = countryByName(countryName);
        Player player = playerByName(playerName);
        player.addNoOfArmies(country.removeNoOfArmyInCountry(units));
        return new Result(true, "");
    }

    /**
     *
     * @param playerName
     * @return
     */
    public Result removePlayer(String playerName) {
        if (playerMap().containsKey(playerName)) {
            playerMap().remove(playerName);
        }
        return new Result(true, "");
    }

}
