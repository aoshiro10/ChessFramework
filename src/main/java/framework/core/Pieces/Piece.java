package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;

import java.util.List;

public interface Piece {

    void move(Coordinate coordinate);
    Coordinate getCoordinate();
    List<Coordinate> getPossibleMoves();
    Side getSide();
    Piece copy();

}
