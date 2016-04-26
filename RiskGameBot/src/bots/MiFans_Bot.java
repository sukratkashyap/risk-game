package bots;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import riskgamebot.BoardAPI;
import riskgamebot.Bot;
import riskgamebot.Deck;
import riskgamebot.GameData;
import riskgamebot.PlayerAPI;

// put your code here
//MiFans
/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description Bot implemented by MiFans
 */
public class MiFans_Bot implements Bot {

    // The public API of YourTeamName must not change
    // You cannot change any other classes
    // YourTeamName may not alter the state of the board or the player objects
    // It may only inspect the state of the board and the player objects
    // So you can use player.getNumUnits() but you can't use player.addUnits(10000), for example
    private BoardAPI _board;
    private PlayerAPI _player;
    private List<Country> _countries = new ArrayList<>();
    private List<Integer> _continents = new ArrayList<>();
    private Comparator<Country> numUnitCompareDescending = (y, x) -> Integer.compare(x.getNumUnit(), y.getNumUnit());
    private Comparator<Country> attackRatioCompare = (x, y) -> Double.compare(x.attackRatio(), y.attackRatio());
    private Comparator<Country> attackRatioCompareDescending = (y, x) -> Double.compare(x.attackRatio(), y.attackRatio());
    private final int MINIMUM_NUM_UNITS = 3;
    private Random rand = new Random();
    private final int AUS_ID = 3;
    private final int SA_ID = 4;

