package pgrela.eulerproblem.problem113;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NonBouncyNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(NonBouncyNumbers.class);
    }

    public long solve() {
        long stones = 1;
        int holes = 100;
        long maxes = 0;
        for (int i = 0; i < holes; i++) {
            stones = stones * (10 + i) / (i + 1);
            maxes += stones;
        }
        return maxes + stones - 10 * holes - 1;
    }


}
