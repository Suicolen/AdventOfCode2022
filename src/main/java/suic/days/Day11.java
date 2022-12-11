package suic.days;

import one.util.streamex.EntryStream;
import suic.Puzzle;
import suic.util.io.FileUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day11 implements Puzzle<Long> {

    private List<Monkey> monkeys;

    @Override
    public void parse() {
        monkeys = FileUtils.readGroups(getClass().getSimpleName() + "Input.txt")
                .stream()
                .map(Monkey::parse)
                .toList();
    }

    @Override
    public Long solvePart1() {
        return solve(20, 3);
    }

    @Override
    public Long solvePart2() {
        parse();
        return solve(10000, 1);
    }

    private long solve(int cycles, int worryDivisor) {
        Map<Integer, Integer> inspections = new HashMap<>();
        long lcm = monkeys.stream()
                .mapToLong(e -> e.test().divisor())
                .reduce((a, b) -> a * b)
                .orElseThrow();
        for (int i = 0; i < cycles; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                Monkey monkey = monkeys.get(j);
                Operation operation = monkey.operation();
                Test test = monkey.test();
                Iterator<Long> itemIterator = monkey.items().iterator();
                while (itemIterator.hasNext()) {
                    long worryLevel = itemIterator.next();
                    long newWorryLevel = computeNewWorryLevel(operation, worryLevel, worryDivisor);
                    int newMonkeyIndex = newWorryLevel % test.divisor() == 0 ? test.trueValue() : test.falseValue();
                    itemIterator.remove();
                    Monkey newMonkey = monkeys.get(newMonkeyIndex);
                    newMonkey.items().add(newWorryLevel % lcm);
                    inspections.merge(j, 1, Integer::sum);
                }
            }
        }
        return EntryStream.of(inspections)
                .reverseSorted(Map.Entry.comparingByValue())
                .mapToLong(Map.Entry::getValue)
                .limit(2)
                .reduce(1L, (a, b) -> a * b);
    }

    private long computeNewWorryLevel(Operation operation, long worryLevel, long worryDivisor) {
        if (operation.old()) {
            worryLevel = worryLevel * worryLevel;
        } else {
            worryLevel = switch (operation.operand()) {
                case '+' -> worryLevel + operation.value();
                case '*' -> worryLevel * operation.value();
                default -> worryLevel;
            };
        }

        return worryLevel / worryDivisor;
    }

    private record Monkey(List<Long> items, Operation operation, Test test) {

        // ugly :(
        public static Monkey parse(List<String> data) {
            List<Long> startingItems = null;
            Operation operation = null;
            Test test = null;
            for (int i = 0; i < data.size() - 2; i++) {
                String line = data.get(i);
                if (line.contains("Starting items:")) {
                    startingItems = Arrays.stream(line.split(":")[1].trim().split(", "))
                            .map(Long::parseLong)
                            .collect(Collectors.toCollection(ArrayList::new));
                } else if (line.contains("Operation:")) {
                    int equalsIndex = line.indexOf("=") + 1;
                    String[] split = line.substring(equalsIndex).trim().split(" ");
                    boolean old = split[2].equals("old");
                    operation = new Operation(split[1].charAt(0), old ? 0 : Integer.parseInt(split[2]), old);
                } else if (line.contains("Test:")) {
                    int testValue = Integer.parseInt(line.substring(line.indexOf("by") + 3));
                    String ifTrue = data.get(i + 1);
                    String ifFalse = data.get(i + 2);
                    int trueValue = Integer.parseInt(ifTrue.substring(ifTrue.indexOf("monkey") + "monkey".length() + 1));
                    int falseValue = Integer.parseInt(ifFalse.substring(ifFalse.indexOf("monkey") + "monkey".length() + 1));
                    test = new Test(testValue, trueValue, falseValue);
                }
            }
            return new Monkey(startingItems, operation, test);
        }

    }

    private record Operation(char operand, int value, boolean old) {
    }

    private record Test(int divisor, int trueValue, int falseValue) {
    }
}
