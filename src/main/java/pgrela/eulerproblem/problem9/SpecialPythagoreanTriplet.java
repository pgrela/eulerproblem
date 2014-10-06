package pgrela.eulerproblem.problem9;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SpecialPythagoreanTriplet implements EulerSolver {
    public static void main(String[] args) {
        printSolution(SpecialPythagoreanTriplet.class);
    }

    public long solve() {
        return rangeClosed(334, 500).map(
                c -> rangeClosed(1, (1000 - c) / 2)
                        .map(b -> IntStream.of(1000 - c - b)
                                .filter(a -> c * c == b * b + a * a)
                                .map(a -> a * b * c)
                                .findFirst()
                                .orElse(0)
                        ).max().orElse(0)
        ).max().getAsInt();
    }
}
