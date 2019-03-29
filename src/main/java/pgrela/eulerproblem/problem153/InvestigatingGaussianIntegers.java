package pgrela.eulerproblem.problem153;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingGaussianIntegers implements EulerSolver {

    private static final int HUNDRED_MILLIONS = 100_000_000;

    public static void main(String[] args) throws ParseException {
        printSolution(InvestigatingGaussianIntegers.class);
    }

    public long solve() {
        return LongStream.rangeClosed(1, Maths.intSqrt(HUNDRED_MILLIONS)).map(
                rational -> LongStream.rangeClosed(rational, Maths.intSqrt(HUNDRED_MILLIONS - rational * rational))
                        .filter(imaginary -> Maths.gcd(rational, imaginary) == 1)
                        .map(imaginary -> LongStream.rangeClosed(1, HUNDRED_MILLIONS / (rational * rational + imaginary * imaginary))
                                .map(multiple -> (imaginary == rational ? 1 : 2) * (imaginary + rational) * multiple * (HUNDRED_MILLIONS / ((rational * rational + imaginary * imaginary) * multiple)))
                                .sum()
                        ).sum()
        ).sum() + LongStream.rangeClosed(1, HUNDRED_MILLIONS).map(i -> (HUNDRED_MILLIONS / i) * i).sum();
        /**
         * It is faster, much faster:
         * <code>
         * long sum = 0;
         * int mxSquare = Maths.intSqrt(HUNDRED_MILLIONS);
         * for (int rational = 1; rational <= mxSquare; rational++) {
         * int maxImaginary = Maths.intSqrt(HUNDRED_MILLIONS - rational * rational);
         * for (int imaginary = rational; imaginary <= maxImaginary; imaginary++) {
         * if (Maths.gcd(rational, imaginary) == 1) {
         * int squareSum = rational * rational + imaginary * imaginary;
         * int multiple = 1;
         * while (multiple * squareSum <= HUNDRED_MILLIONS) {
         * sum += (imaginary == rational ? 1 : 2) * (imaginary + rational) * multiple * (HUNDRED_MILLIONS / (squareSum * multiple));
         * ++multiple;
         * }
         * }
         * }
         * }
         * for (int i = 1; i <= HUNDRED_MILLIONS; i++) {
         * sum += (HUNDRED_MILLIONS / i) * i;
         * }
         * return sum;
         * </code>
         */
    }
}

