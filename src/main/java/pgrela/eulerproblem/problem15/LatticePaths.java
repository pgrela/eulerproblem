package pgrela.eulerproblem.problem15;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class LatticePaths implements EulerSolver {
    public static void main(String[] args) {
        printSolution(LatticePaths.class);
    }

    public long solve() {
        return Maths.newton(40, 20);
    }

}
