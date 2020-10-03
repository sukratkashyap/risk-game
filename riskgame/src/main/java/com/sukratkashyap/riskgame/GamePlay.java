package riskgame;

import game.core.Card;
import game.core.Constants;
import game.core.Continent;
import game.core.Country;
import game.core.Dice;
import game.core.Player;
import game.core.PlayerType;
import game.core.Result;
import game.data.GetQuery;
import game.data.SetQuery;
import game.data.Validations;
import game.graphic.GUI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description
 */
public class GamePlay {

    private GUI _gui;
    private SetQuery sq = new SetQuery();
    private GetQuery gq = new GetQuery();

    public GamePlay(GUI gui) {
        _gui = gui;
    }

    public void getPlayerNameFromUser() {
        _gui.refresh();

        //Adding Main player
        for (int i = 0; i < Constants.NUM_PLAYERS; i++) {
            String question = "Enter Player " + (i + 1) + " name";
            String playerName = _gui.getInputFromUser(question,
                    new Validations.RequiredAndNotEqualTo(gq.getPlayerNameList()));
            sq.addPlayer(playerName, PlayerType.MainPlayer);
        }

        //adding neutrals
        for (int i = 0; i < Constants.NUM_NEUTRALS; i++) {
            sq.addPlayer("Neutral_" + (i + 1), PlayerType.NeutralPlayer);
        }
        _gui.refresh();
    }

    public void assignTerritoryCard() {
        _gui.addResult("Assigning territories randomly..!!");
        gq.getPlayerListByType(PlayerType.MainPlayer).stream()
                .forEach((player) -> {
                    List<Card> cardList = sq.assignRandomCardsToPlayer(player.getName(), Constants.INIT_COUNTRIES_PLAYER, false);
                    cardList.stream().forEach((card) -> {
                        sq.assignCountryByName(player.getName(), card.getCountryName());
                    });
                });
        gq.getPlayerListByType(PlayerType.NeutralPlayer).stream()
                .forEach((player) -> {
                    List<Card> cardList = sq.assignRandomCardsToPlayer(player.getName(), Constants.INIT_COUNTRIES_NEUTRAL, false);
                    cardList.stream().forEach((card) -> {
                        sq.assignCountryByName(player.getName(), card.getCountryName());
                    });
                });

        gq.getPlayerList().stream()
                .forEach((player) -> {
                    player.getCardList().stream()
                            .forEach((card) -> player.removeCard(card));
                });
        _gui.refresh();
    }

    public List<Player> rollDice() {
        _gui.addResult("Rolling dice for who goes first!");
        List<Player> mainPlayerList = gq.getPlayerListByType(PlayerType.MainPlayer);
        while (true) {
            _gui.addResult("Rolling dice for " + mainPlayerList.get(0).getName());
            int rollForPlayer1 = Dice.roll();
            _gui.addResult("Dice Rolled -> " + rollForPlayer1);

            _gui.addResult("Rolling dice for " + mainPlayerList.get(1).getName());
            int rollForPlayer2 = Dice.roll();
            _gui.addResult("Dice Rolled -> " + rollForPlayer2);

            if (rollForPlayer1 > rollForPlayer2) {
                _gui.addResult("Player " + mainPlayerList.get(0).getName() + " wins");
                mainPlayerList.get(0).setOrder(0);
                mainPlayerList.get(1).setOrder(1);
                break;
            } else if (rollForPlayer1 < rollForPlayer2) {
                _gui.addResult("Player " + mainPlayerList.get(1).getName() + " wins");
                mainPlayerList.get(0).setOrder(1);
                mainPlayerList.get(1).setOrder(0);
                break;
            }

            _gui.addResult("Its a draw! Rolling again!" + rollForPlayer2);

        }
        _gui.refresh();
        return gq.getOrderedMainPlayerList();
    }

    public void setInforcements() {
        _gui.addResult("Set your Inforcements..!!");
        List<Player> mainPlayerList = gq.getOrderedMainPlayerList();
        List<Player> neutralPlayerList = gq.getPlayerListByType(PlayerType.NeutralPlayer);

        for (int i = 0; i < Constants.INIT_COUNTRIES_PLAYER; i++) {
            mainPlayerList.forEach((player) -> {
                _gui.addResult("Player " + player.getName() + " turn");
                String countryAbbrev = _gui.getInputFromUser("Where do you want to place your 3 unit army",
                        new Validations.RequiredAndEqualTo(gq.getCountryAbbreviationListByPlayerName(player.getName())));
                sq.addUnitToCountry(player.getName(), countryAbbrev.toUpperCase(), 3);
                _gui.refresh();
                neutralPlayerList.stream()
                        .forEach((neutral) -> {
                            String question = "Where do you want to place your 1 unit army of " + neutral.getName();
                            String neutralCountryAbbrev = _gui.getInputFromUser(question,
                                    new Validations.RequiredAndEqualTo(gq.getCountryAbbreviationListByPlayerName(neutral.getName())));
                            sq.addUnitToCountry(neutral.getName(), neutralCountryAbbrev.toUpperCase(), 1);

                            _gui.refresh();
                        });
            });
        }

        _gui.refresh();
    }

