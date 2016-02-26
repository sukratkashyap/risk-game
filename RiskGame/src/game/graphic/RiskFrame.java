package game.graphic;

import game.core.Constants;
import game.core.Country;
import game.core.Result;
import game.data.GetQuery;
import game.data.SetQuery;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description Frame for the risk game
 */
public class RiskFrame extends JFrame {

    private PlayerStatsPanel _playerStatsPanel;
    private RiskMapPanel _riskMapPanel;
    private MessagePanel _messagePanel;
    private CommandPanel _commandPanel;

    public RiskFrame() {
        super("RISK GAME");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        this.setVisible(true);

        _playerStatsPanel = (PlayerStatsPanel) this.getContentPane().add(new PlayerStatsPanel(0,
                0));
        _riskMapPanel = (RiskMapPanel) this.getContentPane().add(new RiskMapPanel(Constants.PANEL_PLAYER_STATS_WIDTH,
                0));
        _messagePanel = (MessagePanel) this.getContentPane().add(new MessagePanel(0, 600));
        _commandPanel = (CommandPanel) this.getContentPane().add(new CommandPanel(0, 710));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    public void GetPlayerNames() {

    }

}
