package suic.days;

import one.util.streamex.MoreCollectors;
import one.util.streamex.StreamEx;
import org.apache.commons.collections4.SetUtils;
import suic.Puzzle;
import suic.util.io.FileUtils;
import suic.util.text.StringUtils;

import java.util.*;

public class Day03 implements Puzzle<Integer> {

    private List<String> rucksacks = new ArrayList<>();

    @Override
    public void parse(boolean test) {
        rucksacks = FileUtils.read(getClass().getSimpleName() + (test ? "TestInput.txt" : "Input.txt"));
    }

    @Override
    public Integer solvePart1() {
        return rucksacks.stream().map(rucksack -> {
            int halfLength = rucksack.length() / 2;
            Set<Character> firstSet = StringUtils.toCharSet(rucksack.substring(0, halfLength));
            Set<Character> secondSet = StringUtils.toCharSet(rucksack.substring(halfLength));
            return SetUtils.intersection(firstSet, secondSet);
        }).flatMap(Set::stream).mapToInt(this::getPriority).sum();
    }

    @Override
    public Integer solvePart2() {
        return StreamEx.ofSubLists(rucksacks, 3)
                .map(this::findCommon)
                .mapToInt(this::getPriority)
                .sum();
    }

    private int getPriority(char c) {
        return Character.isLowerCase(c) ? (c - 96) : (c - 38);
    }

    private char findCommon(List<String> group) {
        return StreamEx.of(group)
                .map(StringUtils::toCharSet)
                .collect(MoreCollectors.intersecting())
                .stream()
                .findFirst()
                .orElseThrow();
    }
}
