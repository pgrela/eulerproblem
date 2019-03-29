package pgrela.eulerproblem.problem141;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingProgressiveNumbersNWhichAreAlsoSquare implements EulerSolver {

    public static final long TRILLION = 1000_000_000_000L;

    public static void main(String[] args) {
        printSolution(InvestigatingProgressiveNumbersNWhichAreAlsoSquare.class);
    }

    public long solve() {
        Set<Long> sum = new HashSet<>();
        for (long h = 1; h < 10000; h++) {
            long h3 = h * h * h;
            int maxC = Maths.intSqrt(TRILLION / h3);
            for (int c = 1; c < maxC; c++) {
                long maxM = Math.min(h, TRILLION / h3 / c / c);
                for (int m = 1; m < maxM; m++) {
                    long maybeSquare = m * c * (m + c * h3);
                    if (Maths.isSquare(maybeSquare))
                        sum.add(maybeSquare);
                }
            }
        }
        return sum.stream().mapToLong(Long::valueOf).sum();
    }

    private long slowSolution() {
        return LongStream.rangeClosed(2, Maths.intSqrt(1_000_000_000_000L)).parallel()
                .filter(p -> Primes.allDivisors(p * p)
                        .filter(c -> Primes.allDivisors(p * p / c)
                                .filter(m -> (p * p / c / m - m) > 0)
                                .filter(m -> (p * p / c / m - m) % c == 0)
                                .filter(m -> p * p > ((double) m * m * m * c + m) * m * c)
                                .filter(m -> Maths.isCube((p * p / c / m - m) / c))
                                .findAny().isPresent()
                        )
                        .findAny().isPresent()
                )
                .map(p -> p * p).sum();
    }

}

