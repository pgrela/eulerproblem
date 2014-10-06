package pgrela.eulerproblem.problem48;

import pgrela.eulerproblem.common.EulerSolver;

import static java.util.stream.LongStream.rangeClosed;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.Maths.powMod;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SelfPowers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SelfPowers.class);
    }

    public long solve() {
        return rangeClosed(1, 1000).map(i -> powMod(i, i, pow(10L, 10L))).sum() % pow(10L, 10L);
    }
}
