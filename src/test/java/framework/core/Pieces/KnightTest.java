package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class KnightTest {

    private Knight knight1;
    private Knight knight2;
    private Knight knight3;
    private Knight knight4;

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
        knight1 = new Knight(Side.White, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 7);
        knight2 = new Knight(Side.Black, coordinate2);

        Coordinate coordinate3 = new Coordinate(4, 3);
        knight3 = new Knight(Side.Black, coordinate3);

        Coordinate coordinate4 = new Coordinate(7, 3);
        knight4 = new Knight(Side.White, coordinate4);

    }

    @Test
    public void getPossibleMoves() {

        int[] results1 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1};
        Map<Direction, List<Coordinate>> knight1PossibleMoves = knight1.getPossibleMoves();
        getPossibleMovesHelper(results1, knight1PossibleMoves);

        int[] results2 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1};
        Map<Direction, List<Coordinate>> knight2PossibleMoves = knight2.getPossibleMoves();
        getPossibleMovesHelper(results2, knight2PossibleMoves);

        int[] results3 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, 8, -1};
        Map<Direction, List<Coordinate>> knight3PossibleMoves = knight3.getPossibleMoves();
        getPossibleMovesHelper(results3, knight3PossibleMoves);

        int[] results4 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1};
        Map<Direction, List<Coordinate>> knight4PossibleMoves = knight4.getPossibleMoves();
        getPossibleMovesHelper(results4, knight4PossibleMoves);

    }

    @Test
    public void copy() {

        Knight knight1Copy = (Knight) knight1.copy();

        assert (knight1Copy.equals(knight1));
        assert (knight1Copy != knight1);
        assert (knight1Copy.getCoordinate().equals(knight1.getCoordinate()));
        assert (knight1Copy.getSide().equals(knight1.getSide()));

        Knight knight2Copy = (Knight) knight2.copy();

        assert (knight2Copy.equals(knight2));
        assert (knight2Copy != knight2);
        assert (knight2Copy.getCoordinate().equals(knight2.getCoordinate()));
        assert (knight2Copy.getSide().equals(knight2.getSide()));

        Knight knight3Copy = (Knight) knight3.copy();

        assert (knight3Copy.equals(knight3));
        assert (knight3Copy != knight3);
        assert (knight3Copy.getCoordinate().equals(knight3.getCoordinate()));
        assert (knight3Copy.getSide().equals(knight3.getSide()));

        Knight knight4Copy = (Knight) knight4.copy();

        assert (knight4Copy.equals(knight4));
        assert (knight4Copy != knight4);
        assert (knight4Copy.getCoordinate().equals(knight4.getCoordinate()));
        assert (knight4Copy.getSide().equals(knight4.getSide()));
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
    public void string() {

        String result1 = "White Knight";
        assert (result1.equals(knight1.toString()));

        String result2 = "Black Knight";
        assert (result2.equals(knight2.toString()));

        String result3 = "Black Knight";
        assert (result3.equals(knight3.toString()));

        String result4 = "White Knight";
        assert (result4.equals(knight4.toString()));

    }
}