package framework.gui;

import framework.core.Board;
import framework.core.Chess;
import framework.core.Coordinate;
import framework.core.Move;
import framework.core.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GUI {

    private static JFrame jFrame;
    private static Chess chess;
    private static JPanel boardPanel;

    private static Coordinate selected;
    private static List<TileButton> tiles;

    public GUI(Chess chess) {
        GUI.chess = chess;
        jFrame = gameFrame();
        jFrame.setVisible(true);
        updateBoard();
        jFrame.add(boardPanel);
    }


    public static Coordinate getSelected() {
        return selected;
    }

    public static void setSelected(Coordinate selected) {
        GUI.selected = selected;
    }

    private static void updateBoard() {

        System.out.println("here");

        boardPanel = new JPanel(new GridLayout(9,9));

        for (int row = 0; row < 9; row ++){
            for (int col = 0; col < 9; col++) {
                if (row == 8 && col == 8) {
                    updateCurrentPlayerLabel();
                } else if (row == 8 || col == 8) {
                    updateSideLabels(row, col);
                } else {
                    TileButton tileButton = new TileButton(new Coordinate(row, col));
                    boardPanel.add(tileButton);
                    chess.addTileButton(tileButton);
                }
            }
        }
        chess.updateAll();
        jFrame.revalidate();

    }



    private static void updateSideLabels(int row, int col) {
        JLabel label;
        if (col == 8) {
            label = new JLabel(Character.toString((char) (row + 65)), SwingConstants.CENTER);
        } else {
            label = new JLabel(Integer.toString(col + 1), SwingConstants.CENTER);
        }

        boardPanel.add(label);
    }

    private static void updateCurrentPlayerLabel() {
        JLabel label = new JLabel("Turn: " + chess.getSide().toString(), SwingConstants.CENTER);
        boardPanel.add(label);
    }

    private static JFrame gameFrame() {
        JFrame frame = new JFrame("Chess");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo((Component)null);
        frame.setResizable(false);
        return frame;
    }

}
