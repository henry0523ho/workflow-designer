package controller;

import util.Designer;
import util.links.BasicLink;
import util.objects.BasicObject;
import view.CanvasArea;
import view.SideBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideBarController implements ActionListener {

    public enum SideBarMode {
        SELECT,
        ASSOCIATION,
        GENERALIZATION,
        COMPOSITION,
        RECTANGLE,
        OVAL
    }

    ;

    private SideBar sideBar;

    public SideBarMode currentMode = SideBarMode.SELECT;

    public SideBarController(SideBar sideBar) {
        this.sideBar = sideBar;

        for (Component item : sideBar.getComponents()) {
            if (item instanceof AbstractButton) {
                ((AbstractButton) item).addActionListener(this);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        AbstractButton item = (AbstractButton) e.getSource();
        String actionCommand = item.getActionCommand();
        switch (actionCommand) {
            case "select":
                currentMode = SideBarMode.SELECT;
                Designer.instance.mode = Designer.Mode.SELECT;
                break;
            case "association":
                currentMode = SideBarMode.ASSOCIATION;
                Designer.instance.mode = Designer.Mode.LINK;
                Designer.instance.linkShape= BasicLink.Shape.ASSOCIATION;
                Designer.instance.showConnectionPort();
                CanvasArea.instance.repaint();
                break;
            case "generalization":
                currentMode = SideBarMode.GENERALIZATION;
                Designer.instance.mode = Designer.Mode.LINK;
                Designer.instance.linkShape= BasicLink.Shape.GENERALIZATION;
                Designer.instance.showConnectionPort();
                CanvasArea.instance.repaint();
                break;
            case "composition":
                currentMode = SideBarMode.COMPOSITION;
                Designer.instance.mode = Designer.Mode.LINK;
                Designer.instance.linkShape= BasicLink.Shape.COMPOSITION;
                Designer.instance.showConnectionPort();
                CanvasArea.instance.repaint();
                break;
            case "rect":
                currentMode = SideBarMode.RECTANGLE;
                Designer.instance.mode = Designer.Mode.CREATE;
                Designer.instance.shape = BasicObject.Shape.RECT;
                Designer.instance.clearAllSelected();
                break;
            case "oval":
                currentMode = SideBarMode.OVAL;
                Designer.instance.mode = Designer.Mode.CREATE;
                Designer.instance.shape = BasicObject.Shape.OVAL;
                Designer.instance.clearAllSelected();
                break;
        }
        System.out.println("Current mode: " + currentMode);
    }


}
