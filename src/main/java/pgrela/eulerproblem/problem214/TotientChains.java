package pgrela.eulerproblem.problem214;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TotientChains implements EulerSolver {


    public static final int MAX_PRIME = 40_000_000;
    public static final int CHAIN_LENGTH = 25;
    private int[] totient = new int[MAX_PRIME];

    public static void main(String[] args) {
        printSolution(TotientChains.class);
    }

    @Override
    public long solve() {
        return Primes.primes(MAX_PRIME)
                .filter(p -> IntStream.iterate(p, i -> i > 1, this::totient).count() == CHAIN_LENGTH - 1)
                .mapToLong(i -> i).sum();
    }

    private int totient(int n) {
        return totient[n] == 0 ? totient[n] = Maths.totient(n) : totient[n];
    }
}

