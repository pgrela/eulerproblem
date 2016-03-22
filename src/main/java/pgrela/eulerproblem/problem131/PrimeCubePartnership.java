package pgrela.eulerproblem.problem131;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimeCubePartnership implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimeCubePartnership.class);
    }

    public long solve() {
        return Primes.primes(Integers.ONE_MILLION)
                .filter(p -> (p - 1) % 3 == 0)
                .filter(p -> Maths.isSquare(1 + 4 * (p - 1) / 3))
                .count();
    }

}
