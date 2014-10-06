package pgrela.eulerproblem.problem3;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class LargestPrimeFactor implements EulerSolver {
    public static final long THE_NUMBER = 600851475143L;

    public static void main(String[] args) {
        printSolution(LargestPrimeFactor.class);
    }

    public long solve() {
        int maxPrime = (int) Math.sqrt(THE_NUMBER) + 1;
        return primes(maxPrime)
                .filter(n -> THE_NUMBER % n == 0)
                .max()
                .getAsInt();
    }

}
