package plugin;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Move;
import framework.core.Pieces.*;
import framework.core.Side;

import java.util.List;

public class AlphaBeta implements Player {

    private final Side side;

    public AlphaBeta(Side side) {
        this.side = side;

    }

    public Move chooseMove(Board board) {

        List<Move> moves = board.getValidMoves(this.side);

        Move bestMove = null;
        int max = 0;

        for (Move move : moves) {

            Board tempBoard = board.move(move);
            int tempScore = boardScore(tempBoard);

            if ((bestMove == null) || (tempScore > max)) {
                bestMove = move;
                max = tempScore;
            }
        }

        return bestMove;
    }

    private int boardScore(Board board) {

        int score = 0;

        List<Piece> pieces = board.getPieces(this.side);

        Side oppenent;
        if (this.side.equals(Side.White)) {
            oppenent = Side.Black;
        } else {
            oppenent = Side.White;
        }

        List<Piece> opponents = board.getPieces(oppenent);


        for (Piece piece : pieces) {
            score += pieceScore(piece);
        }

        for (Piece opponent : opponents) {
            score -= pieceScore(opponent);
        }

        return score;
    }


    private int pieceScore(Piece piece) {
        int score = 0;
        //Pawn
        if (piece instanceof Pawn) {
            score = 10;
        }

        //Knight
        if (piece instanceof Knight || piece instanceof Bishop) {
            score = 30;
        }

        //Rook
        if (piece instanceof Rook) {
            score = 50;
        }

        //Queen
        if (piece instanceof Queen) {
            score = 90;
        }

        //King
        if (piece instanceof King) {
            score = 1000000;
        }

        Coordinate coordinate = piece.getCoordinate();
        Side side = piece.getSide();
        if (side.equals(Side.White)) {
            score += (7 - coordinate.getRow());
        } else {
            score += coordinate.getRow();
        }

        return score;
    }



}
