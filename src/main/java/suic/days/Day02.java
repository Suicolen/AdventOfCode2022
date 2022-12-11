package suic.days;

import suic.Puzzle;
import suic.util.io.FileUtils;
import suic.util.text.StringUtils;

import java.util.List;

// simple initial solution
public class Day02 implements Puzzle<Integer> {

    private List<Choice> input;

    @Override
    public void parse() {
        input = FileUtils.read(getClass().getSimpleName() + "Input.txt")
                .stream()
                .map(StringUtils::splitAtSpace)
                .map(Choice::parse)
                .toList();
    }

    @Override
    public Integer solvePart1() {
        return input.stream()
                .mapToInt(this::computePart1)
                .sum();
    }

    @Override
    public Integer solvePart2() {
        return input.stream()
                .mapToInt(this::computePart2)
                .sum();
    }

    public int computePart1(Choice choice) {
        char opponent = choice.opponent();
        char player = choice.player();
        int choiceScore = switch (player) {
            case 'X' -> 1;
            case 'Y' -> 2;
            // 'Z'
            default -> 3;
        };
        return switch (opponent) {
            case 'A' -> (player == 'X' ? 3 : player == 'Y' ? 6 : 0) + choiceScore;
            case 'B' -> (player == 'Y' ? 3 : player == 'Z' ? 6 : 0) + choiceScore;
            // 'C'
            default -> (player == 'Z' ? 3 : player == 'X' ? 6 : 0) + choiceScore;
        };
    }

    public int computePart2(Choice choice) {
        char myChoice = computePart2Choice(choice);
        Choice newChoice = new Choice(choice.opponent(), myChoice);
        return computePart1(newChoice);
    }

    private char computePart2Choice(Choice choice) {
        char opponent = choice.opponent();
        char player = choice.player();
        return switch (player) {
            case 'X' -> opponent == 'A' ? 'Z' : opponent == 'B' ? 'X' : 'Y';
            case 'Y' -> opponent == 'A' ? 'X' : opponent == 'B' ? 'Y' : 'Z';
            // 'Z'
            default -> opponent == 'A' ? 'Y' : opponent == 'B' ? 'Z' : 'X';
        };
    }

    private record Choice(char opponent, char player) {
        public static Choice parse(String[] choices) {
            return new Choice(choices[0].charAt(0), choices[1].charAt(0));
        }
    }
}
