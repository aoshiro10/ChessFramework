package framework.gui;

import framework.core.*;
import framework.core.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class TileButton extends JButton {

    private final Coordinate coordinate;

    public TileButton(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void update(Chess chess) {

        Board board = chess.getBoard();
        Side side = chess.getSide();

        //Piece Image
        if (board.hasPiece(coordinate)) {
            Piece piece = board.getPiece(coordinate);
            setImage(piece);
        } else {
            this.setIcon(null);
        }

        ActionListener[] actionListeners = this.getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            removeActionListener(actionListener);
        }

        if (GUI.hasSelected()) {

            Coordinate selected = GUI.getSelected();
            Piece selectedPiece = board.getPiece(selected);
            List<Move> availableMoves = board.getValidMoves(selectedPiece);
            Move currentMove = findMove(availableMoves);


            if (this.coordinate.equals(selected)) {

                this.setEnabled(true);

                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GUI.setSelected(null);
                        chess.updateAll();
                    }
                });

            } else if (currentMove != null) {

                this.setEnabled(true);

                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GUI.setSelected(null);
                        chess.move(currentMove);
                        chess.updateAll();
                    }
                });

            } else {
                this.setEnabled(false);
            }

        } else {

            if (board.hasPiece(this.coordinate)) {

                Piece piece = board.getPiece(this.coordinate);
                Side pieceSide = piece.getSide();

                if (pieceSide.equals(side)) {

                    this.setEnabled(true);
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            GUI.setSelected(coordinate);
                            System.out.println(GUI.getSelected());
                            chess.updateAll();
                        }
                    });

                } else {
                    this.setEnabled(false);
                }

            } else {
                this.setEnabled(false);
            }

        }


    }


    private Move findMove(List<Move> moves) {

        for (Move move : moves) {
            Coordinate destination = move.getDestPos();
            if (this.coordinate.equals(destination)) {
                return move;
            }
        }

        return null;

    }

    private void setImage(Piece piece) {
        Image img = null;
        try {
            img = piece.getImage(piece.getSide());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIcon(new ImageIcon(img));
    }


}
