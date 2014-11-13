package pgrela.eulerproblem.common;

public class Point {
    public static Point POINT_00 = new Point(0,0);
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point point(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return other.x == x && other.y == y;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
