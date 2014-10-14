package pgrela.eulerproblem.problem80;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SquareRootDigitalExpansion implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SquareRootDigitalExpansion.class);
    }

    public long solve() {
        return IntStream.range(1, 100).filter((value) -> !Maths.isSquare(value)).map(k -> {
            int s = 0;
            int nextDigit = (int) sqrt(k);
            s += nextDigit;
            BigInteger totalValue = ZERO;
            BigInteger c = valueOf(k);
            for (int i = 1; i < 100; i++) {
                c = c.subtract(totalValue.add(valueOf(nextDigit)).multiply(valueOf(nextDigit))).multiply(valueOf(100));
                totalValue = totalValue.add(valueOf(nextDigit * 2)).multiply(TEN);
                nextDigit = 0;
                while (totalValue.add(valueOf(nextDigit)).multiply(valueOf(nextDigit)).compareTo(c) == -1) {
                    ++nextDigit;
                }
                nextDigit = Math.max(0, nextDigit - 1);
                s += nextDigit;
            }
            return s;
        }).sum();
    }
}
