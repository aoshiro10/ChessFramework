package framework.core;

/**
 * Interface for player plugin.
 */
public interface Player {

    /**
     * Getter player's Side.
     * @return side
     */
    Side getSide();

    /**
     * Returns a movement from the set of valid moves.
     * @param board current chess board
     * @ensures \result is in board.getValidMoves(this.side)
     * @return Move
     */
    Move chooseMove(Board board);

}
