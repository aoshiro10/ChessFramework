package plugin;

import framework.core.Board;
import framework.core.Move;

public interface Player {

    Move chooseMove(Board board);

}
