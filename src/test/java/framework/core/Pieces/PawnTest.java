package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class PawnTest {

    private Pawn pawn1;
    private Pawn pawn2;
    private Pawn pawn3;
    private Pawn pawn4;
    private Pawn pawn5;
    private Pawn pawn6;
    private Pawn pawn7;


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
        pawn1 = new Pawn(Side.White, coordinate1);

        Coordinate coordinate2 = new Coordinate(4, 7);
        pawn2 = new Pawn(Side.Black, coordinate2);

        Coordinate coordinate3 = new Coordinate(4, 3);
        pawn3 = new Pawn(Side.Black, coordinate3);
        pawn4 = new Pawn(Side.White, coordinate3);

        Coordinate coordinate4 = new Coordinate(7, 3);
        pawn5 = new Pawn(Side.White, coordinate4);
        pawn6 = new Pawn(Side.Black, coordinate4);

        Coordinate coordinate5 = new Coordinate(6, 1);
        pawn7 = new Pawn(Side.White, coordinate5);
        pawn7.setInitPos(true);

    }

    @Test
    public void getPossibleMoves() {

        int[] results1 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Map<Direction, List<Coordinate>> pawn1PossibleMoves = pawn1.getPossibleMoves();
        getPossibleMovesHelper(results1, pawn1PossibleMoves);

        int[] results2 = {-1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 1};
        Map<Direction, List<Coordinate>> pawn2PossibleMoves = pawn2.getPossibleMoves();
        getPossibleMovesHelper(results2, pawn2PossibleMoves);

        int[] results3 = {-1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 2};
        Map<Direction, List<Coordinate>> pawn3PossibleMoves = pawn3.getPossibleMoves();
        getPossibleMovesHelper(results3, pawn3PossibleMoves);

        int[] results4 = {-1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 2};
        Map<Direction, List<Coordinate>> pawn4PossibleMoves = pawn4.getPossibleMoves();
        getPossibleMovesHelper(results4, pawn4PossibleMoves);

        int[] results5 = {-1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 2};
        Map<Direction, List<Coordinate>> pawn5PossibleMoves = pawn5.getPossibleMoves();
        getPossibleMovesHelper(results5, pawn5PossibleMoves);

        int[] results6 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Map<Direction, List<Coordinate>> pawn6PossibleMoves = pawn6.getPossibleMoves();
        getPossibleMovesHelper(results6, pawn6PossibleMoves);

        int[] results7 = {-1, -1, -1, -1, -1, 2, -1, -1, -1, -1, 2};
        Map<Direction, List<Coordinate>> pawn7PossibleMoves = pawn7.getPossibleMoves();
        getPossibleMovesHelper(results7, pawn7PossibleMoves);

    }

    @Test
    public void copy() {

        Pawn pawn1Copy = (Pawn) pawn1.copy();

        assert (pawn1Copy.equals(pawn1));
        assert (pawn1Copy != pawn1);
        assert (pawn1Copy.getCoordinate().equals(pawn1.getCoordinate()));
        assert (pawn1Copy.getSide().equals(pawn1.getSide()));

        Pawn pawn2Copy = (Pawn) pawn2.copy();

        assert (pawn2Copy.equals(pawn2));
        assert (pawn2Copy != pawn2);
        assert (pawn2Copy.getCoordinate().equals(pawn2.getCoordinate()));
        assert (pawn2Copy.getSide().equals(pawn2.getSide()));

        Pawn pawn3Copy = (Pawn) pawn3.copy();

        assert (pawn3Copy.equals(pawn3));
        assert (pawn3Copy != pawn3);
        assert (pawn3Copy.getCoordinate().equals(pawn3.getCoordinate()));
        assert (pawn3Copy.getSide().equals(pawn3.getSide()));

        Pawn pawn4Copy = (Pawn) pawn4.copy();

        assert (pawn4Copy.equals(pawn4));
        assert (pawn4Copy != pawn4);
        assert (pawn4Copy.getCoordinate().equals(pawn4.getCoordinate()));
        assert (pawn4Copy.getSide().equals(pawn4.getSide()));

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

        String result1 = "White Pawn";
        assert (result1.equals(pawn1.toString()));

        String result2 = "Black Pawn";
        assert (result2.equals(pawn2.toString()));

        String result3 = "Black Pawn";
        assert (result3.equals(pawn3.toString()));

        String result4 = "White Pawn";
        assert (result4.equals(pawn4.toString()));

    }
}