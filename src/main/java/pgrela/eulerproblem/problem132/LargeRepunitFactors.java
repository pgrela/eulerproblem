package pgrela.eulerproblem.problem132;

import static pgrela.eulerproblem.common.FiniteStreamBuilder.streamFrom;
import static pgrela.eulerproblem.common.Integers.ONE_BILLION;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

public class LargeRepunitFactors implements EulerSolver {

    public static void main(String[] args) {
        printSolution(LargeRepunitFactors.class);
    }

    public long solve() {
        return Primes.primes(ONE_MILLION)
                .filter(i -> i > 5)
                .filter(n -> ONE_BILLION % (streamFrom(1L)
                        .of(r -> (r * 10 + 1) % n)
                        .until(r -> r != 0)
                        .stream()
                        .count() + 1) == 0
                )
                .limit(40)
                .sum();
    }

}
