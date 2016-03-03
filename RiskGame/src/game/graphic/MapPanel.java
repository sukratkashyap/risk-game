package game.graphic;

import game.core.Constants;
import game.data.GetQuery;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description this class shows the risk map using the LineComponent and
 * CountryNodeComponent.
 */
public class MapPanel extends JPanel implements IRefreshable {

    private LineComponent _lineComponent;
    private List<CountryNodeComponent> _countryNodeComponentList = new ArrayList<>();

    public MapPanel(int x, int y, int width, int height) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setVisible(true);

        _lineComponent = new LineComponent(width, height);
        this.add(_lineComponent, 1, 0);
        GetQuery query = new GetQuery();
        query.getCountryList().stream().forEach((country) -> {
            CountryNodeComponent comp = new CountryNodeComponent(country);
            this.add(comp, 2, 0);
            _countryNodeComponentList.add(comp);
        });
    }

    @Override
    public void refresh() {
        this.repaint();
        this.revalidate();
        _countryNodeComponentList.stream()
                .forEach((countryNodeComponent) -> {
                    countryNodeComponent.refresh();
                });
        _lineComponent.refresh();
    }
}
