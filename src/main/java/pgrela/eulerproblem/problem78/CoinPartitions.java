package pgrela.eulerproblem.problem78;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;

public class CoinPartitions implements EulerSolver {

    public static final int MAX_ALLOWED_LENGTH = 100000;
    private int[] previous = new int[MAX_ALLOWED_LENGTH + 1];
    private int MODULO = 1000000;

    public static void main(String[] args) {
        printSolution(CoinPartitions.class);
    }

    public long solve() {
        int[] pent = IntStream.range(2, 6000)
                .map(i -> (i % 2 == 0 ? 1 : -1) * (i / 2))
                .map(k -> k * (3 * k - 1) / 2)
                .toArray();
        previous[0] = 1;
        for (int n = 1; n <= MAX_ALLOWED_LENGTH; n++) {
            int sum = 0;
            int k = 0;
            int i = n - pent[k];
            while (i >= 0) {
                sum += (k / 2 % 2 == 0 ? 1 : -1) * previous[i] % MODULO;
                ++k;
                i = n - pent[k];
            }
            previous[n] = sum % MODULO;
            if (previous[n] % MODULO == 0) {
                return n;
            }
        }
        return -1;
    }
}
