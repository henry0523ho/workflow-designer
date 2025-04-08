package util.objects;

import java.awt.*;

public class Label {
    public String name;

    public enum Shape {
        RECT,
        OVAL,
    }

    public Shape shape;

    public enum Color {
        RED,
        BLUE,
        YELLOW,
    }

    public Color color;
    public static int[] Sizes = {12, 24, 36};
    public int size;
    int x, y;

    public Label(int x, int y) {
        this("Hi", Shape.OVAL, Color.YELLOW, 12, x, y);
    }

    public Label(String name, Shape shape, Color color, int size, int x, int y) {
        this.name = name;
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        if (color == Color.RED) {
            g.setColor(java.awt.Color.RED);
        } else if (color == Color.BLUE) {
            g.setColor(java.awt.Color.BLUE);
        } else if (color == Color.YELLOW) {
            g.setColor(java.awt.Color.YELLOW);
        }

        if (shape == Shape.RECT) {
            g.fillRect(x, y, 100, 50);
            g.setColor(java.awt.Color.BLACK);
            g.drawRect(x, y, 100, 50);
        } else if (shape == Shape.OVAL) {
            g.fillOval(x, y, 100, 50);
            g.setColor(java.awt.Color.BLACK);
            g.drawOval(x, y, 100, 50);
        }
        g.setFont(g.getFont().deriveFont((float) size));
        g.drawString(name, x + 15, y + 5 + size);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
