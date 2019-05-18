package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import java.util.List;
import java.util.Map;

public interface Piece {

    void move(Coordinate coordinate);
    Coordinate getCoordinate();
    Map<Direction, List<Coordinate>> getPossibleMoves();
    Side getSide();
    Piece copy();

}
