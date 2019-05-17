package framework.core;

import java.util.ArrayList;
import java.util.List;

public final class Board {

    private Piece[][] board;
    private final int rows = 8;
    private final int cols = 8;

    public Board() {
        board = new Piece[8][];
        for (int row = 0; row < rows; row++) {
            board[row] = new Piece[8];
            for (int col = 0; col < cols; col++){
                board[row][col] = null;
            }
        }
    }

    List<Piece> getPieces() {

        List<Piece> pieces = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Piece piece = getPiece(new Coordinate(row, col));
                if (piece != null) {
                    pieces.add(piece);
                }
            }
        }

        return pieces;
    }


    boolean isValid(Piece piece, Coordinate coordinate) {
        if (!inBounds(coordinate)){
            return false;
        }

        Piece movePiece = getPiece(coordinate);
        return (movePiece == null) || (movePiece.getSide() != piece.getSide());

    }

    private boolean inBounds(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return (row >= 0) && (row < rows) && (col >= 0) && (col < cols);
    }


    private Piece getPiece(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return board[row][col];

    }


}
