package pgrela.eulerproblem.problem57;

import static java.math.BigDecimal.valueOf;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.math.BigDecimal;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Tuple;

public class SquareRootConvergents implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SquareRootConvergents.class);
    }

    public long solve() {
        return Stream.iterate(new Tuple<>(BigDecimal.ONE, BigDecimal.ONE),
                p -> new Tuple<>(
                        p.a.add(p.b.multiply(valueOf(2L))),
                        p.a.add(p.b))
        )
                .limit(1001)
                .filter(p -> p.a.toString().length() > p.b.toString().length())
                .count();
    }
}
