package suic.util.grid;

import java.util.List;

// holds a bunch of methods related to dealing with a grid/its cells
public final class GridUtils {
    public static IntGrid parseIntGrid(List<String> lines) {
        int rows = lines.get(0).length();
        int columns = lines.size();
        IntGrid grid = new IntGrid(rows, columns);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                int value = Character.getNumericValue(lines.get(x).charAt(y));
                grid.set(x, y, value);
            }
        }
        return grid;
    }

    private GridUtils() {

    }

}
