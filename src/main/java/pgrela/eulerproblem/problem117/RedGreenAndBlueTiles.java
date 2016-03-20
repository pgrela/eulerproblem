package pgrela.eulerproblem.problem117;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RedGreenAndBlueTiles implements EulerSolver {

    public static final int BOARD_LENGTH = 50;
    private static final int RED_LENGTH = 2;
    private static final int GREEN_LENGTH = 3;
    private static final int BLUE_LENGTH = 4;

    public static void main(String[] args) {
        printSolution(RedGreenAndBlueTiles.class);
    }

    public long solve() {
        long[] endsWithBlack = new long[BOARD_LENGTH + 1];
        long[] endsWithRed = new long[BOARD_LENGTH + 1];
        long[] endsWithGreen = new long[BOARD_LENGTH + 1];
        long[] endsWithBlue = new long[BOARD_LENGTH + 1];
        IntStream.rangeClosed(1, RED_LENGTH).forEach(i -> endsWithBlack[i] = 1);
        endsWithBlack[RED_LENGTH + 1] = 2;
        endsWithBlack[RED_LENGTH + 2] = 4;
        IntStream.range(1, RED_LENGTH).forEach(i -> endsWithRed[i] = 0);
        endsWithRed[RED_LENGTH] = 1;
        endsWithRed[RED_LENGTH + 1] = 1;
        endsWithRed[2 * RED_LENGTH] = 2;
        IntStream.range(1, GREEN_LENGTH).forEach(i -> endsWithGreen[i] = 0);
        endsWithGreen[GREEN_LENGTH] = 1;
        endsWithGreen[GREEN_LENGTH + 1] = 1;
        IntStream.range(1, BLUE_LENGTH).forEach(i -> endsWithBlue[i] = 0);
        endsWithBlue[BLUE_LENGTH] = 1;
        for (int i = BLUE_LENGTH + 1; i <= BOARD_LENGTH; i++) {
            endsWithBlack[i] = endsWithBlack[i - 1]
                    + endsWithRed[i - 1]
                    + endsWithGreen[i - 1]
                    + endsWithBlue[i - 1];
            endsWithRed[i] = endsWithBlack[i - RED_LENGTH]
                    + endsWithRed[i - RED_LENGTH]
                    + endsWithGreen[i - RED_LENGTH]
                    + endsWithBlue[i - RED_LENGTH];
            endsWithGreen[i] = endsWithBlack[i - GREEN_LENGTH]
                    + endsWithRed[i - GREEN_LENGTH]
                    + endsWithGreen[i - GREEN_LENGTH]
                    + endsWithBlue[i - GREEN_LENGTH];
            endsWithBlue[i] = endsWithBlack[i - BLUE_LENGTH]
                    + endsWithRed[i - BLUE_LENGTH]
                    + endsWithGreen[i - BLUE_LENGTH]
                    + endsWithBlue[i - BLUE_LENGTH];
        }
        return endsWithBlack[BOARD_LENGTH]
                + endsWithRed[BOARD_LENGTH]
                + endsWithGreen[BOARD_LENGTH]
                + endsWithBlue[BOARD_LENGTH];
    }


}
