package pgrela.eulerproblem.problem37;

import pgrela.eulerproblem.common.*;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.FiniteStreamBuilder.*;
import static pgrela.eulerproblem.common.Integers.*;
import static pgrela.eulerproblem.common.Maths.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TruncatablePrimes implements EulerSolver {
    public static void main(String[] args) {
        printSolution(TruncatablePrimes.class);
    }

    public long solve() {
        Set<Integer> primes = Primes.primes(1000000).boxed().collect(Collectors.toSet());

        return range(10, 1000000).filter(
                p -> streamFrom(p).of(n -> n / 10).until(n -> n > 0).stream().allMatch(primes::contains)
        ).filter(
                p -> streamFrom(p).of(n -> n % pow(10, length(n) - 1)).until(n -> n > 0).stream().allMatch(primes::contains)
        ).sum();
    }

}
