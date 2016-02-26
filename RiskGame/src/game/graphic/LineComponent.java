package game.graphic;

import core.Country;
import game.data.GetQuery;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.List;
import javax.swing.JComponent;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description this class represents all the lines connecting each and every
 * country node on the risk map
 */
public class LineComponent extends JComponent {

    public LineComponent(int width, int height) {
        super();
        this.setBounds(0, 0, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GetQuery query = new GetQuery();
        query.getCountryList().stream()
                .forEach((country) -> {
                    query.getCountryList(country.getAdjacentCountryIndexList())
                    .stream()
                    .forEach((adjacentCountry) -> {
                        //drawing the line
                        Line2D.Double line = new Line2D.Double(country.getXCoOrdinate(), country.getYCoOrdinate(),
                                adjacentCountry.getXCoOrdinate(), adjacentCountry.getYCoOrdinate());
                        g2.draw(line);
                    });
                });
    }
}
