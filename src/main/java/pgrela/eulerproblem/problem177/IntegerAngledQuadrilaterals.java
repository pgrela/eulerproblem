package pgrela.eulerproblem.problem177;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class IntegerAngledQuadrilaterals implements EulerSolver {

    public static void main(String[] args) {
        printSolution(IntegerAngledQuadrilaterals.class);
    }

    private static final double EPSILON = 0.000_000_001;

    @Override
    public long solve() {
        double[] sin = IntStream.range(0, 180).mapToDouble(a -> a * Math.PI / 180).map(Math::sin).toArray();
        double[] cos = IntStream.range(0, 180).mapToDouble(a -> a * Math.PI / 180).map(Math::cos).toArray();
        Set<Quadruple> found = new HashSet<>();
        for (int alpha = 1; alpha < 180; alpha++) {
            for (int alphaPrim = alpha; alphaPrim < 180 - alpha; alphaPrim++) {
                int maxBeta = 180 - alpha;
                for (int beta = 1; beta < maxBeta; beta++) {
                    int maxBetaPrim = Math.min(180 - alphaPrim, 180 - beta);
                    for (int betaPrim = 1; betaPrim < maxBetaPrim; betaPrim++) {
                        int gammas = 180 - alpha - beta;
                        int betas = beta + betaPrim;
                        int alphas = alpha + alphaPrim;
                        int phis = 180 - alphaPrim - betaPrim;
                        if (alphas + betas > 180) continue;
                        double b = sin[alpha] / sin[alpha + beta];
                        double c = sin[alphaPrim] / sin[alphaPrim + betaPrim];
                        double asin = Math.asin(sin[betas] * sin[alpha] / sin[gammas] / Math.sqrt(b * b + c * c - 2 * b * c * cos[betas])) / Math.PI * 180;
                        int phiPrim = (int) (asin + .5);
                        double diff = asin - phiPrim;
                        if (-EPSILON < diff && diff < EPSILON) {
                            int phi = phis - phiPrim;
                            int gamma = 180 - alphas - phi;
                            int gammaPrim = gammas - gamma;
                            Quadruple quadruple = Quadruple.quadruple(alphaPrim, alpha, betaPrim, beta);
                            Quadruple byOtherDiagonal = Quadruple.quadruple(phiPrim, phi, gammaPrim, gamma);
                            found.add(quadruple.lower(byOtherDiagonal));
                        }
                    }
                }

            }
        }
        return found.size();
    }

    static class Quadruple {
        int a, b, c, d;

        Quadruple(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Quadruple)) return false;

            Quadruple quadruple = (Quadruple) o;

            return a == quadruple.a && b == quadruple.b && c == quadruple.c && d == quadruple.d;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + c;
            result = 31 * result + d;
            return result;
        }

        static Quadruple quadruple(int leftUpper, int leftLower, int rightUpper, int rightLower) {
            int min = Math.min(Math.min(leftUpper, leftLower), Math.min(rightUpper, rightLower));
            int mins = (leftUpper == min ? 1 : 0) + (leftLower == min ? 1 : 0) + (rightUpper == min ? 1 : 0) + (rightLower == min ? 1 : 0);
            if (mins == 1 && leftUpper != min) {
                if (min == leftLower) return quadruple(leftLower, leftUpper, rightLower, rightUpper);
                return quadruple(rightUpper, rightLower, leftUpper, leftLower);
            }
            if (mins == 2) {
                if (leftUpper != min) {
                    if (leftLower == min) return quadruple(leftLower, leftUpper, rightLower, rightUpper);
                    if (rightUpper == min) return quadruple(rightUpper, rightLower, leftUpper, leftLower);
                    throw new RuntimeException();
                }
                if (leftLower == min) {
                    return new Quadruple(leftUpper, leftLower, Math.min(rightLower, rightUpper), Math.max(rightLower, rightUpper));
                }
                if (rightUpper == min) {
                    return new Quadruple(leftUpper, Math.min(leftLower, rightLower), rightUpper, Math.max(leftLower, rightLower));
                }
                if (rightLower == min) {
                    return new Quadruple(leftUpper, Math.min(leftLower, rightUpper), Math.max(leftLower, rightUpper), rightLower);
                }
            }
            if (mins == 3 && rightLower == min) {
                return quadruple(min, min, min, Math.max(Math.max(leftUpper, leftLower), Math.max(rightUpper, rightLower)));
            }
            return new Quadruple(leftUpper, leftLower, rightUpper, rightLower);
        }

        Quadruple lower(Quadruple other) {
            if (a < other.a) return this;
            if (a > other.a) return other;
            if (b < other.b) return this;
            if (b > other.b) return other;
            if (c < other.c) return this;
            if (c > other.c) return other;
            if (d < other.d) return this;
            if (d > other.d) return other;
            return this;
        }
    }

}

