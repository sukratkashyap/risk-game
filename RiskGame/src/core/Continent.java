package core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the continents and its related data.
 */
public class Continent {

    private final String _name;
    private final int _continentValues;

    /**
     *
     * @param name name of the continent
     * @param continentValue value of the continent
     */
    public Continent(String name, int continentValue) {
        _name = name;
        _continentValues = continentValue;
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
        return _continentValues;
    }
}
