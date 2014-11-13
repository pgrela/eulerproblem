package pgrela.eulerproblem.problem94;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static java.util.stream.IntStream.*;
import static pgrela.eulerproblem.common.Longs.ONE_BILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class AlmostEquilateralTriangles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(AlmostEquilateralTriangles.class);
    }

    public long solve() {
        return range(1, 35000)
                .mapToLong(m -> {
                            int startA = Maths.intSqrt(m * m / 3);
                            int startB = (int) (m / (2 + Math.sqrt(3)));
                            return concat(rangeClosed(startA - 1, startA + 1), rangeClosed(startB - 1, startB + 1))
                                    .filter(n -> n > 0)
                                    .filter(n -> (m & n & 1) == 0)
                                    .filter(n -> Maths.gcd(m, n) == 1)
                                    .distinct()
                                    .mapToLong(n -> {
                                        long a = m * m + n * n;
                                        long baseA = (m * m - n * n) * 2;
                                        long perimeterA = a + a + baseA;
                                        long baseB = (2 * m * n) * 2;
                                        long perimeterB = a + a + baseB;
                                        long returnValue = (perimeterA <= ONE_BILLION && Math.abs(a - baseA) == 1) ? perimeterA : 0;
                                        returnValue += (perimeterB <= ONE_BILLION && Math.abs(a - baseB) == 1) ? perimeterB : 0;
                                        return returnValue;
                                    }).sum();
                        }
                ).sum();
    }

}
