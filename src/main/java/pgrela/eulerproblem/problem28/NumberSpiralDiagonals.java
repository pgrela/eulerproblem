package pgrela.eulerproblem.problem28;

import pgrela.eulerproblem.common.EulerSolver;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NumberSpiralDiagonals implements EulerSolver {

    public static void main(String[] args) {
        printSolution(NumberSpiralDiagonals.class);
    }

    @Override
    public long solve() {
        return solve(500);
    }

    protected long solve(int radius) {
        return rangeClosed(1, radius).map(i -> 2 * i - 1).map(i -> 4 * (i * i + i + 1 + i + 1 + i / 2 + 1)).sum() + 1;
    }
}
