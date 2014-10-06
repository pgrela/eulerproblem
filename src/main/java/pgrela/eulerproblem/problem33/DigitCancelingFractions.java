package pgrela.eulerproblem.problem33;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Point;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.Point.point;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DigitCancelingFractions implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DigitCancelingFractions.class);
    }

    @Override
    public long solve() {
        Point fraction = Coordinates.square(1, 1, 9, 9)
                .filter(p -> p.x < p.y)
                .filter(
                        p -> IntStream.range(1, 10)
                                .filter(
                                        k -> (
                                                theSame(10 * k + p.x, 10 * p.y + k, p.x, p.y) || theSame(10 * p.x + k, 10 * k + p.y, p.x, p.y)
                                        )
                                )
                                .count() != 0)
                .reduce(point(1, 1), (p, s) -> point(p.x * s.x, p.y * s.y));
        return fraction.y / fraction.x;
    }

    boolean theSame(int a, int b, int c, int d) {
        return a * d == b * c;
    }
}
