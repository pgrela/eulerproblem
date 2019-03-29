package pgrela.eulerproblem.problem156;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingDigits implements EulerSolver {

    private static final long MAX = 80_000_000_000L;

    public static void main(String[] args) throws ParseException {
        printSolution(CountingDigits.class);
    }

    public long solve() {
        long sum = 0;
        for (int digit = 1; digit <= 9; digit++) {
            long fValue = 0;
            long i = 1;
            while (i <= MAX) {
                long thisDigits = countDigits(digit, i);
                if (i % 1000000 == 0 && ((i > fValue + 600000 + 1000000 * thisDigits) || (i < fValue && thisDigits > 0) || (i + 1000000 < fValue && thisDigits == 0))) {
                    i += 1000000;
                    fValue += 600000 + 1000000 * thisDigits;
                    continue;
                }
                if (i % 10000 == 0 && ((i > fValue + 4000 + 10000 * thisDigits) || (i < fValue && thisDigits > 0) || (i + 10000 < fValue && thisDigits == 0))) {
                    i += 10000;
                    fValue += 4000 + 10000 * thisDigits;
                    continue;
                }
                if (i % 100 == 0 && ((i > fValue + 20 + 100 * thisDigits) || (i < fValue && thisDigits > 0) || (i + 100 < fValue && thisDigits == 0))) {
                    i += 100;
                    fValue += 20 + 100 * thisDigits;
                    continue;
                }
                fValue += thisDigits;
                if (fValue == i) {
                    sum += i;
                }
                ++i;
            }
        }
        return sum;
    }

    private int countDigits(int digit, long number) {
        int r = 0;
        while (number > 0) {
            if (number % 10 == digit) ++r;
            number /= 10;
        }
        return r;
    }
}

