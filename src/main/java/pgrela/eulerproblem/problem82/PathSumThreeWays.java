package pgrela.eulerproblem.problem82;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.of;
import static pgrela.eulerproblem.common.Files.fileTo2DArray;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.DjikstraSolver;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.ValuePoint.point;

import pgrela.eulerproblem.common.EulerSolver;

public class PathSumThreeWays implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem82/matrix.txt";

    public static void main(String[] args) {
        printSolution(PathSumThreeWays.class);
    }

    public long solve() {
        return of(new DjikstraSolver(fileTo2DArray(",", RESOURCE_FILE),
                p -> of(
                        point(p.x + 1, p.y),
                        point(p.x, p.y + 1),
                        point(p.x, p.y - 1)
                )
        ).startFrom(range(0, 80).mapToObj(i -> point(0, i)).collect(toList())))
                .mapToInt(i -> i[i.length - 1]).min().getAsInt();
    }
}
