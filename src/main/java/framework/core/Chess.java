package framework.core;

import java.util.List;

public class Chess {

    private Side side;
    private Board board;

    public Chess() {
        board = new Board();
        side = Side.White;
    }

    public Board getBoard() {
        return board;
    }

    public void move(Move move) {
        this.board = this.board.move(move);
        this.side = switchSide(side);
    }

    private Side switchSide(Side side) {
        if (side.equals(Side.White)) {
            return Side.Black;
        }
        return Side.White;
    }

    public List<Move> getMoves() {
        return this.board.getValidMoves(this.side);
    }

    public boolean gameOver() {
        return (getMoves().isEmpty());
    }

    public Side getWinner() {
        assert (gameOver());
        return switchSide(this.side);
    }

}
