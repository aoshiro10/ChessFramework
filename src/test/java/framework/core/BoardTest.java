package framework.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board1;
    Board board2;
    Board board3;

    @Before
    public void setUp() {
        board1 = new Board();
    }

    @Test
    public void isCheck() {
        assert (!board1.isCheck(Side.White));
        assert (!board1.isCheck(Side.Black));
    }

    @Test
    public void getValidMoves() {
        assert (board1.getValidMoves(Side.White).size() == 20);
        assert (board1.getValidMoves(Side.Black).size() == 20);
    }

    @Test
    public void inBounds() {

        Coordinate coordinate1 = new Coordinate(0, 0);
        assert (Board.inBounds(coordinate1));

        Coordinate coordinate2 = new Coordinate(3, 7);
        assert (Board.inBounds(coordinate2));

        Coordinate coordinate3 = new Coordinate(7, 7);
        assert (Board.inBounds(coordinate3));

        Coordinate coordinate4 = new Coordinate(-1, 3);
        assert (!Board.inBounds(coordinate4));

        Coordinate coordinate5 = new Coordinate(-1, -2);
        assert (!Board.inBounds(coordinate5));

        Coordinate coordinate6 = new Coordinate(8, 0);
        assert (!Board.inBounds(coordinate6));

    }
}