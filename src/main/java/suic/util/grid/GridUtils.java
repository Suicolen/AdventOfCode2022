package suic.util.grid;

import java.util.List;

// holds a bunch of methods related to dealing with a grid/its cells
public final class GridUtils {
    public static IntGrid parseIntGrid(List<String> lines) {
        int rows = lines.size();
        int columns = lines.get(0).length();
        IntGrid grid = new IntGrid(rows, columns);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                int value = Character.getNumericValue(lines.get(x).charAt(y));
                grid.set(x, y, value);
            }
        }
        return grid;
    }

    public static CharGrid parseCharGrid(List<String> lines) {
        int rows = lines.size();
        int columns = lines.get(0).length();
        CharGrid grid = new CharGrid(rows, columns);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                char value = lines.get(x).charAt(y);
                grid.set(x, y, value);
            }
        }
        return grid;
    }

    private GridUtils() {

    }

}
