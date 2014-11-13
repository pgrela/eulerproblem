package pgrela.eulerproblem.problem87;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.HashSet;
import java.util.Set;

import pgrela.eulerproblem.common.EulerSolver;

public class PrimePowerTriples implements EulerSolver {


    public static final int FIFTY_MILLIONS = 50 * ONE_MILLION;

    public static void main(String[] args) {
        printSolution(PrimePowerTriples.class);
    }

    public long solve() {
        Set<Integer> found = new HashSet<>();
        int[] primes = primes(FIFTY_MILLIONS).toArray();

        for (int i = 0; pow(primes[i], 4) <= FIFTY_MILLIONS; i++) {
            int forthPower = pow(primes[i], 4);
            for (int j = 0; forthPower + pow(primes[j], 3) <= FIFTY_MILLIONS; j++) {
                int cubeAndForthPower = forthPower + pow(primes[j], 3);
                for (int k = 0; cubeAndForthPower + pow(primes[k], 2) <= FIFTY_MILLIONS; k++) {
                    found.add(cubeAndForthPower + pow(primes[k], 2));
                }
            }
        }
        return found.size();
    }
}
