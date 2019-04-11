package pgrela.eulerproblem.problem171;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FindingNumbersForWhichTheSumOfTheSquaresOfTheDigitsIsASquare implements EulerSolver {

    public static final int DIGITS = 20;
    public static final int DISPLAY_DIGITS = 9;
    public static final long MOD = Maths.pow(10, DISPLAY_DIGITS);

    public static void main(String[] args) throws ParseException {
        printSolution(FindingNumbersForWhichTheSumOfTheSquaresOfTheDigitsIsASquare.class);
    }

    public long solve() {
        return solve(0, 0, DIGITS, new int[10]);
    }

    private long solve(int alreadySummed, int nextDigit, int digitsLeft, int[] histogram) {
        if (nextDigit == 10) {
            if (digitsLeft != 0 || !Maths.isSquare(alreadySummed)) {
                return 0;
            }
            int digits = DIGITS;
            long possibleNumbers = 1;
            for (int digit : histogram) {
                possibleNumbers *= Maths.newton(digits, digit);
                digits -= digit;
            }
            long s = 0;
            for (int digit = 1; digit < histogram.length; digit++) {
                if (histogram[digit] == 0) continue;
                long sumPerDigit = possibleNumbers * histogram[digit] / DIGITS * digit;
                for (int i = 0; i < DISPLAY_DIGITS; i++) {
                    s = (s + (sumPerDigit % Maths.pow(10L, DISPLAY_DIGITS - i)) * Maths.pow(10L, i)) % MOD;
                }
            }
            return s;
        }
        long s = 0;
        for (int i = 0; i <= digitsLeft; i++) {
            histogram[nextDigit] = i;
            s += solve(alreadySummed + nextDigit * nextDigit * i, nextDigit + 1, digitsLeft - i, histogram);
        }
        return s % MOD;
    }

}

