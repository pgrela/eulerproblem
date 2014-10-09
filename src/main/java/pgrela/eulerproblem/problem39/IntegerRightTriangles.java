package pgrela.eulerproblem.problem39;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Map;
import java.util.stream.Collectors;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

public class IntegerRightTriangles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(IntegerRightTriangles.class);
    }

    @Override
    public long solve() {
        return Coordinates.square(1, 1, 1000, 1000)
                .filter(p -> Maths.isSquare(p.x * p.x + p.y * p.y))
                .collect(Collectors.toMap(p -> p.x + p.y + Maths.intSqrt(p.x * p.x + p.y * p.y), (k) -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(e -> e.getKey() <= 1000)
                .max((a, b) -> Integer.compare(a.getValue(), b.getValue()))
                .map(Map.Entry::getKey).get();
    }


}
