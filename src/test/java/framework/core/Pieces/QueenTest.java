package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;


public class QueenTest {

    private Queen queen1;
    private Queen queen2;
    private Queen queen3;
    private Queen queen4;


    @Before
    public void setUp() {

        Coordinate coordinate1 = new Coordinate(0, 0);
        queen1 = new Queen(Side.White, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 7);
        queen2 = new Queen(Side.Black, coordinate2);

        Coordinate coordinate3 = new Coordinate(4, 3);
        queen3 = new Queen(Side.Black, coordinate3);

        Coordinate coordinate4 = new Coordinate(7, 3);
        queen4 = new Queen(Side.White, coordinate4);

    }


    @Test
    public void getPossibleMoves() {
    }

    @Test
    public void copy() {

        Queen queen1Copy = (Queen) queen1.copy();

        assert (queen1Copy.equals(queen1));
        assert (queen1Copy != queen1);
        assert (queen1Copy.getCoordinate().equals(queen1.getCoordinate()));
        assert (queen1Copy.getSide().equals(queen1.getSide()));

        Queen queen2Copy = (Queen) queen2.copy();

        assert (queen2Copy.equals(queen2));
        assert (queen2Copy != queen2);
        assert (queen2Copy.getCoordinate().equals(queen2.getCoordinate()));
        assert (queen2Copy.getSide().equals(queen2.getSide()));

        Queen queen3Copy = (Queen) queen3.copy();

        assert (queen3Copy.equals(queen3));
        assert (queen3Copy != queen3);
        assert (queen3Copy.getCoordinate().equals(queen3.getCoordinate()));
        assert (queen3Copy.getSide().equals(queen3.getSide()));

        Queen queen4Copy = (Queen) queen4.copy();

        assert (queen4Copy.equals(queen4));
        assert (queen4Copy != queen4);
        assert (queen4Copy.getCoordinate().equals(queen4.getCoordinate()));
        assert (queen4Copy.getSide().equals(queen4.getSide()));

    }

    @Test
    public void string() {

    }
}