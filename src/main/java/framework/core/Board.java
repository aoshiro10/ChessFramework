package framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Board {

    private final int rows = 8;
    private final int cols = 8;
    private HashMap<Coordinate, Piece> board;

    public Board() {
        initBoard();
    }


    //TODO isCheck
    public boolean isCheck(Side side) {

        return false;


    }


    public Board(Board oldBoard) {

    }

    private void initBoard() {

        //TODO build initial board

    }


    List<Piece> getPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (Coordinate coordinate : board.keySet()) {
            Piece piece = board.get(coordinate);
            pieces.add(piece);
        }
        return pieces;
    }

    List<Piece> getSidePieces(Side side) {
        List<Piece> pieces = new ArrayList<>();
        for (Coordinate coordinate : board.keySet()) {
            Piece piece = board.get(coordinate);
            if (piece.getSide() == side){
                pieces.add(piece);
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
        return board.getOrDefault(coordinate, null);
    }




}
