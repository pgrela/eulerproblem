package pgrela.eulerproblem.problem56;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.math.BigDecimal;

import pgrela.eulerproblem.common.EulerSolver;

public class PowerfulDigitSum implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PowerfulDigitSum.class);
    }

    public long solve() {
        return range(1,100).flatMap(
                a->range(1,100).map(b->BigDecimal.valueOf(a).pow(b).toString().chars().map(c->c-'0').sum())
        ).max().getAsInt();
    }
}
