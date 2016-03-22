package pgrela.eulerproblem.problem129;

import static java.util.stream.LongStream.iterate;
import static pgrela.eulerproblem.common.FiniteStreamBuilder.streamFrom;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.FiniteStreamBuilder;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Primes;

public class RepunitDivisibility implements EulerSolver {

    public static void main(String[] args) {
        printSolution(RepunitDivisibility.class);
    }

    public long solve() {
        return iterate(ONE_MILLION, i -> i + 1)
                .filter(i -> i % 5 != 0 && i % 2 != 0)
                .filter(n -> streamFrom(1L)
                        .of(r -> (r * 10 + 1) % n)
                        .until(r -> r != 0)
                        .stream()
                        .count() > ONE_MILLION
                )
                .findFirst()
                .getAsLong();
    }

}
