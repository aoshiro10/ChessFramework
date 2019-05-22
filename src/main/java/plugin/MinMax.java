package plugin;

import framework.core.*;
import framework.core.Pieces.*;

import java.util.List;

public class MinMax implements Player {

    private final Side side;
    private final int depth;

    private final int MAX = 1000000;
    private final int MIN = -1000000;

    public MinMax(Side side, int depth) {
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
            int tempScore = minMax(tempBoard, depth, this.side);
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


    private int minMax(Board board, int depth, Side side) {

        if (board.isCheck(side) || depth == 0) {
            return boardScore(board);
        }

        if (side.equals(Side.White)) { //max

            int tempMax = MIN;
            List<Move> moves = board.getValidMoves(side);

            for (Move move : moves) {

                Board tempBoard = board.move(move);
                int tempResult = minMax(tempBoard, depth - 1, opponent(side));
                tempMax = Math.max(tempMax, tempResult);

            }
            return tempMax;
        } else { //min

            int tempMin = MAX;
            List<Move> moves = board.getValidMoves(side);

            for (Move move : moves) {
                Board tempBoard = board.move(move);
                int tempResult = minMax(tempBoard, depth - 1, opponent(side));
                tempMin = Math.min(tempMin, tempResult);

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
