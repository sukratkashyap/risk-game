package core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class contains various functions that may be needed in the
 * program that depends only on the inputs being passed.
 */
public class Utils {

    public static String EncodeHtml(String htmlString) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<body>");
        builder.append(htmlString);
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }
}
