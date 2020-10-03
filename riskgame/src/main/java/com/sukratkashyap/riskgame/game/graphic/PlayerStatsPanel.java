package game.graphic;

import game.core.Player;
import game.data.GetQuery;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description this class shows the player stats and other info related to the
 * game
 */
public class PlayerStatsPanel extends JPanel implements IRefreshable {

    private final List<JLabel> _labelList = new ArrayList<JLabel>() {
        {
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
        }
    };
    private final GridLayout _gridLayout;

    public PlayerStatsPanel(int x, int y, int width, int height) {
        super();
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        super.setBounds(x, y, width, height);
        super.setVisible(true);

        _gridLayout = new GridLayout(_labelList.size(), 1);
        super.setLayout(_gridLayout);
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

    @Override
    public void refresh() {
        GetQuery gq = new GetQuery();
        List<Player> playerList = gq.getPlayerList();
        int i = 0;
        for (Player player : playerList) {
            StringBuilder builder = new StringBuilder();
            builder.append("<html><body>");
            builder.append("Name: ").append(player.getName()).append("<br>");
            builder.append("No of armies: ").append(player.getNoOfArmies()).append("<br>");
            builder.append("Territories: ");
            builder.append(
                    gq.getCountryListByPlayerName(player.getName())
                    .parallelStream()
                    .map((country) -> country.getAbbreviation())
                    .collect(Collectors.toList())).append("<br>");
            builder.append("Cards: ").append(player.getCardList());
            builder.append("</body></html>");
            _labelList.get(i).setText(builder.toString());
            i++;
        }
    }
}
