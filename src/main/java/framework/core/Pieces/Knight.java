package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

public class Knight extends PieceAbstract implements Piece {

    private final String name = "Knight";

    public Knight(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


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

    @Override
    public Piece copy() {
        return new Knight(this.getSide(), this.getCoordinate());
    }

    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Knight)) {
            return  false;
        }
        Knight knight = (Knight) obj;
        return (knight.getSide().equals(this.getSide())) && (knight.getCoordinate().equals(this.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide());
    }
}
