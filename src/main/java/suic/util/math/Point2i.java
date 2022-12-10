package suic.util.math;

public record Point2i(int x, int y) {

    public Point2i() {
        this(0, 0);
    }

    public Point2i add(Point2i other) {
        return add(other.x, other.y);
    }

    public Point2i sub(Point2i other) {
        return sub(other.x, other.y);
    }

    public Point2i multiply(Point2i other) {
        return multiply(other.x, other.y);
    }

    public Point2i divide(Point2i other) {
        return divide(other.x, other.y);
    }

    public int dot(Point2i other) {
        return dot(other.x, other.y);
    }

    public Point2i add(int x, int y) {
        return new Point2i(this.x + x, this.y + y);
    }

    public Point2i sub(int x, int y) {
        return new Point2i(this.x - x, this.y - y);
    }

    public Point2i multiply(int x, int y) {
        return new Point2i(this.x * x, this.y * y);
    }

    public Point2i divide(int x, int y) {
        return new Point2i(this.x / x, this.y / y);
    }

    public int dot(int x, int y) {
        return this.x * x + this.y * y;
    }

    public Point2i abs() {
        return new Point2i(Math.abs(x), Math.abs(y));
    }

}