    public MiFans_Bot(BoardAPI inBoard, PlayerAPI inPlayer) {
        _board = inBoard;
        _player = inPlayer;
        for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
            _countries.add(new Country(i));
        }
        for (int i = 0; i < GameData.NUM_CONTINENTS; i++) {
            _continents.add(i);
        }
    }

    @Override
    public String getName() {
        return ("MiFans_BOT");
    }

    @Override
    public String getReinforcement() {
        String command = "";
        List<Country> external = getExternalCountryList(_player.getId());
        external = external.stream()
                .sorted(attackRatioCompare)
                .collect(Collectors.toList());

        //australia and south america preference
        Optional<Country> aus = external.stream()
                .filter(c -> c.getContinent() == AUS_ID).findFirst();
        Optional<Country> sa = external.stream()
                .filter(c -> c.getContinent() == SA_ID).findFirst();

        int numUnits = _player.getNumUnits();
        int reinforce = 0;
        Country to;
        if (aus.isPresent() && !isDefiniteWinnable(aus.get())) {
            to = aus.get();
            reinforce = numUnits < 4 ? numUnits : numUnits / 2;
        } else if (sa.isPresent() && !isDefiniteWinnable(sa.get())) {
            to = sa.get();
            reinforce = numUnits < 4 ? numUnits : numUnits / 2;
        } else if (!external.isEmpty()) {
            Country get = external.get(0);
            to = get;
            int unitsNeeded = getUnitsNeeded(get.attackRatio(), get.getNumUnit());
            if (unitsNeeded == 0) {
                reinforce = rand.nextInt(numUnits) + 1;
            } else if (unitsNeeded > numUnits) {
                reinforce = numUnits;
            } else {
                reinforce = unitsNeeded;
            }
        } else {
            List<Country> countryList = getCountryList(_player.getId());
            to = countryList.get(rand.nextInt(countryList.size()));
            reinforce = rand.nextInt(numUnits) + 1;
        }
        command = to.getName() + " " + reinforce;
        return (command);
    }

    @Override
    public String getPlacement(int forPlayer) {
        String command = "";
        Stream<Country> stream = getCountryList(forPlayer).stream();
        List<Country> myCountries = getCountryList(_player.getId());
        Optional<Country> aus = myCountries.stream()
                .filter(c -> c.getContinent() == AUS_ID).findFirst();
        Optional<Country> sa = myCountries.stream()
                .filter(c -> c.getContinent() == SA_ID).findFirst();
        if (aus.isPresent()) {
            stream = stream.filter(c -> c.getContinent() != AUS_ID);
        }
        if (sa.isPresent()) {
            stream = stream.filter(c -> c.getContinent() != SA_ID);
        }
        List<Country> collect = stream.collect(Collectors.toList());
        command = collect.get(rand.nextInt(collect.size())).getName() + " 1";
        return (command);
    }

    @Override
    public String getCardExchange() {
        String command;
        int artillery = (int) _player.getCards().stream()
                .filter(c -> c.getInsigniaId() == Deck.ARTILLERY).count();
        int cavalry = (int) _player.getCards().stream()
                .filter(c -> c.getInsigniaId() == Deck.CAVALRY).count();
        int infantry = (int) _player.getCards().stream()
                .filter(c -> c.getInsigniaId() == Deck.INFANTRY).count();
        int wildCard = (int) _player.getCards().stream()
                .filter(c -> c.getInsigniaId() == Deck.WILD_CARD).count();

        if (artillery + cavalry + infantry + wildCard < 5) {
            command = "skip";
        } else if (artillery >= 3) {
            command = "AAA";
        } else if (cavalry >= 3) {
            command = "CCC";
        } else if (infantry >= 3) {
            command = "III";
        } else if (artillery > 0 && cavalry > 0 && infantry > 0) {
            command = "IAC";
        } else if (artillery > 0 && cavalry > 0 && wildCard > 0) {
            command = "ACW";
        } else if (artillery > 0 && infantry > 0 && wildCard > 0) {
            command = "AIW";
        } else if (cavalry > 0 && infantry > 0 && wildCard > 0) {
            command = "CIW";
        } else if (artillery + wildCard >= 3) {
            command = new String(new char[artillery]).replace("\0", "A") + new String(new char[3 - artillery]).replace("\0", "W");
        } else if (cavalry + wildCard >= 3) {
            command = new String(new char[cavalry]).replace("\0", "C") + new String(new char[3 - cavalry]).replace("\0", "W");
        } else if (infantry + wildCard >= 3) {
            command = new String(new char[infantry]).replace("\0", "I") + new String(new char[3 - infantry]).replace("\0", "W");
        } else if (wildCard >= 3) {
            command = "WWW";
        } else {
            command = "skip";
        }
        return command;
    }

    @Override
    public String getBattle() {
        String command = "";
        List<Country> external = getExternalCountryList(_player.getId());
        external = external.stream()
                .sorted(attackRatioCompareDescending)
                .collect(Collectors.toList());

        //australia and south america preference
        Optional<Country> aus = external.stream()
                .filter(c -> c.getContinent() == AUS_ID).findFirst();
        Optional<Country> sa = external.stream()
                .filter(c -> c.getContinent() == SA_ID).findFirst();

        Country attack = null;
        Country defend = null;
        if (aus.isPresent() && isWinnable(aus.get())) {
            attack = aus.get();
        } else if (sa.isPresent() && isWinnable(sa.get())) {
            attack = sa.get();
        } else if (!external.isEmpty()) {
            Country get = external.get(0);
            if (isWinnable(get)) {
                attack = get;
            }
        }

        if (attack != null) {
            final Country at = attack;
            defend = attack.getAttackableCountryList().stream()
                    .sorted((y, x) -> Double.compare(at.attackRatio(x), at.attackRatio(y)))
                    .findFirst().get();
            command = String.format("%1$s %2$s %3$d", attack.getName(),
                    defend.getName(), attack.getAttackUnits());
        } else {
            command = "skip";
        }
        return (command);
    }

    @Override
    public String getDefence(int countryId) {
        String command = "";
        int numUnits = _board.getNumUnits(countryId);
        if (numUnits > 2) {
            command = "2";
        } else {
            command = "1";
        }
        return (command);
    }

    @Override
    public String getMoveIn(int attackCountryId) {
        String command = "";
        Country won = getCountry(attackCountryId);
        int units = won.getNumUnit() / 2;
        command = Integer.toString(units);
        return (command);
    }

    @Override
    public String getFortify() {
        String command = "";
        List<Country> internalCountry = getInternalCountryList(_player.getId());
        List<Country> externalCountry = getExternalCountryList(_player.getId());
        if (!internalCountry.isEmpty() && !externalCountry.isEmpty()) {
            externalCountry = externalCountry.stream()
                    .sorted(attackRatioCompare)
                    .collect(Collectors.toList());
            for (Country ext : externalCountry) {
                Optional<Country> findFirst = internalCountry.stream()
                        .filter(c -> c.isConnected(ext) && c.getNumUnit() > MINIMUM_NUM_UNITS)
                        .sorted(numUnitCompareDescending).findFirst();
                if (findFirst.isPresent()) {
                    int units = 0;
                    Country from = findFirst.get();
                    int unitsNeeded = getUnitsNeeded(ext.attackRatio(), ext.getNumUnit());
                    if (unitsNeeded == 0) {
                        units = rand.nextInt(from.getNumUnit() - MINIMUM_NUM_UNITS) + 1;
                    } else if (from.getNumUnit() - MINIMUM_NUM_UNITS >= unitsNeeded) {
                        units = unitsNeeded;
                    } else {
                        units = from.getNumUnit() - MINIMUM_NUM_UNITS;
                    }
                    command = String.format("%1$s %2$s %3$d", from.getName(),
                            ext.getName(), units);
                    break;
                }
            }
        }
        if (command.length() == 0) {
            command = "skip";
        }
        return (command);
    }

    private Country getCountry(int countryId) {
        return _countries.get(countryId);
    }

    private List<Country> getCountryList(int playerId) {
        return _countries.stream()
                .filter(c -> c.isOccupier(playerId))
                .collect(Collectors.toList());
    }

    private List<Country> getInternalCountryList(int playerId) {
        List<Country> internalCountry = new ArrayList<>();
        for (Country country : getCountryList(playerId)) {
            boolean isInternal = true;
            for (Country adj : country.getAdjacentList()) {
                if (!adj.isOccupier(playerId)) {
                    isInternal = false;
                    break;
                }
            }
            if (isInternal) {
                internalCountry.add(country);
            }
        }
        return internalCountry;
    }

    private List<Country> getExternalCountryList(int playerId) {
        List<Country> externalCountry = new ArrayList<>();
        for (Country country : getCountryList(playerId)) {
            for (Country adj : country.getAdjacentList()) {
                if (!adj.isOccupier(playerId)) {
                    externalCountry.add(country);
                    break;
                }
            }
        }
        return externalCountry;
    }

    private int getUnitsNeeded(double attackRatio, int currentUnits) {
        final double ratio = 0.6;
        int unitsNeeded = 0;
        double defence = ((1 - attackRatio) * currentUnits) / attackRatio;
        double unitsShouldBeThere = (ratio * defence) / (1 - ratio);
        if (unitsShouldBeThere > currentUnits) {
            unitsNeeded = (int) (unitsShouldBeThere - currentUnits);
        }
        return unitsNeeded;
    }

    private boolean isWinnable(Country c) {
        double attackRatio = c.attackRatio();
        if (c.getNumUnit() < 6) {
            return attackRatio > 0.5;
        } else {
            return attackRatio >= 0.5;
        }
    }

    private boolean isDefiniteWinnable(Country c) {
        double attackRatio = c.attackRatio();
        if (c.getNumUnit() < 6) {
            return attackRatio > 0.7;
        } else {
            return attackRatio >= 0.7;
        }
    }

    class Country {

        private final int id;

        public Country(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return GameData.COUNTRY_NAMES[id].replaceAll("\\s", "");
        }

        public int getOccupier() {
            return _board.getOccupier(id);
        }

        public boolean isOccupier(int playerId) {
            return _board.getOccupier(id) == playerId;
        }

        public int getNumUnit() {
            return _board.getNumUnits(id);
        }

        public boolean isConnected(Country c) {
            return _board.isConnected(id, c.getId());
        }

        public boolean isAdjacent(Country c) {
            return _board.isAdjacent(id, c.getId());
        }

        public boolean isCoveredBy(int playerId) {
            return getAdjacentList().stream()
                    .allMatch(c -> c.isOccupier(playerId));
        }

        public double attackRatio() {
            return getAttackableCountryList().stream()
                    .mapToDouble(c -> this.attackRatio(c))
                    .min().getAsDouble();
        }

        //higher the attack ratio higher chances of winning
        public double attackRatio(Country c) {
            double attack = this.getNumUnit();
            double defence = c.getNumUnit();
            return (attack / (attack + defence));
        }

        public List<Country> getAdjacentList() {
            return _countries.stream()
                    .filter(c -> this.isAdjacent(c))
                    .collect(Collectors.toList());
        }

        public List<Country> getConnectedList() {
            return _countries.stream()
                    .filter(c -> this.isConnected(c))
                    .collect(Collectors.toList());
        }

        public List<Country> getAttackableCountryList() {
            return getAdjacentList().stream()
                    .filter(c -> c.getOccupier() != this.getOccupier())
                    .collect(Collectors.toList());
        }

        public int getContinent() {
            return GameData.CONTINENT_IDS[id];
        }

        public int getAttackUnits() {
            int numUnit = getNumUnit();
            if (numUnit > 4) {
                return 3;
            } else if (numUnit == 4) {
                return 2;
            } else {
                return 1;
            }
        }
    }
}
