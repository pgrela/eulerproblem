package pgrela.eulerproblem.problem45;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.FiniteStreamBuilder.stream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TriangularPentagonalAndHexagonal implements EulerSolver {

    public static void main(String[] args) {
        printSolution(TriangularPentagonalAndHexagonal.class);
    }

    public long solve() {
        Long limit = 1533776805L+1;
        Set <Long> triangleNumbers = stream(Stream.iterate(1L, n -> n + 1).map(n -> n * (n + 1) / 2)).until(i -> i < limit).stream().collect(Collectors.toSet());
        Set <Long> pentagonalNumbers = stream(Stream.iterate(1L, n -> n + 1).map(n -> n * (3 * n - 1) / 2)).until(i -> i < limit).stream().collect(Collectors.toSet());
        Set <Long> hexagonalNumbers = stream(Stream.iterate(1L, n -> n + 1).map(n -> n * (2 * n - 1))).until(i -> i < limit).stream().collect(Collectors.toSet());
        return hexagonalNumbers.stream()
                .filter(triangleNumbers::contains)
                .filter(pentagonalNumbers::contains)
                .filter(n->n>40755)
                .findFirst().get();
    }
}
