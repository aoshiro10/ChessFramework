package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

public abstract class PieceAbstract implements Piece {

    private final Side side;
    private Coordinate coordinate;

    public PieceAbstract(Side side, Coordinate coordinate) {
        this.side = side;
        this.coordinate = coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }


    @Override
    public void setInitPos(boolean initPos) {
    }

    @Override
    public Side getSide() {
        return side;
    }



}
