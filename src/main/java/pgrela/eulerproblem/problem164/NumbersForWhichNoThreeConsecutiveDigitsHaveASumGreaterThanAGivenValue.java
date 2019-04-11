package pgrela.eulerproblem.problem164;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NumbersForWhichNoThreeConsecutiveDigitsHaveASumGreaterThanAGivenValue implements EulerSolver {

    private static final int DIGITS = 20;
    private static final int MAX_SUM = 9;
    private long[][][] cache = new long[10][10][DIGITS + 1];

    public static void main(String[] args) throws ParseException {
        printSolution(NumbersForWhichNoThreeConsecutiveDigitsHaveASumGreaterThanAGivenValue.class);
    }

    public long solve() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= DIGITS; k++) {
                    cache[i][j][k] = k == 0 && i + j <= MAX_SUM ? 1 : -1;
                }
            }
        }
        return IntStream.rangeClosed(1, 9).mapToLong(previous -> t(0, previous, DIGITS - 1)).sum();
    }

    private long t(int previousTwo, int previos, int digits) {
        return cache[previousTwo][previos][digits] == -1 ?
                cache[previousTwo][previos][digits] = IntStream.rangeClosed(0, MAX_SUM - previos - previousTwo)
                        .mapToLong(i -> t(previos, i, digits - 1))
                        .sum()
                : cache[previousTwo][previos][digits];
    }

}

