package pgrela.eulerproblem.problem243;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Resilience implements EulerSolver {

    public static void main(String[] args) {
        printSolution(Resilience.class);
    }

    public static final int FIRST_PRIMES_PRODUCT = Primes.primes(25).reduce((a, b) -> a * b).getAsInt();

    public long solve() {
        return IntStream.iterate(1, i -> i + 1)
                .map(i -> i * FIRST_PRIMES_PRODUCT)
                .filter(d -> Maths.totient(d) / (d - 1.0) < 15499. / 94744)
                .findFirst()
                .getAsInt();
    }
}
