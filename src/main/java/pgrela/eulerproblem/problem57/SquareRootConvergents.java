package pgrela.eulerproblem.problem57;

import static java.math.BigDecimal.valueOf;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.math.BigDecimal;
import java.util.stream.Stream;

import javafx.util.Pair;
import pgrela.eulerproblem.common.EulerSolver;

public class SquareRootConvergents implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SquareRootConvergents.class);
    }

    public long solve() {
        return Stream.iterate(new Pair<>(BigDecimal.ONE, BigDecimal.ONE),
                p -> new Pair<>(
                        p.getKey().add(p.getValue().multiply(valueOf(2L))),
                        p.getKey().add(p.getValue()))
        )
                .limit(1001)
                .filter(p -> p.getKey().toString().length() > p.getValue().toString().length())
                .count();
    }
}
