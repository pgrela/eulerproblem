package pgrela.eulerproblem.problem75;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static java.util.stream.IntStream.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SingularIntegerRightTriangles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SingularIntegerRightTriangles.class);
    }

    public long solve() {
        int limit = 1500000;
        int[] s = new int[limit + 1];
        range(1, 1000)
                .forEach(m -> range(1, m)
                        .filter(n -> (m & n & 1) == 0)
                        .filter(n -> Maths.gcd(m, n) == 1)
                        .map(n -> 2 * m * (m + n))
                        .forEach(perimeter -> rangeClosed(1, limit / perimeter)
                                .forEach(nonTrivial -> s[nonTrivial * perimeter]++)
                        )
                );
        return of(s).filter(i -> i == 1).count();
    }

}
