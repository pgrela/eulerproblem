package pgrela.eulerproblem.problem191;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrizeStrings implements EulerSolver {

    private static final int DAYS = 30;
    private static final int X = 0;
    private static final int XA = 1;
    private static final int XAA = 2;
    private static final int LX = 3;
    private static final int LXA = 4;
    private static final int LXAA = 5;

    public static void main(String[] args) {
        printSolution(PrizeStrings.class);
    }

    @Override
    public long solve() {
        int[][] prizeStrings = new int[DAYS][6];
        prizeStrings[0][X] = 1;
        prizeStrings[0][XA] = 1;
        prizeStrings[0][XAA] = 0;
        prizeStrings[0][LX] = 1;
        prizeStrings[0][LXA] = 0;
        prizeStrings[0][LXAA] = 0;
        for (int day = 1; day < DAYS; day++) {
            prizeStrings[day][X] = prizeStrings[day - 1][X] + prizeStrings[day - 1][XA] + prizeStrings[day - 1][XAA];
            prizeStrings[day][XA] = prizeStrings[day - 1][X];
            prizeStrings[day][XAA] = prizeStrings[day - 1][XA];
            prizeStrings[day][LX] = prizeStrings[day][X] + prizeStrings[day - 1][LX] + prizeStrings[day - 1][LXA] + prizeStrings[day - 1][LXAA];
            prizeStrings[day][LXA] = prizeStrings[day - 1][LX];
            prizeStrings[day][LXAA] = prizeStrings[day - 1][LXA];
        }
        return prizeStrings[prizeStrings.length - 1][X] + prizeStrings[prizeStrings.length - 1][XA] + prizeStrings[prizeStrings.length - 1][XAA] + prizeStrings[prizeStrings.length - 1][LX] + prizeStrings[prizeStrings.length - 1][LXA] + prizeStrings[prizeStrings.length - 1][LXAA];
    }

}

