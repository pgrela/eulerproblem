package pgrela.eulerproblem.problem92;

import pgrela.eulerproblem.common.EulerSolver;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SquareDigitChains implements EulerSolver {

    public static final int MAX_CACHED = 9 * 9 * 7;

    public static void main(String[] args) {
        printSolution(SquareDigitChains.class);
    }

    public long solve() {
        boolean[] cachedBelow1000 = new boolean[MAX_CACHED+1];
        for (int i = 1; i <= MAX_CACHED; i++) {
            int k = i;
            while (k != 1 && k != 89) {
                k = sumDigitSquares(k);
            }
            cachedBelow1000[i] = k == 89;
        }
        return range(1, ONE_MILLION * 10)
                .filter(i -> cachedBelow1000[sumDigitSquares(i)])
                .count();
    }

    private int sumDigitSquares(int number) {
        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }
}
