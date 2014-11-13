package pgrela.eulerproblem.problem95;

import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Primes.allDivisors;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.TreeSet;

import com.google.common.collect.Sets;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Pair;
import pgrela.eulerproblem.common.Primes;

public class AmicableChains implements EulerSolver {

    public static void main(String[] args) {
        printSolution(AmicableChains.class);
    }

    public long solve() {
        int[] primes = Primes.primes(ONE_MILLION).toArray();
        return rangeClosed(2, ONE_MILLION)
                .mapToObj(n -> {
                    TreeSet<Integer> processed = Sets.newTreeSet();
                    int lastElement = iterate(n, k -> allDivisors(Math.max(1, k), primes).filter(i -> i < k).sum())
                            .filter(k -> !processed.add(k))
                            .findFirst().getAsInt();
                    if (processed.contains(0) || lastElement != n || processed.last() > ONE_MILLION) return null;
                    return Pair.pair(processed.size(), processed.first());
                }).filter(i -> i != null).max((a, b) -> Integer.compare(a.a, b.a)).get().b;
    }
}
