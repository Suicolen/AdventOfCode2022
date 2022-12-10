package suic.days;

import suic.Puzzle;
import suic.util.io.FileUtils;

import java.util.List;

// non refactored/improved version
public class Day10 implements Puzzle<Integer> {

    private List<Instruction> instructions;

    @Override
    public void parse() {
        instructions = FileUtils.readAsStream(this.getClass().getSimpleName() + "Input.txt")
                .map(Instruction::parse)
                .toList();
    }

    @Override
    public Integer solvePart1() {
        int result = 0;
        int cycle = 0;
        int x = 1;
        int cyclesRequired = 20;
        for (Instruction instruction : instructions) {
            cycle += instruction.isNoop() ? 1 : 2;
            if (cycle >= cyclesRequired) {
                result += (x * cyclesRequired);
                cyclesRequired += 40;
            }
            x += instruction.value();
        }
        return result;
    }

    @Override
    public Integer solvePart2() {
        int cycle = 0;
        int x = 1;
        for (Instruction instruction : instructions) {
            int cycleCount = instruction.isNoop() ? 1 : 2;
            while (cycleCount-- > 0) {
                int target = cycle % 40;
                System.out.print(Math.abs(x - target) < 2 ? "oo" : "  ");
                cycle++;
                if (cycle % 40 == 0) {
                    System.out.println();
                }
            }
            x += instruction.value();
        }

        return 0;
    }

    record Instruction(int value) {

        public static Instruction parse(String str) {
            return new Instruction(str.equals("noop") ? 0 : Integer.parseInt(str.split(" ")[1]));
        }

        // safe to assume as every addx is non-zero
        public boolean isNoop() {
            return value == 0;
        }
    }
}
