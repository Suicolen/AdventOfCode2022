package suic.util.math;

import one.util.streamex.IntStreamEx;

import java.util.List;

public record Range(int start, int end, int step) {

    public Range {
        if (end < start) {
            throw new IllegalArgumentException("end must be greater or equal to start");
        }

        if (step == 0) {
            throw new IllegalArgumentException("step must be non-zero");
        }
    }

    public boolean containsFully(Range other) {
        return start <= other.start && end >= other.end;
    }

    public boolean overlaps(Range other) {
        return (start <= other.end) && (end >= other.start);
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
