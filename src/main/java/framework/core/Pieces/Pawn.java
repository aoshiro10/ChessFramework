package framework.core.Pieces;

import framework.core.*;

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
 * Object representing a pawn piece.
 */
public class Pawn extends Piece {

    private boolean initPos = false;
    private final String NAME = "Pawn";

    /**
     * Constructor for the piece.
     * @param side piece color
     * @param coordinate location on the board
     */
    public Pawn(final Side side, final Coordinate coordinate) {
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

        int sideMult = 1;

        List<Coordinate> verticalMoves = new ArrayList<>();
        Direction verticalDir = Direction.South;
        if (getSide().equals(Side.White)) {
            sideMult = -1;
            verticalDir = Direction.North;
        }

        //vertical move
        int tempRow1 = row + sideMult;
        if (tempRow1 >= 0 && tempRow1 < Board.getRows()) {
            Coordinate coordinate1 = new Coordinate(tempRow1, col);
            verticalMoves.add(coordinate1);
        }

        //checking for double jump
        if (initPos) {
            int tempRow2 = row + (2 * sideMult);
            Coordinate coordinate2 = new Coordinate(tempRow2, col);
            verticalMoves.add(coordinate2);
        }

        if (!verticalMoves.isEmpty()) {
            moves.put(verticalDir, verticalMoves);
        }

        List<Coordinate> captureMoves = new ArrayList<>();
        //checking for capture moves
        int tempRow3 = row + sideMult;
        if (tempRow3 >= 0 && tempRow3 < Board.getRows()) {
            int tempCol1 = col + 1;
            if (tempCol1 < Board.getCols()) {
                Coordinate coordinate3 = new Coordinate(tempRow3, tempCol1);
                captureMoves.add(coordinate3);
            }

            int tempCol2 = col - 1;
            if (tempCol2 >= 0) {
                Coordinate coordinate4 = new Coordinate(tempRow3, tempCol2);
                captureMoves.add(coordinate4);
            }
        }

        if (!captureMoves.isEmpty()) {
            moves.put(Direction.Capture, captureMoves);
        }

        return moves;
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
            File file = new File("src/main/resources/pawn_white.png");
            return ImageIO.read(file);
        }
        File file = new File("src/main/resources/pawn_black.png");
        return ImageIO.read(file);
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

        int sideMult = 1;
        if (getSide().equals(Side.White)) {
            sideMult = -1;
        }

        int tempRow3 = row + sideMult;
        if (tempRow3 >= 0 && tempRow3 < Board.getRows()) {
            int tempCol1 = col + 1;
            Coordinate coordinate3 = new Coordinate(tempRow3, tempCol1);

            int tempCol2 = col - 1;
            Coordinate coordinate4 = new Coordinate(tempRow3, tempCol2);

            if (destination.equals(coordinate3)
                    || destination.equals(coordinate4)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Setter for initPos.
     * @param initPos new initPos value for piece
     */
    public void setInitPos(final boolean initPos) {
        this.initPos = initPos;
    }

    /**
     * Makes a copy of the current piece.
     * @return copy of current piece
     */
    @Override
    public Piece copy() {
        Pawn copy = new Pawn(this.getSide(), this.getCoordinate());
        copy.initPos = this.initPos;
        return copy;
    }

    /**
     * String representation of the piece.
     * @return string representation
     */
    @Override
    public String toString() {
        return getSide().toString() +  " " + NAME;
    }

    /**
     * Compares two objects for equality.
     * @param obj objects being compared to
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Pawn)) {
            return false;
        }
        Pawn pawn = (Pawn) obj;
        return (pawn.initPos == this.initPos)
                && (pawn.getSide().equals(this.getSide()))
                && (pawn.getCoordinate().equals(this.getCoordinate()));
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

