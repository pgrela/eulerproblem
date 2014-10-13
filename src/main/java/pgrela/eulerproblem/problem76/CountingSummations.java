package pgrela.eulerproblem.problem76;

import pgrela.eulerproblem.common.EulerSolver;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingSummations implements EulerSolver {

    public static final int MAX_ALLOWED_LENGTH = 100;
    private static final BigInteger[][] cache = new BigInteger[MAX_ALLOWED_LENGTH + 1][MAX_ALLOWED_LENGTH + 1];

    public static void main(String[] args) {
        printSolution(CountingSummations.class);
    }

    public String solveToString() {
        return countSumations(MAX_ALLOWED_LENGTH, MAX_ALLOWED_LENGTH - 1).toString();
    }

    public BigInteger countSumations(int length, int max) {
        if (length == 0) {
            return ONE;
        }
        if (max == 0) {
            return ZERO;
        }
        if (cache[length][max] != null) {
            return cache[length][max];
        }

        BigInteger sum = ZERO;
        int i = length;
        while (i >= 0) {
            sum = sum.add(countSumations(i, max - 1));
            i -= max;
        }
        return cache[length][max] = sum;
    }

}
