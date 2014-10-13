package pgrela.eulerproblem.problem65;

import pgrela.eulerproblem.common.EulerSolver;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static java.util.stream.IntStream.iterate;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ConvergentsOfE implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ConvergentsOfE.class);
    }

    public long solve() {
        Iterator<BigDecimal> e = iterate(100, i -> i - 1).map(i -> i % 3 == 0 ? i / 3 * 2 : 1).mapToObj(BigDecimal::valueOf).iterator();
        return Stream.iterate(factor(valueOf(1), valueOf(0)), p -> factor(p[0].multiply(e.next()).add(p[1]), p[0]))
                .skip(99)
                .map(p -> p[0].multiply(valueOf(2)).add(p[1]))
                .findFirst().get()
                .toString().chars()
                .map(a -> a - '0')
                .sum();
    }

    BigDecimal[] factor(BigDecimal nominator, BigDecimal denominator) {
        return new BigDecimal[]{nominator, denominator};
    }
}

