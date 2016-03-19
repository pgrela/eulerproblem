package pgrela.eulerproblem.problem106;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SpecialSubsetSumsMetaTesting implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SpecialSubsetSumsMetaTesting.class);
    }

    public long solve() {
        return solveForSetSize(12);
    }

    protected long solveForSetSize(int size) {
        int[] comparisonsForSubSetsOf = new int[size / 2 + 1];
        for (int subsetSize = 2; subsetSize < comparisonsForSubSetsOf.length; subsetSize++) {
            for (int combination = 1 << (subsetSize * 2 - 1); combination < 1 << (subsetSize * 2); combination++) {
                int ones = 0;
                int signum = 0;
                boolean negative = false;
                int combinationsCopy = combination;
                while (combinationsCopy > 0) {
                    ones += combinationsCopy & 1;
                    signum += (combinationsCopy & 1) == (combination & 1) ? 1 : -1;
                    if (signum < 0) {
                        negative = true;
                    }
                    combinationsCopy >>= 1;
                }
                if (ones == subsetSize && negative) {
                    comparisonsForSubSetsOf[subsetSize]++;
                }
            }
        }
        return IntStream.rangeClosed(2, size / 2)
                .mapToLong(subsetSize -> Maths.newton(size, subsetSize * 2) * comparisonsForSubSetsOf[subsetSize])
                .sum();
    }

}
