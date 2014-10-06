package pgrela.eulerproblem.problem44;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.FiniteStreamBuilder.stream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PentagonNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PentagonNumbers.class);
    }

    public long solve() {
        Set<Long> pentagonalNumbers = stream(Stream.iterate(1L, n -> n + 1).map(n -> n * (3 * n - 1) / 2)).until(i -> i < 100 * 1000 * 1000).stream().collect(Collectors.toSet());
        return pentagonalNumbers.stream().flatMap(
                first -> pentagonalNumbers.stream()
                        .filter(second -> first < second)
                        .filter(second -> pentagonalNumbers.contains(second - first))
                        .filter(second -> pentagonalNumbers.contains(second + first))
                        .map(second -> second - first)
        ).min(Long::compare).get();
    }
}
