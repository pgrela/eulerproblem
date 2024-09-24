package pgrela.eulerproblem.problem196;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Point;
import pgrela.eulerproblem.common.Primes;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimeTriplets implements EulerSolver {

    public static final long ROW1 = 5678027;
    public static final long ROW2 = 7208785;
    public static final int[] PRIMES = Primes.primes(Maths.intSqrt((ROW2 + 2) * (ROW2 + 3) / 2)).toArray();

    public static void main(String[] args) {
        printSolution(PrimeTriplets.class);
    }


    @Override
    public long solve() {
        return S(ROW1) + S(ROW2);
    }

    protected long S(long n) {
        long[][] rows = new long[5][(int) n + 2];
        int[][] prims = new int[5][(int) n + 2];
        for (int i = -2; i <= 2; i++) {
            long start = start(n + i);
            for (int j = 0; j < n + i; j++) {
                rows[2 + i][j] = start + j;
            }
        }
        for (int i = -2; i <= 2; i++) {
            long start = start(n + i);
            for (int p : PRIMES) {
                long lowestPrimeAboveStart = (start - 1) / p * p + p;
                if (lowestPrimeAboveStart == p) lowestPrimeAboveStart += p;
                int j = (int) (lowestPrimeAboveStart - start);
                while (j < n + i) {
                    rows[2 + i][j] = 0;
                    j += p;
                }
            }
        }
        markTrips(n, rows, prims[1], -1);
        markTrips(n, rows, prims[2], 0);
        markTrips(n, rows, prims[3], 1);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (rows[2][i] == 0) continue;
            if (Point.points(i - 1, 2 - 1, i + 1, 2 + 1)
                    .filter(p -> p.x >= 0)
                    .map(p -> prims[p.y][p.x])
                    .anyMatch(r -> r >= 3)) {
                sum += rows[2][i];
            }
        }
        return sum;
    }

    private static void markTrips(long n, long[][] rows, int[] prim, int rowOffset) {
        for (int i = 0; i < n - rowOffset; i++) {
            if (rows[2 + rowOffset][i] == 0) continue;
            prim[i] = (int) Point.points(i - 1, 2+rowOffset - 1, i + 1, 2+rowOffset + 1)
                    .filter(p -> p.x >= 0)
                    .map(p -> rows[p.y][p.x])
                    .filter(r->r>0)
                    .count();
        }
    }

    private long start(long row) {
        return row * (row - 1) / 2 + 1;
    }

}

