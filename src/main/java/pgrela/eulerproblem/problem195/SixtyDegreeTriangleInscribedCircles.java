package pgrela.eulerproblem.problem195;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SixtyDegreeTriangleInscribedCircles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SixtyDegreeTriangleInscribedCircles.class);
    }


    @Override
    public long solve() {
        long T = 1053779;
        return triangles(T);
    }

    protected long triangles(long t) {
        long n = 0;
        for (long p = 1; p < t * 3; p++) {
            double sqrt3 = Math.sqrt(3);
            double delta = 9 * p * p - 16 * t * sqrt3;
            double minR = delta < 0 ? 1 : Math.max(1, (Math.sqrt(delta) - p) / 4);
            for (long r = (long) minR; r < p / 2 + 1; r++) {
                if ((r - 2 * p) % 3 == 0) continue;
                if (Maths.gcd(p, r) > 1) continue;
                if (2 * r < p) {
                    double aR = (p - 2 * r) * (r + p) / sqrt3 / 2;
                    n += (int) (t / aR);
                }

            }
            double maxR = t / sqrt3 * 2 / p + p + 1;
            for (long r = 2 * p; r < maxR; r++) {
                if ((r - 2 * p) % 3 == 0) continue;
                if (Maths.gcd(p, r) > 1) continue;
                if (p < r) {
                    double aR = sqrt3 / 2 * p * (r - p);
                    n += (int) (t / aR);
                }
            }
        }
        return n;
    }

}

