package pgrela.eulerproblem.problem53;

import static java.util.stream.IntStream.concat;
import static java.util.stream.IntStream.of;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;

public class CombinatoricSelections implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CombinatoricSelections.class);
    }

    public long solve() {
        int[] a = new int[102];
        a[a.length - 1] = 1;
        IntStream stream = of(0);
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                int value = a[j + 1] + a[j];
                a[j] = value > ONE_MILLION ? ONE_MILLION + 1 : value;
            }
            stream = concat(stream, of(a.clone()));
        }
        return stream.filter(i -> i > ONE_MILLION).count();
    }
}
