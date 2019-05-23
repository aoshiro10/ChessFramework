package framework.core;

public class Move {

    private final Coordinate initPos;
    private final Coordinate destPos;
    private final Direction direction;

    public Move(Coordinate initPos, Coordinate destPos, Direction direction) {
        this.direction = direction;
        this.initPos = initPos;
        this.destPos = destPos;
    }

    public Coordinate getDestCoor() {
        return destPos;
    }

    public Coordinate getInitCoor() {
        return initPos;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "From: " + this.initPos.toString() + " to: " + this.destPos.toString();
    }
}
