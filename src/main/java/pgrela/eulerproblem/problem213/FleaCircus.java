package pgrela.eulerproblem.problem213;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FleaCircus implements EulerSolver {

    public static final int BOARD_SIZE = 30;
    public static final int BELLS = 50;

    public static void main(String[] args) {
        printSolution(FleaCircus.class);
    }

    @Override
    public String solveToString() {
        double[][] chanceToBeEmpty = new double[BOARD_SIZE][BOARD_SIZE];
        for (double[] row : chanceToBeEmpty)
            Arrays.fill(row, 1.0);
        for (int fleaX = 0; fleaX < BOARD_SIZE; fleaX++) {
            for (int fleaY = 0; fleaY < BOARD_SIZE; fleaY++) {
                double[][] previousFleaProbability = new double[BOARD_SIZE][BOARD_SIZE];
                previousFleaProbability[fleaX][fleaY] = 1;
                for (int k = 0; k < BELLS; k++) {
                    double[][] fleaProbability = new double[BOARD_SIZE][BOARD_SIZE];
                    for (int squareX = 0; squareX < BOARD_SIZE; squareX++) {
                        for (int squareY = 0; squareY < BOARD_SIZE; squareY++) {
                            double r = 0;
                            r += squareX > 0 ? 1 : 0;
                            r += squareX < FleaCircus.BOARD_SIZE - 1 ? 1 : 0;
                            r += squareY > 0 ? 1 : 0;
                            r += squareY < FleaCircus.BOARD_SIZE - 1 ? 1 : 0;
                            if (squareX > 0)
                                fleaProbability[squareX - 1][squareY] += previousFleaProbability[squareX][squareY] / r;
                            if (squareX < FleaCircus.BOARD_SIZE - 1)
                                fleaProbability[squareX + 1][squareY] += previousFleaProbability[squareX][squareY] / r;
                            if (squareY > 0)
                                fleaProbability[squareX][squareY - 1] += previousFleaProbability[squareX][squareY] / r;
                            if (squareY < FleaCircus.BOARD_SIZE - 1)
                                fleaProbability[squareX][squareY + 1] += previousFleaProbability[squareX][squareY] / r;
                        }
                    }
                    previousFleaProbability = fleaProbability;
                }
                for (int squareX = 0; squareX < BOARD_SIZE; squareX++) {
                    for (int squareY = 0; squareY < BOARD_SIZE; squareY++) {
                        chanceToBeEmpty[squareX][squareY] *= 1 - previousFleaProbability[squareX][squareY];
                    }
                }
            }
        }
        double totalExpectedValue = 0;
        for (int squareX = 0; squareX < BOARD_SIZE; squareX++) {
            for (int squareY = 0; squareY < BOARD_SIZE; squareY++) {
                totalExpectedValue += chanceToBeEmpty[squareX][squareY];
            }
        }
        return String.format("%.6f", totalExpectedValue);
    }
}

