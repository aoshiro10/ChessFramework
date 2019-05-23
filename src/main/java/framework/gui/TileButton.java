package framework.gui;

import framework.core.*;
import framework.core.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class TileButton extends JButton implements Listener{

    private final Coordinate coordinate;

    public TileButton(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void init(Chess chess) {
        Board board = chess.getBoard();
        setImage(board);
    }

    @Override
    public void update(Chess chess) {

        Board board = chess.getBoard();

        cleanButton();
        setImage(board);

        if (chess.gameOver()) {
            this.setEnabled(true);
            return;
        }

        if (GUI.hasSelected()) {
            updateHelperSelected(chess);
        } else {
            updateHelperUnselected(chess);
        }

        this.repaint();
    }

    private void updateHelperUnselected(Chess chess) {

        Board board = chess.getBoard();
        this.setEnabled(true);
        Side side = chess.getSide();
        Player player = chess.getPlayer(side);

        if (board.hasPiece(this.coordinate)) {

            Piece piece = board.getPiece(this.coordinate);

            if ((piece.getSide().equals(side)) && (player == null)) {

                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GUI.setSelected(coordinate);
                        chess.updateAll();
                    }
                });

            }
        }
    }

    private void updateHelperSelected(Chess chess) {

        Board board = chess.getBoard();
        Player player = chess.getPlayer(chess.getSide());

        Coordinate selected = GUI.getSelected();
        Piece selectedPiece = board.getPiece(selected);
        List<Move> availableMoves = board.getValidMoves(selectedPiece);
        Move currentMove = findMove(availableMoves);

        if (this.coordinate.equals(selected)) { //current button is selected

            this.setEnabled(true);

            if (player == null) {
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GUI.setSelected(null);
                        chess.updateAll();
                    }
                });
            }

            return;
        }

        if (currentMove != null) { //current button is possible move

            this.setEnabled(true);

            if (player == null) {
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GUI.setSelected(null);
                        chess.move(currentMove);

                        chess.nextTurn();
                    }
                });
            }
        }

    }

    private Move findMove(List<Move> moves) {
        for (Move move : moves) {
            Coordinate destination = move.getDestCoor();
            if (this.coordinate.equals(destination)) {
                return move;
            }
        }
        return null;

    }

    private void setImage(Board board) {

        if (board.hasPiece(coordinate)) {
            Piece piece = board.getPiece(coordinate);
            Image img = null;
            try {
                img = piece.getImage(piece.getSide());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setIcon(new ImageIcon(img));
        } else {
            this.setIcon(null);
        }
    }

    private void cleanButton() {

        ActionListener[] actionListeners = this.getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            removeActionListener(actionListener);
        }
        this.setEnabled(false);

    }

}
