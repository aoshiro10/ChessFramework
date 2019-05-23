package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

/**
 * Object representing a rook piece.
 */
public class Rook extends Piece {

    private final String name = "Rook";
    private boolean initPos = false;

    /**
     * Constructor for the piece.
     * @param side piece color
     * @param coordinate location on the board
     */
    public Rook(final Side side, final Coordinate coordinate) {
        super(side, coordinate);
    }

    /**
     * Returns the NAME of the piece.
     * @return NAME of the piece
     */
    @Override
    public String getPieceName() {
        return name;
    }

    /**
     * Getter for initPos value.
     * @return initPos
     */
    public boolean getInitPos() {
        return this.initPos;
    }

    /**
     * Setter for initPos.
     * @param initPos new value.
     */
    public void setInitPos(final boolean initPos) {
        this.initPos = initPos;
    }

    /**
     * Creates a map with all the possible move destinations.
     * Each move destination is mapped from a direction.
     * i.e. if the destination is a move east, it will be under the east key.
     * @return map with directions mapping to destination coordinates
     */
    @Override
    public Map<Direction, List<Coordinate>> getPossibleMoves() {

        Map<Direction, List<Coordinate>> moves = new HashMap<>();

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        //NORTH
        List<Coordinate> movesNorth = new ArrayList<>();
        for (int tempRow = row - 1; tempRow >= 0; tempRow--) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            movesNorth.add(tempCoordinate);
        }

        if (!movesNorth.isEmpty()) {
            moves.put(Direction.North, movesNorth);
        }

        //WEST
        List<Coordinate> movesWest = new ArrayList<>();
        for (int tempCol = col - 1; tempCol >= 0; tempCol--) {
            Coordinate tempCoordinate = new Coordinate(row, tempCol);
            movesWest.add(tempCoordinate);
        }

        if (!movesWest.isEmpty()) {
            moves.put(Direction.West, movesWest);
        }

        //SOUTH
        List<Coordinate> movesSouth = new ArrayList<>();
        for (int tempRow = row + 1; tempRow < Board.getRows(); tempRow++) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            movesSouth.add(tempCoordinate);
        }

        if (!movesSouth.isEmpty()) {
            moves.put(Direction.South, movesSouth);
        }

        //EAST
        List<Coordinate> movesEast = new ArrayList<>();
        for (int tempCol = col + 1; tempCol < Board.getCols(); tempCol++) {
            Coordinate tempCoordinate = new Coordinate(row, tempCol);
            movesEast.add(tempCoordinate);
        }
        if (!movesEast.isEmpty()) {
            moves.put(Direction.East, movesEast);
        }
        return moves;

    }

    /**
     * Checks if coordinate is a possible capture position for the piece.
     * @param destination coordinate
     * @return true if coordinate is a possible capture location for the piece;
     * false otherwise.
     */
    @Override
    public boolean hasPossibleCapture(final Coordinate destination) {

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        //NORTH
        for (int tempRow = row - 1; tempRow >= 0; tempRow--) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }

        //WEST
        for (int tempCol = col - 1; tempCol >= 0; tempCol--) {
            Coordinate tempCoordinate = new Coordinate(row, tempCol);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }

        //SOUTH
        for (int tempRow = row + 1; tempRow < Board.getRows(); tempRow++) {
            Coordinate tempCoordinate = new Coordinate(tempRow, col);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }

        //EAST
        for (int tempCol = col + 1; tempCol < Board.getCols(); tempCol++) {
            Coordinate tempCoordinate = new Coordinate(row, tempCol);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a copy of the current piece.
     * @return copy of current piece
     */
    @Override
    public Piece copy() {
        Rook rook = new Rook(getSide(), getCoordinate());
        rook.initPos = initPos;
        return rook;
    }

    /**
     * String representation of the piece.
     * @return string representation
     */
    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }

    /**
     * Getter for piece image.
     * @param side color of the piece
     * @return BufferedImage of the image for current piece.
     * @throws IOException image file not found
     */
    @Override
    public BufferedImage getImage(final Side side) throws IOException {
        if (side.equals(Side.White)) {
            File file = new File("src/main/resources/rook_white.png");
            return ImageIO.read(file);
        }
        File file = new File("src/main/resources/rook_black.png");
        return ImageIO.read(file);
    }

    /**
     * Compares two objects for equality.
     * @param obj objects being compared to
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Rook)) {
            return false;
        }
        Rook rook = (Rook) obj;
        return (rook.initPos == this.initPos)
                && (rook.getSide().equals(this.getSide()))
                && (rook.getCoordinate().equals(this.getCoordinate()));
    }

    /**
     * Getter for object's hashcode.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide(), this.initPos);
    }

}
