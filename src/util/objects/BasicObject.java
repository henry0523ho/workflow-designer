package util.objects;

import java.awt.*;
import java.util.ArrayList;

public abstract class BasicObject {
    public double x, y;
    public boolean selected = false;
    public boolean mute = false;

    ArrayList<ConnectionPort> connectionPorts = new ArrayList<ConnectionPort>();

    public enum Shape {
        RECT,
        OVAL,
    }

    public BasicObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void _drawConnectionPort(Graphics g) {
        for (ConnectionPort c : connectionPorts) {
            c.render(g);
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract void render(Graphics g);

    public abstract boolean inside(double detectX, double detextY);

    public abstract boolean inside(double x1, double y1, double x2, double y2);

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        for (ConnectionPort c : connectionPorts) {
            c.move((int) dx, (int) dy);
        }
    }
}
