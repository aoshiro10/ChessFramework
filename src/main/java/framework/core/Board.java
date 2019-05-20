package framework.core;

import framework.core.Pieces.*;

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

        int[] rows = {0, 1, 6, 7};
        int[] cols = {0, 1, 2, 3, 4, 5, 6, 7};

        board = new HashMap<>();
        for (int row : rows) {
            Side side;

            if (row == 0 || row == 1) {
                side = Side.Black;
            } else {
                side = Side.White;
            }

            for (int col : cols) {
                Coordinate coordinate = new Coordinate(row, col);

                if (row == 1 || row == 6) {
                    Pawn pawn = new Pawn(side, coordinate);
                    pawn.setInitPos(true);
                    board.put(coordinate, pawn);
                } else {

                    if (col == 0 || col == 7) {
                        Rook rook = new Rook(side, coordinate);
                        rook.setInitPos(true);
                        board.put(coordinate, rook);
                    } else if (col == 1 || col == 6) {
                        Knight knight = new Knight(side, coordinate);
                        board.put(coordinate, knight);
                    } else if (col == 2 || col == 5) {
                        Bishop bishop = new Bishop(side, coordinate);
                        board.put(coordinate, bishop);
                    } else if (col == 3) {
                        Queen queen = new Queen(side, coordinate);
                        board.put(coordinate, queen);
                    }

                }
            }

        }

        Coordinate bKingCoor = new Coordinate(0, 4);
        blackKing = new King(Side.Black, bKingCoor);
        blackKing.setInitPos(true);
        board.put(bKingCoor, blackKing);

        Coordinate wKingCoor = new Coordinate(7, 4);
        whiteKing = new King(Side.White, wKingCoor);
        whiteKing.setInitPos(true);
        board.put(wKingCoor, whiteKing);

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

        int row = kingPos.getRow();
        int col = kingPos.getCol();

        //North
        for (int tempRow = row - 1; row >= 0; row--) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            if (this.board.containsKey(tempCoordinate)) {
                Piece piece = this.board.get(tempCoordinate);
                if ((!piece.getSide().equals(side)) && (piece.hasPossibleMove(kingPos))){
                    return true;
                }
            }
        }


        return false;
    }

    public List<Move> getValidMoves(Side side) {

        //TODO check if pawn is moving forward into an opponents tile

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

                            Board tempBoard = this.move(tempMove);

                            if (!tempBoard.isCheck(side)) {
                                moves.add(tempMove);
                            }


                        }
                    }
                } else if (direction.equals(Direction.Jump)) {
                    Knight knight = (Knight) piece;
                    for (Coordinate destination : directionMoves) {
                        if (validJump(knight, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            Board tempBoard = this.move(tempMove);

                            if (!tempBoard.isCheck(side)) {
                                moves.add(tempMove);
                            }
                        }
                    }
                } else if (direction.equals(Direction.Capture)) {
                    Pawn pawn = (Pawn) piece;
                    for (Coordinate destination : directionMoves) {
                        if (validCapture(pawn, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            Board tempBoard = this.move(tempMove);

                            if (!tempBoard.isCheck(side)) {
                                moves.add(tempMove);
                            }
                        }
                    }
                } else {
                    for (Coordinate destination : directionMoves) {
                        if (validMove(piece, destination)) {
                            Move tempMove = new Move(piece.getCoordinate(), destination, direction);
                            Board tempBoard = this.move(tempMove);

                            if (!tempBoard.isCheck(side)) {
                                moves.add(tempMove);
                            }
                        } else {
                            break;
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

    public Board move(Move move) {

        Board newBoard = new Board();

        HashMap<Coordinate, Piece> prev = this.board;

        newBoard.board = new HashMap<>();

        for (Coordinate coordinate : prev.keySet()) {
            Piece oldPiece = prev.get(coordinate);
            Piece newPiece = oldPiece.copy();
            newBoard.board.put(coordinate, newPiece);
        }

        Coordinate initCoor = move.getInitPos();
        Coordinate destCoor = move.getDestPos();
        Direction direction = move.getDirection();

        Piece piece = newBoard.board.get(initCoor);
        newBoard.board.remove(initCoor);

        if (direction.equals(Direction.Castling)) {

            Piece rook = newBoard.board.get(destCoor);
            int row = piece.getCoordinate().getRow();
            int rootCol;
            if (destCoor.getCol() == 7) {
                rootCol = 5;
            } else {
                rootCol = 2;
            }

            Coordinate rootDist = new Coordinate(row, rootCol);

            newBoard.board.remove(destCoor);
            newBoard.board.put(rootDist, rook);
            rook.setCoordinate(rootDist);
            rook.setInitPos(false);

        }

        piece.setCoordinate(destCoor);
        newBoard.board.put(destCoor, piece);
        piece.setInitPos(false);

        return newBoard;

    }

    public List<Piece> getPieces(Side side) {

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

    public static boolean inBounds(Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return (row >= 0) && (row < Board.rows) && (col >= 0) && (col < Board.cols);
    }

    public Piece getPiece(Coordinate coordinate) {
        return board.get(coordinate);
    }

}
