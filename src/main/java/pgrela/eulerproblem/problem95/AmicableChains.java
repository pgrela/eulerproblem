package pgrela.eulerproblem.problem95;

import com.google.common.collect.Sets;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.TreeSet;

import static java.lang.Integer.compare;
import static java.util.stream.IntStream.*;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Pair.pair;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class AmicableChains implements EulerSolver {

    public static void main(String[] args) {
        printSolution(AmicableChains.class);
    }

    public long solve() {
        int[] divisorsSum = concat(of(0),rangeClosed(1,ONE_MILLION).parallel().map(Primes::sumDivisors)).toArray();
        return rangeClosed(2, ONE_MILLION).parallel()
                .mapToObj(n -> {
                    TreeSet<Integer> processed = Sets.newTreeSet();
                    int lastElement = n;
                    while (processed.add(lastElement)) {
                        lastElement = divisorsSum[lastElement];
                        if (lastElement > ONE_MILLION) break;
                    }
                    if (lastElement != n) return null;
                    return pair(processed.size(), processed.first());
                })
                .filter(i -> i != null)
                .max((a, b) -> compare(a.a, b.a))
                .get().b;
    }
}
