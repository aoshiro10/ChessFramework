package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class QueenTest {

    private Queen queen1;
    private Queen queen2;
    private Queen queen3;
    private Queen queen4;

    private Direction[] directions = {  Direction.NorthWest,
                                        Direction.NorthEast,
                                        Direction.SouthWest,
                                        Direction.SouthEast,
                                        Direction.West,
                                        Direction.North,
                                        Direction.East,
                                        Direction.South,
                                        Direction.Castling,
                                        Direction.Jump,
                                        Direction.Capture};


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

    private void getPossibleMovesHelper(int[] results, Map<Direction, List<Coordinate>> possibleMoves) {
        for (int i = 0; i < directions.length; i++) {
            Direction tempDirection = directions[i];
            List<Coordinate> tempMoves = possibleMoves.get(tempDirection);

            int tempResult = results[i];

            if (tempResult == -1) {
                assert (tempMoves == null);
            } else {
                assert (tempMoves.size() == tempResult);
            }
        }
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

        String result1 = "White Queen";
        assert (result1.equals(queen1.toString()));

        String result2 = "Black Queen";
        assert (result2.equals(queen2.toString()));

        String result3 = "Black Queen";
        assert (result3.equals(queen3.toString()));

        String result4 = "White Queen";
        assert (result4.equals(queen4.toString()));

    }
}