package pgrela.eulerproblem.problem178;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class StepNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(StepNumbers.class);
    }

    public static final int WITHOUT_09 = 0;
    public static final int WITH_9_OUT_0 = 1;
    public static final int WITH_0_OUT_9 = 2;
    public static final int WITH_09 = 3;

    @Override
    public long solve() {
        long count = 0;
        long[][][] m = new long[41][10][4];
        for (int digit = 1; digit <= 8; digit++) {
            m[1][digit][WITHOUT_09] = 1;
        }
        m[1][9][WITH_9_OUT_0] = 1;
        for (int length = 2; length <= 40; length++) {
            int previous = length - 1;
            m[length][0][WITH_0_OUT_9] = m[previous][1][WITH_0_OUT_9] + m[previous][1][WITHOUT_09];
            count += m[length][0][WITH_09] = m[previous][1][WITH_09] + m[previous][1][WITH_9_OUT_0];
            for (int digit = 1; digit <= 8; digit++) {
                m[length][digit][WITHOUT_09] = m[previous][digit - 1][WITHOUT_09] + m[previous][digit + 1][WITHOUT_09];
                m[length][digit][WITH_9_OUT_0] = m[previous][digit - 1][WITH_9_OUT_0] + m[previous][digit + 1][WITH_9_OUT_0];
                m[length][digit][WITH_0_OUT_9] = m[previous][digit - 1][WITH_0_OUT_9] + m[previous][digit + 1][WITH_0_OUT_9];
                count += m[length][digit][WITH_09] = m[previous][digit - 1][WITH_09] + m[previous][digit + 1][WITH_09];
            }
            m[length][9][WITH_9_OUT_0] = m[previous][8][WITH_9_OUT_0] + m[previous][8][WITHOUT_09];
            count += m[length][9][WITH_09] = m[previous][8][WITH_09] + m[previous][8][WITH_0_OUT_9];
        }
        return count;
    }

}

