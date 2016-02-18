package game.graphic;

import core.Constants;
import game.data.DataQuery;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description this class shows the risk map using the LineComponent and
 * CountryNodeComponent.
 */
public class RiskMapPanel extends JPanel {

    private LineComponent _lineComponent;
    private List<CountryNodeComponent> _countryNodeComponentList = new ArrayList<>();

    public RiskMapPanel(int x, int y) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y,
                Constants.PANEL_MAP_WIDTH, Constants.PANEL_MAP_HEIGHT);
        this.setLayout(null);
        this.setVisible(true);

        _lineComponent = new LineComponent(Constants.PANEL_MAP_WIDTH, Constants.PANEL_MAP_HEIGHT);
        this.add(_lineComponent, 1, 0);
        DataQuery query = new DataQuery();
        query.getCountryList().stream().forEach((country) -> {
            CountryNodeComponent comp = new CountryNodeComponent(country);
            this.add(comp, 2, 0);
            _countryNodeComponentList.add(comp);
        });
    }
}
