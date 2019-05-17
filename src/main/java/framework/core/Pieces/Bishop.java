package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Side;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends PieceAbstract implements Piece {

    private final String name = "Bishop";

    public Bishop(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }


    @Override
    public List<Coordinate> getPossibleMoves() {

        ArrayList<Coordinate> moves = new ArrayList<>();

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        //diagonal northeast
        int movesNE = Math.min(row, Math.abs(col-Board.getCols())-1);
        for (int moveNE = 1; moveNE <= movesNE; moveNE++){
            int tempRow = row - moveNE;
            int tempCol = col + moveNE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            moves.add(tempCoordinate);
        }

        //diagonal northwest
        int movesNW = Math.min(row, col);
        for (int moveNW = 1; moveNW <= movesNW; moveNW++) {
            int tempRow = row - moveNW;
            int tempCol = col - moveNW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            moves.add(tempCoordinate);
        }

        //diagonal southeast
        int movesSE = Math.min(Math.abs(row-Board.getRows())-1, Math.abs(col-Board.getCols())-1);
        for (int moveSE = 1; moveSE <= movesSE; moveSE++) {
            int tempRow = row + moveSE;
            int tempCol = col + moveSE;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            moves.add(tempCoordinate);
        }

        //diagonal southwest
        int movesSW = Math.min(Math.abs(row-Board.getRows())-1, col);
        for (int moveSW = 1; moveSW <= movesSW; moveSW++) {
            int tempRow = row + moveSW;
            int tempCol = col - moveSW;
            Coordinate tempCoordinate = new Coordinate(tempRow, tempCol);
            moves.add(tempCoordinate);
        }

        return moves;
    }

    @Override
    public Piece copy() {
        return new Bishop(this.getSide(), this.getCoordinate());
    }

    @Override
    public String toString() {
        return this.getSide().toString() + " " + name;
    }
}
