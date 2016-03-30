package game.data;

import game.core.Card;
import game.core.Continent;
import game.core.ContinentIndex;
import game.core.Country;
import game.core.CountryIndex;
import game.core.DataFactory;
import game.core.Player;
import game.core.PlayerType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description An abstraction on the country and continent map. this class
 * gives various methods to search country or continent
 */
public class GetQuery {
    
    private final DataFactory _dataFactory;
    
    public GetQuery() {
        _dataFactory = DataFactory.getInstance();
    }

    /**
     *
     * @return Continent which has the continent index
     */
    public List<Continent> getContinentList() {
        return _dataFactory.getContinentMap().entrySet().stream()
                .map((continent) -> continent.getValue())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param continentId continent id to search
     * @return Continent which has the continent index
     */
    public Continent getContinent(ContinentIndex continentId) {
        Map<ContinentIndex, Continent> continentMap = _dataFactory.getContinentMap();
        return continentMap.get(continentId);
    }

    /**
     *
     * @return List of all the countries
     */
    public List<Country> getCountryList() {
        return _dataFactory.getCountryMap().entrySet().stream()
                .map((country) -> country.getValue())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param countryIdList List of country ids
     * @return gives list of countries bases on the country ids passd
     */
    public List<Country> getCountryList(List<CountryIndex> countryIdList) {
        List<Country> countryList = new ArrayList<>();
        countryIdList.stream()
                .forEach((index) -> {
                    countryList.add(_dataFactory.getCountryMap().get(index));
                });
        return countryList;
    }

    /**
     *
     * @param countryId id of the country
     * @return the country object corresponding to countryId
     */
    public Country getCountry(CountryIndex countryId) {
        return _dataFactory.getCountryMap().get(countryId);
    }

    /**
     *
     * @param abbreviation
     * @return the country object corresponding to country abbreviation
     */
    public Country getCountryByAbbreviation(String abbreviation) {
        return _dataFactory.getCountryAbbreviationMap().get(abbreviation);
    }

    /**
     *
     * @param abbreviation
     * @return the country object corresponding to country abbreviation
     */
    public List<String> getAdjacentCountryByAbbreviation(String abbreviation) {
        return _dataFactory.getCountryAbbreviationMap().get(abbreviation)
                .getAdjacentCountryIndexList()
                .stream()
                .map((c) -> _dataFactory.getCountryMap().get(c).getAbbreviation())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param abbreviation
     * @return the country object corresponding to country abbreviation
     */
    public List<String> getAdjacentCountryByAbbreviationAndPlayer(String abbreviation, String playerName) {
        return _dataFactory.getCountryAbbreviationMap().get(abbreviation)
                .getAdjacentCountryIndexList()
                .stream()
                .map((c) -> _dataFactory.getCountryMap().get(c))
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
        return _dataFactory.getCountryNameMap().get(countryName);
    }

    /**
     *
     * @return list of players
     */
    public List<Player> getPlayerList() {
        return _dataFactory.getPlayerMap().entrySet()
                .stream()
                .map((player) -> player.getValue())
                .collect(Collectors.toList());
    }

    /**
     *
     * @return list of players
     */
    public List<String> getPlayerNameList() {
        return _dataFactory.getPlayerMap().entrySet()
                .stream()
                .map((player) -> player.getValue().getName())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerType
     * @return player list by player type
     */
    public List<Player> getPlayerList(PlayerType playerType) {
        return _dataFactory.getPlayerMap().entrySet()
                .stream()
                .map((player) -> player.getValue())
                .filter((player) -> player.getPlayerType() == playerType)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName name of the player
     * @return the player whose name has been passed
     */
    public Player getPlayer(String playerName) {
        return _dataFactory.getPlayerMap().get(playerName);
    }

    /**
     *
     * @return
     */
    public List<Player> getOrderedMainPlayerList() {
        return _dataFactory.getPlayerMap().entrySet()
                .stream()
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
        return _dataFactory.getCountryMap().entrySet()
                .stream()
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
                .stream()
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
                .stream()
                .map((country) -> country.getAbbreviation())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param playerName
     * @param continentId
     * @return
     */
    public List<Country> getCountryListByPlayerNameAndContinentId(String playerName, ContinentIndex continentId) {
        return getCountryListByPlayerName(playerName)
                .stream()
                .filter((country) -> country.getContinent().getContinentId() == continentId)
                .collect(Collectors.toList());
    }
    
    public Player getOtherPlayer(String playerName) {
        return _dataFactory.getPlayerMap().entrySet().stream()
                .filter((p) -> !p.getKey().equals(playerName)
                        && p.getValue().getPlayerType() == PlayerType.MainPlayer)
                .findFirst().get().getValue();
    }
}
