package util.objects;

import java.awt.*;

public class RectObject extends BasicObject {
    static final int WIDTH = 150;
    static final int HEIGHT = 160;

    public RectObject(double x, double y) {
        super(x, y);
        for (double _x = 0; _x <= 1; _x += 0.5) {
            for (double _y = 0; _y <= 1; _y += 0.5) {
                if (_x == 0.5 && _y == 0.5) continue;
                connectionPorts.add(new ConnectionPort((int) (_x * WIDTH + x), (int) (_y * HEIGHT + y)));
            }
        }
    }

    void _draw(Graphics g) {
        g.setColor(new Color(180, 180, 255, 255));
        g.fillRect((int) x, (int) y, WIDTH, HEIGHT);
        g.setColor(new Color(0, 0, 160, 255));
        g.drawRect((int) x, (int) y, WIDTH, HEIGHT);
    }

    public void render(Graphics g) {
        _draw(g);
        if (selected) _drawConnectionPort(g);
    }

    public boolean inside(double detectX, double detectY) {
        return x <= detectX && detectX <= x + WIDTH && y <= detectY && detectY <= y + HEIGHT;
    }

    public boolean inside(double x1, double y1, double x2, double y2) {
        if(x1>x2){
            double tmp=x1;
            x1=x2;
            x2=tmp;
        }
        if(y1>y2){
            double tmp=y1;
            y1=y2;
            y2=tmp;
        }
        return x1<=x&&x+WIDTH<=x2&&y1<=y&&y+HEIGHT<=y2;
    }
}
