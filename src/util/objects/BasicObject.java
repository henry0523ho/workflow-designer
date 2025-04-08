package util.objects;

import java.awt.*;
import java.util.ArrayList;

public abstract class BasicObject {
    public double x, y;
    public boolean selected = false;
    public boolean mute = false;
    public Label label = null;

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

    public abstract boolean inside(double detectX, double detectY);

    public abstract boolean inside(double x1, double y1, double x2, double y2);

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        for (ConnectionPort c : connectionPorts) {
            c.move((int) dx, (int) dy);
        }
        if (label != null) {
            label.move((int) dx, (int) dy);
        }
    }

    public ConnectionPort detectPort(double detectX, double detectY) {
        if (mute) return null;
        for (ConnectionPort c : connectionPorts) {
            if (c.inside((int) detectX, (int) detectY)) {
                return c;
            }
        }
        return null;
    }

    public abstract Label getLabel();

    public void setLabel(Label label) {
        this.label = label;
    }


}
