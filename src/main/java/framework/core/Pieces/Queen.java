package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static framework.core.Direction.*;
import static framework.core.Direction.SouthWest;
import static java.util.Objects.hash;

public class Queen extends PieceAbstract implements Piece {

    private final String name = "Queen";

    public Queen(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


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
        int movesNE = Math.min(row, Math.abs(col-Board.getCols())-1);
        List<Coordinate> movesNorthEast = new ArrayList<>();
        for (int moveNE = 1; moveNE <= movesNE; moveNE++){
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
        int movesSE = Math.min(Math.abs(row-Board.getRows())-1, Math.abs(col-Board.getCols())-1);
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
        int movesSW = Math.min(Math.abs(row-Board.getRows())-1, col);
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

    @Override
    public Piece copy() {
        return new Queen(this.getSide(), this.getCoordinate());
    }

    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Queen)) {
            return false;
        }
        Queen queen = (Queen) obj;
        return (queen.getSide().equals(this.getSide())) && (queen.getCoordinate().equals(this.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide());
    }
}
