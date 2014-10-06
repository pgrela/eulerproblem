package pgrela.eulerproblem.problem47;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static java.util.stream.Stream.iterate;
import static pgrela.eulerproblem.common.Primes.factorize;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DistinctPrimesFactors implements EulerSolver {

    public static final int FACTORS = 4;

    public static void main(String[] args) {
        printSolution(DistinctPrimesFactors.class);
    }

    public long solve() {
        return iterate(1, i -> i + 1).filter(i -> IntStream.range(0, FACTORS).allMatch(k -> factorize(i + k).stream().distinct().count() == FACTORS)).findFirst().get();
    }
}
