package pgrela.eulerproblem.problem174;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingTheNumberOfHollowSquareLaminaeThatCanFormOneTwoThreeDistinctArrangements implements EulerSolver {

    public static final int MILLION = 1_000_000;

    public static void main(String[] args) throws ParseException {
        printSolution(CountingTheNumberOfHollowSquareLaminaeThatCanFormOneTwoThreeDistinctArrangements.class);
    }

    public long solve() {
        int[] N = new int[MILLION + 1];
        for (long larger = 1; larger <= Maths.pow(10L, 7); larger++) {
            long n = larger * larger - MILLION;
            for (long smaller = (n > 10 ? Maths.intSqrt(n) : 1) / 2 * 2 + (larger & 1); smaller < larger; smaller += 2) {
                if (smaller < 1) continue;
                long laminaSize = larger * larger - smaller * smaller;
                if (laminaSize > MILLION) continue;
                ++N[(int) laminaSize];
            }
        }
        return IntStream.of(N).filter(i -> 1 <= i && i <= 10).count();
    }

}

