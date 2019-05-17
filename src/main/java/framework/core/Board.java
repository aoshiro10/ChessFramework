package framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Board {

    private final int rows = 8;
    private final int cols = 8;
    private List<Piece> board;

//    private final Piece blackKing;
//    private final Piece whiteKing;

    public Board() {
        initBoard();
    }



    //TODO isCheck
    public boolean isCheck(Side side) {

        return false;

    }



    public Board(Board oldBoard) {
//        this.blackKing = oldBoard.blackKing;
//        this.whiteKing = oldBoard.whiteKing;
        this.board = new ArrayList<>();
        //TODO init and put pieces
    }

    private void initBoard() {

        //TODO build initial board

    }


    List<Piece> getPieces() {
        return board;
    }




    boolean isValid(Side side, Coordinate coordinate) {
        if (!inBounds(coordinate)){
            return false;
        }

        Piece movePiece = getPiece(coordinate);
        return (movePiece == null) || (movePiece.getSide() != side);

    }

    private boolean inBounds(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return (row >= 0) && (row < rows) && (col >= 0) && (col < cols);
    }


    private Piece getPiece(Coordinate coordinate) {

        for (Piece piece : board) {
            if (piece.getCoordinate().equals(coordinate)) {
                return piece;
            }
        }

        return null;
    }






}
