package org.pcs.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelA = new JPanel();

        JLabel label = new JLabel("Change Me");
        JButton button = new JButton("Click Me");
        button.addActionListener(e -> label.setText("I have changed"));

        panelA.add(label);
        panelA.add(button);

        frame.getContentPane().add(BorderLayout.SOUTH, panelA);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
