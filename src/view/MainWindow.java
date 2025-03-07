package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainWindow() {
        setTitle("Workflow Designer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 加入不同畫面
//        mainPanel.add(new LoginPanel(this), "LOGIN");
//        mainPanel.add(new DashboardPanel(this), "DASHBOARD");

        add(mainPanel);
        setVisible(true);
    }

    public void switchPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
