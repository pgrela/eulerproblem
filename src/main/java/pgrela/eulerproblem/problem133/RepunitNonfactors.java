package pgrela.eulerproblem.problem133;

import static pgrela.eulerproblem.common.FiniteStreamBuilder.streamFrom;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

public class RepunitNonfactors implements EulerSolver {

    public static void main(String[] args) {
        printSolution(RepunitNonfactors.class);
    }

    public long solve() {
        return Primes.primes(100000)
                .filter(p -> p != 2 && p != 5)
                .filter(n -> !isGood(streamFrom(1L)
                        .of(r -> (r * 10 + 1) % n)
                        .until(r -> r != 0)
                        .stream()
                        .count() + 1)
                )
                .sum() + 2 + 5;
    }

    public boolean isGood(long n) {
        if (n % 2 == 0) return isGood(n / 2);
        if (n % 5 == 0) return isGood(n / 5);
        return n == 1;
    }

}
