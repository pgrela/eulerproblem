package pgrela.eulerproblem.problem30;

import pgrela.eulerproblem.common.EulerSolver;

import static java.lang.String.*;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DigitFifthPowers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DigitFifthPowers.class);
    }

    @Override
    public long solve() {
        return range(2, 333333)
                .filter(n -> (valueOf(n).chars().map(c -> c - '0').map(i -> pow(i, 5)).sum()) == n)
                .sum();
    }
}
