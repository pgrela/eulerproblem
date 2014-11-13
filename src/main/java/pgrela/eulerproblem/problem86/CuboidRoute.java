package pgrela.eulerproblem.problem86;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Maths.isSquare;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;

public class CuboidRoute implements EulerSolver {


    public static void main(String[] args) {
        printSolution(CuboidRoute.class);
    }

    public long solve() {
        int cuboids = 0;
        int M = 0;
        while (cuboids < ONE_MILLION) {
            ++M;
            for (int sumTwoSides = 2; sumTwoSides <= 2 * M; sumTwoSides++) {
                if (isSquare(M * M + sumTwoSides * sumTwoSides)) {
                    cuboids += sumTwoSides / 2 + Math.min(0, - sumTwoSides + M + 1);
                }
            }
        }
        return M;
    }
}
