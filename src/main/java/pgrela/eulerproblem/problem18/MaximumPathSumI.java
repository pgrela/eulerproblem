package pgrela.eulerproblem.problem18;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class MaximumPathSumI implements EulerSolver {
    public static void main(String[] args) {
        printSolution(MaximumPathSumI.class);
    }

    private static final int[][] THE_TRIANGLE = new int[][]{
            {75},
            {95, 64},
            {17, 47, 82},
            {18, 35, 87, 10},
            {20, 4, 82, 47, 65},
            {19, 1, 23, 75, 3, 34},
            {88, 2, 77, 73, 7, 63, 67},
            {99, 65, 4, 28, 6, 16, 70, 92},
            {41, 41, 26, 56, 83, 40, 80, 70, 33},
            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
            {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
            {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}
    };

    public long solve(){
        return solve(THE_TRIANGLE);
    }

    public static long solve(int[][] theTriangle) {
        int[][] max = Stream.of(theTriangle)
                .map(a -> a.clone())
                .peek(a -> Arrays.fill(a, 0))
                .toArray(int[][]::new);
        max[0][0] = theTriangle[0][0];
        for (int i = 1; i < theTriangle.length; i++) {
            for (int j = 0; j < theTriangle[i - 1].length; j++) {
                max[i][j] = Math.max(max[i][j], max[i - 1][j] + theTriangle[i][j]);
                max[i][j + 1] = Math.max(max[i][j + 1], max[i - 1][j] + theTriangle[i][j + 1]);
            }
        }
        return IntStream.of(max[max.length - 1]).max().getAsInt();
    }

}
