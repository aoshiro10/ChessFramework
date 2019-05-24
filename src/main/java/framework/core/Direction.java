package framework.core;

/**
 * Enum for different directions.
 * Includes also, capture, castling, and jump
 * used for different types of movements.
 */
public enum Direction {

    /**
     * Pawn capturing a piece diagonally.
     */
    Capture,

    /**
     * King and rook performing a castling.
     * The castling is king's movement,
     * but the rook moves whenever it's called.
     */
    Castling, East,

    /**
     * Knight movement.
     */
    Jump, North, NorthEast, NorthWest, South,
    SouthEast, SouthWest, West

}


