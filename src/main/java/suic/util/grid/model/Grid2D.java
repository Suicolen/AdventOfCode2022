package suic.util.grid.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import suic.util.grid.model.cell.GridCell2D;

// mutable 2d grid structure
@RequiredArgsConstructor
@Getter
public class Grid2D {
    private final GridCell2D[][] cells;

    public void set(int row, int column, char value) {
        cells[row][column] = new GridCell2D(row, column, value);
    }

    public void print() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                System.out.print(cells[x][y].value() + " ");
            }
            System.out.println();
        }
    }
}
