package pgrela.eulerproblem.problem206;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ConcealedSquare implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ConcealedSquare.class);
    }

    @Override
    public long solve() {
        String pattern = "1.2.3.4.5.6.7.8.9.0";
        return LongStream.iterate(0, n -> n + 1)
                .flatMap(n->LongStream.of(n*10+7, n*10+3))
                .map(n->n*10)
                .filter(n->n*n>Maths.pow(10L,pattern.length()-1))
                .filter(n -> String.valueOf( n*n).matches(pattern))
                .findFirst()
                .orElseThrow();
    }
}

