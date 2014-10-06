package pgrela.eulerproblem.problem32;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PandigitalProducts implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PandigitalProducts.class);
    }

    @Override
    public long solve() {
        return Coordinates.square(1, 99, 99, 9999)
                .filter(p -> arePandigital(p.x, p.y, p.x * p.y))
                .map(p -> p.x * p.y)
                .distinct()
                .reduce(0, Integer::sum);
    }

    private static boolean arePandigital(Integer... numbers) {
        return isPandigital(Stream.of(numbers).map(String::valueOf).collect(Collectors.joining()));
    }

    public static boolean isPandigital(String number) {
        return number.length() == 9 && !number.contains("0") && number.chars().distinct().count() == 9;
    }
}
