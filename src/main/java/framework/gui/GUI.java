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

    public GUI(Chess chess) {
        GUI.chess = chess;
        jFrame = gameFrame();
        jFrame.setVisible(true);
        updateBoard();
        jFrame.add(boardPanel);
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
                    updateButtons(row, col);
                }
            }
        }
        jFrame.repaint();
        jFrame.revalidate();

    }

    private static void updateButtons(int row, int col) {

        Coordinate tempCoordinate = new Coordinate(row, col);
        Board board = chess.getBoard();


        if (selected == null) {

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

                if (!piece.getSide().equals(chess.getSide())) {
                    button.setEnabled(false);
                }

                final int tempRow = row;
                final int tempCol = col;

                button.addActionListener(e -> {
                    selected = new Coordinate(tempRow, tempCol);
                    updateBoard();
                });

                boardPanel.add(button);

            } else {
                JButton button = new JButton();
                button.setEnabled(false);
                boardPanel.add(button);

            }

        } else {

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

                if (tempCoordinate.equals(selected)) {

                    System.out.println("once");
                    button.setEnabled(true);

                    button.addActionListener(e -> {
                        selected = null;
                        updateBoard();
                    });

                } else {
                    button.setEnabled(false);
                }
                boardPanel.add(button);
            } else {

                Piece selectedPiece = board.getPiece(selected);
                List<Move> availableMoves = board.getValidMoves(selectedPiece);

                boolean foundTile = false;

                for (Move move : availableMoves) {
                    if (move.getDestPos().equals(tempCoordinate)) {
                        foundTile = true;
                        break;
                    }

                }
                JButton button = new JButton();
                if (foundTile) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
                boardPanel.add(button);
            }

        }

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
