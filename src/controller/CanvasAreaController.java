package controller;

import util.Designer;
import util.links.AssociationLink;
import util.links.BasicLink;
import util.links.CompositionLink;
import util.links.GeneralizationLink;
import util.objects.BasicObject;
import util.objects.ConnectionPort;
import util.objects.OvalObject;
import util.objects.RectObject;
import view.CanvasArea;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CanvasAreaController implements MouseMotionListener, MouseListener {
    CanvasArea canvasArea;

    public CanvasAreaController(CanvasArea canvasArea) {
        this.canvasArea = canvasArea;
        canvasArea.addMouseMotionListener(this);
        canvasArea.addMouseListener(this);
    }

    int _dragStartX = -1;
    int _dragStartY = -1;
    int _dragEndX = -1;
    int _dragEndY = -1;

    ConnectionPort _sourcePort = null;
    ConnectionPort _targetPort = null;

    enum DragMode {
        SELECT_AREA,
        DRAG_OBJECT,
        LINK,
    }

    DragMode dragMode = null;
    BasicObject dragObject = null;

    public void drawSelectArea(Graphics g) {
        if (dragMode != DragMode.SELECT_AREA) return;
        g.setColor(new Color(180, 180, 255, 200));
        g.fillRect(Math.min(_dragStartX, _dragEndX), Math.min(_dragStartY, _dragEndY), Math.abs(_dragStartX - _dragEndX), Math.abs(_dragStartY - _dragEndY));
        g.setColor(new Color(180, 180, 255, 100));
        g.drawRect(Math.min(_dragStartX, _dragEndX), Math.min(_dragStartY, _dragEndY), Math.abs(_dragStartX - _dragEndX), Math.abs(_dragStartY - _dragEndY));
    }

    public void drawLinkPreview(Graphics g) {
        if (dragMode != DragMode.LINK) return;
//        if(_sourcePort==null) return ;
        if (_dragStartY == -1 || _dragStartX == -1) return;
        g.setColor(Color.black);
//        g.drawLine((int) _sourcePort.x, (int) _sourcePort.y, (int) _dragEndX, (int) _dragEndY);
        g.drawLine((int) _dragStartX, (int) _dragStartY, (int) _dragEndX, (int) _dragEndY);

    }

    public void mouseMoved(MouseEvent e) {
//        System.out.println("Mouse moved to: " + e.getX() + ", " + e.getY());
//        canvasArea.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        if (Designer.instance.mode == Designer.Mode.SELECT) {
            if (_dragStartX == -1 || _dragStartY == -1) {
                dragObject = Designer.instance.detectObject(e.getX(), e.getY());
                _dragStartX = e.getX();
                _dragStartY = e.getY();
                if (dragObject != null) {
                    dragMode = DragMode.DRAG_OBJECT;
                    return;
                } else {
                    dragMode = DragMode.SELECT_AREA;
                    return;
                }
            }
            if (dragMode == DragMode.DRAG_OBJECT) {
                dragObject.move(e.getX() - _dragStartX, e.getY() - _dragStartY);
                _dragStartX = e.getX();
                _dragStartY = e.getY();
                canvasArea.repaint();
            } else if (dragMode == DragMode.SELECT_AREA) {
                _dragEndX = e.getX();
                _dragEndY = e.getY();
                canvasArea.repaint();
            }
        } else if (Designer.instance.mode == Designer.Mode.LINK) {
            if (dragMode != DragMode.LINK) return;
            _dragEndX = e.getX();
            _dragEndY = e.getY();
            canvasArea.repaint();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mode: " + Designer.instance.mode);
        Designer.instance.clearAllSelected();
        if (Designer.instance.mode == Designer.Mode.SELECT) {
            System.out.println("Select mode");
            BasicObject result = Designer.instance.detectObject(x, y);
            if (result != null) result.setSelected(!result.selected);
            else Designer.instance.clearAllSelected();
            canvasArea.repaint();
        } else if (Designer.instance.mode == Designer.Mode.CREATE) {
            Designer.instance.clearAllSelected();
            switch (Designer.instance.shape) {
                case RECT:
                    System.out.println("Rectangle");
                    RectObject rect = new RectObject(x, y);
                    Designer.instance.addObject(rect);
                    canvasArea.repaint();
                    break;
                case OVAL:
                    System.out.println("Circle");
                    OvalObject oval = new OvalObject(x, y);
                    Designer.instance.addObject(oval);
                    canvasArea.repaint();
                    break;
            }
        } else {
            Designer.instance.clearAllSelected();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        _dragStartX = e.getX();
//        _dragStartY = e.getY();
        if (Designer.instance.mode == Designer.Mode.LINK) {
            _sourcePort = Designer.instance.detectPort(e.getX(), e.getY());
            _dragStartX = e.getX();
            _dragStartY = e.getY();
            dragMode = DragMode.LINK;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (Designer.instance.mode == Designer.Mode.SELECT) {
            if (dragMode == DragMode.DRAG_OBJECT) {
                _dragStartX = _dragEndX = _dragStartY = _dragEndY = -1;
                dragMode = null;
            } else if (dragMode == DragMode.SELECT_AREA) {
                if (_dragEndX == -1 || _dragEndY == -1) {
                    return;
                }
                Designer.instance.clearAllSelected();
                ArrayList<BasicObject> selectedObjects = Designer.instance.detectObject(_dragStartX, _dragStartY, _dragEndX, _dragEndY);
                for (BasicObject object : selectedObjects) {
                    if (!object.mute) {
                        object.setSelected(true);
                    }
                }
                _dragStartX = _dragEndX = _dragStartY = _dragEndY = -1;
                canvasArea.repaint();
                dragMode = null;
            }
        } else if (Designer.instance.mode == Designer.Mode.LINK) {

            _targetPort = Designer.instance.detectPort(e.getX(), e.getY());
            if (_sourcePort != null && _targetPort != null && _sourcePort != _targetPort) {
                BasicLink link = null;
                if (Designer.instance.linkShape == BasicLink.Shape.ASSOCIATION) {
                    link = new AssociationLink(_sourcePort, _targetPort);
                } else if (Designer.instance.linkShape == BasicLink.Shape.GENERALIZATION) {
                    link = new GeneralizationLink(_sourcePort, _targetPort);
                } else if (Designer.instance.linkShape == BasicLink.Shape.COMPOSITION) {
                    link = new CompositionLink(_sourcePort, _targetPort);
                }
                Designer.instance.addLink(link);
            }
            _dragStartX = _dragEndX = _dragStartY = _dragEndY = -1;
            _sourcePort = null;
            _targetPort = null;
            dragMode = null;
            CanvasArea.instance.repaint();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("Mouse entered the panel");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("Mouse exited the panel");
    }
}
