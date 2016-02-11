package core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description class for storing the continents and its related data.
 */
public class Continent {

    private final ContinentIndex _continentIndex;
    private final String _name;
    private final int _continentValues;

    /**
     *
     * @param continentId id of the continent
     * @param name name of the continent
     * @param continentValue value of the continent
     */
    public Continent(ContinentIndex continentId, String name, int continentValue) {
        _continentIndex = continentId;
        _name = name;
        _continentValues = continentValue;
    }

    /**
     *
     * @return id of the continent
     */
    public ContinentIndex getContinentId() {
        return _continentIndex;
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
