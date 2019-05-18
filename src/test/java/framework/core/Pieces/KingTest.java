package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class KingTest {

    private King king1;
    private King king2;
    private King king3;
    private King king4;

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

        int[] results1 = {-1, -1, -1, 1, -1, -1, 1, 1, -1, -1, -1};
        Map<Direction, List<Coordinate>> king1PossibleMoves = king1.getPossibleMoves();
        getPossibleMovesHelper(results1, king1PossibleMoves);

        int[] results2 = {1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1};
        Map<Direction, List<Coordinate>> king2PossibleMoves = king2.getPossibleMoves();
        getPossibleMovesHelper(results2, king2PossibleMoves);

        int[] results3 = {-1, -1, 1, 1, 1, -1, 1, 1, 2, -1, -1};
        Map<Direction, List<Coordinate>> king3PossibleMoves = king3.getPossibleMoves();
        getPossibleMovesHelper(results3, king3PossibleMoves);

        int[] results4 = {1, 1, -1, -1, 1, 1, 1, -1, 2, -1, -1};
        Map<Direction, List<Coordinate>> king4PossibleMoves = king4.getPossibleMoves();
        getPossibleMovesHelper(results4, king4PossibleMoves);
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

        King king1Copy = (King) king1.copy();

        assert (king1Copy.equals(king1));
        assert (king1Copy != king1);
        assert (king1Copy.getCoordinate().equals(king1.getCoordinate()));
        assert (king1Copy.getSide().equals(king1.getSide()));

        King king2Copy = (King) king2.copy();

        assert (king2Copy.equals(king2));
        assert (king2Copy != king2);
        assert (king2Copy.getCoordinate().equals(king2.getCoordinate()));
        assert (king2Copy.getSide().equals(king2.getSide()));

        King king3Copy = (King) king3.copy();

        assert (king3Copy.equals(king3));
        assert (king3Copy != king3);
        assert (king3Copy.getCoordinate().equals(king3.getCoordinate()));
        assert (king3Copy.getSide().equals(king3.getSide()));

        King king4Copy = (King) king4.copy();

        assert (king4Copy.equals(king4));
        assert (king4Copy != king4);
        assert (king4Copy.getCoordinate().equals(king4.getCoordinate()));
        assert (king4Copy.getSide().equals(king4.getSide()));
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