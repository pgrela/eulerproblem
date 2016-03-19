package pgrela.eulerproblem.problem111;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.LongStream;

import static java.util.stream.LongStream.range;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.Primes.isPrime;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimesWithRuns implements EulerSolver {

    public static final long DIGITS = 10L;

    public static void main(String[] args) {
        printSolution(PrimesWithRuns.class);
    }

    public long solve() {
        return solveForDigits(DIGITS);
    }

    public long solveForDigits(long numberLength) {
        long[] primes = Primes.primes(110000).asLongStream().toArray();
        return range(0, 10).flatMap(digit ->
                {
                    long prototype = range(0, numberLength).map(i -> digit * pow(10L, i)).sum();

                    return range(0, numberLength).mapToObj(
                            alienDigits -> range(0, pow(numberLength, alienDigits))
                                    .flatMap(positions -> range(0, pow(10L, alienDigits))
                                            .map(
                                                    substitutes -> construct(prototype, positions, substitutes, numberLength, alienDigits)
                                            )
                                            .filter(n -> isPrime(n, primes))
                                            .filter(n -> isNDigitNumber(n, numberLength))
                                    )
                                    .distinct()
                    )
                            .map(LongStream::toArray)
                            .filter(a -> a.length > 0)
                            .map(LongStream::of)
                            .findFirst()
                            .get();
                }
        ).sum();
    }

    private boolean isNDigitNumber(long n, long length) {
        return n >= pow(10L, length - 1);
    }

    private long construct(long prototype, long positions, long substitutions, long numberLength, long alienDigits) {
        long digit = prototype % 10;
        for (int i = 0; i < alienDigits; i++) {
            prototype += (substitutions % 10 - digit) * pow(10L, positions % numberLength);
            positions /= numberLength;
            substitutions /= 10;
        }
        return prototype;
    }
}
