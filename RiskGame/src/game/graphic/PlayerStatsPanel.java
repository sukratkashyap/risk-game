package game.graphic;

import core.Constants;
import core.Player;
import game.data.GetQuery;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description this class shows the player stats and other info related to the
 * game
 */
public class PlayerStatsPanel extends JPanel {

    private List<JLabel> _labelList = new ArrayList<JLabel>() {
        {
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
        }
    };
    private GridLayout _gridLayout;

    public PlayerStatsPanel(int x, int y) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y,
                Constants.PANEL_PLAYER_STATS_WIDTH, Constants.PANEL_PLAYER_STATS_HEIGHT);
        this.setVisible(true);

        _gridLayout = new GridLayout(_labelList.size(), 1);
        this.setLayout(_gridLayout);
        _labelList.stream()
                .forEach((label) -> {
                    this.add(label);
                });
    }

    public void RefreshTheStat() {
        GetQuery dq = new GetQuery();
        List<Player> playerList = dq.getPlayerList();
        int i = 0;
        for (Player player : playerList) {
            _labelList.get(i).setText(player.toString());
            i++;
        }
    }
}
