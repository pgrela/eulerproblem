package pgrela.eulerproblem.problem140;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Tuple;
import pgrela.eulerproblem.problem137.FibonacciGoldenNuggets.Factor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ModifiedFibonacciGoldenNuggets implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ModifiedFibonacciGoldenNuggets.class);
    }

    public String solveToString() {
        int n = 100;
        List<Long> observeFibonnacci = Coordinates.square(1, 1, n, n)
                .filter(p -> p.x < p.y)
                .map(Factor::new)
                .distinct()
                .sorted((x, y) -> signum(x.a * y.b - x.b * y.a))
                .filter(p -> (3 * p.b * p.b - 2 * p.a * p.b) % (p.b * p.b - p.a * p.b - p.a * p.a) == 0)
                .filter(p -> (p.b * p.b - p.a * p.b - p.a * p.a) > 0)
                //.peek(p -> System.out.println(p + "->" + ((3 * p.b * p.b - 2 * p.a * p.b) / (p.b * p.b - p.a * p.b - p.a * p.a) - 3)))
                .map(p -> p.a * p.b / (p.b * p.b - p.a * p.b - p.a * p.a) - 3)
                .collect(Collectors.toList());
        return Stream.concat(
                Stream.iterate(new Tuple<>(BigDecimal.valueOf(1L), BigDecimal.valueOf(2L)), p -> new Tuple<>(p.a.add(p.b), p.a.add(p.b).add(p.b)))
                        .limit(30),
                Stream.iterate(new Tuple<>(BigDecimal.valueOf(2L), BigDecimal.valueOf(5L)), p -> new Tuple<>(p.a.add(p.b), p.a.add(p.b).add(p.b)))
                        .limit(30)
        )
                .sorted((x, y) -> (x.a.multiply(y.b).compareTo(x.b.multiply(y.a))))
                .limit(30)
                //.peek((x1) -> System.out.println(x1 + x1.a.divide(x1.b, 14, RoundingMode.HALF_UP).toString()))
                .map(p -> BigDecimal.valueOf(3L).multiply(p.b).multiply(p.b)
                        .subtract(BigDecimal.valueOf(2).multiply(p.a).multiply(p.b))
                        .divide(
                                p.b.multiply(p.b)
                                        .subtract(p.a.multiply(p.b))
                                        .subtract(p.a.multiply(p.a)
                                        ), RoundingMode.UNNECESSARY)
                        .subtract(BigDecimal.valueOf(3)))
                //.peek(System.out::println)
                .reduce(BigDecimal.ZERO, BigDecimal::add).toString();
    }

    static int signum(long n) {
        return n > 0 ? 1 : n < 0 ? -1 : 0;
    }

}

