package framework.core;

import framework.core.Pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Board {

    private final int rows = 8;
    private final int cols = 8;
    private HashMap<Coordinate, Piece> board;

//    private final Piece blackKing;
//    private final Piece whiteKing;

    public Board() {
        initBoard();
    }



    //TODO isCheck
    public boolean isCheck(Side side) {

        return false;

    }


    public static Board getBoard(Board oldBoard, Move move) {
        return null;
    }

    public Board(Board oldBoard, Move move) {
//        this.blackKing = oldBoard.blackKing;
//        this.whiteKing = oldBoard.whiteKing;
        this.board = new HashMap<>();
        //TODO init and put pieces
    }

    private void initBoard() {

        //TODO build initial board

    }


    private List<Piece> getPieces() {

        List<Piece> pieces = new ArrayList<>();

        for (Coordinate coordinate : this.board.keySet()) {
            Piece piece = this.board.get(coordinate);
            Piece copy = piece.copy();
            pieces.add(copy);
        }

        return pieces;
    }


    private boolean isValidCoordinate(Side side, Coordinate coordinate) {
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
        return board.getOrDefault(coordinate, null);
    }






}
