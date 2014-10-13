package pgrela.eulerproblem.problem72;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static java.util.stream.LongStream.rangeClosed;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingFractions implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CountingFractions.class);
    }

    public long solve() {
        return rangeClosed(2, ONE_MILLION).map(Maths::totientLikeInt).sum();
    }
}
