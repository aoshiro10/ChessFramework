package framework.gui;

import framework.core.Chess;
import framework.core.Side;

import javax.swing.*;

public class PlayerLabel extends JLabel implements Listener {

    @Override
    public void update(Chess chess) {

        if (chess.gameOver()) {
            this.setText("Checkmate");
            return;
        }

        Side side = chess.getSide();
        this.setText("Turn: " + side.toString());

    }
    
    public PlayerLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }
}
