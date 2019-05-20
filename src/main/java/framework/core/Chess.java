package framework.core;

import framework.gui.TileButton;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Side side;
    private Board board;
    private List<TileButton> tileButtons;


    public Chess() {
        tileButtons = new ArrayList<>();
        board = new Board();
        side = Side.White;
    }

    public Board getBoard() {
        return board;
    }

    public void move(Move move) {
        this.board = this.board.move(move);
        this.side = switchSide(side);
    }

    private Side switchSide(Side side) {
        if (side.equals(Side.White)) {
            return Side.Black;
        }
        return Side.White;
    }

    public void addTileButton(TileButton tileButton) {
        tileButtons.add(tileButton);
    }

    public void updateAll() {
        for (TileButton tileButton : tileButtons) {
            tileButton.update(this);
        }
    }

    public List<Move> getMoves() {
        return this.board.getValidMoves(this.side);
    }

    public boolean gameOver() {
        return (getMoves().isEmpty());
    }

    public Side getWinner() {
        assert (gameOver());
        return switchSide(this.side);
    }

    public Side getSide() {
        return side;
    }
}
