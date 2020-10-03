package riskgametest;

import com.sukratkashyap.riskgame.game.core.Constants;
import com.sukratkashyap.riskgame.game.core.Country;
import com.sukratkashyap.riskgame.game.core.Player;
import com.sukratkashyap.riskgame.game.core.PlayerType;
import com.sukratkashyap.riskgame.game.data.GetQuery;
import com.sukratkashyap.riskgame.game.graphic.GUI;
import com.sukratkashyap.riskgame.game.graphic.RiskFrame;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this is the app test which test the whole app by acting as
 * virtual user. To run this please add a break point and then run as the test
 * is not yet complete.
 */
public class AppTest {

    private RiskGameTest riskGameTest;
    private RiskFrame riskFrame;
    private GUI gui;
    private Thread gameThread;
    private GetQuery gq;

    public AppTest() {
        riskGameTest = new RiskGameTest();
        gameThread = new Thread(riskGameTest);
        gui = riskGameTest.getGUI();
        riskFrame = gui.getRiskFrame();
        gq = new GetQuery();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws InterruptedException {
        gameThread.start();
        Thread.sleep(3000); //giving time to load fully
    }

    @After
    public void tearDown() {
    }

    private void setCommand(String command) {
        riskFrame.getCommandPanel().runCommand(command);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void mainTest() {
        enterPlayerNameTest();
        setReInforcements();
        List<Player> orderedMainPlayerList = gq.getOrderedMainPlayerList();
        while (orderedMainPlayerList.size() == 2) {
            for (Player player : orderedMainPlayerList) {
                exchangeCards(player);
                setInforcements(player);
                attackOrNot(player);
                fortifyOrNot(player);
            }
            sleep(2);
            orderedMainPlayerList = gq.getOrderedMainPlayerList();
        }
    }

    private void enterPlayerNameTest() {
        setCommand("Ben");
        setCommand("Ben");
        setCommand("Sukrat");
        sleep(1);
        assertTrue("Name succesfully entered", gq.getPlayerList().size() == 6);
    }

    private void setReInforcements() {
        for (int i = 0; i < Constants.INIT_COUNTRIES_PLAYER; i++) {
            sleep(1);
            gq.getOrderedMainPlayerList().stream()
                    .forEach((player) -> {
                        String cAbb = gq.getCountryAbbreviationListByPlayerName(player.getName())
                                .stream().findAny().get();
                        setCommand(cAbb);

                        gq.getPlayerListByType(PlayerType.NeutralPlayer).stream()
                                .forEach((neutral) -> {
                                    String nAbb = gq.getCountryAbbreviationListByPlayerName(neutral.getName())
                                            .stream().findAny().get();
                                    setCommand(nAbb);
                                });
                    });
        }
        sleep(1);
        assertTrue("Inforcements are set all right!", gq.getCountryList()
                .stream().filter((c) -> c.getOwnerOfTheCountry() == null).count() == 0);
    }

    private void setInforcements(Player player) {
        while (player.getNoOfArmies() > 0) {
            Random random = new Random();
            String cAbb = gq.getCountryAbbreviationListByPlayerName(player.getName())
                    .stream().findAny().get();
            setCommand(cAbb);
            Integer number = random.nextInt(player.getNoOfArmies()) + 1;
            setCommand(number.toString());
        }
    }

    private void exchangeCards(Player player) {
        if (player.getCardList().size() > 3) {
            int noOfArmies = player.getNoOfArmies();
            String cards = player.getCardList()
                    .stream()
                    .sorted((x, y) -> x.getCardType().compareTo(y.getCardType()))
                    .limit(3)
                    .map(c -> c.toString())
                    .collect(Collectors.joining());
            setCommand(cards);
            if (noOfArmies == player.getNoOfArmies()) {
                setCommand(TestUtils.skip);
            }
        } else {
            setCommand(TestUtils.skip);
        }
    }

    private void attackOrNot(Player player) {
        Random rand = new Random();
        int r = rand.nextInt(2);
        if (r == 0) {
            Country attackCountry = gq.getCountryListByPlayerName(player.getName())
                    .stream().findAny().get();
            Optional<Country> findAny = gq.getAdjCountryByAbb(attackCountry.getAbbreviation())
                    .stream()
                    .filter((c) -> c.getOwnerOfTheCountry() != player)
                    .findAny();

            if (findAny.isPresent() && attackCountry.getNoOfArmyInCountry() > 2) {
                setCommand(TestUtils.attack);
                Country defence = findAny.get();
                setCommand(attackCountry.getAbbreviation());
                setCommand(defence.getAbbreviation());
                Integer attackArmy = (attackCountry.getNoOfArmyInCountry() - 1) > 2 ? rand.nextInt(3) + 1 : 1;
                setCommand(attackArmy.toString());
                Integer defenceArmy = defence.getNoOfArmyInCountry() > 1 ? (rand.nextInt(2) + 1) : 1;
                setCommand(defenceArmy.toString());
                if (defence.getOwnerOfTheCountry() == player) {
                    setCommand(attackArmy.toString());
                }
            }
        }
        setCommand(TestUtils.skip);
    }

    private void fortifyOrNot(Player player) {
        Random rand = new Random();
        int r = rand.nextInt(2);
        if (r == 0) {
            Country from = gq.getCountryListByPlayerName(player.getName())
                    .stream().findAny().get();
            Optional<Country> findAny = gq.getAdjCountryByAbb(from.getAbbreviation())
                    .stream()
                    .filter((c) -> c.getOwnerOfTheCountry() == player)
                    .findAny();
            if (findAny.isPresent() && from.getNoOfArmyInCountry() > 2) {
                setCommand(TestUtils.fortify);
                Country to = findAny.get();
                setCommand(from.getAbbreviation());
                Integer units = (from.getNoOfArmyInCountry() - 1) > 2 ? rand.nextInt(3) + 1 : 1;
                setCommand(units.toString());
                setCommand(to.getAbbreviation());
            } else {
                setCommand(TestUtils.skip);
            }
        } else {
            setCommand(TestUtils.skip);
        }
    }

}
