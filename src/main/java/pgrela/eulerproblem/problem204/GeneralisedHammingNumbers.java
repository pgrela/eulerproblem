package pgrela.eulerproblem.problem204;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;
import pgrela.eulerproblem.common.Streams;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class GeneralisedHammingNumbers implements EulerSolver {

    public static final long LIMIT = 100_000_0000L;

    public static void main(String[] args) {
        printSolution(GeneralisedHammingNumbers.class);
    }

    @Override
    public long solve() {
        return Primes.primes(100)
                .mapToObj(prime -> LongStream.iterate(1, n -> n <= LIMIT, next -> next * prime).toArray())
                .reduce(new long[]{1L}, (c, v) -> Streams.cartessian(c, v).mapToLong(t -> t.a * t.b).filter(n -> n <= LIMIT).toArray())
                .length;
    }
}

