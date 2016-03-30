package game.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description this class is used to display message on the screen
 */
public class MessagePanel extends JPanel {

    private JTextArea _textArea;
    private JScrollPane _scrollPane;

    public MessagePanel(int x, int y, int width, int height) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y, width, height);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        _textArea = new JTextArea();
        _textArea.setLineWrap(true);
        _textArea.setWrapStyleWord(true);
        _textArea.setEditable(false);
        ((DefaultCaret) _textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        _scrollPane = new JScrollPane(_textArea);
        _scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(_scrollPane, BorderLayout.CENTER);
    }

    public void addQuestion(String text) {
        _textArea.setText(_textArea.getText() + "\n" + ">? " + text + "?");
    }

    public void addResult(String text) {
        _textArea.setText(_textArea.getText() + "\n" + ">! " + text + "!");
    }

    public void addError(String text) {
        _textArea.setText(_textArea.getText() + "\n" + ">Error: " + text + ".");
    }
}
