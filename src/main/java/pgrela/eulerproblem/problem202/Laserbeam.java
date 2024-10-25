package pgrela.eulerproblem.problem202;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Maths;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Laserbeam implements EulerSolver {

    public static void main(String[] args) {
        printSolution(Laserbeam.class);
    }

    public static final long BOUNCES = 12017639147L;

    @Override
    public long solve() {
        return LongStream.range(0, (BOUNCES + 3) / 12)
                .map(k -> 3 * k + BOUNCES % 3)
                .filter(n -> Maths.gcd(n, (BOUNCES + 3) / 2 - n) == 1)
                .count() * 2;
    }
}

