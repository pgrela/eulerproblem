package pgrela.eulerproblem.problem192;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Maths;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.common.geometry.LongRectangle.rectangle;

public class BestApproximations implements EulerSolver {

    public static final long TEN_TO_12 = Maths.pow(10L, 12);

    public static void main(String[] args) {
        printSolution(BestApproximations.class);
    }

    @Override
    public long solve() {
        long s = 0;
        for (long n = 2; n <= Maths.pow(10L, 5); n++) {
            if (Maths.isSquare(n)) continue;
            LongFraction left = new LongFraction(0, 1);
            LongFraction right = new LongFraction(1, 1);
            LongFraction centre = new LongFraction(left.getNominator() + right.getNominator(), left.getDenominator() + right.getDenominator());
            long integer = Maths.intSqrt(n);
            BigDecimal fourN = valueOf(4 * n);
            BigDecimal twoInts = valueOf(2 * integer);
            while (centre.getDenominator() <= TEN_TO_12) {
                long h = centre.getNominator() + centre.getDenominator() * integer;
                if (rectangle(h, h).isSmallerThan(rectangle(centre.getDenominator() * n, centre.getDenominator()))) {
                    left = centre;
                } else {
                    right = centre;
                }
                centre = new LongFraction(left.getNominator() + right.getNominator(), left.getDenominator() + right.getDenominator());
            }
            BigDecimal a = valueOf(left.getNominator());
            BigDecimal b = valueOf(left.getDenominator());
            BigDecimal c = valueOf(right.getNominator());
            BigDecimal d = valueOf(right.getDenominator());
            int compareTo = b.pow(2)
                    .multiply(d.pow(2))
                    .multiply(fourN)
                    .subtract(a.multiply(d).add(b.multiply(c)).add(b.multiply(d).multiply(twoInts)).pow(2))
                    .compareTo(BigDecimal.ZERO);
            if (compareTo < 0) {
                s += left.getDenominator();
            } else {
                s += right.getDenominator();
            }
        }
        return s;
    }

}

