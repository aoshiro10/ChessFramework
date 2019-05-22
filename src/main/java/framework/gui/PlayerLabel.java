package framework.gui;

import framework.core.Chess;
import framework.core.Side;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerLabel extends JPanel implements Listener {


    @Override
    public void init(Chess chess) {
        JButton button = new JButton("Move");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chess.init();

            }
        });

        this.add(button);
    }

    @Override
    public void update(Chess chess) {


//        Component[] components = this.getComponents();
//        for (Component component : components) {
//            this.remove(component);
//        }
//
//        JLabel label = new JLabel();
//
//        if (chess.gameOver()) {
//            label.setText("Checkmate");
//            return;
//        }
//
//        Side side = chess.getSide();
//        label.setText("Turn: " + side.toString());
//
//        this.add(label);

    }
    
    public PlayerLabel() {

    }
}
