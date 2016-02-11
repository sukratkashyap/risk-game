package game.graphic;

import core.Constants;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description Frame for the risk game
 */
public class RiskFrame extends JFrame {

    public RiskFrame() throws HeadlessException {
        super("Risk Game");
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
