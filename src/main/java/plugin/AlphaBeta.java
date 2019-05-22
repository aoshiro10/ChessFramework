package plugin;

import framework.core.*;
import framework.core.Pieces.*;

import java.util.List;

public class AlphaBeta implements Player {


    private final Side side;
    private final int depth;

    private final int MAX = 10000000;
    private final int MIN = -10000000;

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
            int tempScore = alphaBeta(tempBoard, depth, MIN, MAX, this.side);
            if (this.side.equals(Side.Black)) {
                tempScore = -tempScore;
            }

            if ((bestMove == null) || (tempScore > max)) {
                bestMove = move;
                max = tempScore;
            }
        }

        return bestMove;
    }


    private int alphaBeta(Board board, int depth, int alpha, int beta, Side side) {

        if (board.isCheck(side) || depth == 0) {
            return boardScore(board);
        }

        if (side.equals(Side.White)) { //max

            int tempMax = MIN;
            List<Move> moves = board.getValidMoves(side);

            for (Move move : moves) {

                Board tempBoard = board.move(move);
                int tempResult = alphaBeta(tempBoard, depth - 1, alpha , beta , opponent(side));
                tempMax = Math.max(tempMax, tempResult);
                alpha = Math.max(alpha, tempResult);
                if (beta <= alpha) {
                    break;
                }

            }
            return tempMax;
        } else { //min

            int tempMin = MAX;
            List<Move> moves = board.getValidMoves(side);

            for (Move move : moves) {
                Board tempBoard = board.move(move);
                int tempResult = alphaBeta(tempBoard, depth - 1, alpha, beta, opponent(side));
                tempMin = Math.min(tempMin, tempResult);
                beta = Math.min(beta, tempResult);
                if (beta <= alpha) {
                    break;
                }
            }
            return tempMin;

        }

    }



    private Side opponent(Side side) {
        if (side.equals(Side.White)) {
            return Side.Black;
        }
        return Side.White;
    }


    private int boardScore(Board board) {

        return sideScore(board, Side.White) - sideScore(board, Side.Black);

    }

    private int sideScore(Board board, Side side) {

        int score = 0;

        List<Piece> pieces = board.getPieces(side);

        Side opponent = opponent(side);
        List<Piece> opponents = board.getPieces(opponent);


        for (Piece piece : pieces) {
            score += pieceScore(piece);
        }

        for (Piece opponentPiece : opponents) {
            score -= pieceScore(opponentPiece);
        }

        if (board.isCheck(opponent)) {
            score += 30;
        }

        if (board.checkMate(opponent)) {
            score += 100000;
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
