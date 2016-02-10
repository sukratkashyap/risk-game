package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the country and its related data
 */
public class Country {

    private final String _name;
    private final ContinentIndex _continentIndex;
    private final Point _countryCoOrd;
    private final List<CountryIndex> _adjacentCountryIndexList;

    /**
     *
     * @param name name of the country
     * @param continentIndex continent of the country
     * @param xCoOrd x coordinate of the country
     * @param yCoOrd y coordinate of the country
     * @param adjacentCountries list of adjacent countries index
     */
    public Country(String name, ContinentIndex continentIndex,
            int xCoOrd, int yCoOrd, List<CountryIndex> adjacentCountries) {
        _name = name;
        _continentIndex = continentIndex;
        _countryCoOrd = new Point(xCoOrd, yCoOrd);
        _adjacentCountryIndexList = Collections.unmodifiableList(new ArrayList<>(adjacentCountries));
    }

    /**
     *
     * @return name of the Country
     */
    public String getName() {
        return _name;
    }

    /**
     *
     * @return country's continent index
     */
    public ContinentIndex getContinentIndex() {
        return _continentIndex;
    }

    /**
     *
     * @return (Point) coordinate of the country
     */
    public Point getCountryCoOrd() {
        return _countryCoOrd;
    }

    /**
     * Getter for the adjacent countries which are connected either by border or
     * sea
     *
     * @return (List)unmodifiable list of adjacent countries index
     */
    public List<CountryIndex> getAdjacentCountryIndexList() {
        return _adjacentCountryIndexList;
    }
}
