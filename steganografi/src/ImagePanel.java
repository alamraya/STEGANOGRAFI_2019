/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        super();
        this.backgroundImage = backgroundImage;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, null);
    }
}
