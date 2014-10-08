package pgrela.eulerproblem.problem63;

import static java.util.stream.LongStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;

public class PowerfulDigitCounts implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PowerfulDigitCounts.class);
    }

    public long solve() {
        return range(1, 10).map(i -> range(1, 30)
                .mapToDouble(exponent -> exponent - exponent * Math.log10(i))
                .filter(d -> 0 < d && d <= 1)
                .count()
        ).sum();
    }
}

