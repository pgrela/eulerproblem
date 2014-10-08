package pgrela.eulerproblem.common;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.rangeClosed;

public class Primes {
    private static int[] primesUpTo100000 = null;
    private static Set<Integer> primesSetUpTo100000 = null;

    public static IntStream primes(int maxPrime) {
        BitSet set = new BitSet();
        return IntStream
                .range(2, maxPrime)
                .filter(n -> !set.get(n))
                .peek(n -> IntStream.iterate(n, k -> k + n).limit(maxPrime / n).forEach(set::set));

    }

    public static int[] getPrimesToFactorizeUpTo100000() {
        if (primesUpTo100000 == null) {
            primesUpTo100000 = primes(100000).toArray();
        }
        return primesUpTo100000;
    }

    public static int[] getPrimesToFactorizeInteger() {
        if (primesUpTo100000 == null) {
            primesUpTo100000 = primes((int) Math.sqrt(Integer.MAX_VALUE)).toArray();
        }
        return primesUpTo100000;
    }

    public static boolean isPrime(int n) {
        int[] primes = getPrimesToFactorizeInteger();
        if (n < primes[primes.length - 1])
            return getSetOfPrimesToFactorizeInteger().contains(n);
        for (int prime : primes) {
            if (n % prime == 0) {
                return false;
            }
            if (prime * prime > n) {
                return true;
            }
        }
        return false;
    }

    private static Set<Integer> getSetOfPrimesToFactorizeInteger() {
        if (primesSetUpTo100000 == null) {
            primesSetUpTo100000 = IntStream.of(getPrimesToFactorizeInteger()).boxed().collect(Collectors.toSet());
        }
        return primesSetUpTo100000;
    }

    public static List<Integer> factorize(int n) {
        List<Integer> primeFactors = new ArrayList<>();
        if (n < 1) return primeFactors;
        int[] primes = getPrimesToFactorizeInteger();
        for (int prime : primes) {
            while (n % prime == 0) {
                primeFactors.add(prime);
                n /= prime;
            }
            if (prime * prime > n) {
                if (n != 1) {
                    primeFactors.add(n);
                }
                return primeFactors;
            }
        }
        return primeFactors;

    }

    public static int sumDivisors(int number) {
        int originalNumber = number;
        IntStream d = of(1);
        for (int prime : getPrimesToFactorizeUpTo100000()) {
            int times = 0;
            while (number % prime == 0) {
                number /= prime;
                ++times;
            }
            if (times > 0) {
                final int finalTimes = times;
                d = d.flatMap(i -> rangeClosed(0, finalTimes).map(e -> i * Maths.pow(prime, e)));
            }
            if (number == 1) {
                break;
            }
        }
        return d.sum() - originalNumber;
    }
}
