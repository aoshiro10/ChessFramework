package framework.core.Pieces;

import framework.core.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends PieceAbstract implements Piece {

    private boolean initPos = true;

    public Pawn(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    @Override
    public List<Coordinate> getAvailableMoves(boolean check) {

        if (check) {
            return getCheckMoves();
        }

        return getNonCheckMoves();

    }

    private List<Coordinate> getCheckMoves() {
        List<Coordinate> moves = getNonCheckMoves();



        return moves;
    }

    private List<Coordinate> getNonCheckMoves() {
        List<Coordinate> moves = new ArrayList<>();

        if (initPos) {
            //checking for double jump
        }

        //checking for vertical move

        //checking for diagonal move

        return moves;
    }


    @Override
    public void move(Coordinate coordinate) {
        initPos = false;
    }
}

