package game.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the country and its related data
 */
public class Country {

    private final CountryId _countryId;
    private final String _name;
    private final Continent _continent;
    private final Point _countryCoOrd;
    private final List<CountryId> _adjacentCountryIdList;
    private final String _abbreviation;

    private Player _ownerOfTheCountry = null;
    private int _noOfArmyInCountry = 0;

    /**
     *
     * @param countryId unique id for finding the country
     * @param abbreviation shortcut name of the country
     * @param name name of the country
     * @param continent continent of the country
     * @param xCoOrd x coordinate of the country
     * @param yCoOrd y coordinate of the country
     * @param adjacentCountries list of adjacent countries index
     */
    public Country(CountryId countryId, String abbreviation, String name, Continent continent,
            int xCoOrd, int yCoOrd, List<CountryId> adjacentCountries) {
        _countryId = countryId;
        _abbreviation = abbreviation;
        _name = name;
        _continent = continent;
        _countryCoOrd = new Point(Utils.getRatioCoOrdinate(xCoOrd, yCoOrd));
        _adjacentCountryIdList = Collections.unmodifiableList(new ArrayList<>(adjacentCountries));
    }

    /**
     *
     * @return Id of the country
     */
    public CountryId getCountryId() {
        return _countryId;
    }

    /**
     *
     * @return abbreviations of the Country
     */
    public String getAbbreviation() {
        return _abbreviation;
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
    public Continent getContinent() {
        return _continent;
    }

    /**
     *
     * @return the x coordinate of the country
     */
    public int getXCoOrdinate() {
        return _countryCoOrd.x;
    }

    /**
     *
     * @return the y coordinate of the country
     */
    public int getYCoOrdinate() {
        return _countryCoOrd.y;
    }

    /**
     * Getter for the adjacent countries which are connected either by border or
     * sea
     *
     * @return (List)unmodifiable list of adjacent countries index
     */
    public List<CountryId> getAdjacentCountryIdList() {
        return _adjacentCountryIdList;
    }

    /**
     *
     * @return (Player) the owner of the country
     */
    public Player getOwnerOfTheCountry() {
        return _ownerOfTheCountry;
    }

    /**
     *
     * @param owner (Player) owner of the country
     */
    public void setOwnerOfTheCountry(Player owner) {
        _ownerOfTheCountry = owner;
    }

    /**
     *
     * @return no. of army in the country
     */
    public int getNoOfArmyInCountry() {
        return _noOfArmyInCountry;
    }

    /**
     *
     * @param noOfArmy
     * @return the no of army added
     */
    public int addNoOfArmyInCountry(int noOfArmy) {
        _noOfArmyInCountry += noOfArmy;
        return noOfArmy;
    }

    /**
     *
     * @param noOfArmy
     * @return the no of army removed
     */
    public int removeNoOfArmyInCountry(int noOfArmy) {
        _noOfArmyInCountry -= noOfArmy;
        return noOfArmy;
    }

    public boolean isCountryInAdjacent(String countryName) {
        return false;
    }

    @Override
    public String toString() {
        return _name + "(" + _noOfArmyInCountry + ")";
    }

}
