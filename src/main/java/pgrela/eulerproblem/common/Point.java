package pgrela.eulerproblem.common;

import java.util.stream.Stream;

public class Point {
    public static Point POINT_00 = new Point(0, 0);
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

    public static Stream<Point> points(int xMin, int yMin, int xMax, int yMax) {
        return Pair.pairs(xMin, yMin, xMax, yMax).map(p -> new Point(p.a, p.b));
    }
}
