package game.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class creates the map of country or continent. Acts as data
 * storage for the game. Only one instance is created.
 */
public class DataFactory {

    //Singleton Pattern used
    private static final DataFactory _instance = new DataFactory();

    private Map<ContinentIndex, Continent> _continentMap = new ConcurrentHashMap<>();
    private Map<CountryIndex, Country> _countryMap = new ConcurrentHashMap<>();
    private Map<String, Country> _countryNameMap = new ConcurrentHashMap<>();
    private Map<String, Player> _playerMap = new ConcurrentHashMap<>(Constants.NUM_PLAYERS);
    private Map<String, Card> _cardMap = new ConcurrentHashMap<>();

    private DataFactory() {
        //creating continentMap first
        createContinentList().stream()
                .forEach((continent) -> {
                    _continentMap.put(continent.getContinentId(), continent);
                });
        _continentMap = Collections.unmodifiableMap(_continentMap);

        //creating countryMap
        createCountryList().stream()
                .forEach((country) -> {
                    _countryMap.put(country.getCountryId(), country);
                    _countryNameMap.put(country.getName(), country);
                    _cardMap.put(country.getName(), new Card(country.getName(), CardType.Territory));
                });
        _cardMap.put("wild1", new Card("wild1", CardType.Wild));
        _cardMap.put("wild2", new Card("wild2", CardType.Wild));
        _countryMap = Collections.unmodifiableMap(_countryMap);
        _cardMap = Collections.unmodifiableMap(_cardMap);
    }

    public static DataFactory getInstance() {
        return _instance;
    }

    /**
     * Gets the Continent map which contains the Continent object as value and
     * ContinentIndex as key
     *
     * @return (Map)unmodifiable of Continents
     */
    public Map<ContinentIndex, Continent> getContinentMap() {
        return _continentMap;
    }

    /**
     * Gets the country map which contains the Country object as value and
     * CountryIndex as key
     *
     * @return (Map)unmodifiable of country
     */
    public Map<CountryIndex, Country> getCountryMap() {
        return _countryMap;
    }

    public Map<String, Country> getCountryNameMap() {
        return _countryNameMap;
    }
    
    public Map<String, Player> getPlayerMap() {
        return _playerMap;
    }

    public Map<String, Card> getCardMap() {
        return _cardMap;
    }

    private List<Continent> createContinentList() {
        List<Continent> continentList = new ArrayList<>(Constants.NUM_CONTINENTS);

        continentList.add(new Continent(ContinentIndex.NAmerica, "North America", 5, Color.RED));
        continentList.add(new Continent(ContinentIndex.Europe, "Europe", 5, Color.BLUE));
        continentList.add(new Continent(ContinentIndex.Asia, "Asia", 7, Color.PINK));
        continentList.add(new Continent(ContinentIndex.Australia, "Australia", 2, Color.GREEN));
        continentList.add(new Continent(ContinentIndex.SAmerica, "South America", 2, Color.YELLOW));
        continentList.add(new Continent(ContinentIndex.Africa, "Africa", 3, Color.DARK_GRAY));

        return continentList;
    }

