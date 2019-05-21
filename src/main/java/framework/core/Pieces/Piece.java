package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private final Side side;
    private Coordinate coordinate;

    public Piece(Side side, Coordinate coordinate) {
        this.side = side;
        this.coordinate = coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setInitPos(boolean initPos) {
    }

    public Side getSide() {
        return side;
    }

    abstract public Map<Direction, List<Coordinate>> getPossibleMoves();
    abstract public boolean hasPossibleCapture(Coordinate coordinate);
    abstract public Piece copy();
    abstract public BufferedImage getImage(Side side) throws IOException;
    abstract public String getPieceName();

}
