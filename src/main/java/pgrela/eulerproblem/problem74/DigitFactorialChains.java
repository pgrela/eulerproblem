package pgrela.eulerproblem.problem74;

import com.google.common.collect.Sets;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Integers.toDigitArray;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DigitFactorialChains implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DigitFactorialChains.class);
    }

    public long solve() {
        return range(1, ONE_MILLION).parallel()
                .map(n -> {
                    Set<Integer> processed = Sets.newHashSet();
                    return iterate(n, k -> IntStream.of(toDigitArray(k)).map(Maths::factorial).sum()).filter(k -> !processed.add(k)).map(i -> processed.size()).findFirst().getAsInt();
                }).filter(i -> i == 60).count();
    }
}
