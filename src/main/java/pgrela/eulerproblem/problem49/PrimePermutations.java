package pgrela.eulerproblem.problem49;

import pgrela.eulerproblem.common.EulerSolver;

import static java.lang.String.valueOf;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.arePermutations;
import static pgrela.eulerproblem.common.Primes.isPrime;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimePermutations implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimePermutations.class);
    }

    @Override
    public String solveToString() {
        return range(1000, 10000)
                .filter(n -> n != 1487)
                .boxed().flatMap(
                        start ->
                                range(1, (10000 - start) / 2).filter(
                                        delta -> range(0, 3)
                                                .map(i -> start + i * delta)
                                                .allMatch(k -> isPrime(k) && arePermutations(start, k))
                                ).mapToObj(delta -> valueOf(start) + valueOf(start + delta) + valueOf(start + 2 * delta))
                ).findFirst().get();
    }
}
