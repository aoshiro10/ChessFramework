package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;


public class RookTest {

    private Rook rook1;
    private Rook rook2;
    private Rook rook3;
    private Rook rook4;
    private Rook rook5;

    @Before
    public void setUp() {

        Coordinate coordinate1 = new Coordinate(0, 0);
        rook1 = new Rook(Side.White, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 7);
        rook2 = new Rook(Side.Black, coordinate2);

        Coordinate coordinate3 = new Coordinate(4, 3);
        rook3 = new Rook(Side.Black, coordinate3);

        Coordinate coordinate4 = new Coordinate(7, 3);
        rook4 = new Rook(Side.White, coordinate4);

        rook5 = new Rook(Side.Black, coordinate1);
        rook5.setInitPos(true);

    }

    @Test
    public void getPossibleMoves() {
    }

    @Test
    public void copy() {

        Rook rook1Copy = (Rook) rook1.copy();

        assert (rook1Copy.equals(rook1));
        assert (rook1Copy != rook1);
        assert (rook1Copy.getCoordinate().equals(rook1.getCoordinate()));
        assert (rook1Copy.getSide().equals(rook1.getSide()));

        Rook rook2Copy = (Rook) rook2.copy();

        assert (rook2Copy.equals(rook2));
        assert (rook2Copy != rook2);
        assert (rook2Copy.getCoordinate().equals(rook2.getCoordinate()));
        assert (rook2Copy.getSide().equals(rook2.getSide()));

        Rook rook3Copy = (Rook) rook3.copy();

        assert (rook3Copy.equals(rook3));
        assert (rook3Copy != rook3);
        assert (rook3Copy.getCoordinate().equals(rook3.getCoordinate()));
        assert (rook3Copy.getSide().equals(rook3.getSide()));

        Rook rook4Copy = (Rook) rook4.copy();

        assert (rook4Copy.equals(rook4));
        assert (rook4Copy != rook4);
        assert (rook4Copy.getCoordinate().equals(rook4.getCoordinate()));
        assert (rook4Copy.getSide().equals(rook4.getSide()));

    }

    @Test
    public void string() {

    }
}