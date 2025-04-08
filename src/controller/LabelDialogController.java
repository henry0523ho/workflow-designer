package controller;

import util.objects.BasicObject;
import util.objects.Label;
import view.LabelDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelDialogController implements ActionListener {
    private LabelDialog labelDialog;
    private BasicObject object;
    public Label label;

    public LabelDialogController(LabelDialog labelDialog, BasicObject object) {
        this.labelDialog = labelDialog;
        this.object = object;
        this.label=object.getLabel();
        if(this.label==null){
            this.label=new Label((int) object.x, (int) object.y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LabelDialogController");
        AbstractButton item = (AbstractButton) e.getSource();
        String actionCommand = item.getActionCommand();
        System.out.println(actionCommand);
        if (actionCommand.equals("ok")) {
            System.out.println("ok handler");
//            labelDialog.setVisible(false);
            labelDialog.dispose();
        } else if (actionCommand.equals("cancel")) {
            System.out.println("cancel handler");
//            labelDialog.setVisible(false);
            labelDialog.dispose();
        }
    }
}
