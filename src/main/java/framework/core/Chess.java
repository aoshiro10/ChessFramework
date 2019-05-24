package framework.core;

import framework.gui.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Object representing an instance of a chess game.
 * Uses observer method to update UI elements.
 */
public class Chess {

    /**
     * Current side.
     */
    private Side side;

    /**
     * Current board.
     */
    private Board board;

    /**
     * List of listeners from observer method.
     */
    private List<Listener> listeners;

    /**
     * Plugin for white side.
     */
    private final Player whitePlayer;

    /**
     * Plugin for black side.
     */
    private final Player blackPlayer;

    /**
     * Constructor for a new game of chess.
     * @param whitePlayer plugin controlling white pieces
     * @param blackPlayer plugin controlling black pieces.
     */
    public Chess(final Player whitePlayer, final Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.listeners = new ArrayList<>();
        this.board = new Board();
        this.side = Side.White;
    }

    /**
     * Given a side, returns the respective plugin.
     * @param side side requested
     * @return plugin
     */
    public Player getPlayer(final Side side) {
        if (side.equals(Side.White)) {
            return whitePlayer;
        }
        return blackPlayer;
    }

    /**
     * Initializes the game.
     */
    public void init() {
        nextTurn();
    }

    /**
     * Makes a plugin turn if there's a plugin for current turn.
     * Updates all UI elements after play.
     */
    public void nextTurn() {
        if (!gameOver()) {
            pluginMove();
        }
        updateAll();
    }

    /**
     * Getter for current game's board.
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Makes a plugin move for the current turn.
     */
    private void pluginMove() {

        Player player = getPlayer(this.side);
        if (player != null) {
            Move move = player.chooseMove(this.board);
            this.board = this.board.move(move);
            this.side = getOpponent(side);
        }

    }

    /**
     * Makes a move given a move.
     * Initializes a new board and sets it to the current one.
     * Switches side to the next player.
     * @param move current move
     */
    public void move(final Move move) {
        this.board = this.board.move(move);
        this.side = getOpponent(side);
    }

    /**
     * Given a side, returns the opponent's side.
     * @param side side
     * @return opponent's side
     */
    private Side getOpponent(final Side side) {
        if (side.equals(Side.White)) {
            return Side.Black;
        }
        return Side.White;
    }

    /**
     * Add listener from observer method pattern.
     * Observers has to adhere to Listener interface.
     * @param listener object to be updated from chess updates.
     */
    public void addListener(final Listener listener) {
        listeners.add(listener);
    }

    /**
     * Updates all the observers from observer method pattern.
     */
    public void updateAll() {
        for (Listener listener : listeners) {
            listener.update(this);
        }
    }

    /**
     * Returns a list of valid moves that current side can take.
     * @return list of valid moves for current side.
     */
    public List<Move> getMoves() {
        return this.board.getValidMoves(this.side);
    }

    /**
     * Checks if game is over.
     * @return true if game over; false otherwise.
     */
    public boolean gameOver() {
        return board.getValidMoves(side).isEmpty();
    }

    /**
     * Returns the winner's side.
     * @requires: gameOver() == true
     * @return winner's side
     */
    public Side getWinner() {
        assert (gameOver());
        return getOpponent(this.side);
    }

    /**
     * Getter for current side.
     * @return side
     */
    public Side getSide() {
        return side;
    }
}
