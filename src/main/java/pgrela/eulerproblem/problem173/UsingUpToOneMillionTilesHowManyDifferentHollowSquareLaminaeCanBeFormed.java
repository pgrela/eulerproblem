package pgrela.eulerproblem.problem173;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class UsingUpToOneMillionTilesHowManyDifferentHollowSquareLaminaeCanBeFormed implements EulerSolver {

    public static final int MILLION = 1_000_000;

    public static void main(String[] args) throws ParseException {
        printSolution(UsingUpToOneMillionTilesHowManyDifferentHollowSquareLaminaeCanBeFormed.class);
    }

    public long solve() {
        long n = 3;
        long s = 0;
        while (n * n - (n - 2) * (n - 2) <= MILLION) {
            if (n * n <= MILLION) {
                s += (n - 1) / 2;
            } else {
                long starting = Maths.intSqrt(n * n - MILLION) / 2 * 2 + (n & 1);
                while (n * n - starting * starting > MILLION) starting += 2;
                s += (n - starting) / 2;
            }
            ++n;
        }
        return s;
    }

}

