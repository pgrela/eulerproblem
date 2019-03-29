package pgrela.eulerproblem.problem142;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.geometry.PythagoreanTriangle;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PerfectSquareCollection implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PerfectSquareCollection.class);
    }

    public long solve() {
        long maxSum = 1006193;
        int maxGenerator = Maths.intSqrt(maxSum);
        long infinity = Long.MAX_VALUE;
        return Coordinates.square(1, 1, maxGenerator, maxGenerator).parallel()
                .filter(p -> p.x > p.y)
                .filter(p -> (p.x & p.y & 1) == 0)
                .filter(p -> Maths.gcd(p.x, p.y) == 1)
                .flatMap(p -> IntStream.rangeClosed(1, (int) (maxSum / (p.x * p.x)))
                        .mapToObj(k -> new PythagoreanTriangle(p.x, p.y, k)))
                .mapToLong(t -> {
                    long x, y, z, k = 1;
                    do {
                        z = (2 * t.c * k + k * k) / 2;
                        x = z + t.c * t.c;
                        y = z + t.b * t.b;
                        if (Maths.isSquare(z + x)) {
                            if (Maths.isSquare(z + y) && Maths.isSquare(x + y)) {
                                return x + y + z;
                            }
                            y = z + t.a * t.a;
                            if (Maths.isSquare(z + y) && Maths.isSquare(x + y)) {
                                return x + y + z;
                            }
                        }
                        k += 1;
                    } while (x + y + z < maxSum);
                    return infinity;
                })
                .filter(v -> v < infinity)
                .min().orElse(0);
    }

}

