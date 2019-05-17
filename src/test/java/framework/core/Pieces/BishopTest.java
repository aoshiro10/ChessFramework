package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class BishopTest {

    private Bishop bishop1;
    private Bishop bishop2;
    private Bishop bishop3;

    @Before
    public void setUp() {

        Coordinate coordinate1 = new Coordinate(0, 0);
        bishop1 = new Bishop(Side.Black, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 3);
        bishop2 = new Bishop(Side.White, coordinate2);

        Coordinate coordinate3 = new Coordinate(3, Board.getCols()-1);
        bishop3 = new Bishop(Side.Black, coordinate3);

    }

    @Test
    public void getPossibleMoves() {

        List<Coordinate> bishop1PossibleMoves = bishop1.getPossibleMoves();
        assert (bishop1PossibleMoves.size() == 7);

        Coordinate test1 = new Coordinate(1, 1);
        assert (bishop1PossibleMoves.contains(test1));

        List<Coordinate> bishop2PossibleMoves = bishop2.getPossibleMoves();
        assert (bishop2PossibleMoves.size() == 13);

        List<Coordinate> bishop3PossibleMoves = bishop3.getPossibleMoves();
        assert (bishop3PossibleMoves.size() == 7);

    }

    @Test
    public void copy() {

        Bishop bishop3Copy = (Bishop) bishop3.copy();
        assert (bishop3Copy.toString().equals(bishop3.toString()));
        assert (bishop3Copy.getCoordinate().equals(bishop3.getCoordinate()));
        assert (!(bishop3 == bishop3Copy));
        assert (!(bishop3.copy() == bishop3Copy));

        Bishop bishop2Copy = (Bishop) bishop2.copy();
        assert (bishop2Copy.toString().equals(bishop2Copy.toString()));
        assert (bishop2Copy.getCoordinate().equals(bishop2Copy.getCoordinate()));
        assert (!(bishop2 == bishop2Copy));

    }

    @Test
    public void string() {

        String bishop1Str = "Black Bishop";
        assert (bishop1Str.equals(bishop1.toString()));

        String bishop2Str = "White Bishop";
        assert (bishop2Str.equals(bishop2.toString()));

    }
}