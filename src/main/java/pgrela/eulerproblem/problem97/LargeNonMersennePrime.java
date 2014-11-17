package pgrela.eulerproblem.problem97;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.Maths.powMod;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class LargeNonMersennePrime implements EulerSolver {

    public static final long MODULO = Maths.pow(10L, 10L);

    public static void main(String[] args) {
        printSolution(LargeNonMersennePrime.class);
    }

    public long solve() {
        return (28433 * powMod(2, 7830457, MODULO) + 1) % MODULO;
    }
}
