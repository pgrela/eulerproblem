package pgrela.eulerproblem.problem192;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.LongFraction.ONE;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class BestApproximationsArch implements EulerSolver {

    public static void main(String[] args) {
        printSolution(BestApproximationsArch.class);
    }

    @Override
    public long solve() {
        int c1=0;
        int c2=0;
        int bound = 1_000_000;
        for (int n = 13; n < 14; n++) {
            if (n == Maths.pow(Maths.intSqrt(n), 2)) continue;
            double s = Math.sqrt(n);
            long goodDenominator = 1;
            double leftBound = 1;
            double rightBound = n;
            long p = (long) s;
            long q = 1;
            while (q < bound) { ++c1;
                double pByQ = p / (double) q;
                if (leftBound < pByQ && pByQ < rightBound) {
                    if (pByQ < s) {
                        leftBound = pByQ;
                        rightBound = leftBound + 2 * (s - pByQ);
                    } else {
                        rightBound = pByQ;
                        leftBound = rightBound - 2 * (rightBound - s);
                    }
                    goodDenominator = q;
                    System.out.println(goodDenominator);
                    continue;
                }
                if (pByQ <= leftBound) {
                    p = (long) (leftBound * q + 1);
                    continue;
                }
                if (rightBound <= pByQ) {
                    double inc = Math.max((p - rightBound * q) / rightBound, 1d);
                    q += inc;
                    continue;
                }
                throw new RuntimeException();
            }
            double e = s;
            long bestDenominator = 1;
            for (long denominator = 2 / 2; denominator <= bound; denominator++) { ++c2;
                double m = Math.min(Math.abs(s - ((long) (s * denominator)) / ((double) denominator)), Math.abs(s - (1 + (long) (s * denominator)) / ((double) denominator)));
                if (m < e) {
                    e = m;
                    bestDenominator = denominator;
                    System.out.println(String.format("%d: %d", n, bestDenominator));
                }
            }
            long lowerNominator = (long) (bestDenominator * s);
            LongFraction lowerApproximation = new LongFraction(lowerNominator, bestDenominator);
            LongFraction upperApproximation = new LongFraction(lowerNominator + 1, bestDenominator);
            LongFraction bestApproximation = Math.abs(s - lowerApproximation.asDouble()) < Math.abs(s - upperApproximation.asDouble()) ? lowerApproximation : upperApproximation;
            LongFraction fraction = ONE;
            while (true) {
                LongFraction nextFraction = LongFraction.natural(n).divide(fraction).add(fraction).divide(2);
                if (nextFraction.getDenominator() > bound) {
                    break;
                }
                fraction = nextFraction;
            }
            System.out.println(String.format("%d: %d", n, bestDenominator));
            System.out.println(String.format("rec: %s: %1.10f", fraction, Math.abs(s - fraction.asDouble())));
            System.out.println(String.format("bru: %s: %1.10f", bestApproximation, Math.abs(s - bestApproximation.asDouble())));
        }
        System.out.println(c1);
        System.out.println(c2);
        return 0;
    }

}