    private List<Country> createCountryList() {
        List<Country> countryList = new ArrayList<>(Constants.NUM_COUNTRIES);

        countryList.add(new Country(CountryIndex.Ontario, "Ontario", _continentMap.get(ContinentIndex.NAmerica), 191, 150, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Greenland);
                add(CountryIndex.Quebec);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.WUnitedStates);
                add(CountryIndex.Alberta);
                add(CountryIndex.NWTerritory);
            }
        }));

        countryList.add(new Country(CountryIndex.Quebec, "Quebec", _continentMap.get(ContinentIndex.NAmerica), 255, 161, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Greenland);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.Ontario);
            }
        }));

        countryList.add(new Country(CountryIndex.NWTerritory, "NW Territory", _continentMap.get(ContinentIndex.NAmerica), 146, 86, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Greenland);
                add(CountryIndex.Ontario);
                add(CountryIndex.Alberta);
                add(CountryIndex.Alaska);
            }
        }));

        countryList.add(new Country(CountryIndex.Alberta, "Alberta", _continentMap.get(ContinentIndex.NAmerica), 123, 144, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NWTerritory);
                add(CountryIndex.Ontario);
                add(CountryIndex.WUnitedStates);
                add(CountryIndex.Alaska);
            }
        }));

        countryList.add(new Country(CountryIndex.Greenland, "Greenland", _continentMap.get(ContinentIndex.NAmerica), 314, 61, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Iceland);
                add(CountryIndex.Quebec);
                add(CountryIndex.Ontario);
                add(CountryIndex.NWTerritory);
            }
        }));

        countryList.add(new Country(CountryIndex.EUnitedStates, "E United States", _continentMap.get(ContinentIndex.NAmerica), 205, 235, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ontario);
                add(CountryIndex.Quebec);
                add(CountryIndex.CentralAmerica);
                add(CountryIndex.WUnitedStates);
            }
        }));

        countryList.add(new Country(CountryIndex.WUnitedStates, "W United States", _continentMap.get(ContinentIndex.NAmerica), 135, 219, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Alberta);
                add(CountryIndex.Ontario);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.CentralAmerica);
            }
        }));

        countryList.add(new Country(CountryIndex.CentralAmerica, "Central America", _continentMap.get(ContinentIndex.NAmerica), 140, 299, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.WUnitedStates);
                add(CountryIndex.EUnitedStates);
                add(CountryIndex.Venezuela);
            }
        }));

        countryList.add(new Country(CountryIndex.Alaska, "Alaska", _continentMap.get(ContinentIndex.NAmerica), 45, 89, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NWTerritory);
                add(CountryIndex.Alberta);
                add(CountryIndex.Kamchatka);
            }
        }));

        countryList.add(new Country(CountryIndex.GreatBritain, "Great Britain", _continentMap.get(ContinentIndex.Europe), 370, 199, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Iceland);
                add(CountryIndex.Scandinavia);
                add(CountryIndex.NEurope);
                add(CountryIndex.WEurope);
            }
        }));

        countryList.add(new Country(CountryIndex.WEurope, "W Europe", _continentMap.get(ContinentIndex.Europe), 398, 280, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.GreatBritain);
                add(CountryIndex.NEurope);
                add(CountryIndex.SEurope);
                add(CountryIndex.NAfrica);
            }
        }));

        countryList.add(new Country(CountryIndex.SEurope, "S Europe", _continentMap.get(ContinentIndex.Europe), 465, 270, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NEurope);
                add(CountryIndex.Ukraine);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.Egypt);
                add(CountryIndex.WEurope);
            }
        }));

        countryList.add(new Country(CountryIndex.Ukraine, "Ukraine", _continentMap.get(ContinentIndex.Europe), 547, 180, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ural);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.SEurope);
                add(CountryIndex.NEurope);
                add(CountryIndex.Scandinavia);
            }
        }));

        countryList.add(new Country(CountryIndex.NEurope, "N Europe", _continentMap.get(ContinentIndex.Europe), 460, 200, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Scandinavia);
                add(CountryIndex.Ukraine);
                add(CountryIndex.SEurope);
                add(CountryIndex.WEurope);
                add(CountryIndex.GreatBritain);
            }
        }));

        countryList.add(new Country(CountryIndex.Iceland, "Iceland", _continentMap.get(ContinentIndex.Europe), 393, 127, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Scandinavia);
                add(CountryIndex.GreatBritain);
                add(CountryIndex.Greenland);
            }
        }));

        countryList.add(new Country(CountryIndex.Scandinavia, "Scandinavia", _continentMap.get(ContinentIndex.Europe), 463, 122, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ukraine);
                add(CountryIndex.NEurope);
                add(CountryIndex.Iceland);
            }
        }));

        countryList.add(new Country(CountryIndex.Afghanistan, "Afghanistan", _continentMap.get(ContinentIndex.Asia), 628, 227, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ural);
                add(CountryIndex.China);
                add(CountryIndex.India);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.Ukraine);
            }
        }));

        countryList.add(new Country(CountryIndex.India, "India", _continentMap.get(ContinentIndex.Asia), 679, 332, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Afghanistan);
                add(CountryIndex.China);
                add(CountryIndex.Siam);
                add(CountryIndex.MiddleEast);
            }
        }));

        countryList.add(new Country(CountryIndex.MiddleEast, "Middle East", _continentMap.get(ContinentIndex.Asia), 572, 338, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Ukraine);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.India);
                add(CountryIndex.EAfrica);
                add(CountryIndex.Egypt);
                add(CountryIndex.SEurope);
            }
        }));

        countryList.add(new Country(CountryIndex.Japan, "Japan", _continentMap.get(ContinentIndex.Asia), 861, 213, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Mongolia);
                add(CountryIndex.Kamchatka);
            }
        }));

        countryList.add(new Country(CountryIndex.Ural, "Ural", _continentMap.get(ContinentIndex.Asia), 645, 152, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Siberia);
                add(CountryIndex.China);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.Ukraine);
            }
        }));

        countryList.add(new Country(CountryIndex.Yakutsk, "Yakutsk", _continentMap.get(ContinentIndex.Asia), 763, 70, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Kamchatka);
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Siberia);
            }
        }));

        countryList.add(new Country(CountryIndex.Kamchatka, "Kamchatka", _continentMap.get(ContinentIndex.Asia), 827, 94, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Alaska);
                add(CountryIndex.Japan);
                add(CountryIndex.Mongolia);
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Yakutsk);
            }
        }));

        countryList.add(new Country(CountryIndex.Siam, "Siam", _continentMap.get(ContinentIndex.Asia), 751, 360, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.China);
                add(CountryIndex.Indonesia);
                add(CountryIndex.India);
            }
        }));

        countryList.add(new Country(CountryIndex.Irkutsk, "Irkutsk", _continentMap.get(ContinentIndex.Asia), 750, 140, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Yakutsk);
                add(CountryIndex.Kamchatka);
                add(CountryIndex.Mongolia);
                add(CountryIndex.Siberia);
            }
        }));

        countryList.add(new Country(CountryIndex.Siberia, "Siberia", _continentMap.get(ContinentIndex.Asia), 695, 108, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Yakutsk);
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Mongolia);
                add(CountryIndex.China);
                add(CountryIndex.Ural);
            }
        }));

        countryList.add(new Country(CountryIndex.Mongolia, "Mongolia", _continentMap.get(ContinentIndex.Asia), 760, 216, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Irkutsk);
                add(CountryIndex.Kamchatka);
                add(CountryIndex.Japan);
                add(CountryIndex.China);
                add(CountryIndex.Siberia);
            }
        }));

        countryList.add(new Country(CountryIndex.China, "China", _continentMap.get(ContinentIndex.Asia), 735, 277, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Mongolia);
                add(CountryIndex.Siam);
                add(CountryIndex.India);
                add(CountryIndex.Afghanistan);
                add(CountryIndex.Ural);
                add(CountryIndex.Siberia);
            }
        }));

        countryList.add(new Country(CountryIndex.EAustralia, "E Australia", _continentMap.get(ContinentIndex.Australia), 889, 537, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NewGuinea);
                add(CountryIndex.WAustralia);
            }
        }));

        countryList.add(new Country(CountryIndex.NewGuinea, "New Guinea", _continentMap.get(ContinentIndex.Australia), 850, 429, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.EAustralia);
                add(CountryIndex.WAustralia);
                add(CountryIndex.Indonesia);
            }
        }));

        countryList.add(new Country(CountryIndex.WAustralia, "W Australia", _continentMap.get(ContinentIndex.Australia), 813, 526, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NewGuinea);
                add(CountryIndex.EAustralia);
                add(CountryIndex.Indonesia);
            }
        }));

        countryList.add(new Country(CountryIndex.Indonesia, "Indonesia", _continentMap.get(ContinentIndex.Australia), 771, 454, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Siam);
                add(CountryIndex.NewGuinea);
                add(CountryIndex.WAustralia);
            }
        }));

        countryList.add(new Country(CountryIndex.Venezuela, "Venezuela", _continentMap.get(ContinentIndex.SAmerica), 213, 352, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.CentralAmerica);
                add(CountryIndex.Brazil);
                add(CountryIndex.Peru);
            }
        }));

        countryList.add(new Country(CountryIndex.Peru, "Peru", _continentMap.get(ContinentIndex.SAmerica), 221, 426, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Venezuela);
                add(CountryIndex.Brazil);
                add(CountryIndex.Argentina);
            }
        }));

        countryList.add(new Country(CountryIndex.Brazil, "Brazil", _continentMap.get(ContinentIndex.SAmerica), 289, 415, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Venezuela);
                add(CountryIndex.NAfrica);
                add(CountryIndex.Argentina);
                add(CountryIndex.Peru);
            }
        }));

        countryList.add(new Country(CountryIndex.Argentina, "Argentina", _continentMap.get(ContinentIndex.SAmerica), 233, 523, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Peru);
                add(CountryIndex.Brazil);
            }
        }));

        countryList.add(new Country(CountryIndex.Congo, "Congo", _continentMap.get(ContinentIndex.Africa), 496, 462, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.NAfrica);
                add(CountryIndex.EAfrica);
                add(CountryIndex.SAfrica);
            }
        }));

        countryList.add(new Country(CountryIndex.NAfrica, "N Africa", _continentMap.get(ContinentIndex.Africa), 440, 393, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.WEurope);
                add(CountryIndex.SEurope);
                add(CountryIndex.Egypt);
                add(CountryIndex.EAfrica);
                add(CountryIndex.Congo);
                add(CountryIndex.Brazil);
            }
        }));

        countryList.add(new Country(CountryIndex.SAfrica, "S Africa", _continentMap.get(ContinentIndex.Africa), 510, 532, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Congo);
                add(CountryIndex.EAfrica);
                add(CountryIndex.Madagascar);
            }
        }));

        countryList.add(new Country(CountryIndex.Egypt, "Egypt", _continentMap.get(ContinentIndex.Africa), 499, 354, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.SEurope);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.EAfrica);
                add(CountryIndex.NAfrica);
            }
        }));

        countryList.add(new Country(CountryIndex.EAfrica, "E Africa", _continentMap.get(ContinentIndex.Africa), 547, 432, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.Egypt);
                add(CountryIndex.MiddleEast);
                add(CountryIndex.Madagascar);
                add(CountryIndex.SAfrica);
                add(CountryIndex.Congo);
                add(CountryIndex.NAfrica);
            }
        }));

        countryList.add(new Country(CountryIndex.Madagascar, "Madagascar", _continentMap.get(ContinentIndex.Africa), 586, 545, new ArrayList<CountryIndex>() {
            {
                add(CountryIndex.SAfrica);
                add(CountryIndex.EAfrica);
            }
        }));

        return countryList;
    }
}
