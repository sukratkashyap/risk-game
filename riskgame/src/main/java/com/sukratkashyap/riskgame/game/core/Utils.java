package com.sukratkashyap.riskgame.game.core;

import java.awt.Point;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class contains various functions that may be needed in the
 * program that depends only on the inputs being passed.
 */
public class Utils {

    public static String encodeHtml(String htmlString) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<body>");
        builder.append(htmlString);
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }

    public static Point getRatioCoOrdinate(int x, int y) {
        x = (int) (x * Constants.MAP_RATIO_GROWTH_HEIGHT_NEW / (Constants.MAP_RATIO_GROWTH_HEIGHT_ORIGINAL * 1.0));
        y = (int) (y * Constants.MAP_RATIO_GROWTH_WIDTH_NEW / (Constants.MAP_RATIO_GROWTH_WIDTH_ORIGINAL * 1.0));
        return new Point(x, y);
    }
}
