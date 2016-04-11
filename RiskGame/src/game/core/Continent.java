package game.core;

import java.awt.Color;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the continents and its related data.
 */
public class Continent {

    private final ContinentId _continentId;
    private final String _name;
    private final int _continentValue;
    private final Color _color;
    private final int _noOfTerritories;

    /**
     *
     * @param continentId id of the continent
     * @param name name of the continent
     * @param continentValue value of the continent
     * @param color color of the continent
     * @param noOfTerritories no of territories in the continent
     */
    public Continent(ContinentId continentId, String name, int continentValue, Color color, int noOfTerritories) {
        _continentId = continentId;
        _name = name;
        _continentValue = continentValue;
        _color = color;
        _noOfTerritories = noOfTerritories;
    }

    /**
     *
     * @return id of the continent
     */
    public ContinentId getContinentId() {
        return _continentId;
    }

    /**
     *
     * @return name of the continent
     */
    public String getName() {
        return _name;
    }

    /**
     * Getter of the continents value which is used in giving bonus
     * reinforcements
     *
     * @return the value of the continent
     */
    public int getContinentValue() {
        return _continentValue;
    }

    /**
     *
     * @return color of the continent
     */
    public Color getColor() {
        return _color;
    }

    /**
     *
     * @return no of territories in the continent
     */
    public int getNoOfTerritories() {
        return _noOfTerritories;
    }
}
