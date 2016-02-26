package riskgame;

import game.graphic.RiskFrame;
import javax.swing.SwingUtilities;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description
 */
public class RiskGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RiskGame();
        });

    }

    protected RiskGame() {
        RiskFrame riskFrame = new RiskFrame();
       
    }
}
