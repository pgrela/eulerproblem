package pgrela.eulerproblem.problem63;

import static java.util.stream.LongStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Set;
import java.util.TreeSet;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Longs;
import pgrela.eulerproblem.common.Maths;

public class PowerfulDigitCounts implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PowerfulDigitCounts.class);
    }

    public long solve() {
        Set<Long> set = new TreeSet<>();
        range(1, 10000000).filter(
                i -> range(1, 30)
                        .filter(exponent -> exponent * Math.log10(i) < Math.log10(Long.MAX_VALUE))
                        .filter(exponent -> Longs.length(Maths.pow(i, exponent)) == exponent)
                        .peek(e -> System.out.println("" + i + "^" + e + "=" + Maths.pow(i, e)))
                        .peek(exponent -> set.add(Maths.pow(i, exponent)))
                        .count() != 0
        ).count();
        set.stream().peek(System.out::println).count();
        return set.size();
    }
}

