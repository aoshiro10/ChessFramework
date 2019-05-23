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
import static java.util.Objects.hash;

/**
 * Object representing a bishop piece.
 */
public class Bishop extends Piece {

    private final String NAME = "Bishop";

    /**
     * Constructor for the piece.
     * @param side piece color
     * @param coordinate location on the board
     */
    public Bishop(final Side side, final Coordinate coordinate) {
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

        //North East
        int deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesNE = Math.min(row, deltaCol);
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
        deltaCol = Math.abs(col - Board.getCols()) - 1;
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

        //North East
        int deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesNE = Math.min(row, deltaCol);
        for (int moveNE = 1; moveNE <= movesNE; moveNE++) {
            int tempRow = row - moveNE;
            int tempCol = col + moveNE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (tempCoordinate.equals(destination)) {
                return true;
            }
        }

        //North West
        int movesNW = Math.min(row, col);
        for (int moveNW = 1; moveNW <= movesNW; moveNW++) {
            int tempRow = row - moveNW;
            int tempCol = col - moveNW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (tempCoordinate.equals(destination)) {
                return true;
            }
        }

        //South East
        int deltaRow = Math.abs(row - Board.getRows()) - 1;
        deltaCol = Math.abs(col - Board.getCols()) - 1;
        int movesSE = Math.min(deltaRow, deltaCol);
        for (int moveSE = 1; moveSE <= movesSE; moveSE++) {
            int tempRow = row + moveSE;
            int tempCol = col + moveSE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            if (tempCoordinate.equals(destination)) {
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
            if (tempCoordinate.equals(destination)) {
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
        return new Bishop(this.getSide(), this.getCoordinate());
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
            File file = new File("src/main/resources/bishop_white.png");
            return ImageIO.read(file);
        }
        File file = new File("src/main/resources/bishop_black.png");
        return ImageIO.read(file);
    }

    /**
     * String representation of the piece.
     * @return string representation
     */
    @Override
    public String toString() {
        return this.getSide().toString() + " " + NAME;
    }

    /**
     * Compares two objects for equality.
     * @param obj objects being compared to
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Bishop)) {
            return false;
        }
        Bishop bishop = (Bishop) obj;
        return (bishop.getCoordinate().equals(this.getCoordinate()))
                && (bishop.getSide().equals(this.getSide()));
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
