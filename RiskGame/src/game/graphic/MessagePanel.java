package game.graphic;

import game.core.Constants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class is used to display message on the screen
 */

public class MessagePanel extends JPanel {

    private GridLayout _gridLayout;
    private JLabel _label;

    public MessagePanel(int x, int y) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y,
                Constants.PANEL_MESSAGE_WIDTH, Constants.PANEL_MESSAGE_HEIGHT);
        this.setLayout(new SpringLayout());
        this.setVisible(true);

        //adding the label
        _gridLayout = new GridLayout(1, 1);
        this.setLayout(_gridLayout);
        _label = new JLabel();
        _label.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
        this.add(_label);
    }

    public void SetMessage(String message) {
        _label.setText(message);
    }
}
