package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

import java.util.List;

public class Bishop extends PieceAbstract implements Piece {

    private final String name = "Bishop";

    public Bishop(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }

    @Override
    public void move(Coordinate coordinate) {

    }

    @Override
    public List<Coordinate> getPossibleMoves() {
        return null;
    }

    @Override
    public Piece copy() {
        Bishop bishop = new Bishop(this.getSide(), this.getCoordinate());
        return bishop;
    }

    @Override
    public String toString() {
        return this.getSide().toString() + " " + name;
    }
}
