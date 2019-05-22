package framework.core;

public interface Player {

    Side getSide();

    Move chooseMove(Board board);

}
