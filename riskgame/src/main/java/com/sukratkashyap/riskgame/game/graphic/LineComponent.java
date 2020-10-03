package com.sukratkashyap.riskgame.game.graphic;

import game.data.GetQuery;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description this class represents all the lines connecting each and every
 * country node on the risk map
 */
public class LineComponent extends JComponent implements IRefreshable {

    private final int _width;
    private final int _height;

    public LineComponent(int width, int height) {
        super();
        _width = width;
        _height = height;
        super.setBounds(0, 0, _width, _height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, new float[]{5.0f}, 4.0f));
        GetQuery query = new GetQuery();
        query.getCountryList().stream()
                .forEach((country) -> {
                    query.getCountryListByIds(country.getAdjacentCountryIdList())
                            .stream()
                            .forEach((adjacentCountry) -> {
                                //drawing the line
                                Line2D.Double line = new Line2D.Double(country.getXCoOrdinate(), country.getYCoOrdinate(),
                                        adjacentCountry.getXCoOrdinate(), adjacentCountry.getYCoOrdinate());
                                g2.draw(line);
                            });
                });
    }

    @Override
    public void refresh() {
        super.repaint();
        super.revalidate();
    }
}
