package pgrela.eulerproblem.problem119;

import pgrela.eulerproblem.common.*;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DigitPowerSum implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DigitPowerSum.class);
    }

    public long solve() {
        return LongStream.range(2, digitSum(8999999999999999999L))
                .flatMap(n -> FiniteStreamBuilder.stream(IntStream.iterate(2, i -> i + 1)
                        .mapToLong(e -> Maths.pow(n, e))
                        .boxed())
                        .until(i -> Long.MAX_VALUE / n > i)
                        .stream()
                        .mapToLong(i -> i)
                        .filter(i -> digitSum(i) == n)
                )
                .sorted()
                .skip(29)
                .findFirst()
                .getAsLong();
    }

    public int digitSum(long n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }


}
