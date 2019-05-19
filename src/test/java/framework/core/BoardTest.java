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
    }
}