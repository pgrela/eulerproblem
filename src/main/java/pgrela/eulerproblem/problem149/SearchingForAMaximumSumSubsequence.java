package pgrela.eulerproblem.problem149;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SearchingForAMaximumSumSubsequence implements EulerSolver {

    public static final int FOUR_MILLIONS = 4_000_000;

    public static void main(String[] args) throws ParseException {
        printSolution(SearchingForAMaximumSumSubsequence.class);
    }

    public long solve() {
        long m[][] = new long[2000][2000];
        long f[] = new long[FOUR_MILLIONS];
        for (int k = 1; k <= 55; k++) {
            f[k - 1] = (100003 - 200003 * k + 300007L * k * k * k) % 1000000 - 500000;
        }
        for (int k = 55; k < FOUR_MILLIONS; k++) {
            f[k] = (f[k - 24] + f[k - 55] + 10000000) % 1000000 - 500000;
        }
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 2000; j++) {
                m[i][j] = f[i * 2000 + j];
            }
        }
        long maxSum = 0;
        for (int i = 0; i < m.length; i++) {
            long sum = 0;
            for (int j = 0; j < m[i].length; j++) {
                if (sum < 0) sum = 0;
                sum += m[i][j];
                if (sum > maxSum) maxSum = sum;
            }
        }
        for (int i = 0; i < m.length; i++) {
            long sum = 0;
            for (int j = 0; j < m[i].length; j++) {
                if (sum < 0) sum = 0;
                sum += m[j][i];
                if (sum > maxSum) maxSum = sum;
            }
        }
        for (int i = 0; i < m.length; i++) {
            long sum = 0;
            for (int j = 0; j <= i; j++) {
                if (sum < 0) sum = 0;
                sum += m[i-j][j];
                if (sum > maxSum) maxSum = sum;
            }
        }
        for (int i = 0; i < m.length; i++) {
            long sum = 0;
            for (int j = 0; j <= i; j++) {
                if (sum < 0) sum = 0;
                sum += m[m.length-1-i+j][m.length-1-j];
                if (sum > maxSum) maxSum = sum;
            }
        }
        return maxSum;
    }
}

