package pgrela.eulerproblem.problem34;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static java.lang.String.*;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DigitFactorials implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DigitFactorials.class);
    }

    @Override
    public long solve() {
        return range(3, 100000).filter(n-> valueOf(n).chars().map(c->c-'0').map(Maths::factorial).sum()==n).sum();
    }
}
