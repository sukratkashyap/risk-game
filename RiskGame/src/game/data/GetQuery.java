package game.data;

import game.core.Continent;
import game.core.ContinentId;
import game.core.Country;
import game.core.CountryId;
import game.core.Player;
import game.core.PlayerType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description An abstraction on the country and continent map. this class
 * gives various methods to search country or continent
 */
public class GetQuery extends BaseQuery {

    /**
     *
     * @return Continent which has the continent index
     */
    public List<Continent> getContinentList() {
        return continentMap().entrySet()
                .parallelStream()
                .map((continent) -> continent.getValue())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param continentId continent id to search
     * @return Continent which has the continent index
     */
    public Continent getContinentById(ContinentId continentId) {
        return continentById(continentId);
    }

    /**
     *
     * @return List of all the countries
     */
    public List<Country> getCountryList() {
        return countryIdMap().entrySet()
                .parallelStream()
                .map((country) -> country.getValue())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param countryIdList List of country ids
     * @return gives list of countries bases on the country ids passd
     */
    public List<Country> getCountryListByIds(List<CountryId> countryIdList) {
        List<Country> countryList = new ArrayList<>();
        countryIdList.stream()
                .forEach((id) -> {
                    countryList.add(countryIdMap().get(id));
                });
        return countryList;
    }

    /**
     *
     * @param countryId id of the country
     * @return the country object corresponding to countryId
     */
    public Country getCountryById(CountryId countryId) {
        return countryById(countryId);
    }

    /**
     *
     * @param abbreviation
     * @return the country object corresponding to country abbreviation
     */
    public Country getCountryByAbbreviation(String abbreviation) {
        return countryByAbb(abbreviation);
    }

    public List<Country> getAdjCountryByAbb(String abb) {
        return countryByAbb(abb).getAdjacentCountryIdList()
                .parallelStream()
                .map((adjacentId) -> countryById(adjacentId))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param abb
     * @return the country object corresponding to country abbreviation
     */
    public List<String> getAdjCountryAbbByAbb(String abb) {
        return getAdjCountryByAbb(abb)
                .parallelStream()
                .map((adjacent) -> adjacent.getAbbreviation())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param abbreviation
     * @param playerName
     * @return the country object corresponding to country abbreviation
     */
    public List<String> getAdjCountryByAbbAndPlayer(String abbreviation, String playerName) {
        return getAdjCountryByAbb(abbreviation)
                .parallelStream()
                .filter((c) -> c.getOwnerOfTheCountry().getName().equals(playerName))
                .map((c) -> c.getAbbreviation())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param countryName
     * @return the country object corresponding to country abbreviation
     */
    public Country getCountryByName(String countryName) {
        return countryByName(countryName);
    }

    /**
     *
     * @return list of players
     */
    public List<Player> getPlayerList() {
        return playerMap().entrySet()
                .parallelStream()
                .map((player) -> player.getValue())
                .collect(Collectors.toList());
    }

    /**
     *
     * @return list of players
     */
    public List<String> getPlayerNameList() {
        return playerMap().entrySet()
                .parallelStream()
                .map((player) -> player.getValue().getName())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerType
     * @return player list by player type
     */
    public List<Player> getPlayerListByType(PlayerType playerType) {
        return playerMap().entrySet()
                .parallelStream()
                .map((player) -> player.getValue())
                .filter((player) -> player.getPlayerType() == playerType)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName name of the player
     * @return the player whose name has been passed
     */
    public Player getPlayerByName(String playerName) {
        return playerByName(playerName);
    }

    /**
     *
     * @return
     */
    public List<Player> getOrderedMainPlayerList() {
        return playerMap().entrySet()
                .parallelStream()
                .map((player) -> player.getValue())
                .filter((player) -> player.getPlayerType() == PlayerType.MainPlayer)
                .sorted((p1, p2) -> Integer.compare(p1.getOrder(), p2.getOrder()))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName
     * @return
     */
    public List<Country> getCountryListByPlayerName(String playerName) {
        return countryIdMap().entrySet()
                .parallelStream()
                .map((country) -> country.getValue())
                .filter((country) -> {
                    if (country.getOwnerOfTheCountry() == null) {
                        return false;
                    } else {
                        return country.getOwnerOfTheCountry().getName().equals(playerName);
                    }
                }).collect(Collectors.toList());
    }

    /**
     *
     * @param playerName
     * @return
     */
    public List<String> getCountryNameListByPlayerName(String playerName) {
        return getCountryListByPlayerName(playerName)
                .parallelStream()
                .map((country) -> country.getName())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName
     * @return
     */
    public List<String> getCountryAbbreviationListByPlayerName(String playerName) {
        return getCountryListByPlayerName(playerName)
                .parallelStream()
                .map((country) -> country.getAbbreviation())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName
     * @param continentId
     * @return
     */
    public List<Country> getCountryListByPlayerNameAndContinentId(String playerName, ContinentId continentId) {
        return getCountryListByPlayerName(playerName)
                .parallelStream()
                .filter((country) -> country.getContinent().getContinentId() == continentId)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName
     * @return
     */
    public Player getOtherPlayer(String playerName) {
        return playerMap().entrySet().parallelStream()
                .filter((p) -> (!p.getKey().equals(playerName))
                        && (p.getValue().getPlayerType() == PlayerType.MainPlayer))
                .findFirst().get().getValue();
    }

}
