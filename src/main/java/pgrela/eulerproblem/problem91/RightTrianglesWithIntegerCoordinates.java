package pgrela.eulerproblem.problem91;

import static pgrela.eulerproblem.common.Coordinates.squareFrom00WithSideLength;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.Point.POINT_00;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Point;

public class RightTrianglesWithIntegerCoordinates implements EulerSolver {

    public static void main(String[] args) {
        printSolution(RightTrianglesWithIntegerCoordinates.class);
    }

    public long solve() {
        return squareFrom00WithSideLength(50).mapToLong(
                p1 -> squareFrom00WithSideLength(50).filter(p2 -> isRightTriangle(POINT_00, p1, p2)).count()
        ).sum() / 2;
    }

    private boolean isRightTriangle(Point p0, Point p1, Point p2) {
        int aSquared = squaredLength(p0, p1);
        int bSquared = squaredLength(p1, p2);
        int cSquared = squaredLength(p2, p0);
        return aSquared > 0 && bSquared > 0 && cSquared > 0
                && (aSquared + bSquared == cSquared
                || aSquared + cSquared == bSquared
                || bSquared + cSquared == aSquared);
    }

    private int squaredLength(Point p0, Point p1) {
        return pow(p0.x - p1.x, 2) + pow(p0.y - p1.y, 2);
    }
}
