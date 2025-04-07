package view.icons;

import javax.swing.*;
import java.awt.*;

public class SelectIcon implements Icon {
    private int width, height;

    public SelectIcon(int width, int height) {
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
        double[] xs = {0.1, 0.3113248654052, 0.5, 0.7, 0.9, 0.7, 0.8886751345948};
        double[] ys = {0.1, 0.8886751345948, 0.7, 0.9, 0.7, 0.5, 0.3113248654052};
        int[] xPoints = new int[xs.length];
        int[] yPoints = new int[ys.length];
        for (int i = 0; i < xs.length; i++) {
            xPoints[i] = (int) (x + width * xs[i]);
            yPoints[i] = (int) (y + height * ys[i]);
        }
        g2d.fillPolygon(xPoints, yPoints, xs.length);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(xPoints, yPoints, xs.length);
    }
}