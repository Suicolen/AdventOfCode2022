package suic.util.math;

import one.util.streamex.IntStreamEx;

import java.util.List;

public record Range(int start, int end, int step) {

    public Range {
        if (start >= end) {
            throw new IllegalArgumentException("start must be greater than end");
        }

        if (step == 0) {
            throw new IllegalArgumentException("step must be non-zero");
        }
    }

    public Range(int start, int end) {
        this(start, end, 1);
    }

    public IntStreamEx iterate() {
        return IntStreamEx.range(start, end, step);
    }

    public IntStreamEx iterateInclusive() {
        return IntStreamEx.rangeClosed(start, end, step);
    }

    public List<Integer> toList() {
        return IntStreamEx.range(start, end, step).boxed().toList();
    }

    public List<Integer> toListInclusive() {
        return IntStreamEx.rangeClosed(start, end, step).boxed().toList();
    }

}
