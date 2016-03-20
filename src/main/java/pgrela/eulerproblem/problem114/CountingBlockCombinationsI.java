package pgrela.eulerproblem.problem114;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingBlockCombinationsI implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CountingBlockCombinationsI.class);
    }

    public long solve() {
        long[] endsWithBlack = new long[51];
        long[] endsWithRed = new long[51];
        endsWithBlack[0] = 0;
        endsWithBlack[1] = 1;
        endsWithBlack[2] = 1;
        endsWithBlack[3] = 1;
        endsWithRed[0] = 0;
        endsWithRed[1] = 0;
        endsWithRed[2] = 0;
        endsWithRed[3] = 1;
        for (int i = 4; i <= 50; i++) {
            endsWithBlack[i] = endsWithBlack[i - 1] + endsWithRed[i - 1];
            endsWithRed[i] = endsWithRed[i - 1] + endsWithBlack[i - 3];
        }
        return endsWithBlack[50] + endsWithRed[50];
    }


}
