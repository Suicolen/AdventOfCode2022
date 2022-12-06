package suic.days;

import one.util.streamex.StreamEx;
import suic.Puzzle;
import suic.util.io.FileUtils;
import suic.util.regex.MatchWrapper;
import suic.util.regex.RegexUtils;

import java.util.*;
import java.util.regex.Pattern;

// TODO: refactor later
public class Day05 implements Puzzle<String> {

    private List<Instruction> instructions;
    private Map<Integer, Deque<Character>> stacks;
    private boolean testInput;

    private final Pattern PATTERN = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    @Override
    public void parse(boolean testInput) {
        this.testInput = testInput;
        stacks = new HashMap<>();
        List<String> input = FileUtils.readAsStream(getClass().getSimpleName() + (testInput ? "TestInput.txt" : "Input.txt"))
                .limit(testInput ? 3 : 8)
                .toList();
        for (String row : input) {
            for (int j = 1; j < row.length(); j += 4) {
                int column = (j / 4) + 1;
                char value = row.charAt(j);
                Deque<Character> stack = stacks.computeIfAbsent(column, k -> new ArrayDeque<>());
                if (value != ' ') {
                    stack.add(value);
                }
            }
        }

        instructions = FileUtils.readAsStream(this.getClass()
                        .getSimpleName() + (testInput ? "TestInput.txt" : "Input.txt"))
                .filter(str -> PATTERN.matcher(str).matches())
                .map(str -> {
                    MatchWrapper matcher = RegexUtils.parseMatch(PATTERN, str);
                    return new Instruction(matcher.groupInt(1), matcher.groupInt(2), matcher.groupInt(3));
                }).toList();

    }

    @Override
    public String solvePart1() {
        for (Instruction instruction : instructions) {
            int quantity = instruction.quantity();
            int fromIndex = instruction.fromIndex();
            int toIndex = instruction.toIndex();
            Deque<Character> fromCrate = stacks.get(fromIndex);
            Deque<Character> toCrate = stacks.get(toIndex);
            for (int i = 0; i < quantity; i++) {
                toCrate.addFirst(fromCrate.removeFirst());
            }
        }

        // using a method reference here(Deque::peekFirst) causes a weird runtime cast error
        return StreamEx.of(stacks.values())
                .mapToInt(stack -> stack.peekFirst())
                .charsToString();
    }

    @Override
    public String solvePart2() {
        parse(testInput);
        for (Instruction instruction : instructions) {
            int quantity = instruction.quantity();
            int fromIndex = instruction.fromIndex();
            int toIndex = instruction.toIndex();
            Deque<Character> fromCrate = stacks.get(fromIndex);
            Deque<Character> toCrate = stacks.get(toIndex);
            List<Character> toAdd = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                toAdd.add(fromCrate.removeFirst());
            }
            Collections.reverse(toAdd);
            toAdd.forEach(toCrate::addFirst);
        }

        // using a method reference here(Deque::peekFirst) causes a weird runtime cast error
        return StreamEx.of(stacks.values())
                .mapToInt(stack -> stack.peekFirst())
                .charsToString();
    }

    record Instruction(int quantity, int fromIndex, int toIndex) {
    }

}
