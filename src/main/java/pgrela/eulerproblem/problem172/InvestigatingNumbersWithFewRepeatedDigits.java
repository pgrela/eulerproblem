package pgrela.eulerproblem.problem172;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingNumbersWithFewRepeatedDigits implements EulerSolver {

    private static final int DIGITS = 18;

    public static void main(String[] args) throws ParseException {
        printSolution(InvestigatingNumbersWithFewRepeatedDigits.class);
    }

    public long solve() {
        return solve(0, DIGITS, new int[10], DIGITS) * 9 / 10;
    }

    private long solve(int nextDigit, int digitsLeft, int[] histogram, int totalDigits) {
        if (nextDigit == 10) {
            if (digitsLeft != 0) {
                return 0;
            }
            int digits = totalDigits;
            long possibleNumbers = 1;
            for (int digit : histogram) {
                possibleNumbers *= Maths.newton(digits, digit);
                digits -= digit;
            }
            return possibleNumbers;
        }
        long s = 0;
        for (int i = 0; i <= Math.min(3, digitsLeft); i++) {
            histogram[nextDigit] = i;
            s += solve(nextDigit + 1, digitsLeft - i, histogram, totalDigits);
        }
        return s;
    }

}

