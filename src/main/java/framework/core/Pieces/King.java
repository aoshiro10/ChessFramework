package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

import java.util.List;

public class King extends PieceAbstract implements Piece {

    private final String name = "King";

    private boolean initPos = true;

    public King(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }

    @Override
    public void move(Coordinate coordinate) {
        super.move(coordinate);
        this.initPos = false;
    }

    @Override
    public List<Coordinate> getPossibleMoves() {
        return null;
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
}
