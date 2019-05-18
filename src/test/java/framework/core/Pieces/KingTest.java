package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    private King king1;
    private King king2;
    private King king3;
    private King king4;

    @Before
    public void setUp() {

        Coordinate coordinate1 = new Coordinate(0, 0);
        king1 = new King(Side.Black, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 3);
        king2 = new King(Side.White, coordinate2);

        Coordinate coordinate3 = new Coordinate(0, 4);
        king3 = new King(Side.Black, coordinate3);
        king3.setInitPos(true);

        Coordinate coordinate4 = new Coordinate(7, 4);
        king4 = new King(Side.White, coordinate4);
        king4.setInitPos(true);

    }


    @Test
    public void getPossibleMoves() {
    }

    @Test
    public void copy() {

        King king1Copy = (King) king1.copy();

        assert (king1Copy.equals(king1));
        assert (king1Copy != king1);

        King king2Copy = (King) king2.copy();

        assert (king2Copy.equals(king2));
        assert (king2Copy != king2);

        King king3Copy = (King) king3.copy();

        assert (king3Copy.equals(king3));
        assert (king3Copy != king3);

        King king4Copy = (King) king4.copy();

        assert (king4Copy.equals(king4));
        assert (king4Copy != king4);

    }

    @Test
    public void string() {

        String result1 = "Black King";
        assert (king1.toString().equals(result1));

        String result2 = "White King";
        assert (king2.toString().equals(result2));

        String result3 = "Black King";
        assert (king3.toString().equals(result3));

        String result4 = "White King";
        assert (king4.toString().equals(result4));

    }
}