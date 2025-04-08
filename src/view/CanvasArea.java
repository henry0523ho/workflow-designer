package view;

import controller.CanvasAreaController;
import util.Designer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CanvasArea extends Canvas implements ActionListener {
    public static CanvasArea instance;

    CanvasAreaController canvasAreaController;

    public CanvasArea() {
        if(instance==null) instance = this;
        setBackground(Color.WHITE);
        canvasAreaController = new CanvasAreaController(this);
    }

    public void actionPerformed(ActionEvent e) {
        AbstractButton item = (AbstractButton) e.getSource();
        System.out.println(item.getText());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Designer.instance.render(g);
        canvasAreaController.drawSelectArea(g);
        canvasAreaController.drawLinkPreview(g);
    }
}
