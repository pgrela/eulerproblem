package pgrela.eulerproblem.problem78;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CoinPartitions implements EulerSolver {

    public static final int MAX_ALLOWED_LENGTH = 5000;
    private static final int[][] cache = new int[MAX_ALLOWED_LENGTH + 1][MAX_ALLOWED_LENGTH + 1];

    public static void main(String[] args) {
        printSolution(CoinPartitions.class);
    }

    public long solve() {
        long million = 1000000;
        return IntStream.iterate(1, i -> i + 1).limit(MAX_ALLOWED_LENGTH).filter(i -> {
            long s = countSumations(i, i);
            return s%million==0;
        }).findFirst().getAsInt();
    }

    public int countSumations(int length, int max) {
        if (length == 0) {
            return 1;
        }
        if (max == 0) {
            return 0;
        }
        if (cache[length][max] != 0) {
            return cache[length][max];
        }

        int sum = 0;
        int i = length;
        while (i >= 0) {
            sum = sum%(ONE_MILLION*10)+(countSumations(i, max - 1));
            i -= max;
        }
        return cache[length][max] = sum%(ONE_MILLION*10);
    }

}
