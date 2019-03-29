package pgrela.eulerproblem.problem139;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.geometry.PythagoreanTriangle;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PythagoreanTiles implements EulerSolver {

    private static final int MAX_PERIMITER = 100 * ONE_MILLION;

    public static void main(String[] args) {
        printSolution(PythagoreanTiles.class);
    }

    public long solve() {
        int maxGenerator = Maths.intSqrt(MAX_PERIMITER * 2 / 3);

        return Coordinates.square(1, 1, maxGenerator, maxGenerator)
                .filter(p -> p.x > p.y)
                .filter(p -> (p.x & p.y & 1) == 0)
                .filter(p -> Maths.gcd(p.x, p.y) == 1)
                .map(p -> new PythagoreanTriangle(p.x, p.y))
                .filter(t -> t.perimeter() <= MAX_PERIMITER)
                .filter(t -> t.c % (t.b - t.a) == 0)
                .distinct()
                .peek(System.out::println) // interesting series of m and n generators
                .mapToLong(t -> (MAX_PERIMITER - 1) / t.perimeter())
                .sum();
    }

}

