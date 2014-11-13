package pgrela.eulerproblem.problem60;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.length;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimePairSets implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimePairSets.class);
    }

    public long solve() {
        int[] primes = Primes.primes(8390).toArray();
        List<Set<Integer>> graph = new ArrayList<>(primes.length);
        range(0, primes.length).forEach(i -> graph.add(new TreeSet<>()));
        for (int i = 1; i < primes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (!Primes.isPrime(concat(primes[i], primes[j]))) continue;
                if (!Primes.isPrime(concat(primes[j], primes[i]))) continue;
                graph.get(i).add(j);
                graph.get(j).add(i);
            }
        }
        int numbers = 5;
        return BronKerbosch1(new TreeSet<>(), range(0, primes.length).boxed().collect(Collectors.toSet()), new TreeSet<>(), graph)
                .filter(s->s.size()==numbers)
                .mapToInt(s -> s.stream().mapToInt(i -> primes[i]).sum())
                .min().getAsInt();
    }

    private int concat(int a, int b) {
        return a * pow(10, length(b)) + b;
    }

    public Stream<Set<Integer>> BronKerbosch1(Set<Integer> R, Set<Integer> P, Set<Integer> X, List<Set<Integer>> graph) {
        if (P.isEmpty() && X.isEmpty())
            return Stream.of(R);
        Set<Integer> PCopy = new TreeSet<>(P);
        Stream r = Stream.of();
        for (Integer v : PCopy) {
            R.add(v);
            r = Stream.concat(r,BronKerbosch1(
                    new TreeSet<>(R),
                    P.stream().filter(graph.get(v)::contains).collect(Collectors.toSet()),
                    X.stream().filter(graph.get(v)::contains).collect(Collectors.toSet()), graph));
            R.remove(v);
            P.remove(v);
            X.add(v);
        }
        return r;
    }
}

