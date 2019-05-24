package framework.core;

import framework.core.Pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Object representing the board of chess.
 */
public final class Board {

    private static final int ROWS = 8;
    private static final int COLS = 8;
    private HashMap<Coordinate, Piece> board;

    private King blackKing;
    private King whiteKing;

    /**
     * Constructor for a initial chess board.
     */
    public Board() {
        this.board = buildInitBoard();
    }

    private HashMap<Coordinate, Piece> buildInitBoard() {

        int[] rows = {0, 1, 6, 7};
        int[] cols = {0, 1, 2, 3, 4, 5, 6, 7};

        HashMap<Coordinate, Piece> board = new HashMap<>();
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

        return board;
    }

    /**
     * Getter for total number of columns.
     * @return number of columns
     */
    public static int getCols() {
        return COLS;
    }

    /**
     * Getter for total number of rows.
     * @return number of rows.
     */
    public static int getRows() {
        return ROWS;
    }

    /**
     * Checks if current board is in check for a given side.
     * @param side side being check for check
     * @return true if in check; false otherwise
     */
    public boolean isCheck(final Side side) {

        Coordinate kingPos;
        if (side.equals(Side.White)) {
            kingPos = whiteKing.getCoordinate();
        } else {
            kingPos = blackKing.getCoordinate();
        }

        int row = kingPos.getRow();
        int col = kingPos.getCol();

        //North
        for (int tempRow = row - 1; tempRow >= 0; tempRow--) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //South
        for (int tempRow = row + 1; tempRow < Board.ROWS; tempRow++) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //East
        for (int tempCol = col + 1; tempCol < Board.COLS; tempCol++) {
            Coordinate tempCoordinate = new Coordinate(row, tempCol);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //West
        for (int tempCol = col - 1; tempCol >= 0; tempCol--) {
            Coordinate tempCoordinate = new Coordinate(row, tempCol);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //North East
        int deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesNE = Math.min(row, deltaCol);
        for (int moveNE = 1; moveNE <= movesNE; moveNE++) {
            int tempRow = row - moveNE;
            int tempCol = col + moveNE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);

            if (this.board.containsKey(tempCoordinate)) {

                if (isCheckHelper(side, kingPos, tempCoordinate)) {

                    return true;
                }
                break;
            }
        }

        //North West
        int movesNW = Math.min(row, col);
        for (int moveNW = 1; moveNW <= movesNW; moveNW++) {
            int tempRow = row - moveNW;
            int tempCol = col - moveNW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //South East
        int deltaRow = Math.abs(row - Board.getRows()) - 1;
        deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesSE = Math.min(deltaRow, deltaCol);
        for (int moveSE = 1; moveSE <= movesSE; moveSE++) {
            int tempRow = row + moveSE;
            int tempCol = col + moveSE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //South West
        deltaRow = Math.abs(row - Board.getRows()) - 1;
        int movesSW = Math.min(deltaRow, col);
        for (int moveSW = 1; moveSW <= movesSW; moveSW++) {
            int tempRow = row + moveSW;
            int tempCol = col - moveSW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (this.board.containsKey(tempCoordinate)) {
                if (isCheckHelper(side, kingPos, tempCoordinate)) {
                    return true;
                }
                break;
            }
        }

        //
        //
        ////
        int tempRow1 = row - 2;
        int tempCol1 = col - 1;
        Coordinate tempCoordinate1 = new Coordinate(tempRow1, tempCol1);
        if (this.board.containsKey(tempCoordinate1)
                && (isCheckHelper(side, kingPos, tempCoordinate1))) {
            return true;
        }

        return checkJumps(row, col, kingPos, side);
    }

    private boolean checkJumps(final int row, final int col,
                               final Coordinate kingPos, final Side side) {
        ////
        //
        //
        int tempRow2 = row + 2;
        int tempCol2 = col - 1;
        Coordinate tempCoordinate2 = new Coordinate(tempRow2, tempCol2);
        if (this.board.containsKey(tempCoordinate2)
                && (isCheckHelper(side, kingPos, tempCoordinate2))) {
            return true;
        }

        //////
        //
        int tempRow3 = row - 1;
        int tempCol3 = col - 2;
        Coordinate tempCoordinate3 = new Coordinate(tempRow3, tempCol3);
        if (this.board.containsKey(tempCoordinate3)
                && (isCheckHelper(side, kingPos, tempCoordinate3))) {
            return true;
        }

        ///////
        //
        int tempRow4 = row - 1;
        int tempCol4 = col + 2;
        Coordinate tempCoordinate4 = new Coordinate(tempRow4, tempCol4);
        if (this.board.containsKey(tempCoordinate4)
                && (isCheckHelper(side, kingPos, tempCoordinate4))) {
            return true;
        }

        ////
        //
        //
        int tempRow5 = row + 2;
        int tempCol5 = col + 1;
        Coordinate tempCoordinate5 = new Coordinate(tempRow5, tempCol5);
        if (this.board.containsKey(tempCoordinate5)
                && (isCheckHelper(side, kingPos, tempCoordinate5))) {
            return true;
        }

        //
        //
        ////
        int tempRow6 = row - 2;
        int tempCol6 = col + 1;
        Coordinate tempCoordinate6 = new Coordinate(tempRow6, tempCol6);
        if (this.board.containsKey(tempCoordinate6)
                && (isCheckHelper(side, kingPos, tempCoordinate6))) {
            return true;
        }

        //
        //////
        int tempRow7 = row + 1;
        int tempCol7 = col - 2;
        Coordinate tempCoordinate7 = new Coordinate(tempRow7, tempCol7);
        if (this.board.containsKey(tempCoordinate7)
                && (isCheckHelper(side, kingPos, tempCoordinate7))) {
            return true;
        }

        //
        //////
        int tempRow8 = row + 1;
        int tempCol8 = col + 2;
        Coordinate tempCoordinate8 = new Coordinate(tempRow8, tempCol8);
        if (this.board.containsKey(tempCoordinate8)
                && (isCheckHelper(side, kingPos, tempCoordinate8))) {
            return true;
        }
        return false;
    }

    private boolean isCheckHelper(Side side, Coordinate kingPos,
                                  Coordinate tempCoordinate) {
        Piece piece = this.board.get(tempCoordinate);

        return (!piece.getSide().equals(side))
                && (piece.hasPossibleCapture(kingPos));
    }

    /**
     * Returns a list of valid moves for a given piece.
     * @param piece piece being evaluated
     * @return list of vaid moves for piece
     */
    public List<Move> getValidMoves(Piece piece) {

        Map<Direction, List<Coordinate>> possibleMoves;
        List<Move> moves = new ArrayList<>();
        Side side = piece.getSide();

        possibleMoves = piece.getPossibleMoves();
        for (Direction direction : possibleMoves.keySet()) {
            List<Coordinate> directionMoves = possibleMoves.get(direction);

            if (direction.equals(Direction.Castling)) {
                King king = (King) piece;

                for (Coordinate destination : directionMoves) {

                    int rookRow = destination.getRow();
                    int rookCol;

                    if (destination.getCol() == 6) {
                        rookCol = 7;
                    } else {
                        rookCol = 0;
                    }

                    Coordinate rookCoor = new Coordinate(rookRow, rookCol);
                    if (validCastling(king, rookCoor)) {
                        Move tempMove = new Move(piece.getCoordinate(),
                                                destination, direction);
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
                        Move tempMove = new Move(piece.getCoordinate(),
                                                destination, direction);
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
                        Move tempMove = new Move(piece.getCoordinate(),
                                                destination, direction);
                        Board tempBoard = this.move(tempMove);
                        if (!tempBoard.isCheck(side)) {
                            moves.add(tempMove);
                        }
                    }
                }
            } else {
                for (Coordinate destination : directionMoves) {
                    if (validMove(piece, destination)) {
                        Move tempMove = new Move(piece.getCoordinate(),
                                                destination, direction);
                        Board tempBoard = this.move(tempMove);
                        if (!tempBoard.isCheck(side)) {
                            moves.add(tempMove);
                            if (board.containsKey(destination)) {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Returns a list of valid moves for a given side.
     * @param side side being analyzed
     * @return list of valid moves for side
     */
    public List<Move> getValidMoves(final Side side) {

        List<Move> moves = new ArrayList<>();
        List<Piece> pieces = getPieces(side);

        for (Piece piece : pieces) {
            List<Move> pieceMoves = getValidMoves(piece);
            moves.addAll(pieceMoves);
        }
        return moves;
    }

    /**
     * Checks if board has a piece on given coordinate.
     * @param coordinate coordinate
     * @return true if has piece; false otherwise
     */
    public boolean hasPiece(final Coordinate coordinate) {
        return this.board.containsKey(coordinate);
    }

    private boolean validMove(final Piece piece,
                              final Coordinate destination) {

        Side side = piece.getSide();
        //checking if piece is pawn,
        // because pawns can't capture moving vertically.
        if ((piece instanceof Pawn) && (board.containsKey(destination))) {

            return false;
        }

        return !((board.containsKey(destination))
                && (board.get(destination).getSide().equals(side)));
    }

    private boolean validCapture(final Pawn pawn,
                                 final Coordinate destination) {
        Side side = pawn.getSide();
        return (board.containsKey(destination)
                && (!(board.get(destination).getSide().equals(side))));
    }

    private boolean validJump(final Knight knight,
                              final Coordinate destination) {
        Side side = knight.getSide();
        return !((board.containsKey(destination))
                && (board.get(destination).getSide().equals(side)));
    }

    private boolean validCastling(final King king,
                                  final Coordinate destination) {

        Side side = king.getSide();
        if (this.isCheck(side)) {
            return false;
        }

        if (board.containsKey(destination)) {
            Piece destPiece = board.get(destination);
            if (!destPiece.getSide().equals(king.getSide())) {
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

    /**
     * Creates a new board instance after
     * performing a move to current board.
     * @param move move to be performed
     * @return board after move
     */
    public Board move(final Move move) {

        Board newBoard = new Board();

        HashMap<Coordinate, Piece> prev = this.board;

        newBoard.board = new HashMap<>();

        for (Coordinate coordinate : prev.keySet()) {
            Piece oldPiece = prev.get(coordinate);
            Piece newPiece = oldPiece.copy();
            newBoard.board.put(coordinate, newPiece);
        }

        King blackKing = (King) this.blackKing.copy();
        King whiteKing = (King) this.whiteKing.copy();
        newBoard.blackKing = blackKing;
        newBoard.whiteKing = whiteKing;
        newBoard.board.put(blackKing.getCoordinate(), blackKing);
        newBoard.board.put(whiteKing.getCoordinate(), whiteKing);

        Coordinate initCoor = move.getInitCoor();
        Coordinate destCoor = move.getDestCoor();
        Direction direction = move.getDirection();

        int destRow = destCoor.getRow();

        Piece piece = newBoard.board.get(initCoor);

        //change pawn to queen
        if ((destRow == 0 || destRow == 7) && (piece instanceof Pawn)) {
            piece = new Queen(piece.getSide(), piece.getCoordinate());
        }

        newBoard.board.remove(initCoor);

        if (direction.equals(Direction.Castling)) {

            int row = piece.getCoordinate().getRow();
            int rookOriginCol;

            if (destCoor.getCol() == 6) {
                rookOriginCol = 7;
            } else {
                rookOriginCol = 0;
            }
            Coordinate rookOrigin = new Coordinate(row, rookOriginCol);

            Piece rook = newBoard.board.get(rookOrigin);

            int rookDestCol;
            if (destCoor.getCol() == 6) {
                rookDestCol = 5;
            } else {
                rookDestCol = 2;
            }

            Coordinate rootDist = new Coordinate(row, rookDestCol);

            newBoard.board.remove(rookOrigin);
            newBoard.board.put(rootDist, rook);
            rook.setCoordinate(rootDist);
            rook.setInitPos(false);

        }

        piece.setCoordinate(destCoor);
        newBoard.board.put(destCoor, piece);
        piece.setInitPos(false);

        return newBoard;

    }

    /**
     * Check if board has a checkmate for given side.
     * @param side side being analyzed
     * @return true if checkmate for side; false otherwise
     */
    public boolean isCheckMate(final Side side) {
        if (!isCheck(side)) {
            return false;
        }
        List<Piece> pieces = this.getPieces(side);
        for (Piece piece : pieces) {
            if (!this.getValidMoves(piece).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of pieces for a given side.
     * @param side pieces' side
     * @return list of pieces
     */
    public List<Piece> getPieces(final Side side) {

        List<Piece> pieces = new ArrayList<>();
        for (Coordinate coordinate : this.board.keySet()) {
            Piece piece = this.board.get(coordinate);
            if (!piece.getSide().equals(side)) {
                continue;
            }
            Piece copy = piece.copy();
            pieces.add(copy);
        }
        return pieces;
    }

    /**
     * Checks if coordinate is inbounds for board.
     * @param coordinate current coordinate
     * @return true if inbounds; false otherwise
     */
    public static boolean inBounds(final Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        return (row >= 0) && (row < Board.ROWS)
                && (col >= 0) && (col < Board.COLS);
    }

    /**
     * Getter for a piece on current coordinate.
     * @param coordinate current coordinate
     * @return piece instance if there is a piece; null otherwise
     */
    public Piece getPiece(final Coordinate coordinate) {
        return board.get(coordinate);
    }

}
