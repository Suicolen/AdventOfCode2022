package suic.days;

import one.util.streamex.StreamEx;
import suic.Puzzle;
import suic.util.io.FileUtils;

import java.util.*;

public class Day01 implements Puzzle<Integer> {

    private List<List<String>> input;

    @Override
    public void parse() {
        input = FileUtils.readGroups(getClass().getSimpleName() + "Input.txt");
    }

    @Override
    public Integer solvePart1() {
        return StreamEx.of(input)
                .mapToInt(group -> group.stream().mapToInt(Integer::parseInt).sum())
                .max()
                .orElseThrow();
    }

    @Override
    public Integer solvePart2() {
        return StreamEx.of(input)
                .mapToInt(group -> group.stream().mapToInt(Integer::parseInt).sum())
                .reverseSorted()
                .limit(3)
                .sum();
    }
}
