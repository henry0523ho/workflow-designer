package view;

import util.objects.BasicObject;
import util.objects.Label;

import javax.swing.*;

public class LabelDialog extends JDialog {
//    LabelDialogController labelDialogController;

    public Label label;

    public JTextField nameTextField;
    public JComboBox<String> shapeComboBox;
    public JComboBox<String> colorComboBox;
    public JComboBox<String> sizeComboBox;

    public JButton okButton;
    public JButton cancelButton;

    public LabelDialog(JFrame parent, BasicObject object) {
        super(parent, "Custom label Style", true);

        this.label=object.getLabel();
        if(this.label==null){
            this.label=new Label((int) object.x, (int) object.y);
        }

        setSize(400, 600);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 10, 100, 30);
        add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(120, 10, 100, 30);
        nameTextField.setText(this.label.name);
        add(nameTextField);

        JLabel shapeLabel = new JLabel("Shape");
        shapeLabel.setBounds(10, 50, 100, 30);
        add(shapeLabel);

        shapeComboBox = new JComboBox<String>();
        for (Label.Shape shape : Label.Shape.values()) {
            shapeComboBox.addItem(shape.toString());
        }
        shapeComboBox.setBounds(120, 50, 100, 30);
        shapeComboBox.setSelectedItem(this.label.shape.toString());
        add(shapeComboBox);

        JLabel colorLabel = new JLabel("Color");
        colorLabel.setBounds(10, 90, 100, 30);
        add(colorLabel);

        colorComboBox = new JComboBox<String>();
        for (Label.Color color : Label.Color.values()) {
            colorComboBox.addItem(color.toString());
        }
        colorComboBox.setBounds(120, 90, 100, 30);
        colorComboBox.setSelectedItem(this.label.color.toString());
        add(colorComboBox);

        JLabel sizeLabel = new JLabel("Size");
        sizeLabel.setBounds(10, 130, 100, 30);
        add(sizeLabel);

        sizeComboBox = new JComboBox<String>();
        for (int size : Label.Sizes) {
            sizeComboBox.addItem(String.valueOf(size));
        }
        sizeComboBox.setBounds(120, 130, 100, 30);
        sizeComboBox.setSelectedItem(String.valueOf(this.label.size));
        add(sizeComboBox);


        okButton = new JButton("OK");
        okButton.setBounds(10, 170, 80, 30);
        okButton.setActionCommand("ok");
        okButton.addActionListener(e -> {
            // Handle OK button action
            String name = nameTextField.getText();
            String shape = (String) shapeComboBox.getSelectedItem();
            String color = (String) colorComboBox.getSelectedItem();
            String size = (String) sizeComboBox.getSelectedItem();
            label.name= name;
            label.shape = Label.Shape.valueOf(shape);
            label.color = Label.Color.valueOf(color);
            label.size = Integer.parseInt(size);
            object.setLabel(label);
            CanvasArea.instance.repaint();
            dispose();
        });
        add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(100, 170, 80, 30);
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
        setVisible(true);

//        labelDialogController = new LabelDialogController(this, object);





//        okButton.addActionListener(labelDialogController);
//        cancelButton.addActionListener(labelDialogController);
    }

    public String getLabel() {
        return nameTextField.getText();
    }
}
