package pgrela.eulerproblem.problem125;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

import static java.util.stream.IntStream.range;
import static java.util.stream.LongStream.iterate;
import static pgrela.eulerproblem.common.FiniteStreamBuilder.stream;
import static pgrela.eulerproblem.common.Maths.intSqrt;
import static pgrela.eulerproblem.common.Maths.isPalindrome;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PalindromicSums implements EulerSolver {

    public static final long MAX = 100 * Integers.ONE_MILLION;

    public static void main(String[] args) {
        printSolution(PalindromicSums.class);
    }

    public long solve() {
        return range(0, intSqrt(MAX))
                .flatMap(n -> stream(iterate(2L, i -> i + 1).map(k -> getSquareSum(n, k)).boxed())
                        .until(i -> i < MAX)
                        .stream()
                        .filter(x -> isPalindrome(x, 10))
                        .mapToInt(i -> (int) ((long) i))
                ).distinct()
                .mapToLong(i -> i)
                .sum();
    }

    private long getSquareSum(long n, long k) {
        return k * (k * (2 * k + 3) + 6 * n * (n + 1 + k) + 1) / 6;
    }
}
