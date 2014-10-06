package pgrela.eulerproblem.problem25;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class The1000DigitFibonacciNumber implements EulerSolver {
    public static final double SQRT_5 = Math.sqrt(5);
    public static final double PHI = (1 + SQRT_5) / 2;

    public static void main(String[] args) {
        printSolution(The1000DigitFibonacciNumber.class);
    }

    @Override
    public long solve() {
        return fibonacciFirstIndexForGivenElementLength(1000);
    }

    protected long fibonacciFirstIndexForGivenElementLength(int i) {
        return ((long) ((i + Math.log10(SQRT_5)) / Math.log10(PHI))) - 4;
    }
}
