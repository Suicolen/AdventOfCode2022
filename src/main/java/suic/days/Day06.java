package suic.days;

import one.util.streamex.EntryStream;
import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import org.jooq.lambda.Seq;
import suic.Puzzle;
import suic.util.io.FileUtils;
import suic.util.text.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day06 implements Puzzle<Long> {
    private String input;

    @Override
    public void parse() {
        input = FileUtils.readString(getClass().getSimpleName() +"Input.txt");
    }

    @Override
    public Long solvePart1() {
        return solve(4);
    }

    @Override
    public Long solvePart2() {
        return solve(14);
    }

    private long solve(int characters) {
        for (int i = 0; i < input.length() - characters + 1; i++) {
            String sub = input.substring(i, i + characters);
            if (sub.chars().distinct().count() == characters) {
                return i + characters;
            }
        }
        return 0;
    }
}
