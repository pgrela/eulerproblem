package pgrela.eulerproblem.problem67;

import static pgrela.eulerproblem.common.Files.fileTo2DArray;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem18.MaximumPathSumI;

public class MaximumPathSumII implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem67/triangle.txt";

    public static void main(String[] args) {
        printSolution(MaximumPathSumII.class);
    }

    public long solve() {
        return MaximumPathSumI.solve(fileTo2DArray(" ", RESOURCE_FILE));
    }
}
