package pgrela.eulerproblem.problem10;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SummationOfPrimes implements EulerSolver {
    public static final int TWO_MILLIONS = 2*1000*1000;
    public static void main(String[] args) {
        printSolution(SummationOfPrimes.class);
    }

    public long solve() {
        return primes(TWO_MILLIONS).asLongStream().sum();
    }
}
