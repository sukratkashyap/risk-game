package game.graphic;

import game.core.Constants;
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

    public RiskFrame() {
        super("RISK GAME");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1000 + 200 + 5, 580 + 100 + 50);
        this.setVisible(true);

        _playerStatsPanel = (PlayerStatsPanel) this.getContentPane()
                .add(new PlayerStatsPanel(0, 0, 200, 580));
        _mapPanel = (MapPanel) this.getContentPane()
                .add(new MapPanel(201, 0, 1000, 580));
        _messagePanel = (MessagePanel) this.getContentPane()
                .add(new MessagePanel(0, 581, 1001 + 201 + 1, 100));
        _commandPanel = (CommandPanel) this.getContentPane()
                .add(new CommandPanel(0, 581 + 101, 1000 + 200, 20));

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

    @Override
    public void refresh() {
        this.repaint();
        this.revalidate();
        _mapPanel.refresh();
        _playerStatsPanel.refresh();
    }
}
