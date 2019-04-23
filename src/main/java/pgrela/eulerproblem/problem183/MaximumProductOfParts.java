package pgrela.eulerproblem.problem183;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class MaximumProductOfParts implements EulerSolver {

    public static void main(String[] args) {
        printSolution(MaximumProductOfParts.class);
    }

    @Override
    public long solve() {
        return IntStream.rangeClosed(5, 10000).map(this::D).sum();
    }

    private int D(int N) {
        int lower = (int) (N / Math.E);
        int upper = lower + 1;
        int k = Math.log(N) + lower*Math.log(lower) > upper * Math.log(upper) ? upper : lower;
        return isDecimalDenominator(k / Maths.gcd(k, N)) ? -N : N;
    }

    private boolean isDecimalDenominator(int k) {
        while (k % 2 == 0)
            k /= 2;
        while (k % 5 == 0)
            k /= 5;
        return k == 1;
    }
}

