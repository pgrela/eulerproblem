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
        return IntStream.rangeClosed(2, size / 2)
                .mapToLong(subsetSize -> Maths.newton(size, subsetSize * 2) * Maths.newton(2*subsetSize-1,subsetSize-2))
                .sum();
    }

}
