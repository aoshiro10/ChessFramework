package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class BishopTest {

    private Bishop bishop1;
    private Bishop bishop2;
    private Bishop bishop3;

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
        bishop1 = new Bishop(Side.Black, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 3);
        bishop2 = new Bishop(Side.White, coordinate2);

        Coordinate coordinate3 = new Coordinate(3, Board.getCols()-1);
        bishop3 = new Bishop(Side.Black, coordinate3);

    }

    @Test
    public void getPossibleMoves() {

        int[] results1 = {-1, -1, -1, 7, -1, -1, -1, -1, -1, -1, -1};
        Map<Direction, List<Coordinate>> bishop1PossibleMoves = bishop1.getPossibleMoves();
        getPossibleMovesHelper(results1, bishop1PossibleMoves);

        int[] results2 = {3, 4, 3, 3, -1, -1, -1, -1, -1, -1, -1};
        Map<Direction, List<Coordinate>> bishop2PossibleMoves = bishop2.getPossibleMoves();
        getPossibleMovesHelper(results2, bishop2PossibleMoves);

        Map<Direction, List<Coordinate>> bishop3PossibleMoves = bishop3.getPossibleMoves();
        int[] results3 = {3, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1};
        getPossibleMovesHelper(results3, bishop3PossibleMoves);

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

        Bishop bishop1Copy = (Bishop) bishop1.copy();

        assert (bishop1Copy.equals(bishop1));
        assert (bishop1Copy != bishop1);
        assert (bishop1Copy.getCoordinate().equals(bishop1.getCoordinate()));
        assert (bishop1Copy.getSide().equals(bishop1.getSide()));

        Bishop bishop2Copy = (Bishop) bishop2.copy();

        assert (bishop2Copy.equals(bishop2));
        assert (bishop2Copy != bishop2);
        assert (bishop2Copy.getCoordinate().equals(bishop2.getCoordinate()));
        assert (bishop2Copy.getSide().equals(bishop2.getSide()));

        Bishop bishop3Copy = (Bishop) bishop3.copy();

        assert (bishop3Copy.equals(bishop3));
        assert (bishop3Copy != bishop3);
        assert (bishop3Copy.getCoordinate().equals(bishop3.getCoordinate()));
        assert (bishop3Copy.getSide().equals(bishop3.getSide()));

        Bishop bishop4Copy = (Bishop) bishop3.copy();

        assert (bishop4Copy.equals(bishop3));
        assert (bishop4Copy != bishop3);
        assert (bishop4Copy.getCoordinate().equals(bishop3.getCoordinate()));
        assert (bishop4Copy.getSide().equals(bishop3.getSide()));

    }

    @Test
    public void string() {

        String bishop1Str = "Black Bishop";
        assert (bishop1Str.equals(bishop1.toString()));

        String bishop2Str = "White Bishop";
        assert (bishop2Str.equals(bishop2.toString()));

    }
}