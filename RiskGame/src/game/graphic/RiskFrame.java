package game.graphic;

import game.core.Constants;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description Frame for the risk game
 */
public class RiskFrame extends JFrame implements IRefreshable {

    private PlayerStatsPanel _playerStatsPanel;
    private MapPanel _mapPanel;
    private MessagePanel _messagePanel;
    private CommandPanel _commandPanel;

    private final Dimension PLAYER_STATS_PANEL = new Dimension(200, Constants.MAP_RATIO_HEIGHT * Constants.MAP_GROWTH_NEW);
    private final Dimension MAP_PANEL = new Dimension(Constants.MAP_RATIO_WIDTH * Constants.MAP_GROWTH_NEW,
            PLAYER_STATS_PANEL.height);
    private final Dimension MESSAGE_PANEL = new Dimension(PLAYER_STATS_PANEL.width + MAP_PANEL.width, 150);
    private final Dimension COMMAND_PANEL = new Dimension(PLAYER_STATS_PANEL.width + MAP_PANEL.width, 30);

    public RiskFrame() {
        super("RISK GAME");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(true);
        this.setSize(PLAYER_STATS_PANEL.width + MAP_PANEL.width + 15,
                PLAYER_STATS_PANEL.height + MESSAGE_PANEL.height + COMMAND_PANEL.height + 40);
        this.setVisible(true);

        _playerStatsPanel = (PlayerStatsPanel) this.getContentPane()
                .add(new PlayerStatsPanel(0, 0, PLAYER_STATS_PANEL.width,
                        PLAYER_STATS_PANEL.height));
        _mapPanel = (MapPanel) this.getContentPane()
                .add(new MapPanel(PLAYER_STATS_PANEL.width, 0,
                        MAP_PANEL.width, MAP_PANEL.height));
        _messagePanel = (MessagePanel) this.getContentPane()
                .add(new MessagePanel(0, PLAYER_STATS_PANEL.height,
                        MESSAGE_PANEL.width, MESSAGE_PANEL.height));
        _commandPanel = (CommandPanel) this.getContentPane()
                .add(new CommandPanel(0, PLAYER_STATS_PANEL.height + MESSAGE_PANEL.height,
                        COMMAND_PANEL.width, COMMAND_PANEL.height));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    public CommandPanel getCommandPanel() {
        return _commandPanel;
    }

    public MessagePanel getMessagePanel() {
        return _messagePanel;
    }

    /**
     * Repaint and revalidate.
     */
    @Override
    public void refresh() {
        this.repaint();
        this.revalidate();
        _mapPanel.refresh();
        _playerStatsPanel.refresh();
    }
}
