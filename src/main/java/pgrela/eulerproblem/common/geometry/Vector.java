package pgrela.eulerproblem.common.geometry;

import pgrela.eulerproblem.common.Point;

public class Vector {
    public final int x, y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int squareLength() {
        return x * x + y * y;
    }

    public static Vector vector(Point point) {
        return new Vector(point.x, point.y);
    }

}
