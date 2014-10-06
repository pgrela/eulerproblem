package pgrela.eulerproblem.problem41;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem43.SubstringDivisibility.getAllPermutations;

public class PandigitalPrime implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PandigitalPrime.class);
    }

    @Override
    public long solve() {
        return rangeClosed(1, 9)
                .mapToObj(k -> rangeClosed(1, k).boxed().collect(Collectors.toList()))
                .flatMapToInt(digits-> getAllPermutations((List) digits))
                .filter(Primes::isPrime).max().getAsInt();
    }
}
