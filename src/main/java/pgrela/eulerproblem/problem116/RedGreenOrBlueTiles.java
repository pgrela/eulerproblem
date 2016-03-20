package pgrela.eulerproblem.problem116;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RedGreenOrBlueTiles implements EulerSolver {

    public static final int BOARD_LENGTH = 50;

    public static void main(String[] args) {
        printSolution(RedGreenOrBlueTiles.class);
    }

    public long solve() {
        return IntStream.rangeClosed(2,4).mapToLong(i->fillFunction(i,BOARD_LENGTH)).sum();
    }

    private long fillFunction(int minColourLength, int boardLength) {
        long[] endsWithBlack = new long[boardLength + 1];
        long[] endsWithColour = new long[boardLength + 1];
        IntStream.rangeClosed(1, minColourLength).forEach(i -> endsWithBlack[i] = 1);
        IntStream.range(1, minColourLength).forEach(i -> endsWithColour[i] = 0);
        endsWithColour[minColourLength] = 1;
        for (int i = minColourLength + 1; i <= boardLength; i++) {
            endsWithBlack[i] = endsWithBlack[i - 1] + endsWithColour[i - 1];
            endsWithColour[i] = endsWithColour[i - minColourLength] + endsWithBlack[i - minColourLength];
        }
        return endsWithBlack[boardLength] + endsWithColour[boardLength] - 1;
    }


}
