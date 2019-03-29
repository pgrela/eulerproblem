package pgrela.eulerproblem.problem150;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SearchingATriangularArrayForASubTriangleHavingMinimumSum implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(SearchingATriangularArrayForASubTriangleHavingMinimumSum.class);
    }

    public long solve() {
        long t = 0;
        long f[] = new long[500500];
        for (int k = 0; k < 500500; k++) {
            t = (615949 * t + 797807) % (1 << 20);
            f[k] = t - (1 << 19);
        }
        long subSums[][] = new long[1000][1000];
        int k = 0;
        for (int i = 0; i < 1000; i++) {
            long sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += f[k++];
                subSums[i][j] = sum;
            }
        }
        long minSum = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j <= i; j++) {
                int row = 0;
                long sum = 0;
                while (row + i < 1000) {
                    sum += subSums[i + row][j + row] - (j > 0 ? subSums[i + row][j - 1] : 0);
                    ++row;
                    if (sum < minSum) minSum = sum;
                }
            }
        }
        return minSum;
    }
}

