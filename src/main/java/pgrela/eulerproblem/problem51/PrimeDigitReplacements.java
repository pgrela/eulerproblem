package pgrela.eulerproblem.problem51;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Integers.length;
import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Set;

import pgrela.eulerproblem.common.EulerSolver;

public class PrimeDigitReplacements implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimeDigitReplacements.class);
    }

    @Override
    public long solve() {
        int[] primes = primes(ONE_MILLION).toArray();
        Set primesSet = of(primes).boxed().collect(toSet());
        return of(primes).filter(prime -> range(1, 1 << length(prime))
                        .filter(pattern -> range(0, 10).map(digit ->
                                        replaceDigitsByPattern(prime, pattern, digit))
                                        .anyMatch(i -> i == prime)
                        )
                        .map(pattern -> (int) range(0, 10).map(digit ->
                                        replaceDigitsByPattern(prime, pattern, digit))
                                        .filter(primesSet::contains)
                                        .filter(replacedPrime -> length(prime) == length(replacedPrime))
                                        .count()
                        ).anyMatch(i -> i == 8)
        ).findFirst().getAsInt();
    }

    private int replaceDigitsByPattern(int number, int pattern, int digit) {
        int replacedPrime = number;
        int reducedPattern = pattern;
        int power = 1;
        while (reducedPattern > 0) {
            if (reducedPattern % 2 == 1) {
                replacedPrime = replacedPrime - replacedPrime / power % 10 * power + digit * power;
            }
            reducedPattern >>= 1;
            power *= 10;
        }
        return replacedPrime;
    }
}
