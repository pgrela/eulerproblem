package pgrela.eulerproblem.problem130;

import static java.util.stream.IntStream.iterate;
import static pgrela.eulerproblem.common.FiniteStreamBuilder.streamFrom;
import static pgrela.eulerproblem.common.Primes.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

public class CompositesWithPrimeRepunitProperty implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CompositesWithPrimeRepunitProperty.class);
    }

    public long solve() {
        return iterate(4, i -> i + 1)
                .filter(i -> i % 5 != 0 && i % 2 != 0)
                .filter((n1) -> !isPrime(n1))
                .filter(n -> (n - 1) % (1 + streamFrom(1L)
                        .of(r -> (r * 10 + 1) % n)
                        .until(r -> r != 0)
                        .stream()
                        .count()) == 0
                )
                .limit(25)
                .sum();
    }

}
