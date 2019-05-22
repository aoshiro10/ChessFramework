package framework.core;

import framework.gui.Listener;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Side side;
    private Board board;
    private List<Listener> listeners;

    private final Player whitePlayer;
    private final Player blackPlayer;

    public Chess(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.listeners = new ArrayList<>();
        this.board = new Board();
        this.side = Side.White;
    }


    public void init() {
        pluginMove();
        updateAll();
    }

    public Board getBoard() {
        return board;
    }

    private void pluginMove() {

        if (this.side.equals(Side.White) && whitePlayer != null) {
            Move move = whitePlayer.chooseMove(this.board);
            this.move(move);
        } else if (this.side.equals(Side.Black) && blackPlayer != null){
            Move move = whitePlayer.chooseMove(this.board);
            this.move(move);
        }

    }

    public void move(Move move) {
        this.board = this.board.move(move);
        this.side = switchSide(side);
        updateAll();

        if (!gameOver()) {
            pluginMove();
        }

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
