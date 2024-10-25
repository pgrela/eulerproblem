package pgrela.eulerproblem.problem203;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.FactorizedNumber;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.common.FactorizedNumber.factorizedNumber;

public class SquarefreeBinomialCoefficients implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SquarefreeBinomialCoefficients.class);
    }

    @Override
    public long solve() {
        return IntStream.range(0, 51)
                .boxed()
                .flatMap(row -> IntStream.range(1, row)
                        .mapToObj(column -> factorizedNumber(row).factorial().divide(factorizedNumber(column).factorial()).divide(factorizedNumber(row-column).factorial())))
                .distinct()
                .filter(this::isSquareFree)
                .mapToLong(FactorizedNumber::toLong)
                .sum()+1;
    }

    private boolean isSquareFree(FactorizedNumber number) {
        return number.factors().values().stream().noneMatch(exp->exp>1);
    }
}

