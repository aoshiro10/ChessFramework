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

public class Rook extends PieceAbstract implements Piece {

    private final String name = "Rook";
    private boolean initPos = false;

    public Rook(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }

    public boolean getInitPos() {
        return this.initPos;
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

    @Override
    public Piece copy() {
        Rook rook = new Rook(getSide(), getCoordinate());
        rook.initPos = initPos;
        return rook;
    }


    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Rook)) {
            return false;
        }
        Rook rook = (Rook) obj;
        return (rook.initPos == this.initPos) && (rook.getSide().equals(this.getSide()))
                && (rook.getCoordinate().equals(this.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide(), this.initPos);
    }

}
