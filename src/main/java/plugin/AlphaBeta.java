package plugin;

import framework.core.*;
import framework.core.Pieces.*;

import java.util.List;

public class AlphaBeta implements Player {

    private final Side side;
    private final int depth;

    private final int MAX = 1000000;
    private final int MIN = -1000000;

    public AlphaBeta(Side side, int depth) {
        this.side = side;
        this.depth = depth;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
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


//    private Move alphaBeta(Board board, int depth, boolean maximizing, int alpha, int beta) {
//
//        if (depth == this.depth) {
//
//            List<Move> moves = board.getValidMoves(this.side);
//
//            return moves.get(0);
//        }
//
//        if (maximizing) {
//
//            int best = MIN;
//
//            for (int i = 0; i < moves.size(); i++) {
//
//                Move tempMove = moves.get(i);
//
//                List<Move> tempMoves = board.move(tempMove);
//
//                Move resultMove = alphaBeta(depth + 1, false, alpha, beta, )
//
//            }
//
//        } else {
//
//
//
//        }
//
//
//
//    }

    private int boardScore(Board board) {

        int score = 0;

        List<Piece> pieces = board.getPieces(this.side);

        Side opponent;
        if (this.side.equals(Side.White)) {
            opponent = Side.Black;
        } else {
            opponent = Side.White;
        }

        List<Piece> opponents = board.getPieces(opponent);


        for (Piece piece : pieces) {
            score += pieceScore(piece);
        }

        for (Piece opponentPiece : opponents) {
            score -= pieceScore(opponentPiece);
        }

        if (board.isCheck(opponent)) {
            score += 10000;
        }

        return score;
    }


    private int pieceScore(Piece piece) {

        Coordinate coordinate = piece.getCoordinate();
        Side side = piece.getSide();

        int score = 0;
        //Pawn
        if (piece instanceof Pawn) {
            score = 10;
            if (side.equals(Side.White)) {
                score += (7 - coordinate.getRow());
            } else {
                score += coordinate.getRow();
            }

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



        return score;
    }



}
