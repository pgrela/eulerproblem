package pgrela.eulerproblem.problem12;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class HighlyDivisibleTriangularNumber implements EulerSolver {
    public static void main(String[] args) {
        printSolution(HighlyDivisibleTriangularNumber.class);
    }

    long[] primes;

    public HighlyDivisibleTriangularNumber() {
        primes = Primes.primes(100000).asLongStream().toArray();
    }

    public long solve() {
        return LongStream.iterate(2, n -> n + 1).map(n -> n * (n - 1) / 2).filter(t -> countDivisors(t) > 500).findFirst().getAsLong();
    }

    protected long countDivisors(long number) {
        int divisors = 1;
        for (long prime : primes) {
            int times = 0;
            while (number % prime == 0) {
                ++times;
                number /= prime;
            }
            divisors *= times + 1;
            if(number==1){
                break;
            }
        }
        return divisors;
    }
}
