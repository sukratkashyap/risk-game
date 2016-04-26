package bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import riskgamebot.BoardAPI;
import riskgamebot.Bot;
import riskgamebot.Deck;
import riskgamebot.GameData;
import riskgamebot.PlayerAPI;

public class RandomBot implements Bot {

    private BoardAPI board;
    private PlayerAPI player;
    private int id;
    private Random rand = new Random();

    public RandomBot(BoardAPI inBoard, PlayerAPI inPlayer) {
        board = inBoard;
        player = inPlayer;
        id = player.getId();
        // put your code here
        return;
    }

    @Override
    public String getName() {
        return "Random Bot";
    }

    @Override
    public String getReinforcement() {
        String command = "";
        if (player.getNumUnits() > 0) {
            List<String> myCountries = myCountries(id);
            if (myCountries.isEmpty()) {
                command = "skip";
            } else {
                command = myCountries.get(rand.nextInt(myCountries.size()));
                command = command.replaceAll("\\s", "");
                command += " " + (rand.nextInt(player.getNumUnits()) + 1);
            }
        }
        return (command);
    }

    @Override
    public String getPlacement(int forPlayer) {
        String command = "";
        List<String> myCountries = myCountries(forPlayer);
        command = myCountries.get(rand.nextInt(myCountries.size()));
        command = command.replaceAll("\\s", "");

        command += " 1";
        return (command);
    }

    @Override
    public String getCardExchange() {
        String command = "";
        int artillery = (int) player.getCards().stream().filter(c -> c.getInsigniaId() == Deck.ARTILLERY).count();
        int cavalry = (int) player.getCards().stream().filter(c -> c.getInsigniaId() == Deck.CAVALRY).count();
        int infantry = (int) player.getCards().stream().filter(c -> c.getInsigniaId() == Deck.INFANTRY).count();
        int wildCard = (int) player.getCards().stream().filter(c -> c.getInsigniaId() == Deck.WILD_CARD).count();
        if (artillery >= 3) {
            command = "AAA";
        } else if (cavalry >= 3) {
            command = "CCC";
        } else if (infantry >= 3) {
            command = "III";
        } else if (artillery > 0 && cavalry > 0 && infantry > 0) {
            command = "IAC";
        } else if (artillery + wildCard >= 3) {
            command = new String(new char[artillery]).replace("\0", "A") + new String(new char[3 - artillery]).replace("\0", "W");
        } else if (cavalry + wildCard >= 3) {
            command = new String(new char[cavalry]).replace("\0", "C") + new String(new char[3 - cavalry]).replace("\0", "W");
        } else if (infantry + wildCard >= 3) {
            command = new String(new char[infantry]).replace("\0", "I") + new String(new char[3 - infantry]).replace("\0", "W");
        } else if (wildCard == 3) {
            command = "WWW";
        } else {
            command = "skip";
        }
        return command;
    }

    @Override
    public String getBattle() {
        String command = "";
        List<String> attackCountry = getCountryThatCanBeAttacked(id);
        if (rand.nextInt(5) > 0 && attackCountry.size() > 0) {
            command = attackCountry.get(rand.nextInt(attackCountry.size()));
        } else {
            command = "skip";
        }
        return command;
    }

    @Override
    public String getDefence(int countryId) {
        String command = "";
        int numUnits = board.getNumUnits(countryId);
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
        int numUnits = board.getNumUnits(attackCountryId);
        command = Integer.toString(rand.nextInt(numUnits - 1) + 1);
        return (command);
    }

    @Override
    public String getFortify() {
        return fortify(id);
    }

    private List<String> myCountries(int forPlayer) {
        List<String> countryList = new ArrayList<>();
        for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
            if (board.getOccupier(i) == forPlayer) {
                countryList.add(GameData.COUNTRY_NAMES[i].replaceAll("\\s", ""));
            }
        }
        return countryList;
    }

    private List<String> getOtherCountries(int forPlayer) {
        List<String> countryList = new ArrayList<>();
        for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
            if (board.getOccupier(i) != forPlayer && board.getOccupier(i) != 0) {
                countryList.add(GameData.COUNTRY_NAMES[i].replaceAll("\\s", ""));
            }
        }
        return countryList;
    }

    private List<String> getCountryThatCanBeAttacked(int forPlayer) {
        List<Integer> countryList = new ArrayList<>();
        for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
            if (board.getOccupier(i) == forPlayer && board.getNumUnits(i) > 1) {
                countryList.add(i);
            }
        }
        List<Integer> attackCountry = new ArrayList<>();
        List<Integer> defenceCountry = new ArrayList<>();
        List<String> attackList = new ArrayList<>();
        for (Integer i : countryList) {
            for (int j = 0; j < GameData.NUM_COUNTRIES; j++) {
                if (i != j && board.isAdjacent(i, j)) {
                    if (board.getOccupier(j) != forPlayer) {
                        attackCountry.add(i);
                        defenceCountry.add(j);
                        int units = board.getNumUnits(i) < 4 ? 1 : 3;
                        attackList.add(GameData.COUNTRY_NAMES[i].replaceAll("\\s", "")
                                + " " + GameData.COUNTRY_NAMES[j].replaceAll("\\s", "") + " " + units);
                    }
                }
            }
        }
        return attackList;
    }

    private String fortify(int forPlayer) {
        String command = "";
        List<Integer> myCountries = new ArrayList<>();
        for (int i = 0; i < GameData.NUM_COUNTRIES; i++) {
            if (board.getOccupier(i) == forPlayer) {
                myCountries.add(i);
            }
        }

        List<Integer> canAttack = new ArrayList<>();
        for (Integer i : myCountries) {
            for (int j = 0; j < GameData.NUM_COUNTRIES; j++) {
                if (i != j && board.isAdjacent(i, j) && board.getOccupier(j) != forPlayer) {
                    if (board.getNumUnits(i) < 5) {
                        canAttack.add(i);
                    }
                }
            }
        }

        for (Integer i : canAttack) {
            for (Integer j : myCountries) {
                if (i == j) {
                    continue;
                }
                if (board.getNumUnits(j) > 5 && board.isConnected(i, j)) {
                    command = GameData.COUNTRY_NAMES[j].replaceAll("\\s", "") + " "
                            + GameData.COUNTRY_NAMES[i].replaceAll("\\s", "") + " "
                            + (rand.nextInt(board.getNumUnits(j) - 3) + 1);
                    break;
                }
                if (command.length() > 0) {
                    break;
                }
            }
        }
        if (command.length() == 0) {
            command = "skip";
        }
        return command;
    }
}
