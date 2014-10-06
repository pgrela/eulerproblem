package pgrela.eulerproblem.problem6;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.function.IntUnaryOperator;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SumSquareDifference implements EulerSolver {
    public static void main(String[] args) {
        printSolution(SumSquareDifference.class);
    }

    public long solve() {
        IntUnaryOperator square = x->x*x;
        return square.applyAsInt(rangeClosed(1, 100).sum()) - rangeClosed(1,100).map(square).sum();
    }
}
