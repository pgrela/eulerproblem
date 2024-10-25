package pgrela.eulerproblem.problem205;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DiceGame implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DiceGame.class);
    }

    @Override
    public String solveToString() {
        long[] k6rolls = rolls(6, 6);
        long[] k4rolls = rolls(4, 9);
        double k6possibilities = Arrays.stream(k6rolls).sum();
        double k4possibilities = Arrays.stream(k4rolls).sum();
        double sum = IntStream.rangeClosed(0, 36)
                .mapToDouble(k4roll -> k4rolls[k4roll] / k4possibilities * Arrays.stream(k6rolls, 0, k4roll).sum() / k6possibilities)
                .sum();
        return String.format("%.7f", sum);
    }

    long[] rolls(int k, int n) {
        long[] sums = new long[k * n + 1];
        sums[0] = 1;
        for (int dice = 0; dice < n; dice++) {
            for (int sum = sums.length - 1; sum >= 0; sum--) {
                sums[sum] = 0;
                for (int side = 1; side <= k; side++) {
                    if (sum - side >= 0)
                        sums[sum] += sums[sum - side];
                }
            }
        }
        return sums;
    }
}

