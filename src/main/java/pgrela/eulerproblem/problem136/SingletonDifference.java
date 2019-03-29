package pgrela.eulerproblem.problem136;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SingletonDifference implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SingletonDifference.class);
    }

    public long solve() {
        return IntStream.range(1, 50 * Integers.ONE_MILLION).parallel()
                .filter(n -> n % 4 == 0 || (n + 1) % 4 == 0)
                .filter(n -> Primes.allDivisors(n).filter(a -> (n / a + a) % 4 == 0 && (a - (n / a + a) / 4) > 0).count() == 1)
                .count();
    }

}
