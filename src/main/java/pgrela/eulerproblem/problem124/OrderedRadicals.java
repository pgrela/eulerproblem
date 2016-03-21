package pgrela.eulerproblem.problem124;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Pair;

import java.util.Arrays;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class OrderedRadicals implements EulerSolver {

    public static void main(String[] args) {
        printSolution(OrderedRadicals.class);
    }

    public long solve() {
        int size = 100000;

        int[] set = new int[size + 1];
        Arrays.fill(set, 1);
        IntStream.rangeClosed(2, size).sequential()
                .filter(n -> set[n] == 1)
                .forEach(n -> IntStream.iterate(n, k -> k + n).limit((size) / n).forEach(k -> set[k] *= n));
        return IntStream.rangeClosed(1, size)
                .mapToObj(i -> Pair.pair(i, set[i]))
                .sorted((rad1, rad2) -> rad1.b != rad2.b ? rad1.b - rad2.b : rad1.a - rad2.a)
                .skip(9999)
                .findFirst()
                .get().a;
    }
}
