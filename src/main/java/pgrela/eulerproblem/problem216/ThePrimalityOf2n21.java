package pgrela.eulerproblem.problem216;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.math.BigInteger;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ThePrimalityOf2n21 implements EulerSolver {


    public static void main(String[] args) {
        printSolution(ThePrimalityOf2n21.class);
    }

    @Override
    public long solve() {
        int N = 50_000_000;
        long[] primes = Primes.primes(N * 10 / 7).mapToLong(p -> p).toArray();
        return LongStream.rangeClosed(2, N)
                .parallel()
                .map(this::t)
                .filter(n -> BigInteger.valueOf(n).isProbablePrime(3))
                //.filter(n -> Primes.isPrime(n, primes))
                .count();
    }

    private long t(long n) {
        return 2 * n * n - 1;
    }
}

