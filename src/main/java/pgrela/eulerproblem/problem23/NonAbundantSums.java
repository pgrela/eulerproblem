package pgrela.eulerproblem.problem23;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.function.IntPredicate;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NonAbundantSums implements EulerSolver {

    public static final int MAX_CONSIDERED = 28123;

    public static void main(String[] args) {
        printSolution(NonAbundantSums.class);
    }

    public long solve() {
        int[] abundantNumbers = rangeClosed(1, MAX_CONSIDERED).filter(isAbundant()).toArray();
        return sumAllNaturalNumbersUpTo(MAX_CONSIDERED) - of(abundantNumbers).flatMap(n -> of(abundantNumbers).map(a -> a + n)).filter(n -> n <= MAX_CONSIDERED).distinct().sum();
    }

    private int sumAllNaturalNumbersUpTo(int max) {
        return max * (max + 1) / 2;
    }

    protected IntPredicate isAbundant() {
        return n -> Primes.sumDivisors(n) > n;
    }

}
