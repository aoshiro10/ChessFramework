package framework.core.Pieces;

import framework.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

public class Pawn extends PieceAbstract implements Piece {

    private boolean initPos = false;

    private final String name = "Pawn";

    public Pawn(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    @Override
    public Map<Direction, List<Coordinate>> getPossibleMoves() {

        Map<Direction, List<Coordinate>> moves = new HashMap<>();

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();



        int sideMult = 1;

        List<Coordinate> verticalMoves = new ArrayList<>();
        Direction verticalDir = Direction.South;
        if (getSide() == Side.White) {
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


    public void setInitPos(boolean initPos) {
        this.initPos = initPos;
    }

    @Override
    public Piece copy() {
        Pawn copy = new Pawn(this.getSide(), this.getCoordinate());
        copy.initPos = this.initPos;
        return copy;
    }

    @Override
    public String toString() {
        return getSide().toString() +  " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Pawn)) {
            return false;
        }
        Pawn pawn = (Pawn) obj;
        return (pawn.initPos == this.initPos) && (pawn.getSide().equals(this.getSide()))
                && (pawn.getCoordinate().equals(this.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide(), this.initPos);
    }
}

