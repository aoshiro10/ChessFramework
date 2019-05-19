package framework.core.Pieces;

import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class RookTest {

    private Rook rook1;
    private Rook rook2;
    private Rook rook3;
    private Rook rook4;
    private Rook rook5;

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

        int[] results1 = {-1, -1, -1, -1, -1, -1, 7, 7, -1, -1, -1};
        Map<Direction, List<Coordinate>> rook1PossibleMoves = rook1.getPossibleMoves();
        getPossibleMovesHelper(results1, rook1PossibleMoves);

        int[] results2 = {-1, -1, -1, -1, 7, 4, -1, 3, -1, -1, -1};
        Map<Direction, List<Coordinate>> rook2PossibleMoves = rook2.getPossibleMoves();
        getPossibleMovesHelper(results2, rook2PossibleMoves);

        int[] results3 = {-1, -1, -1, -1, 3, 4, 4, 3, -1, -1, -1};
        Map<Direction, List<Coordinate>> rook3PossibleMoves = rook3.getPossibleMoves();
        getPossibleMovesHelper(results3, rook3PossibleMoves);

        int[] results4 = {-1, -1, -1, -1, 3, 7, 4, -1, -1, -1, -1};
        Map<Direction, List<Coordinate>> rook4PossibleMoves = rook4.getPossibleMoves();
        getPossibleMovesHelper(results4, rook4PossibleMoves);

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

        String result1 = "White Rook";
        assert (result1.equals(rook1.toString()));

        String result2 = "Black Rook";
        assert (result2.equals(rook2.toString()));

        String result3 = "Black Rook";
        assert (result3.equals(rook3.toString()));

        String result4 = "White Rook";
        assert (result4.equals(rook4.toString()));

    }
}