package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

import java.util.List;

public class Queen extends PieceAbstract implements Piece {

    private final String name = "Queen";

    public Queen(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    @Override
    public List<Coordinate> getPossibleMoves() {
        return null;
    }

    @Override
    public Piece copy() {
        Queen queen = new Queen(this.getSide(), this.getCoordinate());
        return queen;
    }

    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }
}
