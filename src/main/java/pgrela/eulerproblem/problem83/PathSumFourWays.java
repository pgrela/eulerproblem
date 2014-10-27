package pgrela.eulerproblem.problem83;

import static pgrela.eulerproblem.common.Files.fileTo2DArray;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.DjikstraSolver;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.ValuePoint.point;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.bottomRightCornerValue;

import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;

public class PathSumFourWays implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem83/matrix.txt";

    public static void main(String[] args) {
        printSolution(PathSumFourWays.class);
    }

    public long solve() {
        return bottomRightCornerValue(
                new DjikstraSolver(fileTo2DArray(",", RESOURCE_FILE),
                        p -> Stream.of(
                                point(p.x + 1, p.y),
                                point(p.x - 1, p.y),
                                point(p.x, p.y + 1),
                                point(p.x, p.y - 1)
                        )
                ).startFrom(point(0, 0)));
    }
}
