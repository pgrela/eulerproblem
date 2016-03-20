package pgrela.eulerproblem.problem108;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.common.Tuple.tuple;

public class DiophantineReciprocalsI implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DiophantineReciprocalsI.class);
    }


    public long solve() {
        return IntStream.iterate(1, i -> i + 1)
                .mapToObj(n -> tuple(n, Primes.getPrimeFactorsHistogram(n, Primes.getPrimesToFactorizeInteger())))
                .map(t -> tuple(t.a, t.b.values().stream().map(i -> i * 2 + 1).reduce(1, (a, b) -> a * b) / 2 + 1))
                .filter(t -> t.b > 1000)
                .findFirst()
                .get().a;
    }
}
