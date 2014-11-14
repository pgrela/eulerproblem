package pgrela.eulerproblem.problem66;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.math.BigInteger;

import pgrela.eulerproblem.common.EulerSolver;

public class DiophantineEquation implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DiophantineEquation.class);
    }

    public long solve() {
        long maxD = 0;
        BigInteger maxX = BigInteger.ZERO;
        for (long D = 2; D < 1000; D++) {
            long d = (long) Math.sqrt(D);
            if (d * d == D) {
                continue;
            }
            BigInteger bigIntegerD = BigInteger.valueOf(D);
            long denominator = 1;
            long a2 = d;
            long minus = 0;
            BigInteger A0 = BigInteger.valueOf(1), B0 = BigInteger.valueOf(0);
            BigInteger A1 = BigInteger.valueOf(d), B1 = BigInteger.valueOf(1);
            while (!A1.multiply(A1).equals(B1.multiply(B1).multiply(bigIntegerD).add(BigInteger.ONE))) {
                minus = (a2 * denominator) - minus;
                denominator = (D - minus * minus) / denominator;
                a2 = (d + minus) / denominator;
                BigInteger bigIntegerA2 = BigInteger.valueOf(a2);
                BigInteger A2 = A0.add(A1.multiply(bigIntegerA2));
                BigInteger B2 = B0.add(B1.multiply(bigIntegerA2));
                A0 = A1;
                B0 = B1;
                A1 = A2;
                B1 = B2;
            }
            System.out.println(A1.toString());
            if (A1.compareTo(maxX) == 1) {
                maxX = A1;
                maxD = D;
            }
        }
        return maxD;
    }
}

