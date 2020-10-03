package com.sukratkashyap.riskgame.game.graphic;

import com.sukratkashyap.riskgame.game.data.GetQuery;
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
public class MapPanel extends JPanel implements IRefreshable {

    private final LineComponent _lineComponent;
    private final ImageComponent _imageComponent;
    private List<CountryNodeComponent> _countryNodeComponentList = new ArrayList<>();

    public MapPanel(int x, int y, int width, int height) {
        super();
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        super.setBounds(x, y, width, height);
        super.setLayout(null);
        super.setVisible(true);

        _imageComponent = new ImageComponent(width, height);
        super.add(_imageComponent, 0, 0);

        _lineComponent = new LineComponent(width, height);
        super.add(_lineComponent, 1, 0);
        GetQuery query = new GetQuery();
        query.getCountryList().stream().forEach((country) -> {
            CountryNodeComponent comp = new CountryNodeComponent(country);
            super.add(comp, 2, 0);
            _countryNodeComponentList.add(comp);
        });
    }

    @Override
    public void refresh() {
        super.repaint();
        super.revalidate();
        _countryNodeComponentList.stream()
                .forEach((countryNodeComponent) -> {
                    countryNodeComponent.refresh();
                });
        _lineComponent.refresh();
    }
}
