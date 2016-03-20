package pgrela.eulerproblem.problem120;

import pgrela.eulerproblem.common.EulerSolver;

import static java.util.stream.LongStream.range;
import static java.util.stream.LongStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SquareRemainders implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SquareRemainders.class);
    }

    public long solve() {
        return rangeClosed(3, 1000)
                .map(a -> range(0, a).map(k -> (4 * k + 2) % a * a).max().getAsLong())
                .sum();
    }


}
