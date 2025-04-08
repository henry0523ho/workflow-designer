package controller;

import util.Designer;
import view.MenuBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuBarController implements ActionListener {
    private MenuBar menuBar;

    public MenuBarController(MenuBar menuBar) {
        this.menuBar = menuBar;
        for (Component component : menuBar.getComponents()) {
            if (component instanceof JMenu) {
                for (Component item : ((JMenu) component).getMenuComponents()) {
                    if (item instanceof JMenuItem) {
                        ((JMenuItem) item).addActionListener(this);
                    }
                }
            }
        }

//        for (JMenuItem item : menuBar.fileMenuItems) {
//            item.addActionListener(this);
//        }
//        for (JMenuItem item : menuBar.editMenuItems) {
//            item.addActionListener(this);
//        }

    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        if (item.getText().equals("Group")) {
            System.out.println("Group");
            if(Designer.instance.mode==Designer.Mode.SELECT) {
                Designer.instance.groupSelected();
            }
        }
        if (item.getText().equals("Ungroup")) {
            System.out.println("Ungroup");
            if(Designer.instance.mode==Designer.Mode.SELECT) {
                Designer.instance.ungroupSelected();
            }
        }
        if (item.getText().equals("Label")) {
            System.out.println("Label");
            if(Designer.instance.mode==Designer.Mode.SELECT) {
                Designer.instance.customizeLabelStyle();
            }
        }
    }


}
