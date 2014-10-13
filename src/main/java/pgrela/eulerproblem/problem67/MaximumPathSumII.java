package pgrela.eulerproblem.problem67;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem18.MaximumPathSumI;

import static java.util.stream.Stream.of;
import static pgrela.eulerproblem.common.Files.getLinesStream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class MaximumPathSumII implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem67/triangle.txt";

    public static void main(String[] args) {
        printSolution(MaximumPathSumII.class);
    }

    public long solve() {
        return MaximumPathSumI.solve(
                getLinesStream(RESOURCE_FILE).map(line -> of(line.split(" ")).mapToInt(Integer::valueOf).toArray()).toArray(int[][]::new)
        );
    }
}
