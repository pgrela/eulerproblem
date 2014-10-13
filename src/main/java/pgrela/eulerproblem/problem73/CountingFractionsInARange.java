package pgrela.eulerproblem.problem73;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.Coordinates.squareFrom00;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingFractionsInARange implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CountingFractionsInARange.class);
    }

    public long solve() {
        return squareFrom00(12000)
                .filter(p -> 2*p.x < p.y)
                .filter(p -> 3*p.x > p.y)
                .filter(p -> Maths.gcd(p.x, p.y) == 1)
                .count();
    }
}
