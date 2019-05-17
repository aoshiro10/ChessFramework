package framework.core;

public class Move {

    private final Coordinate initPos;
    private final Coordinate destPos;

    public Move(Coordinate initPos, Coordinate destPos) {
        this.initPos = initPos;
        this.destPos = destPos;
    }

    public Coordinate getDestPos() {
        return destPos;
    }

    public Coordinate getInitPos() {
        return initPos;
    }
}
