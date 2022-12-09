package suic.days;

import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import suic.Puzzle;
import suic.util.io.FileUtils;
import suic.util.math.Point2i;
import suic.util.text.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day09 implements Puzzle<Integer> {

    private List<Move> moves;

    @Override
    public void parse() {
        moves = FileUtils.readAsStream(getClass().getSimpleName() + "Input.txt")
                .map(StringUtils::splitAtSpace)
                .map(Move::parse)
                .toList();
    }

    @Override
    public Integer solvePart1() {
        Point2i head = new Point2i(0, 0);
        Point2i tail = new Point2i(0, 0);
        Set<Point2i> visitedTails = new HashSet<>();
        for (Move move : moves) {
            for (int step = 0; step < move.steps(); step++) {
                head = moveHead(head, move.direction());
                tail = move(head, tail);
                visitedTails.add(tail);
            }
        }
        return visitedTails.size();
    }

    @Override
    public Integer solvePart2() {
        Point2i head = new Point2i(0, 0);
        Point2i[] tails = StreamEx.generate(Point2i::new).limit(9).toArray(Point2i[]::new);
        Set<Point2i> visitedTails = new HashSet<>();
        for (Move move : moves) {
            for (int step = 0; step < move.steps(); step++) {
                head = moveHead(head, move.direction());
                tails[0] = move(head, tails[0]);
                for (int i = 0; i < tails.length - 1; i++) {
                    tails[i + 1] = move(tails[i], tails[i + 1]);
                }
                visitedTails.add(tails[tails.length - 1]);
            }
        }
        return visitedTails.size();
    }


    private Point2i moveHead(Point2i head, Direction direction) {
        return switch (direction) {
            case RIGHT -> head.add(1, 0);
            case LEFT -> head.sub(1, 0);
            case UP -> head.sub(0, 1);
            case DOWN -> head.add(0, 1);
        };
    }

    private Point2i move(Point2i head, Point2i tail) {
        Point2i delta = head.sub(tail);
        int dx = delta.x();
        int dy = delta.y();
        if (Math.abs(dy) == 2) {
            return tail.add(Integer.signum(dx), Integer.signum(dy));
        } else if (Math.abs(dx) == 2) {
            return tail.add(Integer.signum(dx), Integer.signum(dy));
        }
        return tail;
    }

    record Move(Direction direction, int steps) {
        public static Move parse(String[] move) {
            return new Move(Direction.fromChar(move[0].charAt(0)), Integer.parseInt(move[1]));
        }
    }

    private enum Direction {
        LEFT, RIGHT, UP, DOWN;

        public static Direction fromChar(char c) {
            return switch (c) {
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                case 'U' -> UP;
                case 'D' -> DOWN;
                default -> throw new IllegalArgumentException("Unknown direction: " + c);
            };
        }
    }
}
