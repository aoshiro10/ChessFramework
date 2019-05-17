package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

import java.util.List;

public class Rook extends PieceAbstract implements Piece {

    private final String name = "Rook";
    private boolean initPos = true;

    public Rook(Side side, Coordinate coordinate) {
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
        Rook rook = new Rook(getSide(), getCoordinate());
        rook.initPos = initPos;
        return rook;
    }


    @Override
    public String toString() {
        return getSide().toString() + " " + name;
    }
}
