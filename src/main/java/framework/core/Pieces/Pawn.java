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
    public List<Coordinate> getAvailableMoves() {

        List<Coordinate> moves = new ArrayList<>();

        int currentRow = this.getCoordinate().getRow();
        int currentCol = this.getCoordinate().getCol();


        int sideMult = 1;
        if (getSide() == Side.White) {
            sideMult = -1;
        }

        if (initPos) {
            //checking for double jump
            Coordinate coordinate1 = new Coordinate(currentRow - (2*sideMult), currentCol);
            moves.add(coordinate1);
        }

        //checking for vertical move
        Coordinate coordinate2 = new Coordinate(currentRow - (1*sideMult), currentCol);
        moves.add(coordinate2);


        //checking for diagonal move
        Coordinate coordinate3 = new Coordinate(currentRow - (1*sideMult), currentCol + 1);
        Coordinate coordinate4 = new Coordinate(currentRow - (1*sideMult), currentCol - 1);
        moves.add(coordinate3);
        moves.add(coordinate4);

        return moves;

    }


    @Override
    public void move(Coordinate coordinate) {
        initPos = false;
    }
}

