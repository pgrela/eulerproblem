package pgrela.eulerproblem.problem75;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SingularIntegerRightTriangles implements EulerSolver {

    private int[] s;
    private int limit;

    public static void main(String[] args) {
        printSolution(SingularIntegerRightTriangles.class);
    }

    public long solve() {
        limit = Integers.ONE_MILLION * 3 / 2 + 1;
        s = new int[limit];
        for (int L = 2; L < limit; L += 2) {
            if (s[L] < 0) continue;
            long LL2 = L * (long) L / 2;
            for (long divisor : Primes.allDivisors(LL2).filter(i -> i < limit * 2).toArray()) {
                applyNewTriangle(L - divisor, L - LL2 / divisor);
                applyNewTriangle(L + divisor, L + LL2 / divisor);
            }
        }
        return IntStream.of(s).filter(i -> i > 0).count();
    }

    private void applyNewTriangle(long x, long y) {
        if (x == 0 || y == 0) return;
        x = Math.abs(x);
        y = Math.abs(y);
        if (x > limit || y > limit) return;
        if (y < x) return;
        int c = (int) Math.sqrt(x * x + y * y);
        int L = (int) (c + x + y);
        addToArray((int) x, L);
    }

    private void addToArray(int lowerSideLength, int perimeter) {
        int i = perimeter;
        int side = lowerSideLength;
        while (i < limit) {
            if (s[i] > 0) {
                if (s[i] != side)
                    s[i] = -1;
            } else {
                if (s[i] == 0)
                    s[i] = side;
            }
            i += perimeter;
            side += lowerSideLength;
        }
    }
}
