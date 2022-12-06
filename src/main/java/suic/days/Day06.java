package suic.days;

import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import suic.Puzzle;
import suic.util.io.FileUtils;

import java.util.HashSet;
import java.util.Set;

public class Day06 implements Puzzle<Integer> {
    private String input;

    @Override
    public void parse(boolean testInput) {
        input = FileUtils.readString(getClass().getSimpleName() + (testInput ? "TestInput.txt" : "Input.txt"));
    }

    @Override
    public Integer solvePart1() {
        return solve(4);
    }

    @Override
    public Integer solvePart2() {
        return solve(14);
    }

    private int solve(int characters) {
        for (int i = 0; i < input.length() - characters; i++) {
            String sub = input.substring(i, i + characters);
            if (sub.chars().distinct().count() == characters) {
                return i + characters;
            }
        }
        return -1;
    }
}
