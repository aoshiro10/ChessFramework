package framework.core;

import java.util.List;

public interface Piece {

    void move(Coordinate coordinate);
    Coordinate getCoordinate();
    List<Coordinate> getAvailableMoves(boolean check);
    Side getSide();

}
