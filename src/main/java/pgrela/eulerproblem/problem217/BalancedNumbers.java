package pgrela.eulerproblem.problem217;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class BalancedNumbers implements EulerSolver {

    public static final int FORTY_SEVEN = 47;
    public static final long MOD = Maths.pow(3L, 15);

    public static void main(String[] args) {
        printSolution(BalancedNumbers.class);
    }

    @Override
    public long solve() {
        long[][] sumAllNumbersByDigitsSumByLength = new long[FORTY_SEVEN / 2 + 1][FORTY_SEVEN / 2 * 9 + 1];
        long[][] countAllNumbersByDigitsSumByLength = new long[FORTY_SEVEN / 2 + 1][FORTY_SEVEN / 2 * 9 + 1];
        long[][] sumAllNumbersStartingWithNoZeroByDigitsSumByLength = new long[FORTY_SEVEN / 2 + 1][FORTY_SEVEN / 2 * 9 + 1];
        long[][] countAllNumbersStartingWithNoZeroByDigitsSumByLength = new long[FORTY_SEVEN / 2 + 1][FORTY_SEVEN / 2 * 9 + 1];
        countAllNumbersByDigitsSumByLength[0][0] = 1;
        countAllNumbersStartingWithNoZeroByDigitsSumByLength[0][0] = 1;
        for (int digits = 1; digits <= FORTY_SEVEN / 2; digits++) {
            int oneLessDigits = digits - 1;
            for (int sumOneLessDigit = 0; sumOneLessDigit <= oneLessDigits * 9; sumOneLessDigit++) {
                for (int nextDigit = 0; nextDigit < 10; nextDigit++) {
                    sumAllNumbersByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] +=
                            sumAllNumbersByDigitsSumByLength[oneLessDigits][sumOneLessDigit] * 10
                                    + countAllNumbersByDigitsSumByLength[oneLessDigits][sumOneLessDigit] * nextDigit;
                    countAllNumbersByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] += countAllNumbersByDigitsSumByLength[oneLessDigits][sumOneLessDigit];
                    sumAllNumbersByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] %= MOD;
                    countAllNumbersByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] %= MOD;
                    if (digits == 1 && nextDigit == 0) continue;
                    sumAllNumbersStartingWithNoZeroByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] +=
                            sumAllNumbersStartingWithNoZeroByDigitsSumByLength[oneLessDigits][sumOneLessDigit] * 10
                                    + countAllNumbersStartingWithNoZeroByDigitsSumByLength[oneLessDigits][sumOneLessDigit] * nextDigit;
                    countAllNumbersStartingWithNoZeroByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] += countAllNumbersStartingWithNoZeroByDigitsSumByLength[oneLessDigits][sumOneLessDigit];
                    sumAllNumbersStartingWithNoZeroByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] %= MOD;
                    countAllNumbersStartingWithNoZeroByDigitsSumByLength[digits][sumOneLessDigit + nextDigit] %= MOD;
                }
            }
        }
        long c = 0;
        for (int length = 1; length <= FORTY_SEVEN; length++) {
            int half = length / 2;
            long middleUnit = Maths.powMod(10, half, MOD);
            for (int sum = 0; sum <= half * 9; sum++) {
                if (length % 2 == 1)
                    for (int middleDigit = 0; middleDigit < 10; middleDigit++) {
                        c += sumAllNumbersStartingWithNoZeroByDigitsSumByLength[half][sum] * 10 * middleUnit % MOD * countAllNumbersByDigitsSumByLength[half][sum] % MOD
                                + sumAllNumbersByDigitsSumByLength[half][sum] * countAllNumbersStartingWithNoZeroByDigitsSumByLength[half][sum] % MOD
                                + middleDigit * middleUnit % MOD * countAllNumbersByDigitsSumByLength[half][sum] % MOD * countAllNumbersStartingWithNoZeroByDigitsSumByLength[half][sum] % MOD;
                        c %= MOD;
                    }
                else
                    c += sumAllNumbersStartingWithNoZeroByDigitsSumByLength[half][sum] * middleUnit % MOD * countAllNumbersByDigitsSumByLength[half][sum] % MOD
                            + sumAllNumbersByDigitsSumByLength[half][sum] * countAllNumbersStartingWithNoZeroByDigitsSumByLength[half][sum] % MOD;
                c %= MOD;
            }
        }
        return c;
    }
}

