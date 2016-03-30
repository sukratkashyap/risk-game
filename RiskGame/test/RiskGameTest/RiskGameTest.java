package RiskGameTest;

import game.graphic.GUI;
import riskgame.RiskGame;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description
 */
public class RiskGameTest extends RiskGame implements Runnable {

    public RiskGameTest() {
        super();
    }

    public GUI getGUI() {
        return _gui;
    }

    @Override
    public void run() {
        super.run();
    }
}
