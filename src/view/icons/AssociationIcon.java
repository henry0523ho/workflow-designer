package view.icons;

import javax.swing.*;
import java.awt.*;

public class AssociationIcon implements Icon {
    private int width, height;

    public AssociationIcon(int width, int height) {
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
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        double[][] lines = {
                {0.1, 0.5, 0.9, 0.5},
                {0.5, 0.1, 0.1, 0.5},
                {0.1, 0.5, 0.5, 0.9}
        };
        for (double[] line : lines) {
            int x1 = (int) (x + width * line[0]);
            int y1 = (int) (y + height * line[1]);
            int x2 = (int) (x + width * line[2]);
            int y2 = (int) (y + height * line[3]);
            g2d.drawLine(x1, y1, x2, y2);
        }

    }
}