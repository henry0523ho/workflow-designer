package view;

import javax.swing.*;

import controller.*;

public class MainWindow extends JFrame {
    public static MainWindow instance;
    MainController maincontroller;

    MenuBar menuBar;
    SideBar sidebar;
    CanvasArea canvasArea;

    public MainWindow() {
        if(instance==null) instance = this;
        setTitle("Workflow util.Designer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        maincontroller = new MainController(this);

        menuBar = new MenuBar();
        setJMenuBar(menuBar);

        sidebar = new SideBar();
        add(sidebar, "West");

        canvasArea=new CanvasArea();
        add(canvasArea, "Center");
        setVisible(true);


    }


}
