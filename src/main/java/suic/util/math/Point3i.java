package suic.util.math;

public record Point3i(int x, int y, int z) {

    public Point3i add(Point3i other) {
        return new Point3i(x + other.x, y + other.y, z + other.z);
    }

    public Point3i sub(Point3i other) {
        return new Point3i(x - other.x, y - other.y, z - other.z);
    }

    public Point3i multiply(Point3i other) {
        return new Point3i(x * other.x, y * other.y, z * other.z);
    }

    public Point3i divide(Point3i other) {
        return new Point3i(x / other.x, y / other.y, z / other.z);
    }

    public int dot(Point3i other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Point3i add(int x, int y, int z) {
        return new Point3i(this.x + x, this.y + y, this.z + z);
    }

    public Point3i sub(int x, int y, int z) {
        return new Point3i(this.x - x, this.y - y, this.z - z);
    }

    public Point3i multiply(int x, int y, int z) {
        return new Point3i(this.x * x, this.y * y, this.z * z);
    }

    public Point3i divide(int x, int y, int z) {
        return new Point3i(this.x / x, this.y / y, this.z / z);
    }

    public int dot(int x, int y, int z) {
        return this.x * x + this.y * y + this.z * z;
    }

}

