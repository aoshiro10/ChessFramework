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

        //Piece Image
        if (board.hasPiece(coordinate)) {
            Piece piece = board.getPiece(coordinate);
            setImage(piece);
        }

        Coordinate selected = GUI.getSelected();

        Side side = chess.getSide();

        if (selected == null) {

            if (board.hasPiece(coordinate)) {
                Piece piece = board.getPiece(coordinate);
                if (piece.getSide().equals(side)) {
                    this.setEnabled(true);

                    addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            GUI.setSelected(coordinate);
                            chess.updateAll();
                        }
                    });
                }
            } else {


                this.setEnabled(false);
            }


        } else if (this.coordinate.equals(selected)) {

            this.setEnabled(true);

            this.setEnabled(true);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GUI.setSelected(null);
                    chess.updateAll();
                }
            });

        } else {

            Piece selectedPiece = board.getPiece(selected);
            List<Move> availableMoves = board.getValidMoves(selectedPiece);

            for (Move move : availableMoves) {
                Coordinate destination = move.getDestPos();
                if (destination.equals(this.coordinate)) {
                    this.setEnabled(true);
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            chess.move(move);
                            chess.updateAll();
                        }
                    });
                    break;
                }  else {
                    this.setEnabled(false);

                }
            }

        }

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
