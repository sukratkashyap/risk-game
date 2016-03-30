/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RiskGameTest;

import game.core.Constants;
import game.core.Player;
import game.core.PlayerType;
import game.data.GetQuery;
import game.graphic.GUI;
import game.graphic.RiskFrame;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        riskFrame.getCommandPanel().setCommand(command);
        try {
            Thread.sleep(500);
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
                setInforcements(player);
                //TODO need to add test cases for other operations like attack
                //Will do in next sprint
            }
            orderedMainPlayerList = gq.getOrderedMainPlayerList();
            break;
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

                        gq.getPlayerList(PlayerType.NeutralPlayer).stream()
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
            Integer number = random.nextInt() % player.getNoOfArmies() + 1;
            setCommand(number.toString());
        }
    }

}
