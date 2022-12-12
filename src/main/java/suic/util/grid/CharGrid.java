package suic.util.grid;

import lombok.Getter;

import java.util.Arrays;

public class CharGrid {
    private final char[][] grid;
    @Getter
    private final int rows;
    @Getter
    private final int columns;

    public CharGrid(char[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        this.grid = grid;
    }

    public CharGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new char[rows][columns];
    }

    public char get(int row, int column) {
        return grid[row][column];
    }

    public void set(int row, int column, char value) {
        grid[row][column] = value;
    }

    public boolean inBounds(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public void print() {
        System.out.println(Arrays.deepToString(grid));
    }
}
