package pgrela.eulerproblem.problem123;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Primes;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimeSquareRemainders implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimeSquareRemainders.class);
    }

    public long solve() {
        int[] primes = Primes.primes(250000).toArray();
        return rangeClosed(1, primes.length)
                .filter(n -> n % 2 == 1)
                .filter(n -> 2L * n * primes[n - 1] > 10L * Integers.ONE_BILLION)
                .findFirst()
                .getAsInt();
    }


}
