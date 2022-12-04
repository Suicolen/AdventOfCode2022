package suic;

public interface Puzzle<O> {
    void parse(boolean testInput);

    O solvePart1();

    O solvePart2();

    default void init(boolean testInput) {
        parse(testInput);
    }
}