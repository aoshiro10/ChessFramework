package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

import java.util.List;

public class Knight extends PieceAbstract implements Piece {

    private final String name = "Knight";

    public Knight(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    @Override
    public List<Coordinate> getPossibleMoves() {
        return null;
    }

    @Override
    public Piece copy() {
        Knight copy = new Knight(this.getSide(), this.getCoordinate());
        return copy;
    }

    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }
}
