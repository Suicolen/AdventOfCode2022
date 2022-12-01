package suic;

public interface Puzzle<O> {
    void parse();

    O solvePart1();

    O solvePart2();

    default void init() {
        parse();
    }
}