package pgrela.eulerproblem.problem85;

import static java.lang.Math.abs;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Point;

public class CountingRectangles implements EulerSolver {

    public static final int TWO_MILLIONS = 2 * ONE_MILLION;

    public static void main(String[] args) {
        printSolution(CountingRectangles.class);
    }

    public long solve() {
        return Coordinates.square(1, 1, 2000, 2000)
                .filter(p -> rectangles(p) < (TWO_MILLIONS * 2))
                .min((a, b) -> (int) (abs(rectangles(a) - TWO_MILLIONS) - abs(rectangles(b) - 2 * TWO_MILLIONS)))
                .map(p -> p.x * p.y)
                .get();
    }

    private long rectangles(Point p) {
        return ((long) p.y + 1) * p.y * (p.x + 1) * p.x / 4;
    }
}
