package framework.core;

import static java.util.Objects.hash;

public final class Coordinate {

    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) obj;
        return (this.row == coordinate.row) && (this.col == coordinate.col);
    }

    @Override
    public int hashCode() {
        return hash(this.row, this.col);
    }

    @Override
    public String toString() {
        return "Row: " + Integer.toString(row) + " Cols: " + Integer.toString(col);
    }
}
