package view;

import controller.MenuBarController;

import javax.swing.*;
public class MenuBar extends JMenuBar{

    MenuBarController menuBarController;

    JMenu fileMenu, editMenu;
    public JMenuItem[] fileMenuItems;
    public JMenuItem[] editMenuItems;

    private void initMenu() {
        fileMenu = new JMenu("File");
        String[] fileItems = {"New", "Exit"};
        fileMenuItems = new JMenuItem[2];
        for (int i = 0; i < 2; i++) {
            fileMenuItems[i] = new JMenuItem(fileItems[i]);
//            fileMenuItems[i].addActionListener(menuController);
            fileMenu.add(fileMenuItems[i]);
        }
        add(fileMenu);

        editMenu = new JMenu("Edit");
        String[] editItems = {"Group", "Ungroup","Label"};
        editMenuItems = new JMenuItem[3];
        for (int i = 0; i < 3; i++) {
            editMenuItems[i] = new JMenuItem(editItems[i]);
//            editMenuItems[i].addActionListener(menuController);
            editMenu.add(editMenuItems[i]);
        }
        add(editMenu);
    }

    public MenuBar() {
        initMenu();
        menuBarController = new MenuBarController(this);
    }

}
