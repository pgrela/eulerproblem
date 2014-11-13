package pgrela.eulerproblem.problem94;

import static pgrela.eulerproblem.common.Longs.ONE_BILLION;
import static pgrela.eulerproblem.common.Maths.isSquare;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.LongStream;

import pgrela.eulerproblem.common.EulerSolver;

public class AlmostEquilateralTriangles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(AlmostEquilateralTriangles.class);
    }

    public long solve() {
        return LongStream.rangeClosed(1, ONE_BILLION / 6 + 1)
                .map(i -> i * 2 + 1)
                .map(a ->
                                ((3 * a - 1 <= ONE_BILLION && isSquare((3 * a + 1) * (a - 1))) ? 3 * a - 1 : 0)
                                        +
                                        ((3 * a + 1 <= ONE_BILLION && isSquare((3 * a - 1) * (a + 1))) ? 3 * a + 1 : 0)
                ).sum();
    }

}
