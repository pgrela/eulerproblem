package pgrela.eulerproblem.problem7;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class The10001stPrime implements EulerSolver {
    public static void main(String[] args) {
        printSolution(The10001stPrime.class);
    }

    public long solve() {
        return primes(1000*1000).skip(10000).findFirst().getAsInt();
    }
}
