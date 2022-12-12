package suic.days;

import suic.Puzzle;
import suic.util.grid.CharGrid;
import suic.util.grid.GridUtils;
import suic.util.io.FileUtils;
import suic.util.math.Point2i;

import java.util.*;

public class Day12 implements Puzzle<Integer> {

    private CharGrid grid;
    private int rows;
    private int columns;
    private Point2i start;
    private Point2i end;
    private final Point2i[] directions = {
            new Point2i(-1, 0),
            new Point2i(1, 0),
            new Point2i(0, 1),
            new Point2i(0, -1)
    };

    @Override
    public void parse() {
        grid = GridUtils.parseCharGrid(FileUtils.read(getClass().getSimpleName() + "Input.txt"));
        rows = grid.getRows();
        columns = grid.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.get(i, j) == 'S') {
                    start = new Point2i(i, j);
                }

                if (grid.get(i, j) == 'E') {
                    end = new Point2i(i, j);
                }
            }
        }
    }

    @Override
    public Integer solvePart1() {
        return solve(false);
    }

    @Override
    public Integer solvePart2() {
        return solve(true);
    }

    private int solve(boolean backtrack) {
        Set<Node> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, backtrack ? end.x() : start.x(), backtrack ? end.y() : start.y()));
        int result;
        while (true) {
            Node node = pq.poll();
            int cost = node.cost;
            int row = node.row;
            int column = node.column;
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            if (backtrack ? getHeight(grid.get(row, column)) == 0 : row == end.x() && column == end.y()) {
                result = cost;
                break;
            }
            for (Point2i neighbor : getNeighbors(row, column, backtrack)) {
                int x = neighbor.x();
                int y = neighbor.y();
                pq.add(new Node(cost + 1, x, y));
            }
        }
        return result;
    }

    private List<Point2i> getNeighbors(int row, int column, boolean backtrack) {
        List<Point2i> neighbors = new ArrayList<>();
        for (Point2i dir : directions) {
            int x = row + dir.x();
            int y = column + dir.y();
            if (!grid.inBounds(x, y)) {
                continue;
            }

            if (backtrack) {
                if (getHeight(grid.get(x, y)) >= getHeight(grid.get(row, column)) - 1) {
                    neighbors.add(new Point2i(x, y));
                }
            } else {
                if (getHeight(grid.get(x, y)) <= getHeight(grid.get(row, column)) + 1) {
                    neighbors.add(new Point2i(x, y));
                }
            }
        }

        return neighbors;
    }

    private int getHeight(char c) {
        if (c == 'S') {
            return 0;
        } else if (c == 'E') {
            return 25;
        }

        return c - 'a';
    }

    private record Node(int cost, int row, int column) implements Comparable<Node> {

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return row == node.row && column == node.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
