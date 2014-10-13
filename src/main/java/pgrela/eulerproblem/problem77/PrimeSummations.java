package pgrela.eulerproblem.problem77;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimeSummations implements EulerSolver {

    public static final int MAX_ALLOWED_LENGTH = 1000;
    private static final long[][] cache = new long[3 * MAX_ALLOWED_LENGTH + 1][MAX_ALLOWED_LENGTH + 1];
    private int[] primes;

    public static void main(String[] args) {
        printSolution(PrimeSummations.class);
    }

    public long solve() {
        primes = Primes.primes(100000).toArray();
        long ways = 0;
        int number = 1;
        int maxPrimeIndex = 1;
        while (ways < 5000) {
            ++number;
            if (primes[maxPrimeIndex] < number) ++maxPrimeIndex;
            ways = countSumations(number, maxPrimeIndex);
        }
        return number;
    }

    public long countSumations(int length, int max) {
        if (length == 0) {
            return 1;
        }
        if (max == -1) {
            return 0;
        }
        if (cache[length][max] != 0) {
            return cache[length][max];
        }

        long sum = 0;
        int i = length;
        while (i >= 0) {
            sum = sum + (countSumations(i, max - 1));
            i -= primes[max];
        }
        return cache[length][max] = sum;
    }

}
