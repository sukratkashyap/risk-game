package game.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description enum for the type of player
 */
public class CommandPanel extends JPanel {

    private JTextField _textField;

    private final List<String> _inputList = new ArrayList<String>();

    public CommandPanel(int x, int y, int width, int height) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        _textField = new JTextField();
        _textField.addActionListener(new TextBoxActionListener());
        this.add(_textField, BorderLayout.CENTER);
    }

    public String getCommand() {
        String command = "";
        synchronized (_inputList) {
            while (_inputList.isEmpty()) {
                try {
                    _inputList.wait();
                } catch (InterruptedException ex) {

                }
            }
            command = _inputList.remove(_inputList.size() - 1);
        }
        return command;
    }

    class TextBoxActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            synchronized (_inputList) {
                _inputList.add(_textField.getText());
                _textField.setText("");
                _inputList.notify();
            }
        }
    }

    public void setCommand(String command) {
        synchronized (_inputList) {
            _inputList.add(command);
            _inputList.notify();
        }
    }

    public JTextField getTextField() {
        return _textField;
    }

}
