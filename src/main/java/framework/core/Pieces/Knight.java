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
 * Object representing a knight piece.
 */
public class Knight extends Piece {

    private final String NAME = "Knight";

    /**
     * Constructor for the piece.
     * @param side piece color
     * @param coordinate location on the board
     */
    public Knight(final Side side, final Coordinate coordinate) {
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

        //Jumps
        List<Coordinate> jumps = new ArrayList<>();

        //
        //
        ////
        int tempRow1 = row - 2;
        int tempCol1 = col - 1;
        Coordinate tempCoordinate1 = new Coordinate(tempRow1, tempCol1);
        if (Board.inBounds(tempCoordinate1)) {
            jumps.add(tempCoordinate1);
        }

        ////
        //
        //
        int tempRow2 = row + 2;
        int tempCol2 = col - 1;
        Coordinate tempCoordinate2 = new Coordinate(tempRow2, tempCol2);
        if (Board.inBounds(tempCoordinate2)) {
            jumps.add(tempCoordinate2);
        }

        //////
            //
        int tempRow3 = row - 1;
        int tempCol3 = col - 2;
        Coordinate tempCoordinate3 = new Coordinate(tempRow3, tempCol3);
        if (Board.inBounds(tempCoordinate3)) {
            jumps.add(tempCoordinate3);
        }

        ///////
        //
        int tempRow4 = row - 1;
        int tempCol4 = col + 2;
        Coordinate tempCoordinate4 = new Coordinate(tempRow4, tempCol4);
        if (Board.inBounds(tempCoordinate4)) {
            jumps.add(tempCoordinate4);
        }

        ////
          //
          //
        int tempRow5 = row + 2;
        int tempCol5 = col + 1;
        Coordinate tempCoordinate5 = new Coordinate(tempRow5, tempCol5);
        if (Board.inBounds(tempCoordinate5)) {
            jumps.add(tempCoordinate5);
        }

          //
          //
        ////
        int tempRow6 = row - 2;
        int tempCol6 = col + 1;
        Coordinate tempCoordinate6 = new Coordinate(tempRow6, tempCol6);
        if (Board.inBounds(tempCoordinate6)) {
            jumps.add(tempCoordinate6);
        }

            //
        //////
        int tempRow7 = row + 1;
        int tempCol7 = col - 2;
        Coordinate tempCoordinate7 = new Coordinate(tempRow7, tempCol7);
        if (Board.inBounds(tempCoordinate7)) {
            jumps.add(tempCoordinate7);
        }

        //
        //////
        int tempRow8 = row + 1;
        int tempCol8 = col + 2;
        Coordinate tempCoordinate8 = new Coordinate(tempRow8, tempCol8);
        if (Board.inBounds(tempCoordinate8)) {
            jumps.add(tempCoordinate8);
        }

        if (!jumps.isEmpty()) {
            moves.put(Direction.Jump, jumps);
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

        //
        //
        ////
        int tempRow1 = row - 2;
        int tempCol1 = col - 1;
        Coordinate tempCoordinate1 = new Coordinate(tempRow1, tempCol1);
        if (destination.equals(tempCoordinate1)) {
            return true;
        }

        ////
        //
        //
        int tempRow2 = row + 2;
        int tempCol2 = col - 1;
        Coordinate tempCoordinate2 = new Coordinate(tempRow2, tempCol2);
        if (destination.equals(tempCoordinate2)) {
            return true;
        }

        //////
        //
        int tempRow3 = row - 1;
        int tempCol3 = col - 2;
        Coordinate tempCoordinate3 = new Coordinate(tempRow3, tempCol3);
        if (destination.equals(tempCoordinate3)) {
            return true;
        }

        ///////
        //
        int tempRow4 = row - 1;
        int tempCol4 = col + 2;
        Coordinate tempCoordinate4 = new Coordinate(tempRow4, tempCol4);
        if (destination.equals(tempCoordinate4)) {
            return true;
        }

        ////
        //
        //
        int tempRow5 = row + 2;
        int tempCol5 = col + 1;
        Coordinate tempCoordinate5 = new Coordinate(tempRow5, tempCol5);
        if (destination.equals(tempCoordinate5)) {
            return true;
        }

        //
        //
        ////
        int tempRow6 = row - 2;
        int tempCol6 = col + 1;
        Coordinate tempCoordinate6 = new Coordinate(tempRow6, tempCol6);
        if (destination.equals(tempCoordinate6)) {
            return true;
        }

        //
        //////
        int tempRow7 = row + 1;
        int tempCol7 = col - 2;
        Coordinate tempCoordinate7 = new Coordinate(tempRow7, tempCol7);
        if (destination.equals(tempCoordinate7)) {
            return true;
        }

        //
        //////
        int tempRow8 = row + 1;
        int tempCol8 = col + 2;
        Coordinate tempCoordinate8 = new Coordinate(tempRow8, tempCol8);
        return destination.equals(tempCoordinate8);
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
            File file = new File("src/main/resources/knight_white.png");
            return ImageIO.read(file);
        }
        File file = new File("src/main/resources/knight_black.png");
        return ImageIO.read(file);
    }

    /**
     * Makes a copy of the current piece.
     * @return copy of current piece
     */
    @Override
    public Piece copy() {
        return new Knight(this.getSide(), this.getCoordinate());
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
        if (!(obj instanceof Knight)) {
            return  false;
        }
        Knight knight = (Knight) obj;
        return (knight.getSide().equals(this.getSide()))
                && (knight.getCoordinate().equals(this.getCoordinate()));
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
