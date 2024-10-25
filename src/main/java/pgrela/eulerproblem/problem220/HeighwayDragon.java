package pgrela.eulerproblem.problem220;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class HeighwayDragon implements EulerSolver {

    private final Map<Integer, DragonVector> b = new HashMap<>();
    private final Map<Integer, DragonVector> a = new HashMap<>();
    static final DragonVector NOOP = new DragonVector(0, Direction.UP, Point.POINT_00);
    static final DragonVector ROTATE_RIGHT = new DragonVector(0, Direction.RIGHT, Point.POINT_00);
    static final DragonVector ROTATE_LEFT = new DragonVector(0, Direction.LEFT, Point.POINT_00);
    static final DragonVector STEP_FORWARD = new DragonVector(1, Direction.UP, new Point(0, 1));

    public static void main(String[] args) {
        printSolution(HeighwayDragon.class);
    }

    @Override
    public String solveToString() {
        a.put(0, NOOP);
        b.put(0, NOOP);
        IntStream.rangeClosed(0, 50).forEach(this::a);
        Point point = pinPointA(50, Maths.pow(10L, 12) - 1).add(Point.point(0, 1));
        return String.format("%d,%d", point.x, point.y);
    }

    private Point pinPointA(int n, long stepsLeft) {
        Direction direction = Direction.UP;
        Point point = Point.POINT_00;
        if (stepsLeft == 0) return point;

        if (a.get(n - 1).steps() > stepsLeft) return point.add(direction.rotate(pinPointA(n - 1, stepsLeft)));
        stepsLeft -= a.get(n - 1).steps();
        point = point.add(direction.rotate(a.get(n - 1).translation()));
        direction = direction.rotate(a.get(n - 1).rotation());
        if (stepsLeft == 0) return point;

        direction = direction.right();

        if (b.get(n - 1).steps() > stepsLeft) return point.add(direction.rotate(pinPointB(n - 1, stepsLeft)));
        stepsLeft -= b.get(n - 1).steps();
        point = point.add(direction.rotate(b.get(n - 1).translation()));
        direction = direction.rotate(b.get(n - 1).rotation());
        if (stepsLeft == 0) return point;

        return point.add(direction.rotate(Point.point(0, 1)));
    }

    private Point pinPointB(int n, long stepsLeft) {
        Direction direction = Direction.UP;
        Point point = Point.POINT_00;
        if (stepsLeft == 0) return point;

        direction = direction.left();

        point = point.add(direction.rotate(Point.point(0, 1)));
        --stepsLeft;
        if (stepsLeft == 0) return point;

        if (a.get(n - 1).steps() > stepsLeft) return point.add(direction.rotate(pinPointA(n - 1, stepsLeft)));
        stepsLeft -= a.get(n - 1).steps();
        point = point.add(direction.rotate(a.get(n - 1).translation()));
        direction = direction.rotate(a.get(n - 1).rotation());
        if (stepsLeft == 0) return point;

        direction = direction.left();

        return point.add(direction.rotate(pinPointB(n - 1, stepsLeft)));
    }

    DragonVector a(int n) {
        return a.computeIfAbsent(n, i -> a(i - 1).add(ROTATE_RIGHT).add(b(i - 1)).add(STEP_FORWARD).add(ROTATE_RIGHT));
    }

    DragonVector b(int n) {
        return b.computeIfAbsent(n, i -> ROTATE_LEFT.add(STEP_FORWARD).add(a(i - 1)).add(ROTATE_LEFT).add(b(i - 1)));
    }
}

record DragonVector(long steps, Direction rotation, Point translation) {
    DragonVector add(DragonVector other) {
        return new DragonVector(steps + other.steps, rotation.rotate(other.rotation), this.translation.add(this.rotation.rotate(other.translation)));
    }
};

enum Direction {
    UP(0, p -> p),
    DOWN(2, p -> Point.point(-p.x, -p.y)),
    LEFT(3, p -> Point.point(-p.y, p.x)),
    RIGHT(1, p -> Point.point(p.y, -p.x));
    private static final Direction[] map = new Direction[4];
    private final int n;
    private final Function<Point, Point> rotation;

    Direction(int n, Function<Point, Point> rotation) {
        this.n = n;
        this.rotation = rotation;
    }

    static {
        for (Direction d : Direction.values()) {
            map[d.n] = d;
        }
    }

    public Direction rotate(Direction rotation) {
        return map[(n + rotation.n) % 4];
    }

    public Point rotate(Point translation) {
        return rotation.apply(translation);
    }

    public Direction right() {
        return map[(n + 1) % 4];
    }

    public Direction left() {
        return map[(n + 3) % 4];
    }
}

