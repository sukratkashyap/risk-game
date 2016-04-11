package game.data;

import game.core.Card;
import game.core.Continent;
import game.core.ContinentId;
import game.core.Country;
import game.core.CountryId;
import game.core.DataFactory;
import game.core.Player;
import java.util.Map;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description An abstraction on the country and continent map. this class
 * gives various methods to search country or continent
 */
public abstract class BaseQuery {

    private DataFactory _dataFactory;

    protected BaseQuery() {
        _dataFactory = DataFactory.getInstance();
    }

    protected Continent continentById(ContinentId id) {
        return _dataFactory.getContinentMap()
                .get(id);
    }

    protected Country countryById(CountryId id) {
        return _dataFactory.getCountryIdMap()
                .get(id);
    }

    protected Country countryByName(String name) {
        return _dataFactory.getCountryNameMap()
                .get(name);
    }

    protected Country countryByAbb(String abb) {
        return _dataFactory.getCountryAbbreviationMap()
                .get(abb);
    }

    protected Player playerByName(String name) {
        return _dataFactory.getPlayerMap()
                .get(name);
    }

    protected Card cardById(String name) {
        return _dataFactory.getCardMap()
                .get(name);
    }

    protected Map<ContinentId, Continent> continentMap() {
        return _dataFactory.getContinentMap();
    }

    protected Map<CountryId, Country> countryIdMap() {
        return _dataFactory.getCountryIdMap();
    }

    protected Map<String, Country> countryNameMap() {
        return _dataFactory.getCountryNameMap();
    }

    protected Map<String, Country> countryAbbMap() {
        return _dataFactory.getCountryAbbreviationMap();
    }

    protected Map<String, Player> playerMap() {
        return _dataFactory.getPlayerMap();
    }

    protected Map<String, Card> cardMap() {
        return _dataFactory.getCardMap();
    }

    protected int getAndMoveGoldenCavalry() {
        return _dataFactory.getAndMoveGoldenCavalryPosition();
    }
}
