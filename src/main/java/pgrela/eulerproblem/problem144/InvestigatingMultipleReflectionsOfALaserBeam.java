package pgrela.eulerproblem.problem144;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingMultipleReflectionsOfALaserBeam implements EulerSolver {

    public static final double EPSILON = 0.0000001;

    public static void main(String[] args) throws ParseException {
        printSolution(InvestigatingMultipleReflectionsOfALaserBeam.class);
    }

    public long solve() {
        double x = 0.007107316949965891;
        double y = 9.99998989720405;
        double a = -19.7 / 1.4;
        double b = 10.1;
        int bounces = -1;
        do {
            ++bounces;
            double d = (2 * a * b) * (2 * a * b) - 4 * (a * a + 4) * (b * b - 100);
            double x1 = (Math.sqrt(d) - 2 * a * b) / (2 * (a * a + 4));
            double x2 = (-Math.sqrt(d) - 2 * a * b) / (2 * (a * a + 4));
            if (e(x2, x)) {
                x = x1;
            } else {
                if (!e(x1, x)) {
                    throw new RuntimeException();
                }
                x = x2;
            }
            y = a * x + b;
            double tangent = -4 * x / y;
            double tg2tangent = 2 * tangent / (1 - tangent * tangent);
            a = (tg2tangent - a) / (1 + tg2tangent * a);
            b = y - a * x;
        } while (!(y > 0 && -0.01 <= x && x <= 0.01));
        return bounces;
    }

    boolean e(double a, double b) {
        double d = a - b;
        return -EPSILON < d && d < EPSILON;
    }


}

