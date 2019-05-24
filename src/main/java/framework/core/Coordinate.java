package framework.core;

import static java.util.Objects.hash;

/**
 * Object representing a location on the board.
 */
public final class Coordinate {

    private final int row;
    private final int col;

    /**
     * Constructor for a new coordinate.
     * @param row row index
     * @param col col index
     */
    public Coordinate(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Getter for row index.
     * @return row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for col index.
     * @return col index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks for object equality.
     * @param obj object being compared to
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) obj;
        return (this.row == coordinate.row) && (this.col == coordinate.col);
    }

    /**
     * Returns a hashcode for current coordinate.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return hash(this.row, this.col);
    }

    /**
     * String representation of the coordinate.
     * @return string representation
     */
    @Override
    public String toString() {
        return "Row: " + row + " Cols: " + col;
    }
}
