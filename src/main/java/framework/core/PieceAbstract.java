package framework.core;

public abstract class PieceAbstract implements Piece{

    private final Side side;
    private Coordinate coordinate;

    public PieceAbstract(Side side, Coordinate coordinate) {
        this.side = side;
        this.coordinate = coordinate;
    }


    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Side getSide() {
        return side;
    }

}
