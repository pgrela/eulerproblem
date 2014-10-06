package pgrela.eulerproblem.problem2;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.Stream;

import static pgrela.eulerproblem.common.Pair.pair;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class EvenFibonacciNumbers implements EulerSolver {
    public static void main(String[] args) {
        printSolution(EvenFibonacciNumbers.class);
    }
    @Override
    public long solve(){
        return Stream
                .iterate(pair(0, 1), previous -> pair(previous.b, previous.a + previous.b))
                .map(pair -> pair.a)
                .limit(50)
                .filter(n -> n % 2 == 0)
                .filter(n->n<4000000)
                .reduce(Integer::sum)
                .get();
    }

}
