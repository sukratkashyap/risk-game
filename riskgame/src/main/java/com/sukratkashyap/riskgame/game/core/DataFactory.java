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

    private Map<ContinentId, Continent> _continentMap = new ConcurrentHashMap<>(Constants.NUM_CONTINENTS);
    private Map<CountryId, Country> _countryIdMap = new ConcurrentHashMap<>(Constants.NUM_COUNTRIES);
    private Map<String, Country> _countryNameMap = new ConcurrentHashMap<>(Constants.NUM_COUNTRIES);
    private Map<String, Country> _countryAbbreviationMap = new ConcurrentHashMap<>(Constants.NUM_COUNTRIES);
    private Map<String, Player> _playerMap = new ConcurrentHashMap<>(Constants.NUM_PLAYERS);
    private Map<String, Card> _cardMap = new ConcurrentHashMap<>(Constants.NUM_CARDS);
    private int _goldenCavalryPosition = 4;

    private DataFactory() {
        //creating continentMap first
        createContinentList().parallelStream()
                .forEach((continent) -> {
                    _continentMap.put(continent.getContinentId(), continent);
                });
        _continentMap = Collections.unmodifiableMap(_continentMap);

        //creating countryMap
        createCountryList().parallelStream()
                .forEach((country) -> {
                    _countryIdMap.put(country.getCountryId(), country);
                    _countryNameMap.put(country.getName(), country);
                    _countryAbbreviationMap.put(country.getAbbreviation(), country);
                });
        _countryIdMap = Collections.unmodifiableMap(_countryIdMap);
        _countryNameMap = Collections.unmodifiableMap(_countryNameMap);
        _countryAbbreviationMap = Collections.unmodifiableMap(_countryAbbreviationMap);

        //creating card map
        createCardList().parallelStream()
                .forEach((card) -> {
                    _cardMap.put(card.getCountryName(), card);
                });
        _cardMap = Collections.unmodifiableMap(_cardMap);

    }

    public static DataFactory getInstance() {
        return _instance;
    }

    public int getAndMoveGoldenCavalryPosition() {
        int currValue = _goldenCavalryPosition;
        _goldenCavalryPosition += 2;
        return currValue;
    }

    /**
     * Gets the Continent map which contains the Continent object as value and
     * ContinentId as key
     *
     * @return (Map)unmodifiable of Continents
     */
    public Map<ContinentId, Continent> getContinentMap() {
        return _continentMap;
    }

    /**
     * Gets the country map which contains the Country object as value and
     * CountryId as key
     *
     * @return (Map)unmodifiable of country
     */
    public Map<CountryId, Country> getCountryIdMap() {
        return _countryIdMap;
    }

    /**
     * Gets the country map which contains the Country object as value and
     * Country name as key
     *
     * @return (Map)unmodifiable of country
     */
    public Map<String, Country> getCountryNameMap() {
        return _countryNameMap;
    }

    /**
     * Gets the country map which contains the Country object as value and
     * Country abbreviation as key
     *
     * @return (Map)unmodifiable of country
     */
    public Map<String, Country> getCountryAbbreviationMap() {
        return _countryAbbreviationMap;
    }

    /**
     * Gets the player map which contains the Player object as value and Player
     * name as key
     *
     * @return (Map)unmodifiable of player
     */
    public Map<String, Player> getPlayerMap() {
        return _playerMap;
    }

    /**
     * Gets the card map which contains the Card object as value and card name
     * as key
     *
     * @return (Map)unmodifiable of card
     */
    public Map<String, Card> getCardMap() {
        return _cardMap;
    }

    private List<Continent> createContinentList() {
        List<Continent> continentList = new ArrayList<>(Constants.NUM_CONTINENTS);

        continentList.add(new Continent(ContinentId.NAmerica, "North America", 5, Color.RED, 9));
        continentList.add(new Continent(ContinentId.Europe, "Europe", 5, Color.BLUE, 7));
        continentList.add(new Continent(ContinentId.Asia, "Asia", 7, Color.PINK, 12));
        continentList.add(new Continent(ContinentId.Australia, "Australia", 2, Color.GREEN, 4));
        continentList.add(new Continent(ContinentId.SAmerica, "South America", 2, Color.YELLOW, 4));
        continentList.add(new Continent(ContinentId.Africa, "Africa", 3, Color.DARK_GRAY, 6));

        return continentList;
    }

    private List<Card> createCardList() {
        List<Card> cardList = new ArrayList<>(Constants.NUM_CARDS);
        cardList.add(new Card(CountryId.Ontario, _countryIdMap.get(CountryId.Ontario).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Quebec, _countryIdMap.get(CountryId.Quebec).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.NWTerritory, _countryIdMap.get(CountryId.NWTerritory).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Alberta, _countryIdMap.get(CountryId.Alberta).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.Greenland, _countryIdMap.get(CountryId.Greenland).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.EUnitedStates, _countryIdMap.get(CountryId.EUnitedStates).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.WUnitedStates, _countryIdMap.get(CountryId.WUnitedStates).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.CentralAmerica, _countryIdMap.get(CountryId.CentralAmerica).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Alaska, _countryIdMap.get(CountryId.Alaska).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.GreatBritain, _countryIdMap.get(CountryId.GreatBritain).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.WEurope, _countryIdMap.get(CountryId.WEurope).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.SEurope, _countryIdMap.get(CountryId.SEurope).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Ukraine, _countryIdMap.get(CountryId.Ukraine).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.NEurope, _countryIdMap.get(CountryId.NEurope).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Iceland, _countryIdMap.get(CountryId.Iceland).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.Scandinavia, _countryIdMap.get(CountryId.Scandinavia).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Afghanistan, _countryIdMap.get(CountryId.Afghanistan).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.India, _countryIdMap.get(CountryId.India).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.MiddleEast, _countryIdMap.get(CountryId.MiddleEast).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Japan, _countryIdMap.get(CountryId.Japan).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.Ural, _countryIdMap.get(CountryId.Ural).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Yakutsk, _countryIdMap.get(CountryId.Yakutsk).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Kamchatka, _countryIdMap.get(CountryId.Kamchatka).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Siam, _countryIdMap.get(CountryId.Siam).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Irkutsk, _countryIdMap.get(CountryId.Irkutsk).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.Siberia, _countryIdMap.get(CountryId.Siberia).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Mongolia, _countryIdMap.get(CountryId.Mongolia).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.China, _countryIdMap.get(CountryId.China).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.EAustralia, _countryIdMap.get(CountryId.EAustralia).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.NewGuinea, _countryIdMap.get(CountryId.NewGuinea).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.WAustralia, _countryIdMap.get(CountryId.WAustralia).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.Indonesia, _countryIdMap.get(CountryId.Indonesia).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Venezuela, _countryIdMap.get(CountryId.Venezuela).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Peru, _countryIdMap.get(CountryId.Peru).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.Brazil, _countryIdMap.get(CountryId.Brazil).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Argentina, _countryIdMap.get(CountryId.Argentina).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.Congo, _countryIdMap.get(CountryId.Congo).getName(), CardType.Artillery));
        cardList.add(new Card(CountryId.NAfrica, _countryIdMap.get(CountryId.NAfrica).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.SAfrica, _countryIdMap.get(CountryId.SAfrica).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Egypt, _countryIdMap.get(CountryId.Egypt).getName(), CardType.Infantry));
        cardList.add(new Card(CountryId.EAfrica, _countryIdMap.get(CountryId.EAfrica).getName(), CardType.Cavalry));
        cardList.add(new Card(CountryId.Madagascar, _countryIdMap.get(CountryId.Madagascar).getName(), CardType.Infantry));
        cardList.add(new Card(null, "", CardType.Wild));
        cardList.add(new Card(null, "", CardType.Wild));
        return cardList;
    }

    private List<Country> createCountryList() {
        List<Country> countryList = new ArrayList<>(Constants.NUM_COUNTRIES);

        countryList.add(new Country(CountryId.Ontario, "ONT", "Ontario", _continentMap.get(ContinentId.NAmerica), 191, 150, new ArrayList<CountryId>() {
            {
                add(CountryId.Greenland);
                add(CountryId.Quebec);
                add(CountryId.EUnitedStates);
                add(CountryId.WUnitedStates);
                add(CountryId.Alberta);
                add(CountryId.NWTerritory);
            }
        }));

        countryList.add(new Country(CountryId.Quebec, "QBE", "Quebec", _continentMap.get(ContinentId.NAmerica), 255, 161, new ArrayList<CountryId>() {
            {
                add(CountryId.Greenland);
                add(CountryId.EUnitedStates);
                add(CountryId.Ontario);
            }
        }));

        countryList.add(new Country(CountryId.NWTerritory, "NTY", "NW Territory", _continentMap.get(ContinentId.NAmerica), 146, 86, new ArrayList<CountryId>() {
            {
                add(CountryId.Greenland);
                add(CountryId.Ontario);
                add(CountryId.Alberta);
                add(CountryId.Alaska);
            }
        }));

        countryList.add(new Country(CountryId.Alberta, "ALB", "Alberta", _continentMap.get(ContinentId.NAmerica), 123, 144, new ArrayList<CountryId>() {
            {
                add(CountryId.NWTerritory);
                add(CountryId.Ontario);
                add(CountryId.WUnitedStates);
                add(CountryId.Alaska);
            }
        }));

        countryList.add(new Country(CountryId.Greenland, "GLD", "Greenland", _continentMap.get(ContinentId.NAmerica), 314, 61, new ArrayList<CountryId>() {
            {
                add(CountryId.Iceland);
                add(CountryId.Quebec);
                add(CountryId.Ontario);
                add(CountryId.NWTerritory);
            }
        }));

        countryList.add(new Country(CountryId.EUnitedStates, "EUS", "E United States", _continentMap.get(ContinentId.NAmerica), 205, 235, new ArrayList<CountryId>() {
            {
                add(CountryId.Ontario);
                add(CountryId.Quebec);
                add(CountryId.CentralAmerica);
                add(CountryId.WUnitedStates);
            }
        }));

        countryList.add(new Country(CountryId.WUnitedStates, "WUS", "W United States", _continentMap.get(ContinentId.NAmerica), 135, 219, new ArrayList<CountryId>() {
            {
                add(CountryId.Alberta);
                add(CountryId.Ontario);
                add(CountryId.EUnitedStates);
                add(CountryId.CentralAmerica);
            }
        }));

        countryList.add(new Country(CountryId.CentralAmerica, "CAM", "Central America", _continentMap.get(ContinentId.NAmerica), 140, 299, new ArrayList<CountryId>() {
            {
                add(CountryId.WUnitedStates);
                add(CountryId.EUnitedStates);
                add(CountryId.Venezuela);
            }
        }));

        countryList.add(new Country(CountryId.Alaska, "ALA", "Alaska", _continentMap.get(ContinentId.NAmerica), 45, 89, new ArrayList<CountryId>() {
            {
                add(CountryId.NWTerritory);
                add(CountryId.Alberta);
                add(CountryId.Kamchatka);
            }
        }));

        countryList.add(new Country(CountryId.GreatBritain, "GBP", "Great Britain", _continentMap.get(ContinentId.Europe), 370, 199, new ArrayList<CountryId>() {
            {
                add(CountryId.Iceland);
                add(CountryId.Scandinavia);
                add(CountryId.NEurope);
                add(CountryId.WEurope);
            }
        }));

        countryList.add(new Country(CountryId.WEurope, "WEU", "W Europe", _continentMap.get(ContinentId.Europe), 398, 280, new ArrayList<CountryId>() {
            {
                add(CountryId.GreatBritain);
                add(CountryId.NEurope);
                add(CountryId.SEurope);
                add(CountryId.NAfrica);
            }
        }));

        countryList.add(new Country(CountryId.SEurope, "SEU", "S Europe", _continentMap.get(ContinentId.Europe), 465, 270, new ArrayList<CountryId>() {
            {
                add(CountryId.NEurope);
                add(CountryId.Ukraine);
                add(CountryId.MiddleEast);
                add(CountryId.Egypt);
                add(CountryId.WEurope);
            }
        }));

        countryList.add(new Country(CountryId.Ukraine, "UKR", "Ukraine", _continentMap.get(ContinentId.Europe), 547, 180, new ArrayList<CountryId>() {
            {
                add(CountryId.Ural);
                add(CountryId.Afghanistan);
                add(CountryId.MiddleEast);
                add(CountryId.SEurope);
                add(CountryId.NEurope);
                add(CountryId.Scandinavia);
            }
        }));

        countryList.add(new Country(CountryId.NEurope, "NEU", "N Europe", _continentMap.get(ContinentId.Europe), 460, 200, new ArrayList<CountryId>() {
            {
                add(CountryId.Scandinavia);
                add(CountryId.Ukraine);
                add(CountryId.SEurope);
                add(CountryId.WEurope);
                add(CountryId.GreatBritain);
            }
        }));

        countryList.add(new Country(CountryId.Iceland, "ICE", "Iceland", _continentMap.get(ContinentId.Europe), 393, 127, new ArrayList<CountryId>() {
            {
                add(CountryId.Scandinavia);
                add(CountryId.GreatBritain);
                add(CountryId.Greenland);
            }
        }));

        countryList.add(new Country(CountryId.Scandinavia, "SCA", "Scandinavia", _continentMap.get(ContinentId.Europe), 463, 122, new ArrayList<CountryId>() {
            {
                add(CountryId.Ukraine);
                add(CountryId.NEurope);
                add(CountryId.Iceland);
            }
        }));

        countryList.add(new Country(CountryId.Afghanistan, "AFG", "Afghanistan", _continentMap.get(ContinentId.Asia), 628, 227, new ArrayList<CountryId>() {
            {
                add(CountryId.Ural);
                add(CountryId.China);
                add(CountryId.India);
                add(CountryId.MiddleEast);
                add(CountryId.Ukraine);
            }
        }));

        countryList.add(new Country(CountryId.India, "IND", "India", _continentMap.get(ContinentId.Asia), 679, 332, new ArrayList<CountryId>() {
            {
                add(CountryId.Afghanistan);
                add(CountryId.China);
                add(CountryId.Siam);
                add(CountryId.MiddleEast);
            }
        }));

        countryList.add(new Country(CountryId.MiddleEast, "MIE", "Middle East", _continentMap.get(ContinentId.Asia), 572, 338, new ArrayList<CountryId>() {
            {
                add(CountryId.Ukraine);
                add(CountryId.Afghanistan);
                add(CountryId.India);
                add(CountryId.EAfrica);
                add(CountryId.Egypt);
                add(CountryId.SEurope);
            }
        }));

        countryList.add(new Country(CountryId.Japan, "JAP", "Japan", _continentMap.get(ContinentId.Asia), 861, 213, new ArrayList<CountryId>() {
            {
                add(CountryId.Mongolia);
                add(CountryId.Kamchatka);
            }
        }));

        countryList.add(new Country(CountryId.Ural, "URA", "Ural", _continentMap.get(ContinentId.Asia), 645, 152, new ArrayList<CountryId>() {
            {
                add(CountryId.Siberia);
                add(CountryId.China);
                add(CountryId.Afghanistan);
                add(CountryId.Ukraine);
            }
        }));

        countryList.add(new Country(CountryId.Yakutsk, "YAK", "Yakutsk", _continentMap.get(ContinentId.Asia), 763, 70, new ArrayList<CountryId>() {
            {
                add(CountryId.Kamchatka);
                add(CountryId.Irkutsk);
                add(CountryId.Siberia);
            }
        }));

        countryList.add(new Country(CountryId.Kamchatka, "KAM", "Kamchatka", _continentMap.get(ContinentId.Asia), 827, 94, new ArrayList<CountryId>() {
            {
                add(CountryId.Alaska);
                add(CountryId.Japan);
                add(CountryId.Mongolia);
                add(CountryId.Irkutsk);
                add(CountryId.Yakutsk);
            }
        }));

        countryList.add(new Country(CountryId.Siam, "SIA", "Siam", _continentMap.get(ContinentId.Asia), 751, 360, new ArrayList<CountryId>() {
            {
                add(CountryId.China);
                add(CountryId.Indonesia);
                add(CountryId.India);
            }
        }));

        countryList.add(new Country(CountryId.Irkutsk, "IRK", "Irkutsk", _continentMap.get(ContinentId.Asia), 750, 140, new ArrayList<CountryId>() {
            {
                add(CountryId.Yakutsk);
                add(CountryId.Kamchatka);
                add(CountryId.Mongolia);
                add(CountryId.Siberia);
            }
        }));

        countryList.add(new Country(CountryId.Siberia, "SIA", "Siberia", _continentMap.get(ContinentId.Asia), 695, 108, new ArrayList<CountryId>() {
            {
                add(CountryId.Yakutsk);
                add(CountryId.Irkutsk);
                add(CountryId.Mongolia);
                add(CountryId.China);
                add(CountryId.Ural);
            }
        }));

        countryList.add(new Country(CountryId.Mongolia, "MOG", "Mongolia", _continentMap.get(ContinentId.Asia), 760, 216, new ArrayList<CountryId>() {
            {
                add(CountryId.Irkutsk);
                add(CountryId.Kamchatka);
                add(CountryId.Japan);
                add(CountryId.China);
                add(CountryId.Siberia);
            }
        }));

        countryList.add(new Country(CountryId.China, "CHN", "China", _continentMap.get(ContinentId.Asia), 735, 277, new ArrayList<CountryId>() {
            {
                add(CountryId.Mongolia);
                add(CountryId.Siam);
                add(CountryId.India);
                add(CountryId.Afghanistan);
                add(CountryId.Ural);
                add(CountryId.Siberia);
            }
        }));

        countryList.add(new Country(CountryId.EAustralia, "EAU", "E Australia", _continentMap.get(ContinentId.Australia), 889, 537, new ArrayList<CountryId>() {
            {
                add(CountryId.NewGuinea);
                add(CountryId.WAustralia);
            }
        }));

        countryList.add(new Country(CountryId.NewGuinea, "NWG", "New Guinea", _continentMap.get(ContinentId.Australia), 850, 429, new ArrayList<CountryId>() {
            {
                add(CountryId.EAustralia);
                add(CountryId.WAustralia);
                add(CountryId.Indonesia);
            }
        }));

        countryList.add(new Country(CountryId.WAustralia, "WAU", "W Australia", _continentMap.get(ContinentId.Australia), 813, 526, new ArrayList<CountryId>() {
            {
                add(CountryId.NewGuinea);
                add(CountryId.EAustralia);
                add(CountryId.Indonesia);
            }
        }));

        countryList.add(new Country(CountryId.Indonesia, "INO", "Indonesia", _continentMap.get(ContinentId.Australia), 771, 454, new ArrayList<CountryId>() {
            {
                add(CountryId.Siam);
                add(CountryId.NewGuinea);
                add(CountryId.WAustralia);
            }
        }));

        countryList.add(new Country(CountryId.Venezuela, "VEN", "Venezuela", _continentMap.get(ContinentId.SAmerica), 213, 352, new ArrayList<CountryId>() {
            {
                add(CountryId.CentralAmerica);
                add(CountryId.Brazil);
                add(CountryId.Peru);
            }
        }));

        countryList.add(new Country(CountryId.Peru, "PEU", "Peru", _continentMap.get(ContinentId.SAmerica), 221, 426, new ArrayList<CountryId>() {
            {
                add(CountryId.Venezuela);
                add(CountryId.Brazil);
                add(CountryId.Argentina);
            }
        }));

        countryList.add(new Country(CountryId.Brazil, "BRA", "Brazil", _continentMap.get(ContinentId.SAmerica), 289, 415, new ArrayList<CountryId>() {
            {
                add(CountryId.Venezuela);
                add(CountryId.NAfrica);
                add(CountryId.Argentina);
                add(CountryId.Peru);
            }
        }));

        countryList.add(new Country(CountryId.Argentina, "ARG", "Argentina", _continentMap.get(ContinentId.SAmerica), 233, 523, new ArrayList<CountryId>() {
            {
                add(CountryId.Peru);
                add(CountryId.Brazil);
            }
        }));

        countryList.add(new Country(CountryId.Congo, "CON", "Congo", _continentMap.get(ContinentId.Africa), 496, 462, new ArrayList<CountryId>() {
            {
                add(CountryId.NAfrica);
                add(CountryId.EAfrica);
                add(CountryId.SAfrica);
            }
        }));

        countryList.add(new Country(CountryId.NAfrica, "NAF", "N Africa", _continentMap.get(ContinentId.Africa), 440, 393, new ArrayList<CountryId>() {
            {
                add(CountryId.WEurope);
                add(CountryId.SEurope);
                add(CountryId.Egypt);
                add(CountryId.EAfrica);
                add(CountryId.Congo);
                add(CountryId.Brazil);
            }
        }));

        countryList.add(new Country(CountryId.SAfrica, "SAF", "S Africa", _continentMap.get(ContinentId.Africa), 510, 532, new ArrayList<CountryId>() {
            {
                add(CountryId.Congo);
                add(CountryId.EAfrica);
                add(CountryId.Madagascar);
            }
        }));

        countryList.add(new Country(CountryId.Egypt, "EGY", "Egypt", _continentMap.get(ContinentId.Africa), 499, 354, new ArrayList<CountryId>() {
            {
                add(CountryId.SEurope);
                add(CountryId.MiddleEast);
                add(CountryId.EAfrica);
                add(CountryId.NAfrica);
            }
        }));

        countryList.add(new Country(CountryId.EAfrica, "EAF", "E Africa", _continentMap.get(ContinentId.Africa), 547, 432, new ArrayList<CountryId>() {
            {
                add(CountryId.Egypt);
                add(CountryId.MiddleEast);
                add(CountryId.Madagascar);
                add(CountryId.SAfrica);
                add(CountryId.Congo);
                add(CountryId.NAfrica);
            }
        }));

        countryList.add(new Country(CountryId.Madagascar, "MAG", "Madagascar", _continentMap.get(ContinentId.Africa), 586, 545, new ArrayList<CountryId>() {
            {
                add(CountryId.SAfrica);
                add(CountryId.EAfrica);
            }
        }));

        return countryList;
    }
}
