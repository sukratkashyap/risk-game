package game.graphic;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description dialog box class which contains various method
 */
public class DialogBox {

    /**
     *
     * @param cmp
     * @param message
     * @return
     */
    public static String TakeInputAsString(Component cmp, String message) {
        String input = "";
        input = JOptionPane.showInputDialog(cmp, message);
        while (input == null || input.isEmpty()) {
            input = JOptionPane.showInputDialog(cmp, "String musn't be empty! " + message);
        }
        return input;
    }

    /**
     *
     * @param cmp
     * @param message
     */
    public static void ShowError(Component cmp, String message) {
        JOptionPane.showMessageDialog(cmp, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
