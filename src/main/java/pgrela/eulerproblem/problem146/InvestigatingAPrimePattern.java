package pgrela.eulerproblem.problem146;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingAPrimePattern implements EulerSolver {

    private static final int MAX = 150_000_000;

    public static void main(String[] args) throws ParseException {
        printSolution(InvestigatingAPrimePattern.class);
    }

    public long solve() {
        long[] primes = Primes.primes(MAX + 10000).mapToLong(i -> i).toArray();
        int maxRest = 3 * 7 * 13 * 2 * 5 * 11 * 17 * 19 * 23;
        long[] rests = LongStream.range(1, maxRest)
                .filter(n -> Maths.gcd(n * n + 1, maxRest) == 1)
                .filter(n -> Maths.gcd(n * n + 3, maxRest) == 1)
                .filter(n -> Maths.gcd(n * n + 7, maxRest) == 1)
                .filter(n -> Maths.gcd(n * n + 9, maxRest) == 1)
                .filter(n -> Maths.gcd(n * n + 13, maxRest) == 1)
                .filter(n -> Maths.gcd(n * n + 27, maxRest) == 1)
                .distinct()
                .toArray();
        return LongStream.range(0, MAX / maxRest + 1)
                .flatMap(n -> Arrays.stream(rests).map(r -> maxRest * n + r))
                .filter(n -> n < MAX)
                .parallel()
                .filter(n -> Primes.isPrime(n * n + 1, primes))
                .filter(n -> Primes.isPrime(n * n + 3, primes))
                .filter(n -> Primes.isPrime(n * n + 7, primes))
                .filter(n -> Primes.isPrime(n * n + 9, primes))
                .filter(n -> Primes.isPrime(n * n + 13, primes))
                .filter(n -> Primes.isPrime(n * n + 27, primes))
                .filter(n -> !Primes.isPrime(n * n + 5, primes))
                .filter(n -> !Primes.isPrime(n * n + 11, primes))
                .filter(n -> !Primes.isPrime(n * n + 15, primes))
                .filter(n -> !Primes.isPrime(n * n + 17, primes))
                .filter(n -> !Primes.isPrime(n * n + 19, primes))
                .filter(n -> !Primes.isPrime(n * n + 21, primes))
                .filter(n -> !Primes.isPrime(n * n + 23, primes))
                .filter(n -> !Primes.isPrime(n * n + 25, primes))
                .sum();
        // it takes almost 6000 seconds
    }


}

