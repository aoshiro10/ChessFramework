package framework.gui;

import framework.core.Chess;
import framework.core.Side;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerLabel extends JPanel implements Listener {


    private JPanel mainPanel;


    @Override
    public void init(Chess chess) {
        JButton button = new JButton("Start");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chess.init();
            }
        });
        mainPanel.add(button);

        JLabel label = new JLabel("");
        mainPanel.add(label);

    }

    @Override
    public void update(Chess chess) {

        cleanLabel();
        JButton button = new JButton("Next");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chess.nextTurn();
            }
        });

        mainPanel.add(button);


        String text = chess.getSide().toString() + " turn";
        JLabel label = new JLabel(text);

        mainPanel.add(label);

        this.repaint();

    }

    private void cleanLabel() {
        Component[] components = this.mainPanel.getComponents();
        for (Component component : components) {
            this.mainPanel.remove(component);
        }
    }
    
    public PlayerLabel() {
        this.mainPanel = new JPanel(new GridLayout(2, 1));
        this.add(this.mainPanel);
    }
}
