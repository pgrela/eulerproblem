package pgrela.eulerproblem.problem131;

import static java.util.stream.IntStream.iterate;
import static pgrela.eulerproblem.common.FiniteStreamBuilder.streamFrom;
import static pgrela.eulerproblem.common.Primes.isPrime;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.ArbitraryPair;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.FiniteStreamBuilder;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Pair;
import pgrela.eulerproblem.common.Primes;

public class PrimeCubePartnership implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimeCubePartnership.class);
    }

    public long solve() {
        return IntStream.of(19)//Primes.primes(2)//Integers.ONE_MILLION)
                .filter(p ->
                        FiniteStreamBuilder.stream(LongStream.iterate(1, i -> i + 1).boxed())
                                .until(m -> 4 * p - m * m >= 0)
                                .stream()
                                .filter(m -> (4 * p - m * m) % 3 == 0)
                                .peek(System.out::println)
                                .flatMap(m -> Stream.of(-m + 2 * p, m + 2 * p))
                                .filter(s -> s > 0)
                                .filter(s -> Maths.isSquare(s * (4 * p - 3 * s)))
                                .filter(s -> (s * Maths.intSqrt(s * (4 * p - 3 * s)) - 3 * s * s) % 2 == 0)
                                .map(s -> ArbitraryPair.pair((s * Maths.intSqrt(s * (4 * p - 3 * s)) - 3 * s * s) / 2, s))
                                .map(ns -> ArbitraryPair.pair(ns.a, ns.a + ns.b))
                                .filter(nk -> nk.a * nk.a * (nk.a + p) == nk.b * nk.b * nk.b).findAny().isPresent()
                )
                .count();
    }

}
