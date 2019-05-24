package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for template method for constructing pieces.
 */
public abstract class Piece {

    private final Side side;
    private Coordinate coordinate;

    /**
     * Constructor for the piece.
     * @param side piece color
     * @param coordinate location on the board
     */
    public Piece(final Side side, final Coordinate coordinate) {
        this.side = side;
        this.coordinate = coordinate;
    }

    /**
     * Setter for current coordinate.
     * @param coordinate current position in the board
     */
    public void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Getter for current coordinate.
     * @return coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Setter for initPos.
     * @param initPos new initPos value for piece
     */
    public void setInitPos(final boolean initPos) {
    }

    /**
     * Getter for side.
     * @return side
     */
    public Side getSide() {
        return side;
    }

    /**
     * Creates a map with all the possible move destinations.
     * Each move destination is mapped from a direction.
     * i.e. if the destination is a move east, it will be under the east key.
     * @return map with directions mapping to destination coordinates
     */
    public abstract Map<Direction, List<Coordinate>> getPossibleMoves();

    /**
     * Checks if coordinate is a possible capture position for the piece.
     * @param destination coordinate
     * @return true if coordinate is a possible capture location for the piece;
     * false otherwise.
     */
    public abstract boolean hasPossibleCapture(Coordinate destination);

    /**
     * Makes a copy of the current piece.
     * @return copy of current piece
     */
    public abstract Piece copy();

    /**
     * Getter for piece image.
     * @param side color of the piece
     * @return BufferedImage of the image for current piece.
     * @throws IOException image file not found
     */
    public abstract BufferedImage getImage(Side side) throws IOException;

    /**
     * Returns the NAME of the piece.
     * @return NAME of the piece
     */
    public abstract String getPieceName();

}
