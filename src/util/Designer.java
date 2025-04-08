package util;

import java.awt.*;
import java.util.ArrayList;

import util.links.BasicLink;
import util.objects.BasicObject;
import util.objects.CompositeObject;
import util.objects.ConnectionPort;
import util.objects.Label;
import view.CanvasArea;
import view.LabelDialog;
import view.MainWindow;

public class Designer {
    public static Designer instance = new Designer();

//    boolean _update = true;

//    ArrayList<Runnable> _updateHooks = new ArrayList<>();

    ArrayList<BasicObject> _objects = new ArrayList<>();

    ArrayList<BasicLink> _links = new ArrayList<>();

    public enum Mode {
        CREATE,
        LINK,
        SELECT,
        GROUP,
    }

    public Mode mode = Mode.SELECT;
    public BasicObject.Shape shape = null;
    public BasicLink.Shape linkShape = null;


    public Designer() {
        if (instance == null) {
            instance = this;
            System.out.println("util.Designer created");
        } else {
            System.out.println("util.Designer already exists");
        }
    }

    public void addObject(BasicObject object) {
        _objects.add(object);
//        _update = true;
    }

    public void removeObject(BasicObject object) {
        boolean result = _objects.remove(object);
//        if (result) _update = true;
    }

    public void addLink(BasicLink link) {
        _links.add(link);
    }

    public void removeLink(BasicLink link) {
        boolean result = _links.remove(link);
    }

    public void render(Graphics g) {
        if (mode == Mode.LINK) {
            showConnectionPort();
        }
        for (BasicObject object : _objects) {
            object.render(g);
        }
        for (BasicLink link : _links) {
            link.render(g);
        }
    }

    public BasicObject detectObject(double detectX, double detectY) {
        BasicObject result = null;
        for (BasicObject object : _objects) {
            if (object.inside(detectX, detectY)) {
                result = object;
            }
        }
        return result;
    }

    public ArrayList<BasicObject> detectObject(double x1, double y1, double x2, double y2) {
        ArrayList<BasicObject> result = new ArrayList<>();
        for (BasicObject object : _objects) {
            if (object.inside(x1, y1, x2, y2)) {
                result.add(object);
            }
        }
        return result;
    }

    public ConnectionPort detectPort(double detectX, double detectY) {
        for (BasicObject object : _objects) {
            if (object instanceof CompositeObject compositeObject) {
                continue;
            } else {
                ConnectionPort port = object.detectPort(detectX, detectY);
                if (port != null) {
                    return port;
                }
            }
        }
        return null;
    }

    public void showConnectionPort() {
        for (BasicObject object : _objects) {
            if (object instanceof CompositeObject compositeObject) {
                object.setSelected(false);
            } else {
                object.setSelected(true);
            }
        }
    }

    public void clearAllSelected() {
        for (BasicObject object : _objects) {
            object.setSelected(false);
        }
    }

    public void groupSelected() {
        ArrayList<BasicObject> selectedObjects = new ArrayList<>();
        for (BasicObject object : _objects) {
            if (object.selected && !object.mute) {
                selectedObjects.add(object);
            }
        }
        for (BasicObject object : selectedObjects) {
//            _objects.remove(object);
            object.mute = true;
        }
        if (selectedObjects.size() > 1) {
            CompositeObject compositeObject = new CompositeObject(selectedObjects);
            _objects.add(compositeObject);
            clearAllSelected();
        }
    }

    public void ungroupSelected() {
        ArrayList<BasicObject> selectedObjects = new ArrayList<>();
        for (BasicObject object : _objects) {
            if (object.selected) {
                selectedObjects.add(object);
            }
        }
        if (selectedObjects.isEmpty()) {
            return;
        }
        BasicObject object = selectedObjects.getLast();
        if (!(object instanceof CompositeObject compositeObject)) {
            return;
        }
//        _objects.addAll(compositeObject.objects);

        for (BasicObject obj : compositeObject.objects) {
            obj.setSelected(true);
            obj.mute = false;
        }
        _objects.remove(object);
    }

    public void customizeLabelStyle() {
        ArrayList<BasicObject> selectedObjects = new ArrayList<>();
        for (BasicObject object : _objects) {
            if (object.selected) {
                selectedObjects.add(object);
            }
        }
        if (selectedObjects.size() != 1) {
            return;
        }
        BasicObject object = selectedObjects.getFirst();
        if (object instanceof CompositeObject compositeObject) {
            return;
        }
        System.out.println("customizeLabelStyle");
        LabelDialog labelDialog = new LabelDialog(MainWindow.instance, object);
    }

//    public void addUpdateHook(Runnable hook) {
//        _updateHooks.add(hook);
//    }
//
//    public void removeUpdateHook(Runnable hook) {
//        _updateHooks.remove(hook);
//    }
//
//    public void update() {
//        if (!_update) return;
//
//        for (Runnable hook : _updateHooks) {
//            hook.run();
//        }
//    }
//
//    public void makeUpdate(){
//        _update = true;
//    }

    public void test() {
        System.out.println("test");
    }

}
