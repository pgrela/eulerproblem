package pgrela.eulerproblem.problem92;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Integers.toDigitArray;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.function.IntUnaryOperator;

import pgrela.eulerproblem.common.EulerSolver;

public class SquareDigitChains implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SquareDigitChains.class);
    }

    public long solve() {
        IntUnaryOperator sumDigitSquares = i -> of(toDigitArray(i)).map(a -> a * a).sum();
        return range(1, ONE_MILLION * 10)
                .map(sumDigitSquares)
                .map(sumDigitSquares)
                .map(sumDigitSquares)
                .map(sumDigitSquares)
                .map(sumDigitSquares)
                .map(sumDigitSquares)
                .map(sumDigitSquares)
                .filter(i->i!=1)
                .count();
    }
}
