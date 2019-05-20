package framework.core;

import org.junit.Before;
import org.junit.Test;

import java.rmi.MarshalledObject;

import static org.junit.Assert.*;

public class BoardTest {

    Board board1;
    Board board2;
    Board board3;
    Board board4;
    Board board5;
    Board board6;

    @Before
    public void setUp() {
        board1 = new Board();
        Move move1 = new Move(new Coordinate(6,5), new Coordinate(5, 5), Direction.North);
        board2 = board1.move(move1);

        Move move2 = new Move(new Coordinate(1,4), new Coordinate(3, 4), Direction.South);
        board3 = board2.move(move2);

        Move move3 = new Move(new Coordinate(6,6), new Coordinate(4, 6), Direction.North);
        board4 = board3.move(move3);

        Move move4 = new Move(new Coordinate(0,3), new Coordinate(4, 7), Direction.SouthEast);
        board5 = board4.move(move4);

        Move move5 = new Move(new Coordinate(7,4), new Coordinate(6, 5), Direction.SouthEast);
        board6 = board5.move(move5);
    }

    @Test
    public void isCheck() {

        assert (!board1.isCheck(Side.White));
        assert (!board1.isCheck(Side.Black));

        assert (!board2.isCheck(Side.White));
        assert (!board2.isCheck(Side.Black));

        assert (!board3.isCheck(Side.White));
        assert (!board3.isCheck(Side.Black));

        assert (!board4.isCheck(Side.White));
        assert (!board4.isCheck(Side.Black));

        assert (board5.isCheck(Side.White));
        assert (!board5.isCheck(Side.Black));

    }

    @Test
    public void getValidMoves() {

        assert (board1.getValidMoves(Side.White).size() == 20);
        assert (board1.getValidMoves(Side.Black).size() == 20);

        assert (board2.getValidMoves(Side.Black).size() == 20);
        assert (board2.getValidMoves(Side.White).size() == 19);

        assert (board3.getValidMoves(Side.White).size() == 19);
        assert (board3.getValidMoves(Side.Black).size() == 30);

        assert (board4.getValidMoves(Side.White).size() == 20);
        assert (board4.getValidMoves(Side.Black).size() == 30);

        assert (board5.getValidMoves(Side.White).size() == 0);
        assert (board5.getValidMoves(Side.Black).size() == 39);

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