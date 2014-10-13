package pgrela.eulerproblem.common;

import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.Point.point;

public class Coordinates {
    public static Stream<Point> squareFrom00(int maxXYCoord) {
        return square(0, 0, maxXYCoord, maxXYCoord);
    }

    public static Stream<Point> square(int x0, int y0, int x1, int y1) {
        return rangeClosed(x0, x1).boxed().flatMap(x -> rangeClosed(y0, y1).mapToObj(y -> point(x, y)));
    }
}
