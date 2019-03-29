package pgrela.eulerproblem.problem157;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongPair;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SolvingTheDiophantineEquation implements EulerSolver {

    private static final int DEG = 9;

    public static void main(String[] args) throws ParseException {
        printSolution(SolvingTheDiophantineEquation.class);
    }

    public long solve() {
        int[] primes = primes(70_000_000).toArray();
        long max = Maths.pow(10L, DEG);
        long[] base = Primes.allDivisors(max).toArray();
        Set<LongPair> pairs = new HashSet<>();
        for (long a : base) {
            for (long b : base) {
                if (a <= b) {
                    Primes.allDivisors(getaLong(max, a, b), primes).mapToObj(d -> new LongPair(d * a, d * b)).forEach(pairs::add);
                }
            }
        }
        return pairs.stream()
                .map(p -> getaLong(max, p.a, p.b))
                .mapToInt(this::countTensFactors)
                .sum();
    }

    private long getaLong(long max, long a, long b) {
        long ab = a + b;
        long amax = Maths.gcd(a, max);
        a /= amax;
        max /= amax;
        long bmax = Maths.gcd(b, max);
        b /= bmax;
        max /= bmax;
        return max * ab / a / b;
    }

    private int countTensFactors(long n) {
        int s = 1;
        while (n > 0 && n % 10 == 0) {
            n /= 10;
            ++s;
        }
        return Math.min(s, DEG);
    }
}

