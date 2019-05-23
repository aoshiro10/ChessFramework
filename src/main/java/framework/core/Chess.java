package framework.core;

import framework.gui.Listener;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Side side;
    private Board board;
    private List<Listener> listeners;
    private boolean started;

    private final Player whitePlayer;
    private final Player blackPlayer;

    public Chess(Player whitePlayer, Player blackPlayer) {
        this.started = false;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.listeners = new ArrayList<>();
        this.board = new Board();
        this.side = Side.White;
    }

    public Player getPlayer(Side side) {
        if (side.equals(Side.White)) {
            return whitePlayer;
        }
        return blackPlayer;
    }

    public void init() {
        this.started = true;
        nextTurn();
    }

    public void nextTurn() {
        if (!gameOver()) {
            pluginMove();
        }
        updateAll();
    }

    public Board getBoard() {
        return board;
    }

    private void pluginMove() {

        Player player = getPlayer(this.side);
        if (player != null) {
            Move move = player.chooseMove(this.board);
            this.board = this.board.move(move);
            this.side = switchSide(side);
        }

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
        return board.getValidMoves(side).isEmpty();
    }

    public Side getWinner() {
        assert (gameOver());
        return switchSide(this.side);
    }

    public Side getSide() {
        return side;
    }
}
