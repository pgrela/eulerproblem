package pgrela.eulerproblem.problem127;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import static java.util.stream.LongStream.range;
import static java.util.stream.LongStream.rangeClosed;
import static pgrela.eulerproblem.common.Maths.gcd;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ABCHits implements EulerSolver {
    public static void main(String[] args) {
        printSolution(ABCHits.class);
    }

    public static final int MAX = 120000;

    public static final int[] primes = Primes.primes(MAX).toArray();

    public long solve() {
        long[] rads = range(0, MAX).map(i->rad((int)i)).toArray();
        return range(1, MAX).parallel()
                .filter(c -> !Primes.isPrime((int) c))
                .map(c ->
                        rangeClosed(1, c / 2).parallel()
                                .filter(b -> rads[(int) (c - b)] * rads[(int) b] * rads[(int) c] < c && gcd(c, (c - b) * b) == 1 && gcd(c, c - b) == 1)
                                .count() * c
                ).sum();
    }

    public static int rad(int number) {
        if (number < 1) return 0;
        int rad = 1;
        for (int prime : primes) {
            int times = 0;
            if (number % prime == 0) {
                while (number % prime == 0) {
                    number /= prime;
                    ++times;
                }
                if (times > 0) {
                    rad *= prime;
                }
            }
            if (number == 1) {
                return rad;
            }
            if (prime * prime > number) {
                return rad * number;
            }
        }
        return rad;
    }

}
