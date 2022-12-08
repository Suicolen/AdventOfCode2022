package suic.util.grid;

import lombok.Getter;

public class IntGrid {
    private final int[][] grid;
    @Getter
    private final int rows;
    @Getter
    private final int columns;

    public IntGrid(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        this.grid = grid;
    }

    public IntGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new int[rows][columns];
    }

    public int get(int row, int column) {
        return grid[row][column];
    }

    public void set(int row, int column, int value) {
        grid[row][column] = value;
    }

    public boolean inBounds(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
