package riskgame;

import game.core.Card;
import game.core.Constants;
import game.core.Dice;
import game.core.Player;
import game.core.PlayerType;
import game.core.Result;
import game.data.GetQuery;
import game.data.SetQuery;
import game.data.Validations;
import game.graphic.GUI;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description
 */
public class GamePlay {

    private GUI _gui;

    public GamePlay(GUI gui) {
        _gui = gui;
    }

    public void getPlayerNameFromUser() {
        _gui.refresh();
        SetQuery sq = new SetQuery();
        GetQuery gq = new GetQuery();
        //Adding Main player
        for (int i = 0; i < Constants.NUM_PLAYERS; i++) {
            String question = "Enter Player " + (i + 1) + " name";
            String playerName = _gui.getInputFromUser(question,
                    new Validations.RequiredAndNotBeEqualTo(gq.getPlayerList()
                            .stream().map((p) -> p.getName()).collect(Collectors.toList())));
            Result<Player> addMainPlayer = sq.addPlayer(playerName, Constants.INIT_UNITS_PLAYER,
                    PlayerType.MainPlayer);
            _gui.addResult(addMainPlayer.Result().getName());
        }

        //adding neutrals
        for (int i = 0; i < Constants.NUM_NEUTRALS; i++) {
            Result<Player> addMainPlayer = sq.addPlayer("Neutral_" + (i + 1), Constants.INIT_UNITS_NEUTRAL,
                    PlayerType.NeutralPlayer);
        }
        _gui.refresh();
    }

    public void assignTerritoryCard() {
        GetQuery gq = new GetQuery();
        SetQuery sq = new SetQuery();
        gq.getPlayerList(PlayerType.MainPlayer).stream()
                .forEach((player) -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Player Territory- ").append(player.getName()).append("\n");
                    for (int i = 0; i < Constants.INIT_COUNTRIES_PLAYER; i++) {
                        Card assignRandomCardToPlayer = sq.assignRandomCardToPlayer(player.getName());
                        builder.append(assignRandomCardToPlayer.getCountryName()).append(",");
                    }
                    _gui.addResult(builder.toString());
                });
        gq.getPlayerList(PlayerType.NeutralPlayer).stream()
                .forEach((player) -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Player Territory- ").append(player.getName()).append("\n");
                    for (int i = 0; i < Constants.INIT_COUNTRIES_NEUTRAL; i++) {
                        Card assignRandomCardToPlayer = sq.assignRandomCardToPlayer(player.getName());
                        builder.append(assignRandomCardToPlayer.getCountryName()).append(",");
                    }
                    _gui.addResult(builder.toString());
                });
        _gui.refresh();
    }

    public void rollDice() {
        _gui.refresh();
        GetQuery gq = new GetQuery();
        SetQuery sq = new SetQuery();
        List<Player> mainPlayerList = gq.getPlayerList(PlayerType.MainPlayer);
        List<Player> neutralPlayerList = gq.getPlayerList(PlayerType.NeutralPlayer);
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

    }

    public void setInforcements() {
        _gui.refresh();
        GetQuery gq = new GetQuery();
        SetQuery sq = new SetQuery();
        List<Player> mainPlayerList = gq.getPlayerList(PlayerType.MainPlayer);
        List<Player> neutralPlayerList = gq.getPlayerList(PlayerType.NeutralPlayer);
        mainPlayerList = gq.getOrderedMainPlayerList();

        for (int i = 0; i < 9; i++) {
            mainPlayerList.forEach((player) -> {
                _gui.addResult("Player " + player.getName() + " turn");
                String countryName = _gui.getInputFromUser("Where do you want to place your 3 unit army",
                        new Validations.RequiredAndEqualTo(gq.getCountryNameListByPlayerName(player.getName())));
                sq.addUnitToCountry(player.getName(), countryName, 3);

                _gui.refresh();

                neutralPlayerList.stream()
                        .forEach((neutral) -> {
                            String question = "Where do you want to place your 1 unit army of " + neutral.getName();
                            String neutralCountryName = _gui.getInputFromUser(question,
                                    new Validations.RequiredAndEqualTo(gq.getCountryNameListByPlayerName(neutral.getName())));
                            sq.addUnitToCountry(neutral.getName(), neutralCountryName, 1);

                            _gui.refresh();
                        });
            });
        }

        _gui.refresh();
    }

    public void removeNoArimesPlayer() {
        _gui.refresh();
        SetQuery sq = new SetQuery();
        GetQuery gq = new GetQuery();
        gq.getPlayerList().stream().filter((player) -> (player.getNoOfArmies() == 0)).forEach((player) -> {
            sq.removePlayer(player.getName());

        });
        _gui.refresh();
    }

    public void attackOrNot() {
        String skip = "SKIP";
        _gui.getInputFromUser("Who do you want to attack? Enter SKIP if you do NOT want to attack", new Validations.RequiredAndEqualTo(skip));

    }
}
