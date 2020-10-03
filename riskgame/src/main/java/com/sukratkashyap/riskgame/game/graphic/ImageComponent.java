package game.graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @author Sukrat Kashyap (14200092)
 * @Description
 */
public class ImageComponent extends JComponent implements IRefreshable {

    private final int _width;
    private final int _height;

    public ImageComponent(int width, int height) {
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
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("RiskMap.jpg"));
        } catch (IOException e) {
        }
        g2.drawImage(img, 0, 0, _width, _height, this);
    }

    @Override
    public void refresh() {
        super.repaint();
        super.revalidate();
    }

}
