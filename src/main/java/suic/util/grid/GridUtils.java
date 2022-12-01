package suic.util.grid;

import suic.util.grid.model.Grid2D;
import suic.util.grid.model.cell.GridCell2D;

import java.util.List;

// holds a bunch of methods related to dealing with a grid/its cells
public final class GridUtils {

    public static Grid2D parse2D(List<String> lines) {
        int rows = lines.get(0).length();
        int columns = lines.size();
        Grid2D grid = new Grid2D(new GridCell2D[columns][rows]);
        for (int column = 0; column < columns; column++) {
            String line = lines.get(column);
            for (int row = 0; row < columns; row++) {
                grid.set(row, column, line.charAt(row));
            }
        }
        return grid;
    }

    private GridUtils() {

    }

}
