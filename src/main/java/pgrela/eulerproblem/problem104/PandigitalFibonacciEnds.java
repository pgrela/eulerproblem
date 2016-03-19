package pgrela.eulerproblem.problem104;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem32.PandigitalProducts;

import java.math.BigInteger;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.ArbitraryPair.pair;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.common.Tuple.tuple;

public class PandigitalFibonacciEnds implements EulerSolver {


    public static void main(String[] args) {
        printSolution(PandigitalFibonacciEnds.class);
    }

    public long solve() {
        return Stream
                .iterate(tuple(0, pair(BigInteger.ZERO, BigInteger.ONE)), previous -> tuple(previous.a + 1, pair(previous.b.b, previous.b.a.add(previous.b.b))))
                .filter(s -> s.a > 50)
                .filter(s -> PandigitalProducts.isPandigital(s.b.a.toString().substring(0, 9)))
                .filter(s -> PandigitalProducts.isPandigital(lastNineCharacters(s.b.a.toString())))
                .findFirst().get().a;
    }

    private String lastNineCharacters(String string) {
        return string.substring(string.length()-9);
    }
}
