package game.data;

import core.Continent;
import core.ContinentIndex;
import core.Country;
import core.CountryIndex;
import core.DataFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description An abstraction on the country and continent map. this class
 * gives various methods to search country or continent
 */
public class CountryQuery {

    private final Map<CountryIndex, Country> _countryMap;
    private final Map<ContinentIndex, Continent> _continentMap;

    public CountryQuery() {
        _countryMap = DataFactory.GetCountryMap();
        _continentMap = DataFactory.GetContinentMap();
    }

    public CountryQuery(Map<CountryIndex, Country> countryMap,
            Map<ContinentIndex, Continent> continentMap) {
        _countryMap = countryMap;
        _continentMap = continentMap;
    }

    public List<Country> getCountryList() {
        List<Country> countryList = new ArrayList<>();
        for (Map.Entry<CountryIndex, Country> countryMapElem : _countryMap.entrySet()) {
            countryList.add(countryMapElem.getValue());
        }
        return Collections.unmodifiableList(countryList);
    }

    public Country getCountry(CountryIndex countryId) throws Exception {
        if (_countryMap.containsKey(countryId)) {
            return _countryMap.get(countryId);
        } else {
            throw new Exception("Country Id not found in the country map.");
        }
    }

    public List<Country> getCountry(List<CountryIndex> countryIdList) throws Exception {
        List<Country> countryList = new ArrayList<>();
        for (CountryIndex countryId : countryIdList) {
            if (_countryMap.containsKey(countryId)) {
                countryList.add(_countryMap.get(countryId));
            } else {
                throw new Exception("Country Id not found in the country map.");
            }
        }
        return Collections.unmodifiableList(countryList);
    }

}
