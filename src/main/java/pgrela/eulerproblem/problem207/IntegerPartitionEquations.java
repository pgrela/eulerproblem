package pgrela.eulerproblem.problem207;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Longs;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class IntegerPartitionEquations implements EulerSolver {

    public static void main(String[] args) {
        printSolution(IntegerPartitionEquations.class);
    }

    @Override
    public long solve() {
        long s = LongStream.iterate(10,a->a+1)
                .filter(m -> {
                    LongFraction fraction = new LongFraction(Longs.binaryLength(m) - 1, (m - 1));
                    return fraction.isLessThan(new LongFraction(1, 12345));
                })
                .findFirst().orElseThrow();
        return s*s-s;
    }

}

