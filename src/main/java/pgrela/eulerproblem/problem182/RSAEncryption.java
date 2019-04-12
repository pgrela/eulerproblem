package pgrela.eulerproblem.problem182;

import java.util.stream.LongStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RSAEncryption implements EulerSolver {

    private static final int BLACK = 60;
    private static final int WHITE = 40;

    private long[][][] cache;

    public static void main(String[] args) {
        printSolution(RSAEncryption.class);
    }

    @Override
    public long solve() {
        int phi = 1008 * 3642;
        return LongStream.range(1, phi).filter(e -> Maths.gcd(phi, e) == 1).count();
    }
}