    public void setInforcements(Player player) {
        _gui.addResult("Set Re-Inforcements..!!");
        _gui.addResult("Player " + player.getName() + " turn");
        while (player.getNoOfArmies() > 0) {
            String countryAbbrev = _gui.getInputFromUser("Where do you want to place your reinforcements?",
                    new Validations.RequiredAndEqualTo(gq.getCountryAbbreviationListByPlayerName(player.getName())));
            String numberOfArmies = _gui.getInputFromUser("How many armies you want to place",
                    new Validations.RequiredNumberFieldAndEqualTo(1, player.getNoOfArmies()));
            sq.addUnitToCountry(player.getName(), countryAbbrev.toUpperCase(), Integer.parseInt(numberOfArmies));
            _gui.refresh();
        }
        _gui.refresh();
    }

    public void giveReInforcements(Player player) {
        _gui.refresh();
        List<String> countryNameListByPlayerName = gq.getCountryNameListByPlayerName(player.getName());
        int noOfReInforcements = countryNameListByPlayerName.size() / 3;

        for (Continent continent : gq.getContinentList()) {
            List<Country> countryList = gq.getCountryListByPlayerNameAndContinentId(player.getName(),
                    continent.getContinentId());
            if (countryList.size() == continent.getNoOfTerritories()) {
                noOfReInforcements += continent.getContinentValue();
            }
        }
        player.addNoOfArmies(noOfReInforcements);
        _gui.addResult(String.format("Player %1$s receives %2$d reinforcements", player.getName(), noOfReInforcements));
        _gui.refresh();
    }

    public void exchangeCards(Player player) {
        Result<Integer> result;
        do {
            String input = _gui.getInputFromUser("Do you want to exchange territory cards else skip? (enter without space or commas)\n "
                    + "eg. III for 3 Infantry cards",
                    new Validations.RequiredField());
            if (input.equalsIgnoreCase("skip")) {
                break;
            }
            result = sq.exchangeInfantryCard(player.getName(), input.toUpperCase());
            if (!result.isSuccessful()) {
                _gui.addResult(result.errorMsg());
            } else {
                _gui.addResult("Successfully exchanged! You got " + result.result());
            }
        } while (result.isSuccessful());
    }

    public boolean attackOrNot(Player player) {
        boolean didPlayerOccupyTerritory = false;
        _gui.addResult("Attack phase..!!");
        while (true) {
            String isAttack = _gui.getInputFromUser("Enter 'attack' if you want to attack a country else enter 'skip'",
                    new Validations.RequiredAndEqualTo("skip", "attack"));

            if (isAttack.equalsIgnoreCase("skip")) {
                break;
            }

            String input = _gui.getInputFromUser("Enter the country from which you want to attack!",
                    new Validations.RequiredAndEqualTo(gq.getCountryAbbreviationListByPlayerName(player.getName())));

            Country attackCountry = gq.getCountryByAbbreviation(input.toUpperCase());
            if (attackCountry.getNoOfArmyInCountry() <= 1) {
                _gui.addResult("Sorry! The country must have more than 1 unit army to launch attack!");
                continue;
            }

            input = _gui.getInputFromUser("Enter the country you want to invade!",
                    new Validations.RequiredAndEqualTo(gq.getAdjCountryAbbByAbb(attackCountry.getAbbreviation())));
            Country defenceCountry = gq.getCountryByAbbreviation(input.toUpperCase());
            _gui.addResult(String.format("Player %1$s is invading %2$s from %3$s", player.getName(),
                    defenceCountry.getName(),
                    attackCountry.getName()));

            input = _gui.getInputFromUser("Enter the number of unit you wanna use in attack!",
                    new Validations.RequiredNumberFieldAndEqualTo(1,
                            attackCountry.getNoOfArmyInCountry() <= 3 ? 2 : 3));
            int attackingUnits = Integer.parseInt(input);

            input = _gui.getInputFromUser("Player " + gq.getOtherPlayer(player.getName()).getName()
                    + " Enter the number of unit you wanna use in defence!",
                    new Validations.RequiredNumberFieldAndEqualTo(1,
                            defenceCountry.getNoOfArmyInCountry() == 1 ? 1 : 2));
            int defenceUnits = Integer.parseInt(input);

            //removing units from country
            attackCountry.removeNoOfArmyInCountry(attackingUnits);
            defenceCountry.removeNoOfArmyInCountry(defenceUnits);

            int attackUnitLeft = attackUnitLeft(attackingUnits, defenceUnits);
            defenceUnits = attackingUnits - attackUnitLeft;

            attackCountry.addNoOfArmyInCountry(attackUnitLeft);
            defenceCountry.addNoOfArmyInCountry(defenceUnits);

            _gui.addResult(String.format("Attacker's country (%1$s) has %2$d army left",
                    attackCountry.getName(), attackCountry.getNoOfArmyInCountry()));

            _gui.addResult(String.format("Defencer's country (%1$s) has %2$d army left",
                    defenceCountry.getName(), defenceCountry.getNoOfArmyInCountry()));

            if (defenceCountry.getNoOfArmyInCountry() == 0) {
                didPlayerOccupyTerritory = true;
                _gui.addResult(String.format("Player %1$s wins the invasion", player.getName()));
                defenceCountry.setOwnerOfTheCountry(player);
                input = _gui.getInputFromUser("Enter the number of unit you want to place in your newly owned terrritory (>1)",
                        new Validations.RequiredNumberFieldAndEqualTo(1, attackCountry.getNoOfArmyInCountry() - 1));
                int unitMoved = Integer.parseInt(input);
                attackCountry.removeNoOfArmyInCountry(unitMoved);
                defenceCountry.addNoOfArmyInCountry(unitMoved);
            }

            _gui.refresh();
        }
        _gui.refresh();
        return didPlayerOccupyTerritory;
    }

