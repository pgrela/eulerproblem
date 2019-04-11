package pgrela.eulerproblem.problem160;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FactorialTrailingDigits implements EulerSolver {

    private static final int TWELVE = 12;
    private static final long N = Maths.pow(10L, TWELVE);
    private static final int DIGITS = 5;
    private static final int TEN_TO_DIGITS = Maths.pow(10, DIGITS);

    public static void main(String[] args) throws ParseException {
        printSolution(FactorialTrailingDigits.class);
    }

    public long solve() {
        long[] reductors = LongStream.rangeClosed(0, Maths.log(2, N))
                .map(a -> Maths.pow(2, a))
                .flatMap(a -> LongStream.rangeClosed(0, Maths.log(5, N / a))
                        .map(b -> Maths.pow(5, b))
                        .map(b -> a * b)).toArray();
        long maxReminder = Math.max(1, N / TEN_TO_DIGITS);
        return rd(LongStream.rangeClosed(1, Math.min(TEN_TO_DIGITS, N))
                .filter(aPrime -> aPrime % 2 != 0 && aPrime % 5 != 0)
                .map(aPrime -> pow(aPrime, Arrays.stream(reductors)
                        .filter(reductor -> N >= aPrime * reductor)
                        .map(reductor -> (maxReminder - 1 - aPrime * reductor / TEN_TO_DIGITS) / reductor + 1)
                        .sum()))
                .reduce(1, (a, b) -> rd(a * b)) * pow(2, countFactorInFactorial(2, N) - countFactorInFactorial(5, N)));
    }

    private long countFactorInFactorial(long factor, long factorialOf) {
        long count = 0;
        long round = factor;
        do {
            count += factorialOf / round;
            round *= factor;
        } while (N >= round);
        return count;
    }

    private long rd(long n) {
        while (n % 10 == 0) n /= 10;
        return n % (TEN_TO_DIGITS);
    }

    public long pow(long number, long exponent) {
        if (exponent == 0)
            return 1;
        long numberSquared = rd(pow(rd(number * number), exponent / 2));
        return exponent % 2 == 0 ? numberSquared : rd(numberSquared * number);
    }
}

