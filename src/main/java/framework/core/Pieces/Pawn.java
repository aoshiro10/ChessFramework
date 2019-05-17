package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Piece;
import framework.core.PieceAbstract;
import framework.core.Side;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends PieceAbstract implements Piece {

    public Pawn(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    @Override
    public List<Coordinate> getAvailableMoves() {

        List<Coordinate> moves = new ArrayList<>();

        return moves;

    }
}

