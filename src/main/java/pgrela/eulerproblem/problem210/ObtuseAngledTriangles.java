package pgrela.eulerproblem.problem210;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Longs;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ObtuseAngledTriangles implements EulerSolver {

    public static final long N = 1_000_000_000L;

    public static void main(String[] args) {
        printSolution(ObtuseAngledTriangles.class);
    }

    @Override
    public long solve() {
        long r = N / 8;
        long bottom = (N + 1) * (N / 4) + N / 2 * N / 2 - N / 4;
        long top = (N + 1) * (N / 2) + N * N / 2 - N / 2;
        long circle = withinCircle(r);
        long insideCircleButCollinear = 2 * r - 1;

        return top + bottom + circle - insideCircleButCollinear;
    }

    long withinCircle(long r) {
        long square = (2 * r + 1) * (2 * r + 1) - 4;
        long c = 0;
        long k = 0;
        while (true) {
            ++k;
            long xSquared = 2 * r * r - (r + k) * (r + k);
            if (xSquared <= 0) break;
            long x = Longs.sqrt(xSquared);
            c += x + x + (x * x == xSquared?-1:1);
        }
        return square + 4 * c;
    }

}