    public void drawCardFromDeck(Player player) {
        _gui.addResult("Since you conquered one of the territory. You can draw a card from the deck!");
        _gui.addResult("Drawing from the deck!");
        if (gq.getCardDeck().size() > 0) {
            List<Card> randomcard = sq.assignRandomCardsToPlayer(player.getName(), 1, true);
            _gui.addResult("You got a " + randomcard);
        } else {
            _gui.addResult("Sorry! No card in the deckk!!");
        }
        _gui.refresh();
    }

    public void fortifyOrNot(Player player) {
        _gui.addResult("Fortify phase..!!");
        String isAttack = _gui.getInputFromUser("Enter 'fortify' if you want to fortify else enter 'skip'",
                new Validations.RequiredAndEqualTo("skip", "fortify"));

        if (isAttack.equalsIgnoreCase("skip")) {
            return;
        }
        while (true) {

            String input = _gui.getInputFromUser("Enter the territory from which you want to move your units",
                    new Validations.RequiredAndEqualTo(gq.getCountryAbbreviationListByPlayerName(player.getName())));

            Country fromCountry = gq.getCountryByAbbreviation(input.toUpperCase());
            if (fromCountry.getNoOfArmyInCountry() == 1) {
                _gui.addResult("This country has 1 army left. Every territory should have atleast one army");
                continue;
            }
            input = _gui.getInputFromUser("Enter the no of units you want to move",
                    new Validations.RequiredNumberFieldAndEqualTo(1, fromCountry.getNoOfArmyInCountry() - 1));
            int moveUnits = Integer.parseInt(input);

            input = _gui.getInputFromUser("Enter the country you want to move your units",
                    new Validations.RequiredAndEqualTo(
                            gq.getAdjCountryByAbbAndPlayer(fromCountry.getAbbreviation(), player.getName())
                    ));
            Country toCountry = gq.getCountryByAbbreviation(input.toUpperCase());

            fromCountry.removeNoOfArmyInCountry(moveUnits);
            toCountry.addNoOfArmyInCountry(moveUnits);

            _gui.addResult("Units successfully moved");
            break;
        }
        _gui.refresh();
    }

    public boolean isGameOver() {
        List<Player> playerList = gq.getPlayerList();
        playerList.stream()
                .forEach((player) -> {
                    List<String> countryNameListByPlayerName = gq.getCountryNameListByPlayerName(player.getName());
                    if (countryNameListByPlayerName.isEmpty()) {
                        sq.removePlayer(player.getName());
                    }
                });
        return gq.getPlayerList().size() == 1;
    }

    public void printWinner() {
        Player winner = gq.getPlayerList().get(0);
        _gui.addResult(winner.getName() + " wins the Risk Game!");
        JOptionPane.showMessageDialog(null, "Game Over! " + winner.getName() + " wins the Risk Game!");
    }

    private int attackUnitLeft(int attackUnits, int defenceUnits) {
        List<Integer> attackRoll = Dice.roll(attackUnits);
        List<Integer> defenceRoll = Dice.roll(defenceUnits);
        attackRoll = attackRoll.stream()
                .sorted((x, y) -> Integer.compare(y, x))
                .collect(Collectors.toList());

        defenceRoll = defenceRoll.stream()
                .sorted((x, y) -> Integer.compare(y, x))
                .collect(Collectors.toList());

        _gui.addResult("Attacker rolls(sorted) - " + attackRoll);
        _gui.addResult("Defence rolls(sorted)" + defenceRoll);

        int index = 0;
        for (Integer roll : defenceRoll) {
            if (roll < attackRoll.get(index)) {
                defenceUnits--;
            } else {
                attackUnits--;
            }
        }
        _gui.addResult(String.format("Attacker army unit left - %1$d", attackUnits));
        _gui.addResult(String.format("Defence army unit left - %1$d", defenceUnits));
        return attackUnits;
    }

    public void close() {
        _gui.close();
    }
}
