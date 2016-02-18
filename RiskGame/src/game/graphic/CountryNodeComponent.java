package game.graphic;

import core.Constants;
import core.Country;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class represents a node on the risk map for each country
 */
public class CountryNodeComponent extends JComponent {

    private final Country _country;

    /**
     *
     * @param country country for which the node should be created
     */
    public CountryNodeComponent(Country country) {
        super();
        _country = country;
        this.setBounds(_country.getXCoOrdinate() - Constants.NODE_RADIUS,
                _country.getYCoOrdinate() - Constants.NODE_RADIUS,
                Constants.NODE_DIAMETER, Constants.NODE_DIAMETER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2.setColor(_country.getContinent().getColor());
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0,
                Constants.NODE_DIAMETER, Constants.NODE_DIAMETER);
        g2.fill(circle);
        this.SetTheToolTip();
    }

    /**
     * Sets the tooltip for the country node.
     */
    public void SetTheToolTip() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>");
        builder.append("Name: ").append(_country.getName());
        if (_country.getOwnerOfTheCountry() != null) {
            builder.append("<br>");
            builder.append("Player: ").append(_country.getOwnerOfTheCountry().getName());
            builder.append("<br>");
            builder.append("Armies: ").append(_country.getArmyInCountry());
        }
        builder.append("</body></html>");
        this.setToolTipText(builder.toString());
    }

}
