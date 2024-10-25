package pgrela.eulerproblem.problem200;

import pgrela.eulerproblem.common.*;

import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimeProofSqubes implements EulerSolver {


    public static final long[] FACTORIZATION_PRIMES = Primes.primes(100_000_000).mapToLong(i -> i).toArray();

    public static void main(String[] args) {
        printSolution(PrimeProofSqubes.class);
    }

    @Override
    public long solve() {
        Long[] primes = Primes.primes(2_000_000).mapToLong(i -> i).boxed().toArray(Long[]::new);
        return Streams.mergeSorted(Arrays.stream(primes)
                        .map(square -> Arrays.stream(primes)
                                .filter(cube -> square != cube)
                                .takeWhile(cube -> Long.MAX_VALUE / cube / cube / cube > square * square)
                                .map(cube -> square * square * cube * cube * cube)
                        ).toList()
                )
                .filter(n -> String.valueOf(n).contains("200"))
                .filter(PrimeProofSqubes::isPrimeProof)
                .skip(199)
                .findFirst().orElseThrow();
    }

    private static boolean isPrimeProof(long n) {
        long length = Longs.length(n);
        for (long digit = 0; digit < length; digit++) {
            long original = (n / Maths.pow(10L, digit)) % 10;
            long zeroed = n - original * Maths.pow(10L, digit);
            for (long i = 0; i < 10; i++) {
                long modified = zeroed + i * Maths.pow(10L, digit);
                if (Primes.isPrime(modified, FACTORIZATION_PRIMES)) {
                    return false;
                }
            }
        }
        return true;
    }

}

