package pgrela.eulerproblem.problem218;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.geometry.PythagoreanTriangle;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PerfectRightAngledTriangles implements EulerSolver {

    public static final long MAX_C = Maths.pow(10L, 16);

    public static void main(String[] args) {
        printSolution(PerfectRightAngledTriangles.class);
    }

    @Override
    public long solve() {
        long c = 0;
        for (long m = 1; m <= 1000; m++) {
            for (long n = 1; n < m; n++) {
                if (Maths.gcd(m, n) != 1 || (m & n & 1) != 0) continue;
                PythagoreanTriangle generator = new PythagoreanTriangle(m, n);
                if (Maths.gcd(generator.a, generator.b) != 1 || (generator.a & generator.b & 1) != 0) continue;
                PythagoreanTriangle triangle = PythagoreanTriangle.triangle(generator.a, generator.b);
                if (triangle.c > MAX_C) continue;
                if (Maths.gcd(triangle.a, triangle.b) != 1) continue;
                if (Maths.gcd(triangle.c, triangle.b) != 1) continue;
                if (Maths.gcd(triangle.a, 168) * Maths.gcd(triangle.b, 168) % 168 == 0) continue;
                ++c;
            }
        }
        return c;
    }
}

