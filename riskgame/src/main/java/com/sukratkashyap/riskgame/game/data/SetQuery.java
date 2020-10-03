package com.sukratkashyap.riskgame.game.data;

import game.core.Card;
import game.core.CardType;
import game.core.Constants;
import game.core.Country;
import game.core.Player;
import game.core.PlayerType;
import game.core.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Result<Integer> exchangeInfantryCard(String playerName, String input) {
        Result<Integer> result = null;
        if (input.length() == 3) {
            Player player = playerByName(playerName);
            if (validateExchangeCardInput(input)) {
                List<Card> cardList = new ArrayList<>();
                for (int i = 0; i < input.length(); i++) {
                    String s = Character.toString(input.charAt(i));
                    Optional<Card> card = player.getCardList()
                            .stream()
                            .filter((c) -> c.toString().equalsIgnoreCase(s))
                            .findAny();
                    if (card.isPresent()) {
                        cardList.add(card.get());
                    } else {
                        result = new Result(false, "Card with insignia " + s + " not present");
                        break;
                    }
                }
                if (cardList.size() == 3) {
                    for (Card card : cardList) {
                        player.removeCard(card);
                    }
                    int addNoOfArmies = player.addNoOfArmies(getAndMoveGoldenCavalry());
                    result = new Result(true, "", addNoOfArmies);
                }
            } else {
                result = new Result(false, "Cards must either be all same or all different");
            }
        } else {
            result = new Result(false, "Only sets of 3 cards can be exchanged");
        }
        return result;
    }

    private boolean validateExchangeCardInput(String input) {
        boolean isValid = true;
        int totalCards = 3;
        int noInfantry = 0;
        int noCavalry = 0;
        int noArtillery = 0;
        int noWild = 0;
        //checking for same input
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'I') {
                noInfantry++;
            } else if (c == 'C') {
                noCavalry++;
            } else if (c == 'A') {
                noArtillery++;
            } else if (c == 'W') {
                noWild++;
            } else {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            if (!(noInfantry + noWild == totalCards
                    || noCavalry + noWild == totalCards
                    || noArtillery + noWild == totalCards
                    || (noInfantry == noCavalry || noInfantry == noArtillery || noCavalry == noArtillery))) {
                isValid = false;
            }
        }
        return isValid;
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
            Optional<Card> randomCard = cardMap().entrySet()
                    .parallelStream()
                    .map((entry) -> entry.getValue())
                    .filter((card) -> {
                        boolean wild = true;
                        if (!wildAlso) {
                            wild = card.getCardType() != CardType.Wild;
                        }
                        return card.isWithPlayer() == false && wild;
                    })
                    .findAny();
            if (randomCard.isPresent()) {
                player.addCard(randomCard.get());
                cardList.add(randomCard.get());
            }
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
