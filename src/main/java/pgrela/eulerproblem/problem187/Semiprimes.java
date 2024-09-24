package pgrela.eulerproblem.problem187;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.List;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Semiprimes implements EulerSolver {

    public static void main(String[] args) {
        printSolution(Semiprimes.class);
    }

    @Override
    public long solve() {
        return IntStream.range(2, 100_000_000)
                .mapToObj(Primes::factorize)
                .map(List::size)
                .filter(d -> 2 == d)
                .count();
    }
}

