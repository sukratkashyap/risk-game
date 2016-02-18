package game.data;

import core.Constants;
import core.Country;
import core.CountryIndex;
import core.DataFactory;
import core.Player;
import core.PlayerType;
import core.Result;
import java.util.List;
import java.util.Map;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description
 */
public class GameQuery {

    private final DataFactory _dataFactory;
    private Result _result;

    public GameQuery() {
        _dataFactory = DataFactory.getInstance();
    }

    /**
     *
     * @param name name of the player (Main player)
     * @return result telling whether the operation was successful
     */
    public Result AddMainPlayer(String name) {
        Player player = new Player(name, PlayerType.MainPlayer);
        Map<String, Player> playerMap = _dataFactory.GetPlayerMap();

        if (playerMap.containsKey(name)) {
            _result = new Result(false, "Player already exists!");
        } else {
            playerMap.put(player.getName(), player);
            _result = new Result(true, "");
        }

        return _result;
    }

    /**
     *
     * @param name name of the player (Neutral player)
     * @return result telling whether the operation was successful
     */
    public Result AddNeutralPlayer(String name) {
        Player player = new Player(name, PlayerType.NeutralPlayer);
        Map<String, Player> playerMap = _dataFactory.GetPlayerMap();

        if (playerMap.containsKey(name)) {
            _result = new Result(false, "Player already exists!");
        } else {
            playerMap.put(player.getName(), player);
            _result = new Result(true, "");
        }

        return _result;
    }

    /**
     * assign countries to the player
     */
    public void AssignCountryToPlayer() {
        int playerIndex = 0;
        int countryIndex = 0;
        DataQuery dq = new DataQuery();
        List<Country> countryList = dq.getCountryList();
        List<Player> playerList = dq.getPlayerList();
        for (Player player : playerList) {
            int initCountryPlayer;
            if (player.getPlayerType() == PlayerType.MainPlayer) {
                initCountryPlayer = Constants.INIT_COUNTRIES_PLAYER;
            } else {
                initCountryPlayer = Constants.INIT_COUNTRIES_NEUTRAL;
            }

            for (int i = 0; i < initCountryPlayer; i++) {
                countryList.get(countryIndex).setOwnerOfTheCountry(player);
                countryList.get(countryIndex).setArmyInCountry(1);
                countryIndex++;
            }
        }
    }
}
