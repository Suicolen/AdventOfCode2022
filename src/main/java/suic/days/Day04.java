package suic.days;

import suic.Puzzle;
import suic.util.io.FileUtils;
import suic.util.math.Range;
import suic.util.regex.MatchWrapper;
import suic.util.regex.RegexUtils;

import java.util.List;
import java.util.regex.Pattern;

// initial solution
public class Day04 implements Puzzle<Integer> {

    private List<ElfPair> elfPairs;

    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    @Override
    public void parse(boolean testInput) {
        elfPairs = FileUtils.readAsStream(this.getClass()
                        .getSimpleName() + (testInput ? "TestInput.txt" : "Input.txt"))
                .map(line -> {
                    MatchWrapper match = RegexUtils.parseMatch(PATTERN, line);
                    Range firstElfRange = new Range(match.groupInt(1), match.groupInt(2));
                    Range secondElfRange = new Range(match.groupInt(3), match.groupInt(4));
                    return new ElfPair(firstElfRange, secondElfRange);
                }).toList();
    }

    @Override
    public Integer solvePart1() {
        return (int) elfPairs.stream()
                .filter(ElfPair::containsFully)
                .count();
    }

    @Override
    public Integer solvePart2() {
        return (int) elfPairs.stream()
                .filter(ElfPair::overlaps)
                .count();
    }

    record ElfPair(Range firstElfRange, Range secondElfRange) {
        public boolean containsFully() {
            return firstElfRange.containsFully(secondElfRange) || secondElfRange.containsFully(firstElfRange);
        }

        public boolean overlaps() {
            return firstElfRange.overlaps(secondElfRange) || secondElfRange.overlaps(firstElfRange);
        }
    }
}
