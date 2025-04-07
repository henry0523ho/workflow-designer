package view.icons;

import javax.swing.*;
import java.awt.*;

public class OvalIcon implements Icon {
    private int width, height;

    public OvalIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillOval((int)(x + width * 0.1), (int)(y + height * 0.1), (int)(width * 0.8), (int)(height * 0.8));
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval((int)(x + width * 0.1), (int)(y + height * 0.1), (int)(width * 0.8), (int)(height * 0.8));

    }
}