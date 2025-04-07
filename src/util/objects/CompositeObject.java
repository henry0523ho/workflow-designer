package util.objects;

import java.awt.*;
import java.util.ArrayList;

public class CompositeObject extends BasicObject {
    public ArrayList<BasicObject> objects;

    public CompositeObject(double x, double y) {
        super(0, 0);
        this.objects = new ArrayList<BasicObject>();
    }

    public CompositeObject(ArrayList<BasicObject> objects) {
        super(0, 0);
        this.objects = objects;
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        for (BasicObject object : objects) {
            object.setSelected(selected);
        }
    }

    @Override
    public void render(Graphics g) {
//        for (BasicObject object : objects) {
//            object.render(g);
//        }
    }

    @Override
    public void move(double dx, double dy) {
        for (BasicObject object : objects) {
            object.move(dx, dy);
        }
    }

    public boolean inside(double detectX, double detectY) {
        for (BasicObject object : objects) {
            if (object.inside(detectX, detectY)) {
                return true;
            }
        }
        return false;
    }

    public boolean inside(double x1, double y1, double x2, double y2) {
        for (BasicObject object : objects) {
            if (!object.inside(x1, y1, x2, y2)) {
                return false;
            }
        }
        return true;
    }
}
