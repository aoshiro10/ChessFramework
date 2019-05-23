package framework.gui;

import framework.core.Chess;
import framework.core.Coordinate;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private static JFrame jFrame;
    private static Chess chess;

    private static Coordinate selected;

    public GUI(Chess chess) {
        GUI.chess = chess;
        jFrame = gameFrame();
        jFrame.setVisible(true);
        initBoard();
    }

    public static Coordinate getSelected() {
        return selected;
    }

    public static boolean hasSelected() {
        return (selected != null);
    }

    public static void setSelected(Coordinate selected) {
        GUI.selected = selected;
    }

    private static void initBoard() {

        JPanel boardPanel = new JPanel(new GridLayout(9,9));
        boardPanel.setSize(700, 700);
        for (int row = 0; row < 9; row ++){
            for (int col = 0; col < 9; col++) {
                if (row == 8 && col == 8) {
                    boardPanel.add(updateCurrentPlayerLabel());
                } else if (row == 8 || col == 8) {
                    boardPanel.add(updateSideLabels(row, col));
                } else {
                    TileButton tileButton = new TileButton(new Coordinate(row, col));
                    boardPanel.add(tileButton);
                    chess.addListener(tileButton);
                    tileButton.init(chess);
                }
            }
        }
        jFrame.add(boardPanel);

    }

    private static JLabel updateSideLabels(int row, int col) {
        JLabel label;
        if (col == 8) {
            label = new JLabel(Character.toString((char) (row + 65)), SwingConstants.CENTER);
        } else {
            label = new JLabel(Integer.toString(col + 1), SwingConstants.CENTER);
        }
        return label;
    }

    private static PlayerLabel updateCurrentPlayerLabel() {
        PlayerLabel label = new PlayerLabel();
        chess.addListener(label);
        label.init(chess);
        return label;
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
