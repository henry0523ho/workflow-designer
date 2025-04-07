package view;

import controller.SideBarController;
import view.icons.*;

import javax.swing.*;
import java.awt.*;



public class SideBar extends JPanel {

    ButtonGroup btnGroup = new ButtonGroup();

    public JToggleButton  selectButton;
    public JToggleButton  associationButton;
    public JToggleButton  generalizationButton;
    public JToggleButton  compositionButton;
    public JToggleButton  rectButton;
    public JToggleButton  ovalButton;

    SideBarController sideBarController;

    public SideBar() {
        setLayout(new GridLayout(0, 1));

        selectButton = new JToggleButton ();
        selectButton.setIcon(new SelectIcon(80, 80));
        selectButton.setActionCommand("select");
        btnGroup.add(selectButton);
        btnGroup.setSelected(selectButton.getModel(), true);
        add(selectButton);

        associationButton = new JToggleButton ();
        associationButton.setIcon(new AssociationIcon(80, 80));
        associationButton.setActionCommand("association");
        btnGroup.add(associationButton);
        add(associationButton);

        generalizationButton = new JToggleButton ();
        generalizationButton.setIcon(new GeneralizationIcon(80, 80));
        generalizationButton.setActionCommand("generalization");
        btnGroup.add(generalizationButton);
        add(generalizationButton);

        compositionButton = new JToggleButton ();
        compositionButton.setIcon(new CompositionIcon(80, 80));
        compositionButton.setActionCommand("composition");
        btnGroup.add(compositionButton);
        add(compositionButton);

        rectButton = new JToggleButton ();
        rectButton.setIcon(new RectIcon(80, 80));
        rectButton.setActionCommand("rect");
        btnGroup.add(rectButton);
        add(rectButton);

        ovalButton = new JToggleButton ();
        ovalButton.setIcon(new OvalIcon(80, 80));
        ovalButton.setActionCommand("oval");
        btnGroup.add(ovalButton);
        add(ovalButton);

        sideBarController = new SideBarController(this);
    }


}
