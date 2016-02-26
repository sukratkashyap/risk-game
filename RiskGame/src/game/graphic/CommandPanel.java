/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphic;

import game.core.Constants;
import game.core.Player;
import game.data.GetQuery;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Ben
 */
public class CommandPanel extends JPanel {

    private GridLayout _gridLayout;
    private JLabel _label;

    public CommandPanel(int x, int y) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(x, y,
                Constants.PANEL_MESSAGE_WIDTH, Constants.PANEL_MESSAGE_HEIGHT);
        this.setLayout(new SpringLayout());
        this.setVisible(true);
        
       _gridLayout = new GridLayout(1, 1);
        this.setLayout(_gridLayout);
        _label = new JLabel();
        _label.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
        this.add(_label);
    }
}