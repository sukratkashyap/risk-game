package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class creates the map of country or continent.
 */
public class DataFactory {

    private static Map<ContinentIndex, Continent> _continentMap;
    private static Map<CountryIndex, Country> _countryMap;

    private DataFactory() {
    }

    /**
     * Gets the Continent map which contains the Continent object as value and
     * ContinentIndex as key
     *
     * @return (Map)unmodifiable of Continents
     */
    public static Map<ContinentIndex, Continent> GetContinentMap() {
        if (_continentMap == null) {
            _continentMap = Collections.unmodifiableMap(CreateContinentMap());
        }
        return _continentMap;
    }

    /**
     * Gets the country map which contains the Country object as value and
     * CountryIndex as key
     *
     * @return (Map)unmodifiable of country
     */
    public static Map<CountryIndex, Country> GetCountryMap() {
        if (_countryMap == null) {
            _countryMap = Collections.unmodifiableMap(CreateCountryMap());
        }
        return _countryMap;
    }

    private static Map<ContinentIndex, Continent> CreateContinentMap() {
        Map<ContinentIndex, Continent> continentMap = new ConcurrentHashMap<>(Constants.NUM_CONTINENTS);

        continentMap.put(ContinentIndex.NAmerica, new Continent("North America", 5));
        continentMap.put(ContinentIndex.Europe, new Continent("Europe", 5));
        continentMap.put(ContinentIndex.Asia, new Continent("Asia", 7));
        continentMap.put(ContinentIndex.Australia, new Continent("Australia", 2));
        continentMap.put(ContinentIndex.SAmerica, new Continent("South America", 2));
        continentMap.put(ContinentIndex.Africa, new Continent("Africa", 3));

        return continentMap;
    }

