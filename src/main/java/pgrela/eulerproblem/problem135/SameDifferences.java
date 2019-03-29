package pgrela.eulerproblem.problem135;

import pgrela.eulerproblem.common.EulerSolver;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Primes.allDivisors;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SameDifferences implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SameDifferences.class);
    }

    public long solve() {
        return range(1, ONE_MILLION)
                .mapToLong(n -> allDivisors(n).filter(a -> (n / a + a) % 4 == 0 && (a - (n / a + a) / 4) > 0).count())
                .filter(i -> i == 10)
                .count();
    }

}
