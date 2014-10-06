package pgrela.eulerproblem.problem20;

import pgrela.eulerproblem.common.EulerSolver;

import java.math.BigDecimal;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FactorialDigitSum implements EulerSolver {
    public static void main(String[] args) {
        printSolution(FactorialDigitSum.class);
    }

    public long solve() {
        return rangeClosed(1, 100)
                .mapToObj(BigDecimal::new)
                .reduce(BigDecimal::multiply)
                .get()
                .toString()
                .chars()
                .map(c->c-'0')
                .reduce(Integer::sum)
                .getAsInt();
    }

}