    private static Map<CountryIndex, Country> CreateCountryMap() {
        Map<CountryIndex, Country> countryMap = new ConcurrentHashMap<>(Constants.NUM_COUNTRIES);

        countryMap.put(CountryIndex.Ontario, new Country("Ontario", ContinentIndex.NAmerica, 191, 150, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Greenland);
                add(CountryIndex.Quebec);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.WUnitedStates);
                add(CountryIndex.Alberta);
                add(CountryIndex.NWTerritory);
            }
        }));

        countryMap.put(CountryIndex.Quebec, new Country("Quebec", ContinentIndex.NAmerica, 255, 161, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Greenland);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.Ontario);
            }
        }));

        countryMap.put(CountryIndex.NWTerritory, new Country("NW Territory", ContinentIndex.NAmerica, 146, 86, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Greenland);
                add(CountryIndex.Ontario);
                add(CountryIndex.Alberta);
                add(CountryIndex.Alaska);
            }
        }));

        countryMap.put(CountryIndex.Alberta, new Country("Alberta", ContinentIndex.NAmerica, 123, 144, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NWTerritory);
                add(CountryIndex.Ontario);
                add(CountryIndex.WUnitedStates);
                add(CountryIndex.Alaska);
            }
        }));

        countryMap.put(CountryIndex.Greenland, new Country("Greenland", ContinentIndex.NAmerica, 314, 61, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Iceland);
                add(CountryIndex.Quebec);
                add(CountryIndex.Ontario);
                add(CountryIndex.NWTerritory);
            }
        }));

        countryMap.put(CountryIndex.EUnitedStates, new Country("E United States", ContinentIndex.NAmerica, 205, 235, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ontario);
                add(CountryIndex.Quebec);
                add(CountryIndex.CentralAmerica);
                add(CountryIndex.WUnitedStates);
            }
        }));

        countryMap.put(CountryIndex.WUnitedStates, new Country("W United States", ContinentIndex.NAmerica, 135, 219, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Alberta);
                add(CountryIndex.Ontario);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.CentralAmerica);
            }
        }));

        countryMap.put(CountryIndex.CentralAmerica, new Country("Central America", ContinentIndex.NAmerica, 140, 299, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.WUnitedStates);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.Venezuela);
            }
        }));

        countryMap.put(CountryIndex.Alaska, new Country("Alaska", ContinentIndex.NAmerica, 45, 89, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NWTerritory);
                add(CountryIndex.Alberta);
                add(CountryIndex.Kamchatka);
            }
        }));

        countryMap.put(CountryIndex.GreatBritain, new Country("Great Britain", ContinentIndex.Europe, 370, 199, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Iceland);
                add(CountryIndex.Scandinavia);
                add(CountryIndex.NEurope);
                add(CountryIndex.WEurope);
            }
        }));

        countryMap.put(CountryIndex.WEurope, new Country("W Europe", ContinentIndex.Europe, 398, 280, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.GreatBritain);
                add(CountryIndex.NEurope);
                add(CountryIndex.SEurope);
                add(CountryIndex.NAfrica);
            }
        }));

        countryMap.put(CountryIndex.SEurope, new Country("S Europe", ContinentIndex.Europe, 465, 270, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NEurope);
                add(CountryIndex.Ukraine);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.Egypt);
                add(CountryIndex.WEurope);
            }
        }));

        countryMap.put(CountryIndex.Ukraine, new Country("Ukraine", ContinentIndex.Europe, 547, 180, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ural);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.SEurope);
                add(CountryIndex.NEurope);
                add(CountryIndex.Scandinavia);
            }
        }));

        countryMap.put(CountryIndex.NEurope, new Country("N Europe", ContinentIndex.Europe, 460, 200, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Scandinavia);
                add(CountryIndex.Ukraine);
                add(CountryIndex.SEurope);
                add(CountryIndex.WEurope);
                add(CountryIndex.GreatBritain);
            }
        }));

        countryMap.put(CountryIndex.Iceland, new Country("Iceland", ContinentIndex.Europe, 393, 127, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Scandinavia);
                add(CountryIndex.GreatBritain);
                add(CountryIndex.Greenland);
            }
        }));

        countryMap.put(CountryIndex.Scandinavia, new Country("Scandinavia", ContinentIndex.Europe, 463, 122, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ukraine);
                add(CountryIndex.NEurope);
                add(CountryIndex.Iceland);
            }
        }));

        countryMap.put(CountryIndex.Afghanistan, new Country("Afghanistan", ContinentIndex.Asia, 628, 227, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ural);
                add(CountryIndex.China);
                add(CountryIndex.India);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.Ukraine);
            }
        }));

        countryMap.put(CountryIndex.India, new Country("India", ContinentIndex.Asia, 679, 332, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Afghanistan);
                add(CountryIndex.China);
                add(CountryIndex.Siam);
                add(CountryIndex.MiddleEast);
            }
        }));

        countryMap.put(CountryIndex.MiddleEast, new Country("Middle East", ContinentIndex.Asia, 572, 338, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ukraine);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.India);
                add(CountryIndex.EAfrica);
                add(CountryIndex.Egypt);
                add(CountryIndex.SEurope);
            }
        }));

        countryMap.put(CountryIndex.Japan, new Country("Japan", ContinentIndex.Asia, 861, 213, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Mongolia);
                add(CountryIndex.Kamchatka);
            }
        }));

        countryMap.put(CountryIndex.Ural, new Country("Ural", ContinentIndex.Asia, 645, 152, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Siberia);
                add(CountryIndex.China);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.Ukraine);
            }
        }));

        countryMap.put(CountryIndex.Yakutsk, new Country("Yakutsk", ContinentIndex.Asia, 763, 70, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Kamchatka);
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Siberia);
            }
        }));

        countryMap.put(CountryIndex.Kamchatka, new Country("Kamchatka", ContinentIndex.Asia, 827, 94, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Alaska);
                add(CountryIndex.Japan);
                add(CountryIndex.Mongolia);
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Yakutsk);
            }
        }));

        countryMap.put(CountryIndex.Siam, new Country("Siam", ContinentIndex.Asia, 751, 360, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.China);
                add(CountryIndex.Indonesia);
                add(CountryIndex.India);
            }
        }));

        countryMap.put(CountryIndex.Irkutsk, new Country("Irkutsk", ContinentIndex.Asia, 750, 140, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Yakutsk);
                add(CountryIndex.Kamchatka);
                add(CountryIndex.Mongolia);
                add(CountryIndex.Siberia);
            }
        }));

        countryMap.put(CountryIndex.Siberia, new Country("Siberia", ContinentIndex.Asia, 695, 108, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Yakutsk);
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Mongolia);
                add(CountryIndex.China);
                add(CountryIndex.Ural);
            }
        }));

        countryMap.put(CountryIndex.Mongolia, new Country("Mongolia", ContinentIndex.Asia, 760, 216, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Kamchatka);
                add(CountryIndex.Japan);
                add(CountryIndex.China);
                add(CountryIndex.Siberia);
            }
        }));

        countryMap.put(CountryIndex.China, new Country("China", ContinentIndex.Asia, 735, 277, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Mongolia);
                add(CountryIndex.Siam);
                add(CountryIndex.India);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.Ural);
                add(CountryIndex.Siberia);
            }
        }));

        countryMap.put(CountryIndex.EAustralia, new Country("E Australia", ContinentIndex.Australia, 889, 537, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NewGuinea);
                add(CountryIndex.WAustralia);
            }
        }));

        countryMap.put(CountryIndex.NewGuinea, new Country("New Guinea", ContinentIndex.Australia, 850, 429, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.EAustralia);
                add(CountryIndex.WAustralia);
                add(CountryIndex.Indonesia);
            }
        }));

        countryMap.put(CountryIndex.WAustralia, new Country("W Australia", ContinentIndex.Australia, 813, 526, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NewGuinea);
                add(CountryIndex.EAustralia);
                add(CountryIndex.Indonesia);
            }
        }));

        countryMap.put(CountryIndex.Indonesia, new Country("Indonesia", ContinentIndex.Australia, 771, 454, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Siam);
                add(CountryIndex.NewGuinea);
                add(CountryIndex.WAustralia);
            }
        }));

        countryMap.put(CountryIndex.Venezuela, new Country("Venezuela", ContinentIndex.SAmerica, 213, 352, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.CentralAmerica);
                add(CountryIndex.Brazil);
                add(CountryIndex.Peru);
            }
        }));

        countryMap.put(CountryIndex.Peru, new Country("Peru", ContinentIndex.SAmerica, 221, 426, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Venezuela);
                add(CountryIndex.Brazil);
                add(CountryIndex.Argentina);
            }
        }));

        countryMap.put(CountryIndex.Brazil, new Country("Brazil", ContinentIndex.SAmerica, 289, 415, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Venezuela);
                add(CountryIndex.NAfrica);
                add(CountryIndex.Argentina);
                add(CountryIndex.Peru);
            }
        }));

        countryMap.put(CountryIndex.Argentina, new Country("Argentina", ContinentIndex.SAmerica, 233, 523, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Peru);
                add(CountryIndex.Brazil);
            }
        }));

        countryMap.put(CountryIndex.Congo, new Country("Congo", ContinentIndex.Africa, 496, 462, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NAfrica);
                add(CountryIndex.EAfrica);
                add(CountryIndex.SAfrica);
            }
        }));

        countryMap.put(CountryIndex.NAfrica, new Country("N Africa", ContinentIndex.Africa, 440, 393, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.WEurope);
                add(CountryIndex.SEurope);
                add(CountryIndex.Egypt);
                add(CountryIndex.EAfrica);
                add(CountryIndex.Congo);
                add(CountryIndex.Brazil);
            }
        }));

        countryMap.put(CountryIndex.SAfrica, new Country("S Africa", ContinentIndex.Africa, 510, 532, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Congo);
                add(CountryIndex.EAfrica);
                add(CountryIndex.Madagascar);
            }
        }));

        countryMap.put(CountryIndex.Egypt, new Country("Egypt", ContinentIndex.Africa, 499, 354, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.SEurope);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.EAfrica);
                add(CountryIndex.NAfrica);
            }
        }));

        countryMap.put(CountryIndex.EAfrica, new Country("E Africa", ContinentIndex.Africa, 547, 432, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Egypt);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.Madagascar);
                add(CountryIndex.SAfrica);
                add(CountryIndex.Congo);
                add(CountryIndex.NAfrica);
            }
        }));

        countryMap.put(CountryIndex.Madagascar, new Country("Madagascar", ContinentIndex.Africa, 586, 545, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.SAfrica);
                add(CountryIndex.EAfrica);
            }
        }));
        return countryMap;
    }
}
