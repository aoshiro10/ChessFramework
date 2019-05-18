package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import static java.util.Objects.hash;

public class King extends PieceAbstract implements Piece {

    private final String name = "King";

    private boolean initPos = true;

    public King(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    public void setInitPos(boolean initPos) {
        this.initPos = initPos;
    }

    @Override
    public Map<Direction, List<Coordinate>> getPossibleMoves() {

        Map<Direction, List<Coordinate>> moves = new HashMap<>();

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        //NORTHEAST
        int tempRow1 = row - 1;
        int tempCol1 = col + 1;
        Coordinate tempCoordinate1 = new Coordinate(tempRow1, tempCol1);
        if (Board.inBounds(tempCoordinate1)) {

            List<Coordinate> movesNE = new ArrayList<>();
            movesNE.add(tempCoordinate1);
            moves.put(Direction.NorthEast, movesNE);
        }

        //NORTHWEST
        int tempRow2 = row - 1;
        int tempCol2 = col - 1;
        Coordinate tempCoordinate2 = new Coordinate(tempRow2, tempCol2);
        if (Board.inBounds(tempCoordinate2)) {
            List<Coordinate> movesNW = new ArrayList<>();
            movesNW.add(tempCoordinate2);
            moves.put(Direction.NorthWest, movesNW);
        }

        //SOUTHWEST
        int tempRow3 = row + 1;
        int tempCol3 = col - 1;
        Coordinate tempCoordinate3 = new Coordinate(tempRow3, tempCol3);
        if (Board.inBounds(tempCoordinate3)) {
            List<Coordinate> movesSW = new ArrayList<>();
            movesSW.add(tempCoordinate3);
            moves.put(Direction.SouthWest, movesSW);
        }

        //SOUTHEAST
        int tempRow4 = row + 1;
        int tempCol4 = col + 1;
        Coordinate tempCoordinate4 = new Coordinate(tempRow4, tempCol4);
        if (Board.inBounds(tempCoordinate4)) {
            List<Coordinate> movesSE = new ArrayList<>();
            movesSE.add(tempCoordinate4);
            moves.put(Direction.SouthEast, movesSE);
        }


        //Castling
        if (initPos) {

            int tempCol5 = 2;
            int tempCol6 = 6;

            Coordinate tempCoordinate5 = new Coordinate(row, tempCol5);
            Coordinate tempCoordinate6 = new Coordinate(row, tempCol6);

            List<Coordinate> movesOther = new ArrayList<>();
            movesOther.add(tempCoordinate5);
            movesOther.add(tempCoordinate6);

            moves.put(Direction.Castling, movesOther);

        }

        return  moves;

    }

    @Override
    public Piece copy() {
        King copy = new King(this.getSide(), this.getCoordinate());
        copy.initPos = this.initPos;
        return copy;
    }

    @Override
    public String toString() {
        return getSide().toString() +  " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof King)) {
            return false;
        }
        King king = (King) obj;
        return (king.initPos == this.initPos) && (king.getSide().equals(this.getSide()))
                && (king.getCoordinate().equals(this.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide(), this.initPos);
    }
}
