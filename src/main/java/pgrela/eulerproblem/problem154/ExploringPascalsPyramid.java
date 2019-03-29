package pgrela.eulerproblem.problem154;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ExploringPascalsPyramid implements EulerSolver {

    private static final int TWO_HUNDRED_THOUSAND = 200_000;
    private static final int TARGET = 12;

    public static void main(String[] args) throws ParseException {
        printSolution(ExploringPascalsPyramid.class);
    }

    public long solve() {
        int[] countedFives = IntStream.rangeClosed(0, TWO_HUNDRED_THOUSAND).map(i -> countFactors(5, i)).toArray();
        int[] countedTwos = IntStream.rangeClosed(0, TWO_HUNDRED_THOUSAND).map(i -> countFactors(2, i)).toArray();

        long total = ((long) TWO_HUNDRED_THOUSAND + 2L) * (TWO_HUNDRED_THOUSAND + 1L) / 2L;
        int p = TWO_HUNDRED_THOUSAND;
        do {
            int qr = TWO_HUNDRED_THOUSAND - p;
            for (int r = 0; r <= qr / 2; r++) {
                if (countedFives[TWO_HUNDRED_THOUSAND] - countedFives[p] - countedFives[r] - countedFives[qr - r] < TARGET
                        || countedTwos[TWO_HUNDRED_THOUSAND] - countedTwos[p] - countedTwos[r] - countedTwos[qr - r] < TARGET) {
                    --total;
                    if (2 * r != qr) --total;
                }
            }
            --p;
        } while (p >= 0);
        return total;
    }

    private int countFactors(int prime, long n) {
        int k = prime;
        int s = 0;
        while (k <= n) {
            s += (n / k);
            k *= prime;
        }
        return s;
    }
}

