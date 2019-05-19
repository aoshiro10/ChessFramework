package framework.core;

import framework.core.Pieces.*;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Board {

    private static final int rows = 8;
    private static final int cols = 8;
    private HashMap<Coordinate, Piece> board;

    private King blackKing;
    private King whiteKing;

    public Board() {
        initBoard();
    }


    public static int getCols() {
        return cols;
    }

    public static int getRows() {
        return rows;
    }

    public boolean isCheck(Side side) {

        Side oppositeSide;
        Coordinate kingPos;
        if (side.equals(Side.White)) {
            kingPos = whiteKing.getCoordinate();
            oppositeSide = Side.Black;
        } else {
            oppositeSide = Side.White;
            kingPos = blackKing.getCoordinate();
        }

        List<Move> opponentMoves = getValidMoves(oppositeSide);
        for (Move move : opponentMoves) {
            Coordinate destination = move.getDestPos();
            if (destination.equals(kingPos)) {
                return true;
            }
        }
        return false;
    }

    public List<Move> getValidMoves(Side side) {

        List<Move> moves = new ArrayList<>();
        List<Piece> pieces = getPieces(side);

        for (Piece piece : pieces) {
            Map<Direction, List<Coordinate>> possibleMoves = piece.getPossibleMoves();
            for (Direction direction : possibleMoves.keySet()) {
                List<Coordinate> directionMoves = possibleMoves.get(direction);

                if (direction.equals(Direction.Castling)) {
                    King king = (King) piece;
                    for (Coordinate destination : directionMoves) {
                        if (validCastling(king, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            moves.add(tempMove);
                        }
                    }
                } else if (direction.equals(Direction.Jump)) {
                    Knight knight = (Knight) piece;
                    for (Coordinate destination : directionMoves) {
                        if (validJump(knight, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            moves.add(tempMove);
                        }
                    }
                } else if (direction.equals(Direction.Capture)) {
                    Pawn pawn = (Pawn) piece;
                    for (Coordinate destination : directionMoves) {
                        if (validCapture(pawn, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            moves.add(tempMove);
                        }
                    }
                } else {
                    for (Coordinate destination : directionMoves) {
                        if (validMove(piece, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            moves.add(tempMove);
                        }
                    }
                }
            }
        }

        return moves;

    }

    private boolean validMove(Piece piece, Coordinate destination) {
        Side side = piece.getSide();
        return !((board.containsKey(destination)) && (board.get(destination).getSide().equals(side)));
    }

    private boolean validCapture(Pawn pawn, Coordinate destination) {
        Side side = pawn.getSide();
        return (board.containsKey(destination) && (!(board.get(destination).getSide().equals(side))));
    }

    private boolean validJump(Knight knight, Coordinate destination) {
        Side side = knight.getSide();
        return !((board.containsKey(destination)) && (board.get(destination).getSide().equals(side)));
    }

    private boolean validCastling(King king, Coordinate destination) {

        if (board.containsKey(destination)) {
            Piece destPiece = board.get(destination);
            if (!destPiece.getSide().equals(king.getSide())){
                return false;
            }
            if (!(destPiece instanceof Rook)) {
                return false;
            } else {
                Rook rook = (Rook) destPiece;
                if (!rook.getInitPos()) {
                    return false;
                }
            }

        } else {
            return false;
        }

        Coordinate currentCoor = king.getCoordinate();
        int cCol = currentCoor.getCol();
        int dCol = destination.getCol();
        int row = currentCoor.getRow();

        int lowCol = Math.min(cCol, dCol);
        int upCol = Math.max(cCol, dCol);

        for (int tempCol = lowCol + 1; tempCol < upCol; tempCol++) {

            Coordinate tempCoor = new Coordinate(row, tempCol);
            if (board.containsKey(tempCoor)) {
                return false;
            }

        }

        return true;

    }

    public Board(Board oldBoard, Move move) {
//        this.blackKing = oldBoard.blackKing;
//        this.whiteKing = oldBoard.whiteKing;
        this.board = new HashMap<>();
        //TODO init and put pieces
    }

    private void initBoard() {

        Coordinate bKingCoor = new Coordinate(0, 4);
        blackKing = new King(Side.Black, bKingCoor);

        Coordinate wKingCoor = new Coordinate(7, 4);
        whiteKing = new King(Side.White, wKingCoor);

    }


    private List<Piece> getPieces(Side side) {

        List<Piece> pieces = new ArrayList<>();

        for (Coordinate coordinate : this.board.keySet()) {
            Piece piece = this.board.get(coordinate);
            if (piece.getSide().equals(side)) {
                continue;
            }
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

    public static boolean inBounds(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return (row >= 0) && (row < Board.rows) && (col >= 0) && (col < Board.cols);
    }


    private Piece getPiece(Coordinate coordinate) {
        return board.get(coordinate);
    }

}
