package pgrela.eulerproblem.problem46;

import pgrela.eulerproblem.common.EulerSolver;

import static java.util.stream.IntStream.of;
import static java.util.stream.Stream.iterate;
import static pgrela.eulerproblem.common.Maths.isSquare;
import static pgrela.eulerproblem.common.Primes.getPrimesToFactorizeInteger;
import static pgrela.eulerproblem.common.Primes.isPrime;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class GoldbachsOtherConjecture implements EulerSolver {

    public static void main(String[] args) {
        printSolution(GoldbachsOtherConjecture.class);
    }

    public long solve() {
        return iterate(1, n -> n + 1)
                .map(i -> 2 * i + 1)
                .filter(i -> !isPrime(i))
                .filter(composite -> of(getPrimesToFactorizeInteger())
                        .filter(p -> p < composite)
                        .noneMatch(prime -> isSquare((composite - prime) / 2))
                ).findFirst().get();
    }
}
