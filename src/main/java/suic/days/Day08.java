package suic.days;

import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import suic.Puzzle;
import suic.util.grid.GridUtils;
import suic.util.grid.IntGrid;
import suic.util.io.FileUtils;
import suic.util.math.Point2i;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08 implements Puzzle<Integer> {

    private IntGrid grid;
    private int rows;
    private int columns;

    @Override
    public void parse() {
        List<String> lines = FileUtils.read(getClass().getSimpleName() + "Input.txt");
        grid = GridUtils.parseIntGrid(lines);
        rows = grid.getRows();
        columns = grid.getColumns();
    }

    @Override
    public Integer solvePart1() {
        int visible = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (isVisible(row, column)) {
                    visible++;
                }
            }
        }
        return visible;
    }

    @Override
    public Integer solvePart2() {
        int score = 0;
        Point2i[] directions = {
                new Point2i(-1, 0),
                new Point2i(1, 0),
                new Point2i(0, 1),
                new Point2i(0, -1)
        };
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int height = grid.get(row, column);
                int curScore = 1;
                for (Point2i dir : directions) {
                    int dx = dir.x();
                    int dy = dir.y();
                    int curRow = row + dx;
                    int curColumn = column + dy;
                    int distance = 0;
                    while (grid.inBounds(curRow, curColumn) && grid.get(curRow, curColumn) < height) {
                        distance++;
                        curRow += dx;
                        curColumn += dy;
                        if (grid.inBounds(curRow, curColumn) && grid.get(curRow, curColumn) >= height) {
                            distance++;
                        }
                    }
                    curScore *= distance;
                }
                score = Math.max(score, curScore);
            }
        }
        return score;
    }

    private boolean isVisible(int row, int column) {
        int height = grid.get(row, column);
        return column == 0 || scanLeftSum(row, column) < height ||
                (column == columns - 1 || scanRightSum(row, column) < height) ||
                (row == 0 || scanUpSum(row, column) < height) ||
                row == rows - 1 || scanDownSum(row, column) < height;
    }

    private int scanLeftSum(int row, int column) {
        return IntStreamEx.range(column)
                .map(j -> grid.get(row, j))
                .max()
                .orElseThrow();
    }

    private int scanRightSum(int row, int column) {
        return IntStreamEx.range(column + 1, columns)
                .map(j -> grid.get(row, j))
                .max()
                .orElseThrow();
    }

    private int scanUpSum(int row, int column) {
        return IntStreamEx.range(row)
                .map(i -> grid.get(i, column))
                .max()
                .orElseThrow();
    }

    private int scanDownSum(int row, int column) {
        return IntStreamEx.range(row + 1, rows)
                .map(i -> grid.get(i, column))
                .max()
                .orElseThrow();
    }
}
