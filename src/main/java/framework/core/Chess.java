package framework.core;

import framework.gui.Listener;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Side side;
    private Board board;
    private List<Listener> listeners;


    public Chess() {
        listeners = new ArrayList<>();
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

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void updateAll() {
        for (Listener listener : listeners) {
            listener.update(this);
        }
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

    public Side getSide() {
        return side;
    }
}
