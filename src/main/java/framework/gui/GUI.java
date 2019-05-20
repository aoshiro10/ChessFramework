package framework.gui;

import framework.core.Board;
import framework.core.Chess;
import framework.core.Coordinate;
import framework.core.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {

    private static JFrame jFrame;
    private static Chess chess;

    public GUI(Chess chess) {
        GUI.chess = chess;
        jFrame = gameFrame();
        jFrame.setVisible(true);
        JPanel boardPanel = boardPanel();
        jFrame.add(boardPanel);

    }




    private static JPanel boardPanel() {

        JPanel boardPanel = new JPanel(new GridLayout(9,9));

        for (int row = 0; row < 9; row ++){
            for (int col = 0; col < 9; col++) {

                if (row == 8 && col == 8) {
                    JLabel label = new JLabel("Turn: White", SwingConstants.CENTER);
                    boardPanel.add(label);
                } else if (row == 8 || col == 8) {
                    JLabel label;
                    if (col == 8) {
                         label = new JLabel(Character.toString((char) (row + 65)), SwingConstants.CENTER);
                    } else {
                        label = new JLabel(Integer.toString(col + 1), SwingConstants.CENTER);
                    }

                    boardPanel.add(label);
                } else {

                    Coordinate tempCoordinate = new Coordinate(row, col);
                    Board board = chess.getBoard();

                    if (board.getPiece(tempCoordinate) != null) {
                        Piece piece = board.getPiece(tempCoordinate);
                        Image img = null;
                        try {
                            img = piece.getImage(piece.getSide());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JButton button = new JButton();
                        button.setIcon(new ImageIcon(img));
                        boardPanel.add(button);

                    } else {
                        JButton button = new JButton();
                        button.setEnabled(false);
                        boardPanel.add(button);

                    }

                }

            }
        }

        return boardPanel;

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
