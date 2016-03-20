package pgrela.eulerproblem.problem115;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingBlockCombinationsII implements EulerSolver {

    public static final int M = 50;

    public static void main(String[] args) {
        printSolution(CountingBlockCombinationsII.class);
    }

    public long solve() {
        long[] endsWithBlack = new long[M * 100];
        long[] endsWithRed = new long[M * 100];
        IntStream.rangeClosed(1, M).forEach(i -> endsWithBlack[i] = 1);
        IntStream.range(1, M).forEach(i -> endsWithRed[i] = 0);
        endsWithRed[M] = 1;
        for (int i = M + 1; i < M * 100; i++) {
            endsWithBlack[i] = endsWithBlack[i - 1] + endsWithRed[i - 1];
            endsWithRed[i] = endsWithRed[i - 1] + endsWithBlack[i - M];
            if (endsWithBlack[i] + endsWithRed[i] > Integers.ONE_MILLION) {
                return i;
            }
        }
        throw new RuntimeException("To small range!");
    }


}
