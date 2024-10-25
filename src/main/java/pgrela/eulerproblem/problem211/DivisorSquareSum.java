package pgrela.eulerproblem.problem211;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Longs;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DivisorSquareSum implements EulerSolver {

    private static final int LIMIT = 64_000_000;
    public static final int[] PRIMES_BELOW_LIMIT = Primes.primes(8010).toArray();

    public static void main(String[] args) {
        printSolution(DivisorSquareSum.class);
    }

    @Override
    public long solve() {
        return LongStream.range(1, LIMIT)
                .parallel()
                .filter(n-> Longs.isPerfectSquare(sigma2(n)))
                .sum();
    }

    // where p%q!=0 and q is a prime
    // sigma(p * q^k) = sigma(p) * ( q^0 + q^1 + q^2 +...+ q^n)
    private static long sigma2(long n) {
        return Primes.allDivisors(n, PRIMES_BELOW_LIMIT).map(d -> d * d).sum();
    }

}

