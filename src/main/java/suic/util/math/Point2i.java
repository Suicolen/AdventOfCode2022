package suic.util.math;

public record Point2i(int x, int y) {

    public Point2i add(Point2i other) {
        return new Point2i(x + other.x, y + other.y);
    }

    public Point2i sub(Point2i other) {
        return new Point2i(x - other.x, y - other.y);
    }

    public Point2i multiply(Point2i other) {
        return new Point2i(x * other.x, y * other.y);
    }

    public Point2i divide(Point2i other) {
        return new Point2i(x / other.x, y / other.y);
    }

    public int dot(Point2i other) {
        return x * other.x + y * other.y;
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

}
