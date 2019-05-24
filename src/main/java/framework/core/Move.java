package framework.core;

/**
 * Object representing a move.
 * A move is a valid movement in chess from an initial
 * position to destination.
 */
public class Move {
    
    private final Coordinate initPos;
    private final Coordinate destPos;
    private final Direction direction;

    /**
     * Constructor for a new move object.
     * @param initPos initial coordinate
     * @param destPos destination coordinate
     * @param direction type of movement
     */
    public Move(final Coordinate initPos,
                final Coordinate destPos, final Direction direction) {
        this.direction = direction;
        this.initPos = initPos;
        this.destPos = destPos;
    }

    /**
     * Getter for destination coordinate.
     * @return destination coordinate
     */
    public Coordinate getDestCoor() {
        return destPos;
    }

    /**
     * Getter for initial coordinate.
     * @return initial coordinate
     */
    public Coordinate getInitCoor() {
        return initPos;
    }

    /**
     * Getter for direction (i.e. type of movement).
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * String representation of the movement.
     * @return string representation
     */
    @Override
    public String toString() {
        return "From: " + initPos + " to: " + destPos;
    }
}
