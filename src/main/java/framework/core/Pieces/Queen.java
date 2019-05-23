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

import static framework.core.Direction.*;
import static framework.core.Direction.SouthWest;
import static java.util.Objects.hash;

/**
 * Object representing a queen piece.
 */
public class Queen extends Piece {

    private final String NAME = "Queen";

    /**
     * Constructor for the piece.
     * @param side piece color
     * @param coordinate location on the board
     */
    public Queen(final Side side, final Coordinate coordinate) {
        super(side, coordinate);
    }

    /**
     * Returns the NAME of the piece.
     * @return NAME of the piece
     */
    @Override
    public String getPieceName() {
        return NAME;
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

        //North East
        int movesNE = Math.min(row, Math.abs(col - Board.getCols()) - 1);
        List<Coordinate> movesNorthEast = new ArrayList<>();
        for (int moveNE = 1; moveNE <= movesNE; moveNE++) {
            int tempRow = row - moveNE;
            int tempCol = col + moveNE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            movesNorthEast.add(tempCoordinate);
        }

        if (!movesNorthEast.isEmpty()) {
            moves.put(NorthEast, movesNorthEast);
        }

        //North West
        int movesNW = Math.min(row, col);
        List<Coordinate> movesNorthWest = new ArrayList<>();
        for (int moveNW = 1; moveNW <= movesNW; moveNW++) {
            int tempRow = row - moveNW;
            int tempCol = col - moveNW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            movesNorthWest.add(tempCoordinate);
        }

        if (!movesNorthWest.isEmpty()) {
            moves.put(NorthWest, movesNorthWest);
        }

        //South East
        List<Coordinate> movesSouthEast = new ArrayList<>();
        int deltaRow = Math.abs(row - Board.getRows()) - 1;
        int deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesSE = Math.min(deltaRow, deltaCol);
        for (int moveSE = 1; moveSE <= movesSE; moveSE++) {
            int tempRow = row + moveSE;
            int tempCol = col + moveSE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            movesSouthEast.add(tempCoordinate);
        }

        if (!movesSouthEast.isEmpty()) {
            moves.put(SouthEast, movesSouthEast);
        }


        //South West
        List<Coordinate> movesSouthWest = new ArrayList<>();
        deltaRow = Math.abs(row - Board.getRows()) - 1;
        int movesSW = Math.min(deltaRow, col);
        for (int moveSW = 1; moveSW <= movesSW; moveSW++) {
            int tempRow = row + moveSW;
            int tempCol = col - moveSW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);

            movesSouthWest.add(tempCoordinate);
        }

        if (!movesSouthWest.isEmpty()) {
            moves.put(SouthWest, movesSouthWest);
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

        //North East
        int movesNE = Math.min(row, Math.abs(col - Board.getCols()) - 1);
        for (int moveNE = 1; moveNE <= movesNE; moveNE++) {
            int tempRow = row - moveNE;
            int tempCol = col + moveNE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }

        //North West
        int movesNW = Math.min(row, col);
        for (int moveNW = 1; moveNW <= movesNW; moveNW++) {
            int tempRow = row - moveNW;
            int tempCol = col - moveNW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }

        //South East
        int deltaRow = Math.abs(row - Board.getRows()) - 1;
        int deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesSE = Math.min(deltaRow, deltaCol);
        for (int moveSE = 1; moveSE <= movesSE; moveSE++) {
            int tempRow = row + moveSE;
            int tempCol = col + moveSE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }

        //South West
        deltaRow = Math.abs(row - Board.getRows()) - 1;
        int movesSW = Math.min(deltaRow, col);
        for (int moveSW = 1; moveSW <= movesSW; moveSW++) {
            int tempRow = row + moveSW;
            int tempCol = col - moveSW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (destination.equals(tempCoordinate)) {
                return true;
            }
        }
        return false;
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
            return ImageIO.read(new File("src/main/resources/queen_white.png"));
        }
        return ImageIO.read(new File("src/main/resources/queen_black.png"));
    }

    /**
     * Makes a copy of the current piece.
     * @return copy of current piece
     */
    @Override
    public Piece copy() {
        return new Queen(this.getSide(), this.getCoordinate());
    }

    /**
     * String representation of the piece.
     * @return string representation
     */
    @Override
    public String toString() {
        return getSide().toString() + " " + NAME;
    }

    /**
     * Compares two objects for equality.
     * @param obj objects being compared to
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Queen)) {
            return false;
        }
        Queen queen = (Queen) obj;
        return (queen.getSide().equals(this.getSide()))
                && (queen.getCoordinate().equals(this.getCoordinate()));
    }

    /**
     * Getter for object's hashcode.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide());
    }
}
