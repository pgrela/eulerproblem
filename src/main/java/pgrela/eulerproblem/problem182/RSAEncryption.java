package pgrela.eulerproblem.problem182;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import static java.util.stream.LongStream.*;
import static pgrela.eulerproblem.common.Primes.allDivisors;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RSAEncryption implements EulerSolver {

    private static final int P = 1009;
    private static final int Q = 3643;
    private static final int PHI = (P - 1) * (Q - 1);

    public static void main(String[] args) {
        printSolution(RSAEncryption.class);
    }

    @Override
    public long solve() {
        int[] cycles = allDivisors(PHI / Maths.gcd(P - 1, Q - 1))
                .sorted()
                .skip(2)
                .toArray();
        return range(2, PHI)
                .filter(e -> Maths.gcd(e, PHI) == 1)
                .filter(e -> Arrays.stream(cycles).noneMatch(c -> (e - 1) % c == 0))
                .sum();
    }

}

